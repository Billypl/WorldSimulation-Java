package com.example.worldsimulationjava;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;

public class Board
{
    private static int width = 10;
    private static int height = 10;

    public static void setWidth(int width)
    {
        Board.width = width;
    }
    public static void setHeight(int height)
    {
        Board.height = height;
    }
    public static int getWidth()
    {
        return width;
    }
    public static int getHeight()
    {
        return height;
    }

    public static Label[][] worldMap = new Label[width][height];

    static public Label getField(Point2D Point2D)
    {
        return worldMap[(int)Point2D.getX()][(int)Point2D.getY()];
    }
    static public void setFieldValue(int x, int y, String value)
    {
        worldMap[x][y].setText(value);
    }
    static public void setFieldValue(int x, int y, char value)
    {
        setFieldValue(x, y, String.valueOf(value));
    }
    static public void setFieldValue(Point2D Point2D, char value)
    {
        setFieldValue(Point2D, String.valueOf(value));
    }
    static public void setFieldValue(Point2D Point2D, String value)
    {
        getField(Point2D).setText(value);
    }

}
