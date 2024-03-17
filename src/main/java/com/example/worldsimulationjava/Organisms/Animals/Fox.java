package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;

import java.util.Optional;

public class Fox extends Animal
{
    public Fox(Point2D position)
    {
        super(position,
                7,
                3,
                "Fox",
                'F',
                OrganismType.FOX
        );
    }

    @Override
    public void action()
    {
        incrementLivedTurnsCounter();
        //TODO: fix fox getting stuck in wall (only forward movement)
        Optional<Point2D> newPoint = world.getFreeField(this);
        if (newPoint.isPresent() && World.isInBounds(newPoint.get()))
            this.position = newPoint.get();
    }
}
