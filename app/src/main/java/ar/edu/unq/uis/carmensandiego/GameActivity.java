package ar.edu.unq.uis.carmensandiego;

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

        pupulateSpinner();
    }

    private void pupulateSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerVillains);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.villains_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void buttonViajarOnClick(View view) {
        setContentView(R.layout.layout_travel);
    }

    public void buttonPedirPistaOnLick(View view) {
        setContentView(R.layout.layout_clue);
    }

    public void buttonEmitirOrdenOnClick(View view) {
        setContentView(R.layout.layout_arrest_order);
    }
}
