import java.util.OptionalInt;

public class PersonBuilder {
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
