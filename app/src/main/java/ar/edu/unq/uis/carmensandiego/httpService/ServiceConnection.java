package ar.edu.unq.uis.carmensandiego.httpService;

import retrofit.RestAdapter;

/**
 * Created by lucasf on 6/21/17.
 */

public class ServiceConnection {

    public CarmenService createService() {
        String IP = "192.168.1.108";
        String API_URL = "http://"+ IP +":9000";
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        return restAdapter.create(CarmenService.class);
    }

}
