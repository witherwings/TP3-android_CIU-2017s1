package ar.edu.unq.uis.carmensandiego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CarmenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen);

        setTitle("Carmen San Diego");
    }

    public void buttonResolverMisterio_onClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
