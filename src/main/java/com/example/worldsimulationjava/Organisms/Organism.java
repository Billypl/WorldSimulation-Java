package com.example.worldsimulationjava.Organisms;

import com.example.worldsimulationjava.GUI;
import com.example.worldsimulationjava.Organisms.Animals.*;
import com.example.worldsimulationjava.Organisms.Plants.*;
import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Organism implements Comparable<Organism>
{
    protected int initiative;
    protected int ageInTurns;
    protected int strength;
    protected Point2D position;
    protected Point2D lastPosition;
    protected World world = World.Get();

    public enum OrganismType
    {
        GRASS,
        DANDELION,
        GUARANA,
        WOLFBERRIES,
        SOSNOWSKY_BORSCHT,

        WOLF,
        SHEEP,
        FOX,
        TURTLE,
        ANTELOPE,
        CYBER_SHEEP,

        HUMAN
    };

    public static Map<OrganismType, String> OrganismsNames = new HashMap<>()
    {
        {
            put(OrganismType.GRASS, "Grass");
            put(OrganismType.DANDELION, "Dandelion");
            put(OrganismType.GUARANA, "Guarana");
            put(OrganismType.WOLFBERRIES, "Wolfberries");
            put(OrganismType.SOSNOWSKY_BORSCHT, "Sosnowsky's Borscht");

            put(OrganismType.WOLF, "Wolf");
            put(OrganismType.SHEEP, "Sheep");
            put(OrganismType.FOX, "Fox");
            put(OrganismType.TURTLE, "Turtle");
            put(OrganismType.ANTELOPE, "Antelope");
            put(OrganismType.CYBER_SHEEP, "Cyber Sheep");

            put(OrganismType.HUMAN, "Human");
        }
    };
    public static Map<String, OrganismType> OrganismsNames2 = new HashMap<>()
    {
        {
            put("Grass", OrganismType.GRASS );
            put( "Dandelion", OrganismType.DANDELION);
            put( "Guarana", OrganismType.GUARANA);
            put("Wolfberries", OrganismType.WOLFBERRIES );
            put("Sosnowsky's Borscht", OrganismType.SOSNOWSKY_BORSCHT );

            put("Wolf", OrganismType.WOLF );
            put("Sheep", OrganismType.SHEEP );
            put("Fox", OrganismType.FOX );
            put("Turtle", OrganismType.TURTLE );
            put("Antelope", OrganismType.ANTELOPE );
            put("Cyber Sheep", OrganismType.CYBER_SHEEP );

            put("Human", OrganismType.HUMAN );
        }
    };

    public final OrganismType type;
    String name;
    char symbol;
    Color color;

    @Override
    public int compareTo(Organism other)
    {
        int initiativeComparison = Integer.compare(this.initiative, other.initiative);
        if(initiativeComparison == 0)
            return Integer.compare(this.ageInTurns, other.ageInTurns);
        else
            return initiativeComparison;
    }

    protected Organism(Point2D position, String name, char symbol, OrganismType type, Color color)
    {
        this.position = position;
        this.lastPosition = new Point2D(-1,-1);
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.color = color;
    }
    //  TODO:  bool operator>(std::shared_ptr<Organism> other) ;

    public int getInitiative()
    {
        return initiative;
    }
    public void setInitiative(int initiative)
    {
        this.initiative = initiative;
    }
    public int getAgeInTurns()
    {
        return ageInTurns;
    }
    public void setAgeInTurns(int ageInTurns)
    {
        this.ageInTurns = ageInTurns;
    }
    public int getStrength()
    {
        return strength;
    }
    public void setStrength(int strength)
    {
        this.strength = strength;
    }
    public Point2D getPosition()
    {
        return position;
    }
    public void setPosition(Point2D position)
    {
        this.position = position;
    }
    public static Organism generateOrganism(OrganismType type, Point2D position)
    {
        if(!World.isInBounds(position))
            return null;

        switch(type)
        {
            case GRASS:
                return new Grass(position);
            case DANDELION:
                return new Dandelion(position);
            case GUARANA:
                return new Guarana(position);
            case WOLFBERRIES:
                return new Wolfberries(position);
            case SOSNOWSKY_BORSCHT:
                return new SosnowskyBorscht(position);

            case WOLF:
                return new Wolf(position);
            case SHEEP:
                return new Sheep(position);
            case FOX:
                return new Fox(position);
            case TURTLE:
                return new Turtle(position);
            case ANTELOPE:
                return new Antelope(position);
            case HUMAN:
                return new Human(position);
            default:
                return null;
        }
    }
    public void reproduce(OrganismType type)
    {
        Optional<Point2D> freeField = world.getFreeField(world.getOrganism(this));
        if(freeField.isPresent() && World.isInBounds(freeField.get()))
        {
            Organism organism = Organism.generateOrganism(type, freeField.get());
            world.getOrganisms().add(organism);

            String message = organism.name + " has reproduced" + organism.position + '\n';
            GUI.logMessage.append(message);
        }
    }
    public void die(Organism killer)
    {
        GUI.printToBoard(this.lastPosition, ' ', GUI.DEFAULT_BG_COLOR);
        GUI.printToBoard(this.position, ' ', GUI.DEFAULT_BG_COLOR);
        GUI.logMessage.append(killer.name).append(" destroyed ").append(this.name).append('\n');
        int index = world.findOrganismIndexByPosition(this.position);
        world.getOrganisms().remove(index);
    }

    public void incrementLivedTurnsCounter()
    {
        ageInTurns++;
    }

    public void action()
    {
        incrementLivedTurnsCounter();
        world.drawOrganisms();
    }
    abstract public void collision(Organism other);
    public void draw()
    {
//        if(lastPosition != position)
//        {
            if(world.findOrganismIndexByPosition(lastPosition) == -1)
            {
                GUI.printToBoard(lastPosition, ' ', GUI.DEFAULT_BG_COLOR);
            }
            lastPosition = position;
            GUI.printToBoard(position, symbol, color);
//        }
    }

};



