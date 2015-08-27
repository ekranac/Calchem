package models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ziga.calchem.R;

public class ComponentViewHolder extends RecyclerView.ViewHolder
{
    public TextView componentName;
    public TextView stockConcentration;
    public TextView desiredConcentration;

    public ComponentViewHolder(View itemView)
    {
        super(itemView);
        componentName = (TextView) itemView.findViewById(R.id.component_name);
        stockConcentration = (TextView) itemView.findViewById(R.id.concentration);
        desiredConcentration = (TextView) itemView.findViewById(R.id.desired_concentration);
    }
}
