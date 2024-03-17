package com.example.worldsimulationjava;

import java.util.Random;

public class Utilities
{
    public static boolean isProbable(double probability)
    {
        if(probability > 1 || probability < 0)
            return false;
        return range(1,100) < probability*100;
    }

    public static int range(int min, int max)
    {
        if (min > max)
            return 0;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;

    }
}
