package com.spjoes.projectpenguin.blocks;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.BlockInit;
import com.spjoes.projectpenguin.init.ItemInit;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockBase extends Block implements IHasModel
{

    int egg;
    int half;

    public BlockBase(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.25D, 0.6D);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        egg = (Math.random() <= 0.5) ? 1 : 2;
        half = (Math.random() <= 0.5) ? 1 : 2;

        if (egg == 1) {
            return Item.getItemFromBlock(this);
    } else if (egg == 2) {

            if (half == 1) {
                return ItemInit.HALF_PENGUIN_EGG_TOP;

            } else if (half == 2) {
                return ItemInit.HALF_PENGUIN_EGG_BOTTOM;
            }
        }

        return null;
    }

//    public int quantityDropped(IBlockState state, int fortune, Random random) {
//        return random.nextInt(1) + 1;               // <--- This will drop 1 to 2 items, and the +1 is so its not from 0 to 2.
//    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

}