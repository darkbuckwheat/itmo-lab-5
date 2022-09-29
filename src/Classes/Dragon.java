package Classes;

import Managers.CollectionManager;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class that must be realised by task. Describes the objects that the collection will store.
 */
public class Dragon implements Comparable<Dragon>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private float wingspan; //Значение поля должно быть больше 0
    private Boolean speaking; //Поле не может быть null
    private Color color; //Поле не может быть null
    private DragonCave cave; //Поле не может быть null

    /**
     * Constructor of Dragon
     * @param name can't be null or empty
     * @param coordinates can't be null
     * @see Coordinates
     * @param age must be greater than 0
     * @param wingspan must be greater than 0
     * @param speaking can't be null
     * @param color can't be null
     * @param cave can't be null
     * @see DragonCave
     * @throws FieldNullException
     * @throws IncorrectFieldValueException
     */
    public Dragon(String name, Coordinates coordinates, int age,
                  float wingspan, Boolean speaking, Color color, DragonCave cave)
            throws FieldNullException, IncorrectFieldValueException {
        if (name == null) {
            throw new FieldNullException("name");
        } else if (coordinates == null) {
            throw new FieldNullException("coordinates");
        } else if (speaking == null) {
            throw new FieldNullException("speaking");
        } else if (color == null) {
            throw new FieldNullException("color");
        } else if (cave == null) {
            throw new FieldNullException("cave");
        }

        if (name.equals("")) {
            throw new IncorrectFieldValueException("name", name, "not null and not \"\"");
        } else if (age <= 0) {
            throw new IncorrectFieldValueException("age", String.valueOf(age), "greater than 0");
        } else if (wingspan <= 0) {
            throw new IncorrectFieldValueException("wingspan", String.valueOf(wingspan), "greater than 0");
        }

        this.id = CollectionManager.getNewId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = CollectionManager.getDate();
        this.age = age;
        this.wingspan = wingspan;
        this.speaking = speaking;
        this.color = color;
        this.cave = cave;
    }


    // гетеры
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getAge() {
        return age;
    }

    public float getWingspan() {
        return wingspan;
    }

    public Boolean getSpeaking() {
        return speaking;
    }

    public Color getColor() {
        return color;
    }

    public DragonCave getCave() {
        return cave;
    }


    // сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWingspan(float wingspan) {
        this.wingspan = wingspan;
    }

    public void setSpeaking(Boolean speaking) {
        this.speaking = speaking;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    public int compareTo(Dragon anotherDragon){
        if (name.compareTo(anotherDragon.getName()) != 0) {
            return name.compareTo(anotherDragon.getName());
        }
        else if (age != anotherDragon.getAge()){
            return Integer.compare(age, anotherDragon.getAge());
        }
        else{
            return Float.compare(wingspan, anotherDragon.getWingspan());
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dragon dragon)) return false;
        return age == dragon.age && Float.compare(dragon.wingspan, wingspan) == 0 && id.equals(dragon.id) &&
                name.equals(dragon.name) && coordinates.equals(dragon.coordinates) &&
                creationDate.equals(dragon.creationDate) && speaking.equals(dragon.speaking) &&
                color == dragon.color && cave.equals(dragon.cave);
    }

    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, wingspan, speaking, color, cave);
    }

    public String toString() {
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", wingspan=" + wingspan +
                ", speaking=" + speaking +
                ", color=" + color +
                ", cave=" + cave +
                '}';
    }
}
