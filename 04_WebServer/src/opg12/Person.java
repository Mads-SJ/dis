package opg12;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private String by;

    public Person(String name, String by) {
        id = (int) (Math.random() * 1000);
        this.name = name;
        this.by = by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", by=" + by + "]";
    }
}
