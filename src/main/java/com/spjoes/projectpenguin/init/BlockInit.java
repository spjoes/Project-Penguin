package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block PENGUIN_EGG = new BlockBase("penguin_egg", Material.WOOD, Main.PPTAB);
    // public static final Block INCUBATOR_EMPTY = new BlockIncubator("incubator_empty", Material.WOOD, Main.PPTAB);

}