package cc.momas.java.demo.generic;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
         *    Object
         *      |
         *    Fruit
         *    /   \
         * Apple   Pear
         *   |
         * FujiApple
         *
         * */

        // 添加自己或者子类
        List<Fruit> fruitList = new ArrayList<>(4);
        fruitList.add(new Fruit());
        fruitList.add(new Apple());
        fruitList.add(new Pear());
        fruitList.add(new FujiApple());

        List<Apple> appleList = new ArrayList<>(4);
//      不可以添加父类
//      appleList.add(new Fruit());
        appleList.add(new Apple());
//       不可以添加不属性自己或子类的对象
//      appleList.add(new Pear());
        appleList.add(new FujiApple());

//        不写类型,相当于 List<Object>
        List list = new ArrayList<>(0);
        list.add(new Fruit());
        list.add(new Apple());
        list.add(new Pear());
        list.add(new FujiApple());

//        未知类型
        List<?> list2 = new ArrayList<>(0);
//        list2.add(new Fruit());
//        list2.add(new Apple());
//        list2.add(new Pear());
//        list2.add(new FujiApple());
//        list2.add(new Object());
//        null可以作为未知类型的值
        list2.add(null);

//        添加上限限制
        List<? extends Fruit> fruits = new ArrayList<>(4);
//        fruits.add(new Fruit());
//        fruits.add(new Apple());
//        fruits.add(new Pear());
//        fruits.add(new FujiApple());
//        只能用于读取操作
//        fruits.get(0);

//        添加下限限制 和 List<Fruit> 基本一样
        List<? super Fruit> fruits2 = new ArrayList<>(4);
        fruits2.add(new Fruit());
        fruits2.add(new Apple());
        fruits2.add(new Pear());
        fruits2.add(new FujiApple());



    }


}
/*
1. 通配符 ? 类型不定,使用在声明上
2. 上限 extends  <= 不能添加信息
   下限 super    >= 不能添加父信息
3. 泛型可嵌套
4. 泛型没有多态,泛型没有数组
5. jdk1.7对泛型写法进行了简化,添加了类型推断,

 */