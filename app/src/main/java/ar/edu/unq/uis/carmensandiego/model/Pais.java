package ar.edu.unq.uis.carmensandiego.model;

import java.util.List;

/**
 * Created by lucasf on 6/21/17.
 */

public class Pais extends PaisSinFeatures {

    List<String> features;

    public Pais(int id, String name, List<String> features, List<MiniObject> connectedCountries, List<String> places) {
        super(id, name, connectedCountries, places);
        this.features = features;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
