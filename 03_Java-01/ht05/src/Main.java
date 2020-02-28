public class Main {

    public static void main(String[] args) {
        Employee[] emps = new Employee[5];
    
        emps[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        emps[1] = new Employee("Petroff Petr", "Manager", "p_petroff@mailbox.com", "892315566", 40000, 33);
        emps[2] = new Employee("Sidoroff Sidor", "Boss", "ss@mailbox.com", "892377777", 50000, 45);
        emps[3] = new Employee("Medvedeva Masha", "Counter", "masha@mailbox.com", "892388888", 40000, 32);
        emps[4] = new Employee("Funt", "Sitzredaktor", "funt@mailbox.com", "892300000", 120000, 78);
        
        for (var emp : emps) {
            if (emp.getAge() > 40) {
                System.out.println("-----------------------------------------------------");
                emp.print();
            }
        }
    }
    
    public static class Employee {
        
        public Employee(String name, String position, String email, String phone, int salary, int age) {
            this.name = name;
            this.position = position;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
            this.age = age;
        }
        
        public void print() {
            System.out.printf("ФИО: %s (%d %s)\n", name, age, properNounForAge(age));
            System.out.printf("Должность: %s, зарплата: %d\n", position, salary);
            System.out.printf("e-mail: %s, телефон: %s\n", email, phone);
        }
        
        private String properNounForAge(int years) {
            final int rem = years % 10;
            
            if (10 < years && years < 20) {
                return "лет";
            } else if (0 <= rem && rem < 5) {
                return "год";
            } else {
                return "лет";
            }
        }
    
        private String name;
        private String position;
        private String email;
        private String phone;
        private int salary;
    
        public int getAge() {
            return age;
        }
    
        private int age;
    }
}
