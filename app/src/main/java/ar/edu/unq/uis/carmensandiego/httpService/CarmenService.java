package ar.edu.unq.uis.carmensandiego.httpService;

import java.util.List;

import ar.edu.unq.uis.carmensandiego.model.Pais;
import ar.edu.unq.uis.carmensandiego.model.Villano;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by lucasf on 6/21/17.
 */

public interface CarmenService {

    /* VILLANOS! */

    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @GET("/villanos/{VillanoId}")
    void getVillanoById(@retrofit.http.Path("VillanoId") String id, Callback<Villano> callback);

    /* PAISES! */

    @GET("/paises")
    void getPaises(Callback<List<Pais>> callback);

    @GET("/paises/{PaisID}")
    void getPaisById(@retrofit.http.Path("PaisId") String id, Callback<Pais> callback);

}
