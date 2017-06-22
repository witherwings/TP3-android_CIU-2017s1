package ar.edu.unq.uis.carmensandiego.model;

/**
 * Created by lucasf on 6/22/17.
 */

public class TravelCountry {

    int destinoId;
    int casoId;

    public TravelCountry(int destinoId, int casoId) {
        this.destinoId = destinoId;
        this.casoId = casoId;
    }

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public int getCasoId() {
        return casoId;
    }

    public void setCasoId(int casoId) {
        this.casoId = casoId;
    }
}
