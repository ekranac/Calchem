package models;

import java.io.Serializable;

public class Component implements Serializable
{
    String name;
    Double concentration;
    Double desiredConcentration;
    String units;

    public Component(String name, Double concentration, Double desiredConcentration, String units)
    {
        this.name = name;
        this.concentration = concentration;
        this.desiredConcentration = desiredConcentration;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConcentration() {
        return concentration;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public Double getDesiredConcentration() {
        return desiredConcentration;
    }

    public void setDesiredConcentration(Double desiredConcentration) {
        this.desiredConcentration = desiredConcentration;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
