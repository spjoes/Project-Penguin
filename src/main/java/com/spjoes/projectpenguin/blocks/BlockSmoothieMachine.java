package com.spjoes.projectpenguin.blocks;

import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.*;
import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachine;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.*;

import java.util.Random;

public class BlockSmoothieMachine extends Block implements IHasModel
{

    public static String getSmoothieID;
    String smoothieID;

    public BlockSmoothieMachine(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    public Item getItemDropped(IBlockState state, Random rand, int fortune, EntityPlayer playerIn) {
        return Item.getItemFromBlock(BlockInit.SMOOTHIE_MACHINE);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // TileEntity te = worldIn.getTileEntity(pos);


        if (!worldIn.isRemote) {

            if (playerIn.inventory.getCurrentItem() != null) {

                if (playerIn.inventory.getCurrentItem().getItem() == ItemInit.STRAWBERRY) {


                    playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);

//                    if (te instanceof TileEntitySmoothieMachine) {
//                        ((TileEntitySmoothieMachine) te).startBlending();
//                    }

                    playerIn.sendMessage(new TextComponentString("Starting Strawberry Smoothie Create Process .Debug"));


                    smoothieID = "strawberry";
                    SmoothieCreationComplete(playerIn);
                }


                if (playerIn.inventory.getCurrentItem().getItem() == Items.APPLE) {


                    playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);


                    playerIn.sendMessage(new TextComponentString("Starting Apple Smoothie Create Process .Debug"));

                    smoothieID = "apple";
                    SmoothieCreationComplete(playerIn);

                }


                if (playerIn.inventory.getCurrentItem().getItem() == Items.COOKIE) {

                    playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);


                    playerIn.sendMessage(new TextComponentString("Starting Chocolate Smoothie Create Process .Debug"));

                    smoothieID = "chocolate";
                    SmoothieCreationComplete(playerIn);


                }
            }

            if (playerIn.inventory.getCurrentItem().getItem() == Items.MELON) {

                    playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);


                    playerIn.sendMessage(new TextComponentString("Starting Watermelon Smoothie Create Process .Debug"));

                    smoothieID = "watermelon";
                    SmoothieCreationComplete(playerIn);

            }

        }
        return true;
    }


    public void SmoothieCreationComplete(EntityPlayer playerIn) {

        if(smoothieID == "strawberry") {
            ItemStack stackstack = new ItemStack(ItemInit.STRAWBERRY_SMOOTHIE);
            playerIn.inventory.addItemStackToInventory(stackstack);
        }

        if(smoothieID == "apple") {
            ItemStack stackstack = new ItemStack(ItemInit.APPLE_SMOOTHIE);
            playerIn.inventory.addItemStackToInventory(stackstack);
        }

        if(smoothieID == "watermelon") {
            ItemStack stackstack = new ItemStack(ItemInit.WATERMELON_SMOOTHIE);
            playerIn.inventory.addItemStackToInventory(stackstack);
        }

        if(smoothieID == "chocolate") {
            ItemStack stackstack = new ItemStack(ItemInit.CHOCOLATE_SMOOTHIE);
            playerIn.inventory.addItemStackToInventory(stackstack);
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

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }


    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySmoothieMachine();
    }
}