package com.spjoes.projectpenguin;

import com.spjoes.projectpenguin.creativetabs.PPTab;
import com.spjoes.projectpenguin.proxy.CommonProxy;
import com.spjoes.projectpenguin.util.Reference;
import com.spjoes.projectpenguin.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions=Reference.MCVER, updateJSON="https://github.com/spjoes/Project-Penguin/raw/master/update.json")
public class Main{

    @Instance
    public static Main instance;

    public static final CreativeTabs PPTAB = new PPTab();

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;



    @EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        RegistryHandler.preInitRegistries();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event){
        RegistryHandler.initRegistries();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event){

    }
}
