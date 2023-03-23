package co.edu.uptc.pojos;

public class Person {
    private static int count;
    private int id;
    private String code;
    private String name;

    public Person() {
        this.id = count++;
    }

    public Person(int id,String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Person clone(){
        return new Person(getId(),getName(),getCode());
    }

    @Override
    public String toString() {
        return "Person{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
