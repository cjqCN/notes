package base;

public class Test {

    public static boolean bb = false;

    public static int i = 100;

    public static void main(String[] args) {
        bb = true;
        System.out.println(bb);
    }

    public void foo() { Object o = new Object(); }
}
