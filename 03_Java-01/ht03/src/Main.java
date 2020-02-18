import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        guessGame();
        wordsGame();
    }
    
    // Получить ответ пользователя, учесть возможные ошибки ввода
    static int getInt(String prompt, Scanner scanner) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("------ Ошибка ввода. Ожидалось число. ------");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            return value;
        }
    }
    
    static boolean playAgainP(Scanner scanner) {
        int res = getInt("Чтобы сыграть еще раз, введите 1, для выхода - 0: ", scanner);
        
        if (res == 1) {
            return true;
        }
        else if (res == 0) {
            return false;
        }
        else {
            System.out.println("Допустимый ввод - 1 или 0.");
            return playAgainP(scanner);
        }
    }
    
    static void guessGame() {
        Scanner inp = new Scanner(System.in);
    
        // Один кон игры
        final Runnable game = () -> {
            int magicNumber = (int)(Math.random() * 10);
            System.out.println("Загадоно число от 0 до 9");
            System.out.println("Угадайте с трех попыток");
            
            for (int i = 0; i < 3; i++) {
                int guess;
                guess = getInt(String.format("Попытка №%d: ", i), inp);
                
                if (guess < magicNumber) {
                    System.out.println("Слишком мало");
                } else if (guess > magicNumber) {
                    System.out.println("Слишком много");
                } else {
                    System.out.println("Вы угадали!");
                    return;
                }
            }
            
            System.out.println("Вы проиграли.");
        };
    
        do { // Главный цикл игры
            game.run();
        }
        while (playAgainP(inp));
    }
    
    static void wordsGame() {
        final String[] WORDS = {
                "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"
        };
        Scanner inp = new Scanner(System.in);
    
    
        do {
            char[] mask = "###############".toCharArray();
            String hiddenWord = WORDS[(int)(Math.random() * WORDS.length)];
            int left = hiddenWord.length(); // Количество неоткрытых букв. Для оптимизации - позволяет уменьшить количество сравнений строк.
        
            while (true) {
                System.out.print("Отгадайте слово: ");
                String answer = inp.nextLine().toLowerCase();
                int len = Math.min(hiddenWord.length(), answer.length());
            
                for (int i = 0; i < len; i++) {
                    char curr = answer.charAt(i);
                    if ((curr == hiddenWord.charAt(i) && (mask[i] == '#'))) {
                        mask[i] = curr;
                        --left;
                    }
                }
            
                System.out.println(mask);
                if (left == 0 && answer.equals(hiddenWord)) {
                    System.out.println("Вы угадали, поздравляем! Игра завершена.");
                    break;
                } else {
                    System.out.println("Вы ошиблись, попробуйте снова.");
                }
            }
        }
        while (playAgainP(inp));
    }
}
