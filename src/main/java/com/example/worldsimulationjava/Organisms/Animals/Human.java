package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.InputController;
import com.example.worldsimulationjava.Organisms.Organism;
import com.example.worldsimulationjava.Utilities;
import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;

public class Human extends Animal
{
    public static UltHandler ultimate = new UltHandler();

    public Human(Point2D position)
    {
        super(position,
                4,
                5,
                "Human",
                'H',
                OrganismType.HUMAN);

    }

    @Override
    public void action()
    {
        Point2D newPosition = this.position.add(InputController.DIRECTION);
        if (World.isInBounds(newPosition))
        {
            if (world.isFieldTaken(newPosition) && !this.position.equals(newPosition))
            {
                world.findOrganismByPosition(newPosition).collision(world.getOrganism(this));
            }
            else
            {
                this.position = newPosition;
            }
        }
        if (ultimate.isActive())
        {
            destroyNeighbour(this, World.Field.UPPER_FIELD);
            destroyNeighbour(this, World.Field.BOTTOM_FIELD);
            destroyNeighbour(this, World.Field.RIGHT_FIELD);
            destroyNeighbour(this, World.Field.LEFT_FIELD);
        }
    }

    void destroyNeighbour(Organism organism, World.Field field)
    {
        if (world.isFieldTaken(this, field))
        {
            var toDelete = world.findOrganismByPosition(World.getOffsettedField(this, field));
            toDelete.die(this);
        }
    }

    @Override
    public void collision(Organism other)
    {
        if (this.strength > other.getStrength())
        {
            other.die(world.getOrganism(this));
        }
        else
        {
            this.die(other);
        }
    }

}
