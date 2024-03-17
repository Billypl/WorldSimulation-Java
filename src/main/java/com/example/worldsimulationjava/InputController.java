package com.example.worldsimulationjava;

import com.example.worldsimulationjava.Organisms.Animals.Human;
import com.example.worldsimulationjava.Organisms.FileHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class InputController
{
    public static Point2D DIRECTION = new Point2D(-1,0);
    public void setControls(Scene scene)
    {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode())
            {
                case UP:
                    DIRECTION = World.getOffset(World.Field.UPPER_FIELD);
                    break;
                case DOWN:
                    DIRECTION = World.getOffset(World.Field.BOTTOM_FIELD);
                    break;
                case LEFT:
                    DIRECTION = World.getOffset(World.Field.LEFT_FIELD);
                    break;
                case RIGHT:
                    DIRECTION = World.getOffset(World.Field.RIGHT_FIELD);
                    break;
                case SPACE:
                    World.Get().doTurn();
                    break;
                case ESCAPE:
                    System.exit(2137);
                    break;
                case U:
                    Human.ultimate.activate();
                    break;
                case S:
                    FileHandler.saveGame();
                    System.out.println("SAVE");
                    break;
                case L:
                    FileHandler.loadGame();
                    System.out.println("LOAD");
                    break;
            }
        });
    }
}