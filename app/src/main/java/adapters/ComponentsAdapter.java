package adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziga.calchem.R;

import java.util.ArrayList;

import models.Component;
import models.ComponentViewHolder;

public class ComponentsAdapter extends RecyclerView.Adapter<ComponentViewHolder>
{
    Activity activity;
    ArrayList<Component> components;

    public ComponentsAdapter(Activity activity, ArrayList<Component> components)
    {
        this.activity = activity;
        this.components = components;
    }

    @Override
    public int getItemCount()
    {
        if(components!=null)
        {
            return components.size();
        }

        return 0;
    }

    @Override
    public ComponentViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);

        ComponentViewHolder holder = new ComponentViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComponentViewHolder holder, int position)
    {
        Component component = components.get(position);

        String componentTxt = "Component name: " + component.getName();
        String concentrationTxt = "Stock concentration: " + component.getConcentration() + component.getUnits();
        String desiredConcentrationTxt = "Desired concentration: " + component.getDesiredConcentration() + component.getUnits();

        holder.componentName.setText(componentTxt);
        holder.stockConcentration.setText(concentrationTxt);
        holder.desiredConcentration.setText(desiredConcentrationTxt);
    }
}
