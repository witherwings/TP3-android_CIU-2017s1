package ar.edu.unq.uis.carmensandiego.model;

import java.util.List;

/**
 * Created by lucasf on 6/21/17.
 */

public class Villano {

    int id;
    String name;
    String gender;
    List<String> signs;
    List<String> hobbies;

    public Villano(int id, String name, String gender, List<String> signs, List<String> hobbies) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.signs = signs;
        this.hobbies = hobbies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSigns() {
        return signs;
    }

    public void setSigns(List<String> signs) {
        this.signs = signs;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
