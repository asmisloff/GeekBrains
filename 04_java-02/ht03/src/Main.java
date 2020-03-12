import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        String[] sw = squeeze(WORDS);
        System.out.print("{ ");
        for (int i = 0; i < sw.length; i++) {
            System.out.print(sw[i] + ", ");
        }
        System.out.println("\b\b }");

        System.out.println(alSqueeze(WORDS));

        System.out.println(hist(WORDS));

        PhoneBook pb = new PhoneBook();
        pb.addContact("Иванов", "8333222555", "ivanoff@email.ru");
        pb.addContact("Петров", "8333223666", "petroff@email.ru");
        pb.addContact("Сидоров", "8333224777", "sidoroff@email.ru");
        pb.addContact("Сидоров", "8444224777", "Sidoroff_2@email.ru");
        pb.addContact("Сидоров", "8444224777", "sidoroff_2@email.ru");
        System.out.println(pb.getEmail("Иванов"));
        System.out.println(pb.getEmail("Петров"));
        System.out.println(pb.getEmail("Сидоров"));
        System.out.println(pb.getPhone("Иванов"));
        System.out.println(pb.getPhone("Петров"));
        System.out.println(pb.getPhone("Сидоров"));
    }

    static String[] squeeze(String[] words) {
        HashSet<String> s = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            s.add(words[i].toLowerCase());
        }
        return s.toArray(new String[0]);
    }

    static ArrayList<String> alSqueeze(String[] words) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String curr = words[i].toLowerCase();
            if (!res.contains(curr)) {
                res.add(curr);
            }
        }
        return res;
    }

    static HashMap<String, Integer> hist(String[] words) {
        HashMap<String, Integer> res = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (res.containsKey(words[i])) {
                res.put(words[i], res.get(words[i]) + 1);
            } else {
                res.put(words[i], 1);
            }
        }
        return res;
    }

    static final String[] WORDS = {
            "Жил", "да" ,"был","Крокодил", "Он", "по", "улицам", "ходил", "Папиросы", "курил",
            "По", "турецки", "говорил", "Крокодил", "Крокодил", "Крокодилович",
            "А", "за", "ним", "то", "народ", "И", "поёт", "и", "орёт", "Вот", "урод", "так", "урод", "Что", "за",
            "нос", "что", "за", "рот", "И", "откуда", "такое", "чудовище"
    };
}
