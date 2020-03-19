import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int H = SIZE / 2;
    static float[] arr = new float[SIZE];
    static final int N = 10;

    public static void main(String[] args) {
        long dt = 0;
        for (int i = 0; i < N; i++) {
            dt += processArray1();
        }
        System.out.printf("Single thread version: %d ms\n", dt / N);
        printArray(arr);

        dt = 0;
        for (int i = 0; i < N; i++) {
            dt += processArray2();
        }
        System.out.printf("Two threads version: %d ms\n", dt / N);
        printArray(arr);
    }

    /* Single thread version */
    private static long processArray1() {
        Arrays.fill(arr, 1.0f);

        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        return System.currentTimeMillis() - start;
    }

    /* Two threads version */
    private static long processArray2() {
        Arrays.fill(arr, 1.0f);

        long start = System.currentTimeMillis();

        float[] head = new float[H];
        float[] tail = new float[H];
        System.arraycopy(arr, 0, head, 0, H);
        System.arraycopy(arr, H, tail, 0, H);
        Thread t1 = new Thread(new ArrayProcessor(head,0));
        Thread t2 = new Thread(new ArrayProcessor(tail, H));
        t1.start();
        t2.start();
        try {
            t1.join();
            System.arraycopy(head, 0, arr, 0, H);
            t2.join();
            System.arraycopy(tail, 0, arr, H, H);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }

        return System.currentTimeMillis() - start;
    }

    static void printArray(float[] arr) {
        StringBuilder s = new StringBuilder();

        s.append("{ ");
        for (int i = 0; i < 5; i++) {
            s.append(arr[i]).append(", ");
        }
        s.append("... ");
        for (int i = arr.length - 5; i < arr.length; i++) {
            s.append(arr[i]).append(", ");
        }
        s.delete(s.length() - 2, s.length() - 1);
        s.append(" }");

        System.out.println(s);
    }

    static class ArrayProcessor implements Runnable {
        public ArrayProcessor(float[] a, int shift) {
            this.a = a;
            this.shift = shift;
        }

        @Override
        public void run() {
            int i;
            for (int cnt = 0; cnt < a.length; cnt++) {
                i = cnt + shift;
                a[cnt] = (float)(a[cnt] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }

        float[] a;
        int shift;
    }

}
