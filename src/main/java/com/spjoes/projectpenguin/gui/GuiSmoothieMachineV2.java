package com.spjoes.projectpenguin.gui;

import com.spjoes.projectpenguin.containers.ContainerSmoothieMachineV2;
import com.spjoes.projectpenguin.tileentities.TileEntitySmoothieMachineV2;
import com.spjoes.projectpenguin.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSmoothieMachineV2 extends GuiContainer
{

    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/smoothie_machine_v2.png");
    public final InventoryPlayer player;
    public final TileEntitySmoothieMachineV2 tileentity;

    public GuiSmoothieMachineV2(InventoryPlayer player, TileEntitySmoothieMachineV2 tileentity) {
        super(new ContainerSmoothieMachineV2(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;


    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tileName = this.tileentity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntitySmoothieMachineV2.isBurning(tileentity)) {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 37 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, l + 1, 16);

    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileentity.getField(1);
        if(i == 0) i = 200;
        return this.tileentity.getField(0) * pixels / i;
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileentity.getField(2);
        int j = this.tileentity.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

}
