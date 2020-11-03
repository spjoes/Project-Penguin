package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.containers.ContainerSmoothieMachineV2;
import com.spjoes.projectpenguin.gui.GuiSmoothieMachineV2;
import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachineV2;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == Reference.GUI_SMOOTHIE_MACHINE_V2) return new ContainerSmoothieMachineV2(player.inventory, (TileEntitySmoothieMachineV2)world.getTileEntity(new BlockPos(x,y,z)));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == Reference.GUI_SMOOTHIE_MACHINE_V2) return new GuiSmoothieMachineV2(player.inventory, (TileEntitySmoothieMachineV2)world.getTileEntity(new BlockPos(x,y,z)));

        return null;
    }
}
