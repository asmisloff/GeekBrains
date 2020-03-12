import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    public void addContact(String name, String phone, String email) {
        name = name.toLowerCase();
        if (book.containsKey(name)) {
            book.get(name)
                    .addPhone(phone)
                    .addEmail(email);
        } else {
            book.put(name, new Person(phone, email));
        }
    }

    public ArrayList<String> getPhone(String name) {
        Person p = book.get(name.toLowerCase());
        if (p != null) {
            return p.getPhone();
        }

        return null;
    }

    public ArrayList<String> getEmail(String name) {
        Person p = book.get(name.toLowerCase());
        if (p != null) {
            return p.getEmail();
        }

        return null;
    }

    private HashMap<String, Person> book = new HashMap<>();
}
