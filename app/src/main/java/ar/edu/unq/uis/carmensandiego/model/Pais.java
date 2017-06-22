package ar.edu.unq.uis.carmensandiego.model;

import java.util.List;

/**
 * Created by lucasf on 6/21/17.
 */

public class Pais {

    int id;
    String name;
    List<String> features;
    List<MiniObject> connectedCountries;
    List<String> places;

    public Pais(int id, String name, List<String> features, List<MiniObject> connectedCountries, List<String> places) {
        this.id = id;
        this.name = name;
        this.features = features;
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

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
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
