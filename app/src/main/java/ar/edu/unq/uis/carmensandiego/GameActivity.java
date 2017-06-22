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
import ar.edu.unq.uis.carmensandiego.model.MiniObject;
import ar.edu.unq.uis.carmensandiego.model.Pais;
import ar.edu.unq.uis.carmensandiego.model.TravelCountry;
import ar.edu.unq.uis.carmensandiego.model.Villano;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private CarmenService service;
    public Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_arrest_order);

        service = ServiceConnection.CreateService();

        populateVillainsSpinner();
        startGame();
    }

    /**
     * pide un caso al servidor y se lo asigna a this.game
     */
    private void startGame() {
        final GameActivity that = this;

        service.startGame().enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                updateGameAndTitle(response, that);
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: manejar error
            }
        });
    }

    /**
     * llena el spinnerConnections con los paises conectados a this.game.getPais
     */
    private void populateConnectionsSpinner() {
        final GameActivity that = this;

        service.getPaises().enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                List<String> paisesNombre = new ArrayList<String>();
                for (Pais p : response.body()) {
                    if (isCountryConnected(p)) {
                        paisesNombre.add(p.getName());
                    }
                }
                populateSpinner(R.id.spinnerConnections, new ArrayAdapter(that, android.R.layout.simple_spinner_item, paisesNombre));
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                // TODO: manejar error
            }
        });
    }

    /**
     * @return dice si p esta conectado a game.getPais()
     */
    private boolean isCountryConnected(Pais p) {
        boolean ret = false;

        for (MiniObject mo : game.getPais().getConnectedCountries()) {
            ret = ret || p.getName().equals(mo.getName());
        }

        return ret;
    }

    /**
     * llena spinnerVillains con todos los villanos disponibles en el
     * servidor
     */
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

    /**
     * llena un spinner
     *
     * @param spinnerId el id del spinner a llenar
     * @param adapter los datos a cargar en el spinner
     */
    private void populateSpinner(int spinnerId, ArrayAdapter adapter) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * va a la layout para viajar
     */
    public void buttonViajarOnClick(View view) {
        setContentView(R.layout.layout_travel);
        populateConnectionsSpinner();
    }

    /**
     * va a la layout para pedir pista
     */
    public void buttonPedirPistaOnLick(View view) {
        setContentView(R.layout.layout_clue);
    }

    /**
     * va a la layout para emitir orden de arresto
     */
    public void buttonEmitirOrdenOnClick(View view) {
        setContentView(R.layout.layout_arrest_order);
        populateVillainsSpinner();
    }

    /**
     * hace que el servidor viaje al pais seleccionado en el spinnerConnections
     */
    public void buttonTravelOnClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerConnections);
        int destinationId = 0;

        for (MiniObject mo : game.getPais().getConnectedCountries()) {
            if (spinner.getSelectedItem().equals(mo.getName())) {
                destinationId = mo.getId();
                break;
            }
        }

        TravelCountry destination = new TravelCountry(destinationId, game.getId());

        final GameActivity that = this;
        service.travel(destination).enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                updateGameAndTitle(response, that);
                populateConnectionsSpinner();
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: mostrar algo lindo
            }
        });
    }

    /**
     * actualiza el juego y el titulo con la respuesta del servidor y la activity
     * sender es importante que se use la parametrizada y no this porque en el
     * contexto que se va a ejecutar este metodo, this puede significar otra cosa
     * (asincronico)
     * @param response respuesta proporcionada por el servidor
     * @param that activity sender (reemplaza this dentro de esta funcion)
     */
    private void updateGameAndTitle(Response<Game> response, GameActivity that) {
        that.game = response.body();
        that.setTitle("Estas en: " + that.game.getPais().getName() + "!");
    }
}
