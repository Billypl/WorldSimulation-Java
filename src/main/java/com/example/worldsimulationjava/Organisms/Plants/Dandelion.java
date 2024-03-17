package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import com.example.worldsimulationjava.Utilities;
import javafx.geometry.Point2D;

public class Dandelion extends Plant
{
    private static final int reproductionTries = 3;

    public Dandelion(Point2D position)
    {
         super(position,
            "Dandelion",
            'D',
                 Organism.OrganismType.DANDELION
         );
    }

    @Override
    public void action()
    {
        for (int i = 0; i < reproductionTries; i++)
        {
            if (Utilities.isProbable(REPRODUCTION_PROBABLILITY))
            {
                this.reproduce(type);
            }
        }
        incrementLivedTurnsCounter();
    }
}
