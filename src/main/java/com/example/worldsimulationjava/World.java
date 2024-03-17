package com.example.worldsimulationjava;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class World
{

    public enum Field
    {
        UPPER_FIELD,
        BOTTOM_FIELD,
        LEFT_FIELD,
        RIGHT_FIELD,
        RIGHT_UPPER,
        LEFT_UPPER,
        RIGHT_BOTTOM,
        LEFT_BOTTOM

    }

    public int turnCounter = 0;
    private ArrayList<Organism> organisms = new ArrayList<>(); 

    private World(){}
    private static World world;
    public static World Get()
    {
       if(world == null)
           world = new World();
        return world;
    }
    public void restart(int width, int height)
    {
        turnCounter = 0;
        organisms.clear();
        GUI.logMessage.setLength(0);
        Board.setHeight(height);
        Board.setWidth(width);
        Board.worldMap = new Label[Board.getWidth()][Board.getHeight()];
        GUI.render();
        drawOrganisms();
    }

    public void start()
    {
        organisms.add(Organism.generateOrganism(Organism.OrganismType.HUMAN, new Point2D(5,5)));
//        organisms.add(Organism.generateOrganism(Organism.OrganismType.SHEEP, new Point2D(0,0)));
//        organisms.add(Organism.generateOrganism(Organism.OrganismType.GRASS, new Point2D(1,0)));
//        organisms.add(Organism.generateOrganism(Organism.OrganismType.GRASS, new Point2D(0,1)));
//        organisms.add(Organism.generateOrganism(Organism.OrganismType.GRASS, new Point2D(2,0)));
//        organisms.add(Organism.generateOrganism(Organism.OrganismType.GRASS, new Point2D(1,1)));
        organisms.add(Organism.generateOrganism(Organism.OrganismType.GRASS, new Point2D(4,5)));
        drawOrganisms();
    }
    class MyInteger {
        public int a;
        MyInteger(int a) {this.a = a;}
    }
    public void doTurn()
    {
        System.out.println("TURN: " + turnCounter);

        int organismsCount = organisms.size();
        for(int i = 0; i < organismsCount; i++)
        {
            Organism organism = organisms.get(i);
            organism.action();

            MyInteger I = new MyInteger(i);
            MyInteger oC = new MyInteger(organismsCount);
            manipulateIteratorOnRemove(oC, I, organism);
            organismsCount = oC.a;
            i = I.a;
        }
        Collections.sort(organisms, Collections.reverseOrder());
        turnCounter++;
        GUI.printLogger(turnCounter);
        drawOrganisms();
    }

    public void drawOrganisms()
    {
        for(Organism organism : organisms)
            organism.draw();
    }

    void manipulateIteratorOnRemove(MyInteger organismsCount, MyInteger I, Organism organism)
    {
        // function sorts out deleting elements
        boolean expiredCheck = false;
        while(organisms.size() < organismsCount.a)
        {
            if(findOrganismIndexByPosition(organism.getPosition()) == -1 && !expiredCheck)
            {
                I.a--;
                organismsCount.a--;
                expiredCheck = true;
            }
            else if(!expiredCheck)
            {
                if(findOrganismIndexByPosition(organism.getPosition()) < I.a)
                {
                    I.a--;
                }
                organismsCount.a--;
            }
            else
            {
                organismsCount.a--;
            }
        }
    }


    public static Point2D getOffset(Field field)
    {
        Point2D offset;
        switch (field)
        {
            case UPPER_FIELD:
                offset = new Point2D(0, -1);
                break;
            case BOTTOM_FIELD:
                offset = new Point2D(0, 1);
                break;
            case LEFT_FIELD:
                offset = new Point2D(-1, 0);
                break;
            case RIGHT_FIELD:
                offset = new Point2D(1, 0);
                break;
            case RIGHT_UPPER:
                offset = new Point2D(1, 1);
                break;
            case LEFT_UPPER:
                offset = new Point2D(-1, 1);
                break;
            case RIGHT_BOTTOM:
                offset = new Point2D(1, -1);
                break;
            case LEFT_BOTTOM:
                offset = new Point2D(-1, -1);
                break;
            default:
                offset = new Point2D(0, 0);
                break;
        }
        return offset;
    }

    public static boolean isInBounds(Point2D position)
    {
        return  position.getX() >= 0 &&
                position.getX() < Board.getWidth() &&
                position.getY() >= 0 &&
                position.getY() < Board.getHeight();
    }

    public Optional<Point2D> getFreeField(Organism organism)
    {
        if(!isFieldTaken(organism, Field.UPPER_FIELD))
            return Optional.ofNullable(getOffsettedField(organism, Field.UPPER_FIELD));
        if(!isFieldTaken(organism, Field.RIGHT_FIELD))
            return Optional.ofNullable(getOffsettedField(organism, Field.RIGHT_FIELD));
        if(!isFieldTaken(organism, Field.BOTTOM_FIELD))
            return Optional.ofNullable(getOffsettedField(organism, Field.BOTTOM_FIELD));
        if(!isFieldTaken(organism, Field.LEFT_FIELD))
            return Optional.ofNullable(getOffsettedField(organism, Field.LEFT_FIELD));
        return Optional.empty();
    }

    public boolean isFieldTaken(Organism organism, Field field)
    {
        Point2D fieldToCheck = getOffsettedField(organism, field);
        return isFieldTaken(fieldToCheck);
    }

    public boolean isFieldTaken(Point2D field)
    {
        for(var it : organisms)
            if(it.getPosition().equals(field))
                return true;
        return false;
    }

    public static Point2D getOffsettedField(Organism organism, Field field)
    {
        return organism.getPosition().add(getOffset(field));
    }

    public Organism getOrganism(Organism organism)
    {
        return findOrganismByPosition(organism.getPosition());
    }

    public Organism findOrganismByPosition(Point2D position)
    {
        return organisms.get(findOrganismIndexByPosition(position));
    }

    public int findOrganismIndexByPosition(Point2D position)
    {
        for(int i = 0; i < organisms.size(); i++)
        {
            if(organisms.get(i).getPosition().equals(position))
            {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Organism> getOrganisms()
    {
        return organisms;
    }

}
