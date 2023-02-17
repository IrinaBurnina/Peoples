import java.util.OptionalInt;

public class Main {
    public class Person {
        protected final String name;
        protected final String surname;
        protected int age;
        protected String address = null;

        public Person(String name, String surname) {
            //new PersonBuilder(name, surname);
            this.name = name;
            this.surname = surname;//TODO установить имя и фамилию
        }

        public Person(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
           //TODO установить имя, фамилию и возраст
        }

        public boolean hasAge() {
            if (age == 0) {
                return false;
            } else {
                return true;
            }
        }

        public boolean hasAddress() {
            if (address == null) {
                return false;
            } else {
                return true;
            }
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public OptionalInt getAge() {
            if (hasAge() == true) {
                return OptionalInt.of(age);
            } else
                return null;
        }

        public String getAddress() { return address; }


        public void setAddress(String address) {
            this.address=address;}

        public void happyBirthday() {
            int newAge=this.age+1;
            this.age=newAge;}

        @Override
        public String toString() {
            if (this.hasAge()&&this.hasAddress()){
            return "Person{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';}
            else if (this.hasAddress()){
               return  "Person{" +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", address='" + address + '\'' +
                        '}';
            }else if(this.hasAge()){
                return "Person{" +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", age=" + age +
                        '}';
            }else {
                return "Person{" +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        '}';
            }
        }

        @Override
        public int hashCode() { /*...*/
        return 0;}//TODO переопределить хеш для ребёнка

        public PersonBuilder newChildBuilder() { /*...*/
        return null;}//TODO сделать ребёнка
    }

    public class PersonBuilder {
        protected String name;
        protected String surname;
        protected int age;
        protected String address;

        public PersonBuilder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        //...

        public PersonBuilder setName(String name) {
            if (this.name == null) {
                this.name = name;
                return this;
            } else {
                System.out.println("Имя для этого человека уже внесено.");
            }
            return null;
        }

        public PersonBuilder setSurname(String surname) {
            if (this.surname == null) {
                this.surname = surname;
                return this;
            } else {
                System.out.println("Фамилия для этого человека уже внесена");
            }
            return null;
        }

        public PersonBuilder setAge(int age) {
            try{ if (!build().hasAge()&&age>0) {//TODO если человеку всё же 0 лет, что тогда
                this.age = age;
                return this;
            } else if(age>0){
                System.out.println("Возраст для этого человека внесен. Данные изменяются при дне рождении.");
            }}
            catch (IllegalArgumentException exception){
                System.out.println("Введено некорректное значение возраста!");
                exception.printStackTrace();
            }
            return null;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            System.out.println("Адрес " + this.name + " " + this.surname + " изменён.");
            return this;
        }

        public Person build() {
            try {
                if (!name.isEmpty() && !surname.isEmpty()) {
                    new PersonBuilder(name, surname).build();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
                System.out.println("Не хватает данных: имени или фамилии");
                //TODO сделать разделение на имя и фамилию
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Person mom = new PersonBuilder(name, surname)
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);


        try {
            // Возраст недопустимый
            new PersonBuilder(name, surname).setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
