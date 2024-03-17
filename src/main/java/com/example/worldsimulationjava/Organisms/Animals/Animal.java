package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.Organisms.Organism;
import com.example.worldsimulationjava.Utilities;
import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Animal extends Organism
{

    public static final double REPRODUCTION_PROBABLILITY = 0.25;

    Animal(Point2D position,
           int initiative,
           int strength,
           String name,
           char symbol,
           OrganismType type
    )
    {
        super(position, name, symbol, type, Color.ORANGE);
        this.initiative = initiative;
        this.strength = strength;
    }

    @Override
    public void action()
    {
        super.action();

        Point2D newPosition = new Point2D(-1, -1);
        while (!World.isInBounds(newPosition))
        {
            World.Field field = World.Field.values()[Utilities.range(0, 3)];
            newPosition = World.getOffsettedField(this, field);
        }
        if(world.isFieldTaken(newPosition))
        {
            world.findOrganismByPosition(newPosition).collision(this);
        }
        else
        {
            this.position = newPosition;
        }
    }

    @Override
    public void die(Organism killer)
    {
        Point2D position = this.getPosition();
        super.die(killer);
        killer.setPosition(position);
    }

    @Override
    public void collision(Organism other)
    {
        if(this.type.equals(other.type))
        {
            if(Utilities.isProbable(REPRODUCTION_PROBABLILITY))
            {
                reproduce(type);
            }
            return;
        }

        if(this.strength > other.getStrength())
        {
            other.die(this);
        }
        else
        {
            this.die(other);
        }
    }

}
