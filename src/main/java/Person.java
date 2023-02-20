import java.util.Objects;
import java.util.OptionalInt;
import java.util.Scanner;

public class Person {
    private final String name;
    private final String surname;
    private int age;
    private String address;

    protected Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        this.age = personBuilder.age;
        this.address = personBuilder.address;
    }

    public boolean hasAge() {
        if (OptionalInt.of(age).isEmpty()) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        int newAge = this.age + 1;
        this.age = newAge;
    }

    @Override
    public String toString() {
        String base = "Person{" + "name='" + name + '\'' +
                ", surname='" + surname + '\'';
        if (this.hasAge() && this.hasAddress()) {
            return base +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        } else if (this.hasAddress()) {
            return base +
                    ", address='" + address + '\'' +
                    '}';
        } else if (this.hasAge()) {
            return base +
                    ", age=" + age +
                    '}';
        } else {
            return base +
                    '}';
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name + surname);
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.surname = this.surname;
        if (this.hasAddress()) {
            child.address = this.address;
        }
        //-в задании условие, что "ребёнка, а именно: с уже заполненными фамилией (родительской), возрастом и текущим городом жительства (родительским)"
        System.out.println("Введите возраст ребенка (родителя " + this.name + " " + this.surname + "): ");
        try {
            Scanner scanner = new Scanner(System.in);
            int childAge = Integer.parseInt(scanner.nextLine());
            child.setAge(childAge);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return child;
    }
}

