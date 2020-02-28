import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static char[][] map;
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 4;
    
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    
    public static final Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    
    enum Player {
        HUMAN, AI, NONE;

        public Player change() {
            if (this == HUMAN) return AI;
            else return HUMAN;
        }
    }
    
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    
    public static void printMap() {
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
    
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }
    
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }
    
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.printf("Ход компьютера: (%d, %d)\n", x + 1, y + 1);
        map[y][x] = DOT_O;
    }
    
//    public static boolean checkWin0(char symb) {
//        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
//        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
//        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
//        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
//        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
//        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
//        return false;
//    }
    
    static Player checkWin() {
        short   zerosInRow = 0, xsInRow = 0,
                zerosInCol = 0, xsInCol = 0,
                zerosInMainDiag = 0, xsInMainDiag = 0,
                zerosInSecondaryDiag = 0, xsInSecondaryDiag = 0;
    
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == DOT_O) ++zerosInMainDiag;
            if (map[i][SIZE - i - 1] == DOT_O) ++zerosInSecondaryDiag;
    
            if (map[i][i] == DOT_X) ++xsInMainDiag;
            if (map[i][SIZE - i - 1] == DOT_X) ++xsInSecondaryDiag;
            
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_O) ++zerosInRow;
                if (map[j][i] == DOT_O) ++zerosInCol;
    
                if (map[i][j] == DOT_X) ++xsInRow;
                if (map[j][i] == DOT_X) ++xsInCol;
            }
        
            if (zerosInRow == DOTS_TO_WIN || zerosInCol == DOTS_TO_WIN) return Player.AI;
            if (xsInRow == DOTS_TO_WIN || xsInCol == DOTS_TO_WIN) return Player.HUMAN;
            zerosInCol = 0; zerosInRow = 0; xsInCol = 0; xsInRow = 0;
        }
        
        if (zerosInMainDiag == DOTS_TO_WIN || zerosInSecondaryDiag == DOTS_TO_WIN) return Player.AI;
        if (xsInMainDiag == DOTS_TO_WIN || xsInSecondaryDiag == DOTS_TO_WIN) return Player.HUMAN;
        
        return Player.NONE;
    }
    
//    public static boolean isMapFull() {
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                if (map[i][j] == DOT_EMPTY) return false;
//            }
//        }
//        return true;
//    }
    
    static Player play(Player p) {
        switch (p) {
            case HUMAN:
                humanTurn();
                return checkWin();
            case AI:
                aiTurn();
                return checkWin();
            default:
                return Player.NONE;
        }
    }
    
    public static void main(String[] args) {
        initMap();
        printMap();
        Player p = Player.NONE;
        Player winner;

        while (true) {
            winner = play(p = p.change());
            System.out.println(p);
            printMap();
            if (winner == Player.HUMAN) {
                System.out.println("Вы выиграли!");
                break;
            }
            else if (winner == Player.AI) {
                System.out.println("Вы проиграли.");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
}
