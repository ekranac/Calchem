package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ziga.calchem.R;

import java.util.ArrayList;

import adapters.ComponentsAdapter;
import helpers.Constants;
import models.Component;


public class MainActivity extends AppCompatActivity {

    private RecyclerView componentsList;
    ArrayList<Component> components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        components = new ArrayList<Component>();

        componentsList = (RecyclerView) findViewById(R.id.list_components);
        componentsList.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        componentsList.setLayoutManager(llm);

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

        Button addComponent = (Button) findViewById(R.id.add_component);
        addComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputName = (EditText) findViewById(R.id.input_name);
                EditText inputConcentration = (EditText) findViewById(R.id.input_concentration);
                EditText inputDesiredConcentration = (EditText) findViewById(R.id.input_desired_concentration);

                String name = inputName.getText().toString();
                String stockConcentration = inputConcentration.getText().toString();
                String desiredConcentration = inputDesiredConcentration.getText().toString();
                String unit = concentrationSpinner.getSelectedItem().toString();

                if(!name.equals("") && !stockConcentration.equals("") && !desiredConcentration.equals("") && unit!=null)
                {
                    Component component = new Component(name, Double.parseDouble(stockConcentration), Double.parseDouble(desiredConcentration), unit);
                    components.add(component);

                    ComponentsAdapter adapter = new ComponentsAdapter(MainActivity.this, components);
                    componentsList.setAdapter(adapter);
                    componentsList.getLayoutManager().scrollToPosition(components.size()-1);

                    if(components.size() > 1)
                    {
                        Button calcualteButton = (Button) findViewById(R.id.btn_calculate);
                        calcualteButton.setEnabled(true);
                    }
                }

            }
        });

        Button calcualteButton = (Button) findViewById(R.id.btn_calculate);
        calcualteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CalculationActivity.class);
                intent.putExtra(Constants.COMPONENTS, components);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {}

        return super.onOptionsItemSelected(item);
    }
}
