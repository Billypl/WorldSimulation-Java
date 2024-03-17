package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.Utilities;
import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;

public class Antelope extends Animal
{

    public Antelope(Point2D position)
    {
        super(position,
                4,
                4,
                "Antelope",
                'A',
                OrganismType.ANTELOPE
        );
    }

    @Override
    public void action()
    {
        incrementLivedTurnsCounter();
        for (int i = 0; i < 2; i++)
        {
            Point2D newPosition = new Point2D(-1, -1);
            while (!World.isInBounds(newPosition))
            {
                World.Field field = World.Field.values()[Utilities.range(0, 3)];
                newPosition = World.getOffsettedField(this, field);
            }
            if (world.isFieldTaken(newPosition))
            {
                world.findOrganismByPosition(newPosition).collision(this);
            }
            else
            {
                this.position = newPosition;
            }
        }
    }
}
