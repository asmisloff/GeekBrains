import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static char[][] map;
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 3;
    
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    
    public static final Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    /*
    * Массив disposition идентифицирует игровую ситуацию.
    * disposition[0] -- код ситуации: -1 -- ничья,
    *                                 0 -- общий случай,
    *                                 1 -- риск выигрыша человека,
    *                                 2 -- возможность выигрыша AI,
    *                                 3 -- человек выиграл,
    *                                 4 -- AI выиграл.
    * disposition[1] -- индекс строки map, куда AI сделает следующий ход или -1 (рандомный ход).
    * disposition[2] -- индекс столбца map, куда AI сделает следующий ход или -1 (рандомный ход).
    */
    static int[] disposition = {-1, -1, -1};
    
    // Набор символических констант -- кодов игровой ситуации
    final static int
            W_DRAW = -1,
            W_NONE = 0,
            W_DANGER = 1,
            W_CAN_WIN = 2,
            W_HUMAN_WIN = 3,
            W_AI_WIN = 4;
    
    static void dpUpdateDisposition(int what, int row, int col) {
        disposition[0] = what;
        disposition[1] = row;
        disposition[2] = col;
    }
    
    static int dpWhat() { return disposition[0]; }
    static int dpGetRow() { return disposition[1]; }
    static int dpGetCol() { return disposition[2]; }
    
    /*-------------------------------------------------------------------------------------------*/
    
    /*
    * Исследует подмассив длиной len массива seq, начиная с индекса start, на предмет того, какова игровая ситуация
    * в этом подмассиве.
    * Возвращает массив из двух элементов:
    *                             [0] -- код игровой ситуации,
    *                             [1] -- индекс DOT_EMPTY, которую AI заполнит в следующий ход, или -1 при [0] == 0.
    */
    static int[] checkSubSequence(char[] seq, int start, int len) {
        int os = 0, xs = 0;
        int empty_cell = -1;
        
        for (int i = start; i < start + len; i++) {
            if (seq[i] == DOT_O) {
                ++os;
                --xs;
            } else if (seq[i] == DOT_X) {
                ++xs;
                --os;
            } else {
                empty_cell = i;
            }
        }
        
        if (os == DOTS_TO_WIN) {
            return new int[] {W_AI_WIN, -1};
        } else if (xs == DOTS_TO_WIN) {
            return new int[] {W_HUMAN_WIN, -1};
        } else if (os == DOTS_TO_WIN - 1) {
            return new int[] {W_CAN_WIN, empty_cell};
        } else if (xs == DOTS_TO_WIN - 1) {
            return new int[] {W_DANGER, empty_cell};
        } else if (empty_cell > -1){
            return new int[] {W_NONE, -1};
        } else {
            return new int[] {W_DRAW, -1};
        }
    }
    
    // Поиск игровых ситуаций в массиве seq. Возвращает "старшую" ситуацию.
    static int[] checkSequence(char[] seq, int len) {
        int[] bestPrompt = new int[]{-1, -1};
        int[] prompt;
        
        for (int start = 0; start <= seq.length - len; start++) {
            prompt = checkSubSequence(seq, start, len);
            if (prompt[0] > bestPrompt[0]) {
                bestPrompt[0] = prompt[0];
                bestPrompt[1] = prompt[1];
            }
        }
        
        return bestPrompt;
    }
    
    /*
     * Исследовать строку с индексом row из map.
     * При обнаружении игровой ситуации с кодом больше, чем в disposition, перезаписать disposition.
     */
    static void traverseRow(int row) {
        int[] prompt = checkSequence(map[row], DOTS_TO_WIN);
        
        if (prompt[0] == W_AI_WIN) {
            dpUpdateDisposition(W_AI_WIN, -1, -1);
        } else if (prompt[0] == W_HUMAN_WIN) {
            dpUpdateDisposition(W_HUMAN_WIN, -1, -1);
        } else if (prompt[0] == W_CAN_WIN && dpWhat() < W_CAN_WIN) {
            dpUpdateDisposition(W_CAN_WIN, row, prompt[1]);
        } else if (prompt[0] == W_DANGER && dpWhat() < W_DANGER) {
            dpUpdateDisposition(W_DANGER, row, prompt[1]);
        } else if (prompt[0] == W_NONE && dpWhat() < W_NONE) {
            dpUpdateDisposition(W_NONE, row, prompt[1]);
        }
    }
    
    static void traverseRows() {
        for (int i = 0; i < SIZE; i++) {
            traverseRow(i);
        }
    }
    
    /*
    * То же, что traverseRow, но для столбцов map.
    */
    static void traverseColumn(int col) {
        char[] seq = new char[SIZE];
        for (int i = 0; i < SIZE; i++) {
            seq[i] = map[i][col];
        }
        int[] prompt = checkSequence(seq, DOTS_TO_WIN);
    
        if (prompt[0] == W_AI_WIN) {
            dpUpdateDisposition(W_AI_WIN, -1, -1);
        } else if (prompt[0] == W_HUMAN_WIN) {
            dpUpdateDisposition(W_HUMAN_WIN, -1, -1);
        } else if (prompt[0] == W_CAN_WIN && dpWhat() < W_CAN_WIN) {
            dpUpdateDisposition(W_CAN_WIN, prompt[1], col);
        } else if (prompt[0] == W_DANGER && dpWhat() < W_DANGER) {
            dpUpdateDisposition(W_DANGER, prompt[1], col);
        } else if (prompt[0] == W_NONE && dpWhat() < W_NONE) {
            dpUpdateDisposition(W_NONE, prompt[1], col);
        }
    }
    
    static void traverseColumns() {
        for (int i = 0; i < SIZE; i++) {
            traverseColumn(i);
        }
    }
    
    /*
    * Выборка элементов с главной диагонали, начальный элемент которой имеет индексы (row, col)
    */
    static char[] getMainDiagAt(int row, int col) {
        int sz = SIZE - Math.max(row, col);
        char[] seq = new char[sz];
        for (int i = 0; i < sz; i++) {
            seq[i] = map[row++][col++];
        }
        return seq;
    }
    
    /*
     * То же, что traverseRow, но для главных диагоналей map.
     */
    static void traverseMainDiagAt(int row, int col) {
        char[] seq = getMainDiagAt(row,col);
        int[] prompt = checkSequence(seq, DOTS_TO_WIN);
    
        if (prompt[0] == W_AI_WIN) {
            dpUpdateDisposition(W_AI_WIN, -1, -1);
        } else if (prompt[0] == W_HUMAN_WIN) {
            dpUpdateDisposition(W_HUMAN_WIN, -1, -1);
        } else if (prompt[0] == W_CAN_WIN && dpWhat() < W_CAN_WIN) {
            dpUpdateDisposition(W_CAN_WIN, row + prompt[1], col + prompt[1]);
        } else if (prompt[0] == W_DANGER && dpWhat() < W_DANGER) {
            dpUpdateDisposition(W_DANGER, row + prompt[1], col + prompt[1]);
        } else if (prompt[0] == W_NONE && dpWhat() < W_NONE) {
            dpUpdateDisposition(W_NONE, -1, -1);
        }
    }
    
    static void traversMainDiags() {
        int cnt = SIZE - DOTS_TO_WIN;
    
        for (int i = cnt; i >= -cnt ; --i) {
            int row = i < 0 ? 0 : i;
            int col = i < 0 ? -i : 0;
            traverseMainDiagAt(row, col);
        }
    }
    
    static char[] getSecondaryDiagAt(int row, int col) {
        int sz = row - col + 1;
        char[] seq = new char[sz];
        for (int i = 0; i < sz; i++) {
            seq[i] = map[row--][col++];
        }
        return seq;
    }
    
    static void traverseSecondaryDiagAt(int row, int col) {
        char[] seq = getSecondaryDiagAt(row,col);
        int[] prompt = checkSequence(seq, DOTS_TO_WIN);
    
        if (prompt[0] == W_AI_WIN) {
            dpUpdateDisposition(W_AI_WIN, -1, -1);
        } else if (prompt[0] == W_HUMAN_WIN) {
            dpUpdateDisposition(W_HUMAN_WIN, -1, -1);
        } else if (prompt[0] == W_CAN_WIN && dpWhat() < W_CAN_WIN) {
            dpUpdateDisposition(W_CAN_WIN, row - prompt[1], col + prompt[1]);
        } else if (prompt[0] == W_DANGER && dpWhat() < W_DANGER) {
            dpUpdateDisposition(W_DANGER, row - prompt[1], col + prompt[1]);
        } else if (prompt[0] == W_NONE && dpWhat() < W_NONE) {
            dpUpdateDisposition(W_NONE, -1, -1);
        }
    }
    
    static void traverseSecondaryDiags() {
        int col = 0;
        int row = DOTS_TO_WIN - 1;
        
        for (int i = 0; i < 2 * (SIZE - DOTS_TO_WIN) + 1; ++i, ++row) {
            if (row > SIZE - 1) {
                row = SIZE - 1;
                ++col;
            }
            traverseSecondaryDiagAt(row, col);
        }
    }
    
    static void traverseMap() {
        dpUpdateDisposition(-1,-1,-1);
        traverseRows();
        traverseColumns();
        traversMainDiags();
        traverseSecondaryDiags();
    }
    
    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    
    static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /*
    * Принять пользовательский ввод и передать ход AI.
    */
    static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты в формате X Y: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            sc.nextLine(); // очистить буфер от остатков прошлого невалидного воода
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        
        aiTurn();
    }
    
    /*
    * Ход AI.
    * Если не достигнута выигрышная игровая ситуация, передать ход пользователю.
    */
    public static void aiTurn() {
        int row = -1, col = -1;
        printMap();
        traverseMap();
        
        switch (dpWhat()) {
            case W_DRAW:
            case W_AI_WIN:
            case W_HUMAN_WIN:
                return;
                
            case W_NONE:
                do {
                    row = rand.nextInt(SIZE);
                    col = rand.nextInt(SIZE);
                } while (!isCellValid(col, row));
                break;
                
            case W_DANGER:
            case W_CAN_WIN:
                row = dpGetRow();
                col = dpGetCol();
                break;
        }
    
        map[row][col] = DOT_O;
        printMap();
        System.out.printf("Ход компьютера: (%d, %d)\n", col + 1, row + 1);
        
        traverseMap();
        if (dpWhat() != W_AI_WIN && dpWhat() != W_DRAW) {
            humanTurn();
        }
    }
    
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }
    
    public static void main(String[] args) {
        initMap();
        printMap();
        humanTurn();
        
        switch (dpWhat()) {
            case W_DRAW:
                System.out.println("Ничья!");
                break;
                
            case W_HUMAN_WIN:
                System.out.println("Вы выиграли!");
                break;
            
            case W_AI_WIN:
                System.out.println("Вы проиграли.");
                break;
        }
        
        System.out.println("Игра закончена");
    }
}
