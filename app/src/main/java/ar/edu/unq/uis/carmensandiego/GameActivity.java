package ar.edu.unq.uis.carmensandiego;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.uis.carmensandiego.httpService.CarmenService;
import ar.edu.unq.uis.carmensandiego.httpService.ServiceConnection;
import ar.edu.unq.uis.carmensandiego.model.Game;
import ar.edu.unq.uis.carmensandiego.model.Pais;
import ar.edu.unq.uis.carmensandiego.model.Villano;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private CarmenService service;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_arrest_order);
        setTitle("Estas en: Egipto!");

        service = ServiceConnection.CreateService();

        populateVillainsSpinner();
        startGame();
    }

    private void startGame() {
        final GameActivity that = this;

        service.startGame().enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                that.game = response.body();
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: manejar error
            }
        });
    }

    private void populateConnectionsSpinner() {
        final GameActivity that = this;

        service.getPaises().enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                List<String> paisesNombre = new ArrayList<String>();
                for (Pais p : response.body()) {
                    // TODO: agregar un if para solo meter las conexiones con el pais actual
                    paisesNombre.add(p.getName());
                }
                populateSpinner(R.id.spinnerConnections, new ArrayAdapter(that, android.R.layout.simple_spinner_item, paisesNombre));
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                // TODO: manejar error
            }
        });
    }

    private void populateVillainsSpinner() {
        final GameActivity that = this;

        service.getVillanos().enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                List<String> paisesNombre = new ArrayList<String>();
                for (Pais p : response.body()) {
                    paisesNombre.add(p.getName());
                }
                populateSpinner(R.id.spinnerVillains, new ArrayAdapter(that, android.R.layout.simple_spinner_item, paisesNombre));
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                // TODO: manejar error
            }
        });
    }

    private void populateSpinner(int spinnerId, ArrayAdapter adapter) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void buttonViajarOnClick(View view) {
        setContentView(R.layout.layout_travel);
        populateConnectionsSpinner();
    }

    public void buttonPedirPistaOnLick(View view) {
        setContentView(R.layout.layout_clue);
    }

    public void buttonEmitirOrdenOnClick(View view) {
        setContentView(R.layout.layout_arrest_order);
        populateVillainsSpinner();
    }
}
