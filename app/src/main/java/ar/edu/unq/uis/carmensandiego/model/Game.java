package ar.edu.unq.uis.carmensandiego.model;

import java.util.List;

/**
 * Created by lucasf on 6/21/17.
 */

public class Game {

    int id;
    PaisSinFeatures pais;
    List<String> paisesVisitados;
    List<String> paisesFallidos;

    public Game(int id, PaisSinFeatures pais, List<String> paisesVisitados, List<String> paisesFallidos) {
        this.id = id;
        this.pais = pais;
        this.paisesVisitados = paisesVisitados;
        this.paisesFallidos = paisesFallidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaisSinFeatures getPais() {
        return pais;
    }

    public void setPais(PaisSinFeatures pais) {
        this.pais = pais;
    }

    public List<String> getPaisesVisitados() {
        return paisesVisitados;
    }

    public void setPaisesVisitados(List<String> paisesVisitados) {
        this.paisesVisitados = paisesVisitados;
    }

    public List<String> getPaisesFallidos() {
        return paisesFallidos;
    }

    public void setPaisesFallidos(List<String> paisesFallidos) {
        this.paisesFallidos = paisesFallidos;
    }
}
