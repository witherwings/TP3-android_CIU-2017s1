package ar.edu.unq.uis.carmensandiego;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.uis.carmensandiego.httpService.CarmenService;
import ar.edu.unq.uis.carmensandiego.httpService.ServiceConnection;
import ar.edu.unq.uis.carmensandiego.model.Clue;
import ar.edu.unq.uis.carmensandiego.model.Game;
import ar.edu.unq.uis.carmensandiego.model.MiniObject;
import ar.edu.unq.uis.carmensandiego.model.Pais;
import ar.edu.unq.uis.carmensandiego.model.PaisSinFeatures;
import ar.edu.unq.uis.carmensandiego.model.TravelCountry;
import ar.edu.unq.uis.carmensandiego.model.Villano;
import ar.edu.unq.uis.carmensandiego.model.Warrant;
import ar.edu.unq.uis.carmensandiego.model.WarrantCheck;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private CarmenService service;
    public Game game;
    public List<Villano> allVillains;
    public List<Pais> paises;
    public Villano suspect = null;

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
                updateGameAndTitle(response);
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: manejar error
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });

        service.getVillanos().enqueue(new Callback<List<Villano>>() {
            @Override
            public void onResponse(Call<List<Villano>> call, Response<List<Villano>> response) {
                updateVillains(response);
            }

            @Override
            public void onFailure(Call<List<Villano>> call, Throwable t) {
                //TODO: handle
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });

        service.getPaises().enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                updatePaises(response);
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                //TODO: handle
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
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

        service.getVillanos().enqueue(new Callback<List<Villano>>() {
            @Override
            public void onResponse(Call<List<Villano>> call, Response<List<Villano>> response) {
                List<String> villanosNombre = new ArrayList<String>();
                for (Villano v : response.body()) {
                    villanosNombre.add(v.getName());
                }
                populateSpinner(R.id.spinnerVillains, new ArrayAdapter(that, android.R.layout.simple_spinner_item, villanosNombre));
            }

            @Override
            public void onFailure(Call<List<Villano>> call, Throwable t) {
                // TODO: manejar error
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
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
        setPlaces();
    }

    private void setPlaces() {
        String firstPlace = game.getPais().getPlaces().get(0);
        ((Button) findViewById(R.id.firstPlace)).setText(firstPlace);
        String secondPlace = game.getPais().getPlaces().get(1);
        ((Button) findViewById(R.id.secondPlace)).setText(secondPlace);
        String thirdPlace = game.getPais().getPlaces().get(2);
        ((Button) findViewById(R.id.thirdPlace)).setText(thirdPlace);
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
                updateGameAndTitle(response);
                updateVisitedCountries();
                populateConnectionsSpinner();
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: mostrar algo lindo
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buttonGetBackOnClick(View view) {

        int destinationId = 0;
        String lastCountry = game.getPaisesVisitados().get(game.getPaisesVisitados().size()-1);

        for (Pais p : paises) {
            if (lastCountry.equals(p.getName())) {
                destinationId = p.getId();
                break;
            }
        }

        TravelCountry destination = new TravelCountry(destinationId, game.getId());
        ((TextView) findViewById(R.id.textViewFailedCountries)).setText(game.getPais().getName());

        service.travel(destination).enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                updateGameAndTitle(response);
                populateConnectionsSpinner();
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                // TODO: mostrar algo lindo
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void buttonWarrantOnClick(View view){
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerVillains);
        int suspectId = 0;

        for (Villano v : allVillains) {
            if (spinner.getSelectedItem().equals(v.getName())) {
                suspectId = v.getId();
                break;
            }
        }
        Warrant orden = new Warrant(suspectId, game.getId());

        final GameActivity that = this;
        service.createWarrantTo(orden).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Villano selected = null;
                for (Villano v : allVillains) {
                    if (spinner.getSelectedItem().equals(v.getName())) {
                        selected = v;
                        break;
                    }
                }
                updateSuspect(selected);
                updateSuspectTextView();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // TODO: mostrar algo lindo
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSuspectTextView() {
        ((TextView) findViewById(R.id.textViewSuspect)).setText(suspect.getName());
    }

    /**
     * actualiza la lista de paises recorridos (textViewVisitedCountries)
     */
    private void updateVisitedCountries() {
        String text = "";

        for (String visited : game.getPaisesVisitados()) {
            if (text.equals("")) {
                text = text + " " + visited;
            }
            else {
                text = text + " -> " + visited;
            }
        }

        ((TextView) findViewById(R.id.textViewVisitedCountries)).setText(text);
    }

    /**
     * actualiza el juego y el titulo con la respuesta del servidor
     * @param response respuesta proporcionada por el servidor
     */
    private void updateGameAndTitle(Response<Game> response) {
        game = response.body();
        setTitle("Estas en: " + game.getPais().getName() + "!");
    }

    private void updateVillains(Response<List<Villano>> response) {
        allVillains = response.body();
    }

    private void updatePaises(Response<List<Pais>> response) {
        paises = response.body();
    }

    private void updateSuspect(Villano v) {
        suspect = v;
    }

    /**
     * implementacion del boton el cual muestra de acuerdo
     * a que lugar su respectiva pista
     * */

    public void buttonPlaceClueOnClick(View view) {
        switch (view.getId()){
            case R.id.firstPlace:
                showCurrentPlace(R.id.firstPlace);
                showClue(R.id.firstPlace, game.getId());
                break;
            case R.id.secondPlace:
                showCurrentPlace(R.id.secondPlace);
                showClue(R.id.secondPlace, game.getId());
                break;
            case R.id.thirdPlace:
                showCurrentPlace(R.id.thirdPlace);
                showClue(R.id.thirdPlace, game.getId());
                break;
        }
    }

    private void showCurrentPlace(@IdRes int place) {
        String currentPlace = ((Button) findViewById(place)).getText().toString();

        ((TextView) findViewById(R.id.currentPlace)).setText(currentPlace);
    }

    public void showClue(@IdRes int buttonID, int caseId){
        String currentPlace = ((Button) findViewById(buttonID)).getText().toString();
        service.getClue(currentPlace, caseId).enqueue(new Callback<Clue>() {
            @Override
            public void onResponse(Call<Clue> call, Response<Clue> response) {
                String pista = response.body().getPista();
                ((TextView) findViewById(R.id.showClue)).setText(pista);

                if(pista.contains("ALTO!! Detengase: ")){
                    if(suspect != null){
                    service.checkWarrant(suspect.getId()).enqueue(new Callback<WarrantCheck>() {
                        @Override
                        public void onResponse(Call<WarrantCheck> call, Response<WarrantCheck> response) {
                            if(response.body().getChequeo()){
                                ((TextView) findViewById(R.id.endgameMessage)).setText("Enhorabuena! Has logrado atrapar al responsable del robo. Felicitaciones!");
                            }
                            else{
                                ((TextView) findViewById(R.id.endgameMessage)).setText("Malas Noticias! Tu Orden de Arresto no concordaba con el responsable del robo. Lamentablemente, el hecho quedara inpune.");
                            }
                        }

                        @Override
                        public void onFailure(Call<WarrantCheck> call, Throwable t) {
                            // TODO: mostrar algo lindo
                            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }
                    });}
                    else{
                        ((TextView) findViewById(R.id.endgameMessage)).setText("PERDISTE!. No generaste ninguna orden de arresto antes de atrapar al sospechoso.");
                    }
                }
            }

            @Override
            public void onFailure(Call<Clue> call, Throwable t) {
                // TODO: mostrar algo lindo
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
