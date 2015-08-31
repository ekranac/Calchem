package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.ziga.calchem.R;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.CalculationsAdapter;
import adapters.ComponentsAdapter;
import helpers.Constants;
import models.Component;

public class CalculationActivity extends AppCompatActivity
{
    HashMap map;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        ActionBar bar = getSupportActionBar();
        if(bar!=null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        ArrayList<Component> extraComponents = (ArrayList<Component>) intent.getSerializableExtra(Constants.COMPONENTS);
        String totalVolume = intent.getStringExtra(Constants.TOTAL_VOLUME);
        String totalVolumeUnits = intent.getStringExtra(Constants.TOTAL_VOLUME_UNITS);

        TextView tvTotalVolume = (TextView) findViewById(R.id.total_volume);
        tvTotalVolume.setText("Total volume: " + totalVolume + " " + totalVolumeUnits);

        ArrayList<HashMap> components = new ArrayList<HashMap>();
        map = new HashMap();
        map.put(Constants.FIRST_COLUMN, "Name");
        map.put(Constants.SECOND_COLUMN, "Result");
        map.put(Constants.THIRD_COLUMN, "Units");
        components.add(map);


        Double volumeLeft = Double.parseDouble(totalVolume);

        for(int i=0; i<extraComponents.size(); i++)
        {
            map = new HashMap();
            Component component = extraComponents.get(i);
            String units = component.getUnits();

            Double calculation = (Double.parseDouble(totalVolume) / component.getConcentration()) * component.getDesiredConcentration();
            if(units.equals("% (weight/volume)"))
            {
                if (totalVolumeUnits.equals("ml"))
                {
                    units = "g";
                }
                else
                {
                    units = "mg";
                }
            }
            else
            {
                units = totalVolumeUnits;
            }

            map.put(Constants.FIRST_COLUMN, extraComponents.get(i).getName());
            map.put(Constants.SECOND_COLUMN, calculation.toString());
            map.put(Constants.THIRD_COLUMN, units);
            components.add(map);

            volumeLeft -= calculation;
        }

        TextView tvVolumeLeft = (TextView) findViewById(R.id.volume_left);
        tvVolumeLeft.setText("Volume left: " + Double.toString(volumeLeft) + " " + totalVolumeUnits);

        CalculationsAdapter adapter = new CalculationsAdapter(this, components);
        ListView list = (ListView) findViewById(R.id.listed_components);
        list.setAdapter(adapter);

        RecyclerView componentsList = (RecyclerView) findViewById(R.id.list_components);
        componentsList.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        componentsList.setLayoutManager(llm);

        ComponentsAdapter componentsAdapter = new ComponentsAdapter(this, extraComponents);
        componentsList.setAdapter(componentsAdapter);
    }
}
