package cn.murphy.stringPool;

/**
 * intern是一个本地方法，作用是如果字符窜常量池中已经包含一个等于此String对象的字符窜，则返回常量池中这个字符窜的String对象的引用
 * 否则 会将次String对象包含的字符窜添加到常量池中，并且返回此String对象的引用
 * 常量池在永久代或者metaSpace
 *
 *
 *
 * java一定为false
 * 因为java初始化的时候就会加载进来
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        String str1 = new StringBuffer("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());
        System.out.println();


        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());




    }
}
