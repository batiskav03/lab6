package data;
import java.io.Serializable;
import java.util.Date;


public class Dragon implements Comparable<Dragon>, Serializable {


    private String owner;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически


    private Integer age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;


    public Dragon(String owner,String name, Coordinates coordinates, Date creationDate, Integer age, Color color, DragonType type, DragonCharacter character, DragonHead head) {
        this.owner = owner;
        this.id = getRandomID();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.head = head;
    }
    public Dragon(Integer id, String owner,String name, Coordinates coordinates, Date creationDate, Integer age, Color color, DragonType type, DragonCharacter character, DragonHead head) {
        this.owner = owner;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.head = head;
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "owner=" + owner +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", color=" + color +
                ", type=" + type +
                ", character=" + character +
                ", head=" + head.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int compareTo(Dragon v) {
        return age - v.age;
    }

    public static int getRandomID(){

        return (int) (Math.random() * Integer.MAX_VALUE);

    }
    public Integer getAge() {
        return age;
    }
    public Float getX() {
        return coordinates.getY();
    }
    public Long getY(){
        return coordinates.getX();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.toString();
    }
    public DragonType getDragonType() {
        return type;
    }
    public DragonHead getDragonHead() {
        return head;
    }

    public String getCharacter() {
        return character.toString();
    }
    public DragonCharacter getDragonCharacter() {
        return character;
    }

    public long getEyes() {
        return head.getEyesCount();
    }
    public float getTooth() {
        return head.getToothCount();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public DragonHead getHead() {
        return head;
    }
}