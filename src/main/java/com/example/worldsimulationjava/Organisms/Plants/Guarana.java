package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;

public class Guarana extends Plant
{
    private final static int BONUS_STRENGTH = 3;

    public Guarana(Point2D position)
    {
        super(position,
                "Guarana",
                'g',
                OrganismType.GUARANA
        );
    }

    @Override
    public void collision(Organism organism)
    {
        organism.setStrength(organism.getStrength() + BONUS_STRENGTH);
        super.collision(organism);
    }
}
