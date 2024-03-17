package com.example.worldsimulationjava.Organisms.Plants;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;

import static com.example.worldsimulationjava.Organisms.Organism.OrganismType;
import static com.example.worldsimulationjava.World.Field;

public class SosnowskyBorscht extends Plant
{
    public SosnowskyBorscht(Point2D position)
    {
        super(position,
                "SosnowskyBorscht",
                'S',
                OrganismType.SOSNOWSKY_BORSCHT
        );
        this.strength = 10;
    }

    @Override
    public void action()
    {
        destroyNeighbour(this, Field.UPPER_FIELD);
        destroyNeighbour(this, Field.BOTTOM_FIELD);
        destroyNeighbour(this, Field.RIGHT_FIELD);
        destroyNeighbour(this, Field.LEFT_FIELD);
        destroyNeighbour(this, Field.RIGHT_UPPER);
        destroyNeighbour(this, Field.LEFT_UPPER);
        destroyNeighbour(this, Field.RIGHT_BOTTOM);
        destroyNeighbour(this, Field.LEFT_BOTTOM);

        super.action();
    }

    void destroyNeighbour(Organism organism, Field field)
    {
        if(world.isFieldTaken(organism, field))
        {
            Organism toDelete = world.findOrganismByPosition(world.getOffsettedField(organism, field));
            if(toDelete.type == OrganismType.SOSNOWSKY_BORSCHT ||
                toDelete.type == OrganismType.WOLFBERRIES ||
                toDelete.type == OrganismType.GUARANA ||
                toDelete.type == OrganismType.DANDELION ||
                toDelete.type == OrganismType.GRASS
            )
                return;

            toDelete.die(this);
        }
    }
    @Override
    public void collision(Organism other)
    {
        other.die(world.getOrganism(this));
        super.collision(other);
    }

}
