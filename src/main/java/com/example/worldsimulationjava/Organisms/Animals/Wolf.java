package com.example.worldsimulationjava.Organisms.Animals;

import javafx.geometry.Point2D;

public class Wolf extends Animal
{
    public Wolf(Point2D position)
    {
        super(position,
                5,
                9,
                "Wolf",
                'W',
                OrganismType.WOLF
        );
    }
}
