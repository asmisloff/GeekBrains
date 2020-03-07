public class HT_02 {

    public static void main(String[] args) {
        for (String ts: new String[] {source, mfSource_1, mfSource_2}) {
            try {
                System.out.printf("Тестовая строка: \n%s\n", ts);
                String[][] matrix = asMatrix(ts);
                System.out.printf("Полусумма всех элементов матрицы: %.1f\n", halfOfSum(matrix));
            } catch (MalformedStringException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }
    }

    private static String source = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static String mfSource_1 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\n300 3 1 0";
    private static String mfSource_2 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 55 0";

    static String[][] asMatrix(String s) throws MalformedStringException {
        String[][] res = new String[4][4];
        String[] rows = s.strip().split("\n");

        if (rows.length != 4) {
            throw new MalformedStringException("Malformed string format: number of rows is not 4.");
        }

        for (int i = 0; i < 4; i++) {
            String[] row = rows[i].strip().split(" ");
            if (row.length != 4) {
                throw new MalformedStringException("Malformed string format: number of elements in row {"
                        + rows[i] + "} is not 4. -- ");
            }
            res[i] = row;
        }

        return res;
    }

    static double halfOfSum(String[][] matrix) throws NumberFormatException {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sum += Integer.parseInt(matrix[i][j]);
            }
        }

        return sum / 2.0;
    }

    static class MalformedStringException extends IllegalArgumentException {
        public MalformedStringException(String s) {
            super(s);
        }
    }
}
