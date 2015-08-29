package adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ziga.calchem.R;

import java.util.ArrayList;

import models.Component;
import models.ComponentHolder;

public class ComponentsAdapter extends RecyclerView.Adapter<ComponentHolder>
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
    public ComponentHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);

        ComponentHolder holder = new ComponentHolder(itemView, null);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComponentHolder holder, final int position)
    {
        Component component = components.get(position);

        String componentTxt = component.getName();
        String concentrationTxt = "Stock concentration: " + component.getConcentration();
        String desiredConcentrationTxt = "Desired concentration: " + component.getDesiredConcentration();
        String unit = "Units: " + component.getUnits();

        holder.componentName.setText(componentTxt);
        holder.stockConcentration.setText(concentrationTxt);
        holder.desiredConcentration.setText(desiredConcentrationTxt);
        holder.unitTextView.setText(unit);
        holder.cardPosition = position;

        holder.closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                components.remove(position);
                notifyDataSetChanged();

                Toast.makeText(activity.getBaseContext(), "Removed", Toast.LENGTH_SHORT).show();

                if(components.size() < 2)
                {
                    Button calcualateBtn = (Button) activity.findViewById(R.id.btn_calculate);
                    calcualateBtn.setEnabled(false);
                }
            }
        });
    }
}
