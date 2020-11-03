package com.spjoes.projectpenguin.util.handlers;

import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachineV2;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntitySmoothieMachineV2.class, "smoothie_machine_v2");
    }

}
