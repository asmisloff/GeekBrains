public class Main {

    public static void main(String[] args) {
        Cat[] cats = new Cat[5];
        Plate plate = new Plate(200);

        for (int i = 0; i < cats.length; i++) {
            String name = "Cat #" + (i + 1);
            Cat cat;
            cat = new Cat(name, (int)(Math.random() * 100));
            cat.info();
            while (!cat.isFull()) {
                System.out.printf("%s подходит к тарелке...\n", name);
                if (!feedTheCat(cat, plate)) {
                    System.out.println("В тарелку добавлено 25");
                    plate.addFood(25);
                }
                plate.info();
            }
            System.out.println("----------------------------------------------------\n");
        }
    }

    private static boolean feedTheCat(Cat cat, Plate plate) {
        boolean success = cat.eat(plate);
        if (!success) {
            System.out.println("Мало еды в тарелке, кот отходит.");
        } else {
            System.out.println("Зверюга насытила свою утробу. Уходит.");
        }
        return success;
    }
}

class Plate {

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int n) {
        if (food < n) {
            food = 0;
        } else {
            food -= n;
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public int remainder() {
        return food;
    }

    public void addFood(int amount) {
        food += amount;
    }

    private int food;
}

class Cat {

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        fullness = false;
    }

    public boolean eat(Plate p) {
        if (appetite <= p.remainder()) {
            p.decreaseFood(appetite);
            fullness = true;
        }
        return fullness;
    }

    public void info() {
        System.out.printf("%s: appetite = %d, fullness = %b\n", name, appetite, fullness);
    }

    public boolean isFull() {
        return fullness;
    }

    private String name;
    private int appetite;
    private boolean fullness;
}
