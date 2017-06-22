package ar.edu.unq.uis.carmensandiego.model;

import java.util.List;

/**
 * Created by lucasf on 6/21/17.
 */

public class PaisSinFeatures {

    int id;
    String name;
    List<MiniObject> connectedCountries;
    List<String> places;

    public PaisSinFeatures(int id, String name, List<MiniObject> connectedCountries, List<String> places) {
        this.id = id;
        this.name = name;
        this.connectedCountries = connectedCountries;
        this.places = places;
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

    public List<MiniObject> getConnectedCountries() {
        return connectedCountries;
    }

    public void setConnectedCountries(List<MiniObject> connectedCountries) {
        this.connectedCountries = connectedCountries;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }
}
