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
import ar.edu.unq.uis.carmensandiego.model.Villano;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_arrest_order);
        setTitle("Estas en: Egipto!");

        populateVillainsSpinner();
    }

    private void populateConnectionsSpinner() {
        populateSpinner(R.id.spinnerConnections, ArrayAdapter.createFromResource(this,
                R.array.connections_array, android.R.layout.simple_spinner_item));
    }

    private void populateVillainsSpinner() {
        //populateSpinner(R.id.spinnerVillains, R.array.villains_array);

        CarmenService service = new ServiceConnection().createService();

        final GameActivity that = this;

        service.getVillanos(new Callback<List<Villano>>() {
            @Override
            public void success(List<Villano> villanos, Response response) {
                // TODO: refactorizar
                List<String> villanosNombre = new ArrayList<String>();
                for (Villano v : villanos) {
                    villanosNombre.add(v.getName());
                }
                populateSpinner(R.id.spinnerVillains, new ArrayAdapter(that, android.R.layout.simple_spinner_item, villanosNombre));
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO: mostrar algo lindo
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
