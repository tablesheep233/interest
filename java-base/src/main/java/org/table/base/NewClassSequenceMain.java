package org.table.base;

/**
 * 类创建时执行顺序，老八股文了
 *
 * @author: tablesheep
 */
public class NewClassSequenceMain {

    public static void main(String[] args) {
        new Son();
    }

    public static class Father {

        private static final String MSG = "I am Father static field";
        private final String msg = "I am Father field";

        static {
            System.out.println(MSG);
            System.out.println(Father.class.getName() + ": static method block");
        }

        {
            System.out.println(msg);
            System.out.println(Father.class.getName() + ": method block");
        }

        public Father() {
            System.out.println(Father.class.getName() + ": constructor");
        }
    }

    public static class Son extends Father {

        private static final String MSG = "I am Son static field";
        private final String msg = "I am Son field";

        static {
            System.out.println(MSG);
            System.out.println(Son.class.getName() + ": static method block");
        }

        {
            System.out.println(msg);
            System.out.println(Son.class.getName() + ": method block");
        }

        public Son() {
            System.out.println(Son.class.getName() + ": constructor");
        }
    }

}
