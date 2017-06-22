package ar.edu.unq.uis.carmensandiego.model;

/**
 * Created by lucasf on 6/21/17.
 */

class MiniObject {

    int id;
    String name;

    public MiniObject(int id, String name) {
        this.id = id;
        this.name = name;
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
}
