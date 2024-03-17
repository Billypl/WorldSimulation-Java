package com.example.worldsimulationjava.Organisms;

import com.example.worldsimulationjava.Board;
import com.example.worldsimulationjava.GUI;
import com.example.worldsimulationjava.World;
import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler
{
    private static String filePath = "save.txt";
    public static void saveGame()
    {
        StringBuilder str = new StringBuilder();
        str.append(Board.getWidth()).append(' ').append(Board.getHeight()).append('\n');
        str.append(World.Get().getOrganisms().size()).append('\n');
        for(Organism organism : World.Get().getOrganisms())
        {
            str.append(organism.name).append('\n')
                    .append((int)organism.position.getX())
                    .append(" ")
                    .append((int)organism.position.getY())
                    .append('\n');
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(str.toString());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static void loadGame()
    {

        File file = new File(filePath);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        World.Get().restart(width, height);


        int organismsCount = scanner.nextInt();
        System.out.println(width + " " + height + " " + organismsCount);
        String name = scanner.nextLine();

        for(int i = 0; i < organismsCount; i++)
        {
            name = scanner.nextLine();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Organism.OrganismType type = Organism.OrganismsNames2.get(name);
            World.Get().getOrganisms().
                    add(Organism.generateOrganism(type, new Point2D(x,y)));

            name = scanner.nextLine();
        }
        World.Get().drawOrganisms();
        GUI.printLogger(1);

    }
}
