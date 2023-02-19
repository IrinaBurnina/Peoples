import java.util.OptionalInt;

public class Main {

    public static class PersonBuilder {
        protected String name;
        protected String surname;
        protected int age;
        protected String address;

        public PersonBuilder(String name, String surname) throws IllegalStateException {
            this.name = name;
            this.surname = surname;
        }

        public PersonBuilder() {
        }

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
            if (OptionalInt.of(this.age) != OptionalInt.empty() && age >= 0) {
                this.age = age;
                return this;
            } else if (age > 0) {
                System.out.println("Возраст для этого человека внесен. Данные изменяются при дне рождении.");
            } else {
                throw new IllegalArgumentException("Введено: " + age + " - некорректное значение возраста!");
            }
            return null;
        }

        public Person build() throws IllegalStateException {

            if (this.name != null && this.surname != null) {
                return new Person(this);
            } else if (this.name != null) {
                throw new IllegalStateException("Не хватает данных: фамилии");
            } else if (this.surname != null) {
                throw new IllegalStateException("Не хватает данных: имени");
            } else {
                throw new IllegalStateException("Не хватает данных: имени или фамилии");
            }
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }
    }

    public static void main(String[] args) throws IllegalStateException, IllegalArgumentException {
        final String name = null;
        final String surname = null;
        Person mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);
        //Возраст 0 лет
        Person baby = new PersonBuilder()
                .setName("Алла")
                .setSurname("Веерер")
                .setAge(0)
                .build();
        System.out.println("Самый маленький пассажир: " + baby);
        System.out.println("Имя мамы: " + mom.getName() + " , фамилия малыша: " + baby.getSurname() + ", возраст ребенка: "
                + son.getAge().getAsInt() + ", адрес мамы: " + mom.getAddress());
        //День рождения у мамы
        mom.happyBirthday();
        System.out.println("День Рождения у мамы! Ей сегодня " + mom.getAge().getAsInt());
        try {
            // Возраст недопустимый
            new PersonBuilder(name, surname).setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            // Не хватает обяхательных полей
            Person person = new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
}
