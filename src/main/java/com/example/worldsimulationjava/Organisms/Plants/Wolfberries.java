package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;

public class Wolfberries extends Plant
{
    public Wolfberries(Point2D position)
    {
        super(
                position,
                "Wolfberries",
                'W',
                OrganismType.WOLFBERRIES
        );
        this.strength = 99;
    }

    @Override
    public void collision(Organism other)
    {
        other.die(world.getOrganism(this));
        super.collision(other);
    }
}
