package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import com.example.worldsimulationjava.Utilities;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Plant extends Organism
{
    final static double REPRODUCTION_PROBABLILITY = 0.05;
    public Plant(Point2D position, String name, char symbol, Organism.OrganismType type)
    {
        super(position, name, symbol, type, Color.GREEN);
        initiative = 0;
    }
    @Override
    public void action()
    {
        if(Utilities.isProbable(REPRODUCTION_PROBABLILITY))
        {
            reproduce(type);
        }
        super.action();
    }
    @Override
    public void collision(Organism other)
    {
        Point2D pos = this.position;
        die(other);
        other.setPosition(pos);
    }

}
