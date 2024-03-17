package com.example.worldsimulationjava.Organisms.Animals;

import com.example.worldsimulationjava.GUI;

public class UltHandler
{
    boolean isUltActive = false;
    int cooldown = 10;

    static final int COOLDOWN_END_TOURS = 0;
    static final int ULT_END_TOURS = 5;
    static final int ULT_CYCLE_PERIOD = 10;

    boolean isActive()
    {
        if (cooldown == COOLDOWN_END_TOURS)
        {
            cooldown = ULT_CYCLE_PERIOD;
            GUI.logMessage.append("Ult renewed!\n");
        }
        else if (cooldown == ULT_END_TOURS)
        {
            isUltActive = false;
            GUI.logMessage.append("Ult deactivated!\n");
        }
        if (cooldown != ULT_CYCLE_PERIOD || isUltActive)
        {
            cooldown--;
        }
        return isUltActive;
    }

    public void activate()
    {
        if (cooldown == ULT_CYCLE_PERIOD)
        {
            isUltActive = true;
            GUI.logMessage.append("Ult activate!\n");
        }
    }


}
