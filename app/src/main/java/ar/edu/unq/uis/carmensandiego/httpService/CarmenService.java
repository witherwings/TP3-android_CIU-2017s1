package ar.edu.unq.uis.carmensandiego.httpService;

import java.util.List;

import ar.edu.unq.uis.carmensandiego.model.Clue;
import ar.edu.unq.uis.carmensandiego.model.Game;
import ar.edu.unq.uis.carmensandiego.model.Pais;
import ar.edu.unq.uis.carmensandiego.model.TravelCountry;
import ar.edu.unq.uis.carmensandiego.model.Villano;
import ar.edu.unq.uis.carmensandiego.model.Warrant;
import ar.edu.unq.uis.carmensandiego.model.WarrantCheck;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lucasf on 6/21/17.
 */

public interface CarmenService {

    /* VILLANOS! */

    @GET("/villanos")
    Call<List<Villano>> getVillanos();

    @GET("/villanos/{VillanoId}")
    void getVillanoById(@retrofit2.http.Path("VillanoId") String id, Callback<Villano> callback);

    /* PAISES! */

    @GET("/paises")
    Call<List<Pais>> getPaises();

    @GET("/paises/{PaisId}")
    void getPaisById(@retrofit2.http.Path("PaisId") String id, Callback<Pais> callback);

    /* GAME! */

    @POST("/iniciarJuego")
    Call<Game> startGame();

    @POST("/viajar")
    Call<Game> travel(@Body TravelCountry destination);



    @POST("/emitirOrdenPara")
    Call<String> createWarrantTo(@Body Warrant orden);

    @GET("/pistaDelLugar")
    Call<Clue> getClue(@retrofit2.http.Query("place") String place, @retrofit2.http.Query("caseID") int id);

    @GET("/chequearResponsable/{VillanoId}")
    Call<WarrantCheck> checkWarrant(@retrofit2.http.Path("VillanoId") int id);
}
