package com.spjoes.projectpenguin.util.handlers;


import com.spjoes.projectpenguin.Main;
import com.spjoes.projectpenguin.init.*;
import com.spjoes.projectpenguin.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler {


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {

        RenderHandler.registerEntityRenders();

        for(Item item : ItemInit.ITEMS){
            if(item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }

    public static void preInitRegistries() {

        EntityInit.registerEntities();
        TileEntitiesInit.registerTileEntities();

    }


    public static void initRegistries() {
        SoundsHandler.registerSounds();
        SmeltingInit.init();
        TradesInit.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
    }


    public static void postInitRegistries() {

    }
}
