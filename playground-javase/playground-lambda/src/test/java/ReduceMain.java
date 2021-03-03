import org.junit.Test;

import java.util.List;
import java.util.Vector;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceMain {

    @Test
    public void originReduce() {
        // reduce 操作可以把所有 item 都处理到 sum 变量上去
        // 集合第一个元素会被当作初 sum 的初始值
        Stream.of(1, 2, 3, 4).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer sum, Integer item) {
                System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                sum += item;
                System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                return sum;
            }
        }).ifPresent(System.out::println);
    }

    @Test
    public void originReduceLambda() {
        // reduce 操作可以把所有 item 都处理到 sum 变量上去
        // 集合第一个元素会被当作初 sum 的初始值
        Stream.of(1, 2, 3, 4).reduce((sum, item) -> {
            System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
            sum += item;
            System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
            return sum;
        }).ifPresent(System.out::println);
    }

    @Test
    public void initialReduce() {
        // 定义初始值
        Integer init = 0;
        final var result = Stream.of(1, 2, 3, 4)
                // sum 的初始值被赋值为 init, 然后再执行 reduce 操作
                .reduce(init, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer sum, Integer item) {
                        System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                        sum += item;
                        System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                        return sum;
                    }
                });
        System.out.println(result);
    }

    @Test
    public void initialReduceLambda() {
        // 定义初始值
        Integer init = 0;
        final var result = Stream.of(1, 2, 3, 4)
                // sum 的初始值被赋值为 init, 然后再执行 reduce 操作
                .reduce(init, (sum, item) -> {
                    System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                    sum += item;
                    System.out.printf("before BinaryOperator: item: %d, sum: %d%n", item, sum);
                    return sum;
                });
        System.out.println(result);
    }

    @Test
    public void customerReduce() {
        // 这里要使用线程安全的容器类, 否则在 parallel 并行流的处理下会发现线程安全问题
        // java.util.ConcurrentModificationException
        // List<Integer> init = new ArrayList<>();
        List<Integer> init = new Vector<>();
        System.out.println(init.hashCode());

        final var reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 10)
                // 启用并行流加速处理, 压榨 CPU 性能
                .parallel()
                .reduce(init,
                        new BiFunction<List<Integer>, Integer, List<Integer>>() {
                            @Override
                            public List<Integer> apply(List<Integer> arr, Integer item) {
                                arr.add(item);
                                System.out.printf("thread name:%s%n", Thread.currentThread().getName());
                                System.out.printf("BiFunction: item: %d, arr:%s@%b%n", item, arr.toString(), (init == arr));
                                return arr;
                            }
                        },
                        // 如果没有启用并行流, 该参数不会使用
                        // 这里使用的 list1和list2完全是同一个对象, 所以不做任何处理
                        new BinaryOperator<List<Integer>>() {
                            @Override
                            public List<Integer> apply(List<Integer> list1, List<Integer> list2) {
                                System.out.printf("BinaryOperator:list1@%d, list2@%d, list1: %s, list2:%s%n",
                                        list1.hashCode(), list2.hashCode(), list1.toString(), list2.toString());
                                return list1;
                            }
                        }

                );
        System.out.println(reduce == init);
//        System.out.println(init);
    }

    @Test
    public void customerStudentReduce() {

        final var test = new Student(88, "test");

        // 初始化 0
        Integer init = 10;
        final var sumAge = Stream.of(
                new Student(1, "one")
                , new Student(2, "two")
                , new Student(3, "three")
                , new Student(4, "four")
                , new Student(1, "one")
                , test
                , test
        )
                // 启用并行, 会有多个 BiFunction 同时执行
                .parallel()
                .reduce(
                        init,
                        // 并行时: BiFunction 相当于 map 操作
                        // 串行时: BiFunction 相当于完整的 reduce
                        new BiFunction<Integer, Student, Integer>() {
                            /**
                             *
                             * @param ageSum 并行时该参数会被赋值为初始值, 如果不对它进行返回它会丢失
                             *               串行时该参数就是最后的返回结果
                             * @param student 入参,源数据
                             * @return 并行时应当返回
                             */
                            @Override
                            public Integer apply(Integer ageSum, Student student) {
                                System.out.printf("ageSum: %d, student: %s%n", ageSum, student.toString());
                                // 并行时
                                return student.age;
                                // 串行时
//                                return ageSum + student.age;
                            }
                        },
                        // 并行时: BinaryOperator 会把两个不同 BiFunction 的结果进行汇总(并行时有多个BiFunction同时进行)
                        // 串行时: BinaryOperator 不执行
                        new BinaryOperator<Integer>() {
                            /**
                             *
                             * @param age1 线程A 在 BiFunction 操作的结果
                             * @param age2 线程B 在 BiFunction 操作的结果
                             * @return 两个结果进行汇总后的结果, 汇总逻辑不定
                             */
                            @Override
                            public Integer apply(Integer age1, Integer age2) {
                                System.out.printf("age1=%d, age2=%d%n", age1, age2);
                                // 汇总逻辑是把结果相加,
                                // 因为本示例是想把所有 student 的 age 进行求和
                                return age1 + age2;
                            }
                        });
        System.out.println(sumAge);
    }

}