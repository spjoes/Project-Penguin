package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachine;
import net.minecraft.tileentity.TileEntity;

public class TileEntitiesInit {


    public static void registerTileEntities() {
        TileEntity.register("monitor", TileEntitySmoothieMachine.class);
    }


}
