package net.hendyzone.lightstick.client;

import net.hendyzone.lightstick.common.CommonProxy;
import net.hendyzone.lightstick.common.blocks.ContainerBonefire;
import net.hendyzone.lightstick.common.blocks.TileEntityBonefire;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiBonefire extends GuiContainer {

	private TileEntityBonefire bonefireInventory;

    public GuiBonefire(InventoryPlayer par1InventoryPlayer, TileEntityBonefire par2TileEntityBonefire)
    {
        super(new ContainerBonefire(par1InventoryPlayer, par2TileEntityBonefire));
        this.bonefireInventory = par2TileEntityBonefire;
    }


    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.bonefireInventory.getInvName();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }


    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.GUI_BONEFIRE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize-1, this.ySize);
        int i1;

        if (this.bonefireInventory.isBurning())
        {
            i1 = this.bonefireInventory.getBurnTimeRemainingScaled();
            this.drawTexturedModalRect(k + 105, l +51+14-i1, 175, 14 - i1, 14, i1);
        }
        i1 = this.bonefireInventory.getCookProgressScaled();
        this.drawTexturedModalRect(k + 100, l + 30, 175, 14, i1 + 1, 17);
    }
}
