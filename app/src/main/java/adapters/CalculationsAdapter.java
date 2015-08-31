package adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ziga.calchem.R;

import java.util.ArrayList;
import java.util.HashMap;

import helpers.Constants;
import models.CalculatedComponentHolder;
import models.Component;

public class CalculationsAdapter extends BaseAdapter
{
    private Activity activity;
    private ArrayList<HashMap> components;

    public CalculationsAdapter(Activity activity, ArrayList<HashMap> components)
    {
        this.activity = activity;
        this.components = components;
    }

    @Override
    public int getCount()
    {
        if(components!=null)
        {
            return components.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CalculatedComponentHolder holder;

        convertView = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.listview_row, null);

        holder = new CalculatedComponentHolder();
        holder.componentName = (TextView) convertView.findViewById(R.id.row_name);
        holder.concentration = (TextView) convertView.findViewById(R.id.row_result);
        holder.units = (TextView) convertView.findViewById(R.id.row_units);

        if(position==0)
        {
            holder.componentName.setTypeface(null, Typeface.BOLD);
            holder.concentration.setTypeface(null, Typeface.BOLD);
            holder.units.setTypeface(null, Typeface.BOLD);
        }

        HashMap map = components.get(position);
        holder.componentName.setText(map.get(Constants.FIRST_COLUMN).toString());
        holder.concentration.setText(map.get(Constants.SECOND_COLUMN).toString());
        holder.units.setText(map.get(Constants.THIRD_COLUMN).toString());



        return convertView;
    }
}