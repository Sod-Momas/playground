package cc.momas.javase;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class JavaSeCompletableFutureMain {
    static long st = System.currentTimeMillis();

    public static void main(String[] args) {
//        test1();
//        test2();

        supplyAsync();
//        runAsync();
//        completedFuture();
//
//        delayedExecutor();
//        completedStage();
//        failedFuture();
//        failedStage();
//
//        allOf();
//        anyOf();
    }

    private static void supplyAsync() {

        String param = "aaa";
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> param);


    }

    private static void test2() {

        // 结果集
        List<String> result;
        // 需要处理的数据集 10个线程，每个线程处理一个str
        List<String> lists = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj");
        //定长10线程池
        ExecutorService exs = Executors.newFixedThreadPool(10);
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        try {
            for (String str : lists) {
                //异步执行
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> calc(str), exs)
                        //thenAccept只接受不返回不影响结果
                        .thenApply("final-"::concat)
                        //如需获取任务完成先后顺序，此处代码即可
                        .whenComplete((v, e) -> System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date()));
                futureList.add(future);
            }
            //1.构造一个空CompletableFuture，子任务数为入参任务list size
            CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
            //2.流式（总任务完成后，每个子任务join取结果，后转换为list）
            result = allDoneFuture.thenApply(v -> futureList.stream().map(CompletableFuture::join).collect(Collectors.toList())).get();
            System.out.println("任务完成先后顺序，结果list2=" + result + ",耗时=" + (System.currentTimeMillis() - st));
            assert !result.isEmpty();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }

    }

    private static void test1() {
        // 结果集
        List<String> result = new ArrayList<>();
        // 需要处理的数据集 10个线程，每个线程处理一个str
        List<String> lists = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj");
        //定长10线程池
        ExecutorService exs = Executors.newFixedThreadPool(10);
        CompletableFuture[] cfs = lists.stream().map(str -> CompletableFuture.supplyAsync(() -> calc(str), exs)
                //thenAccept只接受不返回不影响结果
                .thenApply("final-"::concat)
                //获取任务完成先后顺序
                .whenComplete((v, e) -> {
                    System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date());
                    result.add(v);
                })).toArray(CompletableFuture[]::new);
        //等待总任务完成，但是封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
        System.out.println("任务完成先后顺序，结果list2=" + result + ",耗时=" + (System.currentTimeMillis() - st));
        exs.shutdown();
        assert !result.isEmpty();
    }


    private static String calc(String str) {
        String param;
        do {
            param = UUID.randomUUID().toString();
        } while (param.toLowerCase(Locale.ROOT).startsWith(str.toLowerCase(Locale.ROOT)));
        return str;
    }
}