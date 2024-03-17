package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;

public class Sheep extends Animal
{
    public Sheep(Point2D position)
    {
        super(position,
                4,
                4,
                "Sheep",
                'S',
                Organism.OrganismType.SHEEP
        );
    }
}
