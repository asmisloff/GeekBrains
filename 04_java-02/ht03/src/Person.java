import java.util.ArrayList;

public class Person {
    public Person(String p, String e) {
        phone = new ArrayList<>();
        email = new ArrayList<>();
        phone.add(p);
        email.add(e.toLowerCase());
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public Person addPhone(String p) {
        if (!phone.contains(p)) {
            phone.add(p);
        }
        return this;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public Person addEmail(String e) {
        e = e.toLowerCase();
        if (!email.contains(e)) {
            email.add(e);
        }
        return this;
    }

    private ArrayList<String> phone;
    private ArrayList<String> email;
}