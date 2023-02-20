import java.util.OptionalInt;

public class Main {




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
