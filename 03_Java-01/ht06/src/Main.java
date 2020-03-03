public class Main {

    public static void main(String[] args) {
        Animal d = new Dog();
        d.run(100);
        d.swim(-10);
        d.jump(500);
        
        Animal d1 = new Dog(-10, 100, 3);
        d1.run(100);
        d1.setMaxRunningDist(100);
        d1.run(100);
        
        Cat cat = new Cat();
        cat.run(100);
        cat.swim(10);
        cat.jump(1.5);
        cat.jump(-6);
    }
}

class Animal {
    
    public Animal() {}
    
    public Animal(double maxRunningDist, double maxSwimmingDist, double maxJumpingHeight) {
        if (maxRunningDist < 0 || maxSwimmingDist < 0 || maxJumpingHeight < 0) {
            System.out.printf("Ошибка: Все аргументы должны быть положительными числами -- %s(maxRunningDist, maxSwimmingDist, maxJumpingHeight)\n",
                    this.getClass().getName());
            System.out.println("Объект создан со значениями по умолчанию");
        } else {
            this.maxRunningDist = maxRunningDist;
            this.maxSwimmingDist = maxSwimmingDist;
            this.maxJumpingHeight = maxJumpingHeight;
        }
    }
    
    public void run(double dist) {
        if (dist < 0) {
            System.out.printf("Ошибка: дистанция должна быть положительной -- %s.run\n", this.getClass().getName());
        }
        else if (dist <= maxRunningDist) {
            System.out.printf("%s прыгнула на %.1f метров\n", capitalize(species), dist);
        } else {
            System.out.printf("Ошибка: предельная дистанция, которую может пробежать %s -- %.1f метров\n", species, maxRunningDist);
        }
    }
    
    protected String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public void swim(double dist) {
        if (dist < 0) {
            System.out.printf("Ошибка: дистанция должна быть положительной -- %s.swim\n", this.getClass().getName());
        }
        else if (dist <= maxSwimmingDist) {
            System.out.printf("%s проплыла %.1f метров\n", capitalize(species), dist);
        } else {
            System.out.printf("Ошибка: %s может проплыть не более %.1f метров\n", species, maxSwimmingDist);
        }
    }

    public void jump(double height) {
        if (height < 0) {
            System.out.printf("Ошибка: высота прыжка должна быть положительной -- %s.jump", this.getClass().getName());
        }
        else if (height <= maxJumpingHeight) {
            System.out.printf("%s подпрыгнула на %.1f метров\n", capitalize(species), height);
        } else {
            System.out.printf("Ошибка: %s может подпрыгнуть не более, чем на %.1f метров\n", species, maxJumpingHeight);
        }
    }
    
    public double getMaxRunningDist() {
        return maxRunningDist;
    }
    
    public void setMaxRunningDist(double maxRunningDist) {
        if (maxRunningDist < 0) {
            System.out.printf("Ошибка: дистанция должна быть положительной -- %s.setMaxRunningDist\n", this.getClass().getName());
            return;
        }
        this.maxRunningDist = maxRunningDist;
    }
    
    public double getMaxSwimmingDist() {
        return maxSwimmingDist;
    }
    
    public void setMaxSwimmingDist(double maxSwimmingDist) {
        if (maxRunningDist < 0) {
            System.out.printf("Ошибка: дистанция должна быть положительной -- %s.setMaxSwimmingDist\n", this.getClass().getName());
            return;
        }
        this.maxSwimmingDist = maxSwimmingDist;
    }
   
    public double getMaxJumpingHeight() {
        return maxJumpingHeight;
    }
    
    public void setMaxJumpingHeight(double maxJumpingHeight) {
        if (maxRunningDist < 0) {
            System.out.printf("Ошибка: высота прыжка должна быть положительной -- %s.setMaxJumpingHeight\n", this.getClass().getName());
            return;
        }
        this.maxJumpingHeight = maxJumpingHeight;
    }
    
    protected double maxRunningDist = 0.0;
    protected double maxSwimmingDist = 0.0;
    protected double maxJumpingHeight = 0.0;
    protected String species = "";
}

class Dog extends Animal {
    
    public Dog() {
        maxRunningDist = 500.0;
        maxSwimmingDist = 10.0;
        maxJumpingHeight = 0.5;
        species = "собака";
    }
    
    public Dog(double maxRunningDist, double maxSwimmingDist, double maxJumpingHeight) {
        super(maxRunningDist, maxSwimmingDist, maxJumpingHeight);
        species = "собака";
    }
}

class Cat extends Animal {
    public Cat() {
        maxRunningDist = 200.0;
        maxSwimmingDist = 0.0;
        maxJumpingHeight = 2.0;
        species = "Кошка";
    }
    
    public Cat(double maxRunningDist, double maxSwimmingDist, double maxJumpingHeight) {
        super(maxRunningDist, maxSwimmingDist, maxJumpingHeight);
        species = "Кошка";
    }
}

