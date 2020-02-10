public class Main {

    public static void main(String[] args) {
        byte b = 0;
        short sh = 0;
        int i = 0;
        long l = 0L;
        float f = 0.0f;
        double d = 0.0;
        char ch = 'a';
        boolean bln = false;

        test();
    }

    static float compute(float a, float b, float c, float d) {
        return a * (b + c / d);
    }

    static boolean checkSum(int a, int b) {
        int sum = a + b;
        return 10 <= sum && sum <= 20;
    }

    static void isPositive(int n) {
        System.out.println(n >= 0 ? "Положительное" : "Отрицательное");
    }

    static boolean isNegative(int n) {
        return n <= 0;
    }

    static void greet(String name) {
        System.out.println("Привет, " + name);
    }

    static void isBissextile(int y) {
        boolean res = (y % 400 == 0) ||
                      ((y % 2 == 0) && (y % 100 != 0));
        System.out.println(y + " год " + (res ? "високосный" : "не високосный"));
    }

    static void test() {
        System.out.println("a, b, c, d = 5.9, 10.1, 21.3, 33.6; compute(a, b, c, d) -> " +
                            compute(5.9f, 10.1f, 21.3f, 33.6f));

        System.out.println("a = 5, b = 13; checkSum(a, b) -> " + checkSum(5, 13));
        System.out.println("a = -5, b = 13; checkSum(a, b) -> " + checkSum(-5, 13));

        System.out.print("isPositive(0): ");
        isPositive(0);
        System.out.print("isPositive(13): ");
        isPositive(13);System.out.print("isPositive(-13): ");
        isPositive(-13);

        System.out.println("isNegative(0) -> " + isNegative(0));
        System.out.println("isNegative(13) -> " + isNegative(13));
        System.out.println("isNegative(-13) -> " + isNegative(-13));

        greet("Вася");

        isBissextile(2019);
        isBissextile(2020);
        isBissextile(2000);
        isBissextile(2100);
    }
}
