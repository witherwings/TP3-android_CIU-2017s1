package ar.edu.unq.uis.carmensandiego.httpService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucasf on 6/21/17.
 */

public class ServiceConnection {

    public static CarmenService CreateService() {
        String IP = "192.168.1.108";
        String API_URL = "http://"+ IP +":9000";
        //Retrofit restAdapter = new Retrofit.Builder().setEndpoint(API_URL).build();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(CarmenService.class);
    }

}
