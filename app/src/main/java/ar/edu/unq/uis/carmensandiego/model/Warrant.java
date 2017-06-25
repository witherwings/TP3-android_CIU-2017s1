package ar.edu.unq.uis.carmensandiego.model;

/**
 * Created by Wither on 25/06/2017.
 */

public class Warrant {

    int villanoId;
    int casoId;

    public Warrant(int villanoId, int casoId) {
        this.villanoId = villanoId;
        this.casoId = casoId;
    }

    public int getVillanoId() {return villanoId;}

    public void setVillanoId(int villanoId) {this.villanoId = villanoId;}

    public int getCasoId() {return casoId;}

    public void setCasoId(int casoId) {this.casoId = casoId;}
}
