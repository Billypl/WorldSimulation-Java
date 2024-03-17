package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.Organisms.Organism;
import com.example.worldsimulationjava.Utilities;
import javafx.geometry.Point2D;

public class Turtle extends Animal
{

    private static final int MAX_ATTACK_STRENGTH_ABLE_TO_ENDURE = 5;
    
    
    public Turtle(Point2D position)
    {
        super(position,
                2,
                1,
                "Turtle",
                'T',
                OrganismType.TURTLE
        );
    }

    @Override
    public void action()
    {
        if (Utilities.isProbable(0.25))
        {
            super.action();
        }
        else
        {
            incrementLivedTurnsCounter();
        }
    }

    @Override
    public void collision(Organism other)
    {
        if (this.type.equals(other.type))
        {
            if (Utilities.isProbable(REPRODUCTION_PROBABLILITY))
            {
                reproduce(type);
            }
            return;
        }

        if (other.getStrength() >= MAX_ATTACK_STRENGTH_ABLE_TO_ENDURE)
        {
            this.die(other);
        }
        else
        {
            //TODO: add GUI message
        }

    }
}
