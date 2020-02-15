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
        
        //test hasCentroidPositive
        System.out.println();
        System.out.println(
                hasCentroidPositive(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        
        System.out.println(
                hasCentroidPositive(new int[]{1, 1, 1, 2, 1}));

        System.out.println(
                hasCentroidPositive(new int[]{-2, 2, 2, 1, 2, 2, 10, 1}));
    
        //test hasCentroid
        System.out.println();
        System.out.println(
                hasCentroid(new int[]{-2, -2, -2, -1, 2, 2, -10, 1}));
    
        System.out.println(
                hasCentroid(new int[]{-1, 1, 1, -2, 1}));
    
        System.out.println(
                hasCentroid(new int[]{-2, 2, 2, 1, 2, 2, 10, 1}));
        
        //test revolve
        int[] arr4 = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println();
        printArray(revolve(arr4,-5));
        printArray(revolve(arr4,5005));
    }
    
    static void printArray(int[] arr) {
        for (int elt : arr) {
            System.out.print(elt + ", ");
        }
        System.out.print("\b\b\n");
    }
    
    /*
     * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0.
     */
    static void flipArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
    }
    
    /*
     * 2. Задать пустой целочисленный массив размером 8.
     * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21.
     */
    static int[] fillArray() {
        int[] res = new int[8];
        for (int i = 0; i < res.length; i++) {
            res[i] = 3 * i;
        }
        return res;
    }
    
    /*
    * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
    * пройти по нему циклом, и числа меньшие 6 умножить на 2.
    */
    static void doubleIfLesserThen6(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }
    
    /*
    * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
    * и с помощью цикла(-ов) заполнить его единицами элементы его главной диагонали.
    */
    static int[][] makeDiagMatrix(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (i == j)? 1 : 0;
            }
        }
        return  res;
    }
    
    /*
     * 4'. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
     * и с помощью цикла(-ов) заполнить его единицами элементы его главной и побочной диагоналей.
     */
    static int[][] makeDiagMatrix2(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (i == j || i + 1 == n - j)? 1 : 0;
            }
        }
        return  res;
    }
    
    /*
     * 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы
     */
    static int[] minmax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        return new int[] {min, max};
    }
    
    /*
    * 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    * метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
    * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    * граница показана символами ||, эти символы в массив не входят.
    * ---------------------------------------------------------------------------------------------------------
    * Реализация для массивов ПОЛОЖИТЕЛЬНЫХ чисел, как в примерах из задания
    */
    static boolean hasCentroidPositive(int[] arr) {
        int l = 0; // left side index
        int r = arr.length - 1; // right side index
        int ls = arr[l]; // left sum
        int rs = arr[r]; // right sum
        
        while (r - l > 1) {
            if (ls < rs) {
                ls += arr[++l];
            }
            else if (rs < ls) {
                rs += arr[--r];
            }
            else {
                ls += arr[++l];
                rs += arr[--r];
            }
        }
        
        return ls == rs;
    }
    
    /*
     * 6'. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
     * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
     * граница показана символами ||, эти символы в массив не входят.
     * ---------------------------------------------------------------------------------------------------------
     * Реализация без ограничений знака элементов массива.
     */
    static boolean hasCentroid(int[] arr) {
        int ls, rs; // суммы элементов слева от "центральной точки" и справа от нее.
        int end = arr.length;
        
        for (int c = 0; c < end - 1; c++) { // c - текущий индекс элемента, стоящего слева от "центральной точки"
            ls = 0; rs = 0;
            for (int l = 0; l <= c; l++) { // рассчет ls: сумма элементов "слева": от нулевого до "центральной точки"
                ls += arr[l];
            }
            for (int r = end - 1; r > c; --r) { // расчет rs: сумма элементов "справа": от конечного до "центральной точки"
                rs += arr[r];
            }
            
            if (ls == rs) {
                return true;
            }
        }
        
        return false;
    }
    
    /*
    * 7. **** Написать метод, которому на вход подается одномерный массив и число n
    * (может быть положительным, или отрицательным), при этом метод должен сместить все элементымассива на n позиций.
    * Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    */
    static int[] revolve(int[] arr, int n) {
        if (n >= arr.length) { //Учитываем периодичность
            n %= arr.length;
        }
        
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                rshift(arr);
            }
        }
        
        if (n < 0) {
            for (int i = 0; i < -n; i++) {
                lshift(arr);
            }
        }
        
        return arr;
    }
    
    static void rshift(int[] arr) {
        int size = arr.length;
        int tail = arr[size - 1];
        
        for (int i = size - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = tail;
    }
    
    static void lshift(int[] arr) {
        int head = arr[0];
        int size = arr.length;
    
        for (int i = 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        arr[size - 1] = head;
    }
}
