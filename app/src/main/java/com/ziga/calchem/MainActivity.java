package com.ziga.calchem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner totalVolumeSpinner = (Spinner) findViewById(R.id.total_volume_spinner);
        ArrayAdapter<CharSequence> totalVolumeAdapter = ArrayAdapter.createFromResource(this, R.array.total_volume_units, android.R.layout.simple_spinner_item);
        totalVolumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        totalVolumeSpinner.setAdapter(totalVolumeAdapter);

        final Spinner concentrationSpinner = (Spinner) findViewById(R.id.concentration_spinner);
        final ArrayAdapter<CharSequence> concentrationAdapter = ArrayAdapter.createFromResource(this, R.array.concentration_units, android.R.layout.simple_spinner_item);
        concentrationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        concentrationSpinner.setAdapter(concentrationAdapter);

        concentrationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String unit = concentrationSpinner.getSelectedItem().toString();
                TextView desiredConcentrationUnit = (TextView) findViewById(R.id.desired_concentration_unit);
                desiredConcentrationUnit.setText(unit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ListView list = (ListView) findViewById(R.id.lvItems);
        TextView noComponents = (TextView) findViewById(R.id.components_empty);
        list.setEmptyView(noComponents);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        }

        return super.onOptionsItemSelected(item);
    }
}
