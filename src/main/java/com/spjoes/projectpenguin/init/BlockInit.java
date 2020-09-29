package com.spjoes.projectpenguin.init;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block PENGUIN_EGG = new BlockBase("penguin_egg", Material.WOOD, Main.PPTAB);

}