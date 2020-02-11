public class Main {

    public static void main(String[] args) {
        //test flipArray
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        flipArray(arr);
        printArray(arr);

        //test fillArray
        printArray(fillArray());

        //doubleIfLesserThen6
        int[] arr1 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        doubleIfLesserThen6(arr1);
        printArray(arr1);

        //test makeDiagMatrix
        System.out.println();
        int[][] arr2 = makeDiagMatrix(5);
        for (int[] ints : arr2) {
            printArray(ints);
        }

        //test makeDiagMatrix2
        System.out.println();
        int[][] arr3 = makeDiagMatrix2(5);
        for (int[] ints : arr3) {
            printArray(ints);
        }

        //test minmax
        System.out.println();
        printArray(minmax(new int[] {1, 5, 3, 2, -11, 4, 5, 2, 4, 8, 9, 1}));
    }

    static void printArray(int[] arr) {
        for (int elt : arr) {
            System.out.print(elt + ", ");
        }
        System.out.print("\b\b\n");
    }

    static int[] fillArray() {
        int[] res = new int[8];
        for (int i = 0; i < res.length; i++) {
            res[i] = 3 * i;
        }
        return res;
    }

    static void flipArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
    }

    static void doubleIfLesserThen6(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    static int[][] makeDiagMatrix(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (i == j)? 1 : 0;
            }
        }
        return  res;
    }

    static int[][] makeDiagMatrix2(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (i == j || i + 1 == n - j)? 1 : 0;
            }
        }
        return  res;
    }

    static int[] minmax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        return new int[] {min, max};
    }
}
