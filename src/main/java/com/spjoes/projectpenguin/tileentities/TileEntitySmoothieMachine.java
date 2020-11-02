package com.spjoes.projectpenguin.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;

public class TileEntitySmoothieMachine extends TileEntity {

    private String name = "Smoothie_Machine";
    private boolean isAlreadyCreatingSmoothie = false;
    private int SmoothieCreationProcessTimer = 0;
    private int SmoothieCreationProcessTimerInSeconds = SmoothieCreationProcessTimer / 12;


    public void startBlending() {
        if (this.isAlreadyCreatingSmoothie == false) {

            if (SmoothieCreationProcessTimer == 0) {

                //If no timer is set, set a timer

            }

            isAlreadyCreatingSmoothie = true;


        } else if (this.isAlreadyCreatingSmoothie == true) {

            //If the machine is already creating a smoothie, dont make another one

            if (SmoothieCreationProcessTimer >= 1) {




            }

        }

    }

    public int getSmoothieCreationProcessTimer () {
        return this.SmoothieCreationProcessTimer;
    }


    public void AlreadyASmoothieBeingMade(EntityPlayer player) {
        player.sendMessage(new TextComponentString("There is already a smoothie being made in this machine. Please try again in" + SmoothieCreationProcessTimerInSeconds + "seconds"));
    }

    public void SmoothieCompletedWait() {

    }
}

