package com.spjoes.projectpenguin.blocks;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.*;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

import java.util.Random;

public class BlockIncubator extends Block implements IHasModel
{

boolean eggAlreadyInPlace;

    public BlockIncubator(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
//    {
//        return new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 1.5D, 0.6D);
//    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn.inventory.getCurrentItem() != null)
        {


            if(Block.getBlockFromItem(playerIn.inventory.getCurrentItem().getItem()) == BlockInit.PENGUIN_EGG)
            {

                    if (eggAlreadyInPlace == false) {

                        playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);
                        eggAlreadyInPlace = true;
                        playerIn.sendMessage(new TextComponentString("Penguin Egg Successfully Placed In Incubator"));

                    } else if (eggAlreadyInPlace == true) {

                        playerIn.sendMessage(new TextComponentString("Only One Egg Can Be Incubated At A Time"));

                }
            }
        }

        return true;
    }



    public Item getItemDropped(IBlockState state, Random rand, int fortune, EntityPlayer playerIn) {


        if (eggAlreadyInPlace == true) {
            eggAlreadyInPlace = false;
            return Item.getItemFromBlock(BlockInit.PENGUIN_EGG);

        } else {
            eggAlreadyInPlace = false;
            return Item.getItemFromBlock(BlockInit.PENGUIN_EGG);
        }

    }

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