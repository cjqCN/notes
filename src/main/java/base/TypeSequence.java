package base;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TypeSequence {

    @Contended
    private int i;

    @Contended
    private double d;

    @Contended
    private long l;

    @Contended
    private float f;

    @Contended
    private short s;

    @Contended
    private char c;

    @Contended
    private byte b;

    @Contended
    private boolean bl;

    @Contended
    private boolean o;



    public static Unsafe UNSAFE;

    static {
        try {
            @SuppressWarnings("ALL")
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * i:int	12
     * d:double	16
     * l:long	24
     * f:float	32
     * s:short	36
     * c:char	38
     * b:byte	40
     * bl:boolean	41
     * o:Object	44
     * <p>
     * ----------------------------------------
     * <p>
     * -XX:-RestrictContended
     * <p>
     * i:int	140
     * d:double	272
     * l:long	408
     * f:float	544
     * s:short	676
     * c:char	806
     * b:byte	936
     * bl:boolean	1065
     * o:Object	1196
     */
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        System.out.println("i:int\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("i")));
        System.out.println("d:double\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("d")));
        System.out.println("l:long\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("l")));
        System.out.println("f:float\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("f")));
        System.out.println("s:short\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("s")));
        System.out.println("c:char\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("c")));
        System.out.println("b:byte\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("b")));
        System.out.println("bl:boolean\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("bl")));
        System.out.println("o:Object\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("o")));
    }

}
