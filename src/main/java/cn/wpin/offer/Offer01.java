package cn.wpin.offer;

/**
 * 剑指offer第一题,七种单例模式
 *
 * @author wpin
 */

public class Offer01 {

    /**
     * ①
     * 饿汉式单例，线程安全
     */
    private static class Singleton1 {

        private static final Singleton1 singleton1 = new Singleton1();

        private Singleton1() {

        }

        public static Singleton1 getInstance() {
            return singleton1;
        }
    }


    /**
     * ②
     * 懒汉式单例，线程不安全
     */
    private static class Singleton2 {

        private static Singleton2 singleton2 = null;

        private Singleton2() {

        }

        public static Singleton2 getInstance() {
            if (singleton2 == null) {
                singleton2 = new Singleton2();
            }
            return singleton2;
        }
    }


    /**
     * ③
     * 懒汉式单例，线程安全，多线程环境下效率低下
     */
    public static class Singleton3 {

        private static Singleton3 singleton3 = null;

        private Singleton3() {

        }

        public static synchronized Singleton3 getInstance() {
            if (singleton3 == null) {
                singleton3 = new Singleton3();
            }
            return singleton3;
        }

    }

    /**
     * ④
     * 懒汉式单例，线程安全，利用静态区加载的时候获取实例，保证只有一次
     */
    public static class Singleton4 {

        private static Singleton4 singleton4;

        static {
            singleton4 = new Singleton4();
        }

        private Singleton4() {

        }

        public static synchronized Singleton4 getInstance() {
            return singleton4;
        }

    }

    /**
     * ⑤
     * 使用静态内部类，线程安全【推荐】
     */
    public static class Singleton5 {

        private static final class Singleton5Holder {
            private static final Singleton5 singleton5 = new Singleton5();
        }

        private Singleton5() {

        }

        public static synchronized Singleton5 getInstance() {
            return Singleton5Holder.singleton5;
        }

    }

    /**
     * 使用枚举方式，线程安全，推荐
     */
    public enum Singleton6 {
        SINGLETON6
    }

    public static class Singleton7 {
        private static volatile Singleton7 singleton7 = null;

        private Singleton7() {

        }

        public static Singleton7 getInstance() {
            if (singleton7 == null) {
                synchronized (Singleton7.class) {
                    if (singleton7 == null) {
                        singleton7 = new Singleton7();
                    }
                }
            }
            return singleton7;
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance()==Singleton1.getInstance());
        System.out.println(Singleton2.getInstance()==Singleton2.getInstance());
        System.out.println(Singleton3.getInstance()==Singleton3.getInstance());
        System.out.println(Singleton4.getInstance()==Singleton4.getInstance());
        System.out.println(Singleton5.getInstance()==Singleton5.getInstance());
        System.out.println(Singleton6.SINGLETON6==Singleton6.SINGLETON6);
        System.out.println(Singleton7.getInstance()==Singleton7.getInstance());
    }


}
