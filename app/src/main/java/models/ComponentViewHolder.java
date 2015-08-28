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
    public TextView unitTextView;

    public View closeView;
    public Integer cardPosition;

    public ComponentViewHolder(View itemView, Integer position)
    {
        super(itemView);
        componentName = (TextView) itemView.findViewById(R.id.component_name);
        stockConcentration = (TextView) itemView.findViewById(R.id.concentration);
        desiredConcentration = (TextView) itemView.findViewById(R.id.desired_concentration);
        unitTextView = (TextView) itemView.findViewById(R.id.unit);

        closeView = itemView.findViewById(R.id.close_view);
        this.cardPosition = position;
    }

    public Integer getCardPosition() {
        return cardPosition;
    }

    public void setCardPosition(Integer cardPosition) {
        this.cardPosition = cardPosition;
    }
}
