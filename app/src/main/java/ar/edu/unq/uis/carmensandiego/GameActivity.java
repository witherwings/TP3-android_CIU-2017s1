package ar.edu.unq.uis.carmensandiego;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_arrest_order);
        setTitle("Estas en: Egipto!");

        populateVillainsSpinner();
    }

    private void populateConnectionsSpinner() {
        populateSpinner(R.id.spinnerConnections, R.array.connections_array);
    }

    private void populateVillainsSpinner() {
        populateSpinner(R.id.spinnerVillains, R.array.villains_array);
    }

    private void populateSpinner(int spinnerId, @ArrayRes int arrayRes) {
        Spinner spinner = (Spinner) findViewById(spinnerId);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayRes, android.R.layout.simple_spinner_item);

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
