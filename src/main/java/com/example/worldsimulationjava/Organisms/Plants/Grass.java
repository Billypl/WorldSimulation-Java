package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;

public class Grass extends Plant
{
    public Grass(Point2D position)
    {
        super(position,
                "Grass",
                'G',
                Organism.OrganismType.GRASS
        );
    }
}

