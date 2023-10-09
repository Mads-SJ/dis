package json;

import java.util.ArrayList;

public class DataTransferObject {
    private String name;
    private ArrayList<Person> personer;

    public DataTransferObject(String name, ArrayList<Person> personer) {
        this.name = name;
        this.personer = personer;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Person> getPersoner() {
        return personer;
    }
}
