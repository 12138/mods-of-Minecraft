package SoilMaker.common;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;



public class GuiSoilMaker extends GuiContainer{
	private TileEntitySoilMaker tile;

	public GuiSoilMaker(InventoryPlayer inventoryPlayer,TileEntitySoilMaker tileEntity) {
		super(new ContainerSoilMaker(inventoryPlayer,tileEntity));
		this.tile = tileEntity;
        this.doesGuiPauseGame();
    
	}
	 @Override
     protected void drawGuiContainerForegroundLayer(int par1, int par2) {
             // TODO Auto-generated method stub
             

		// super.drawGuiContainerForegroundLayer(par1, par2);
     this.fontRenderer.drawString(StatCollector.translateToLocal("垃圾焚烧炉"), 60, 6, 4210752);
     this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
     }
	 
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
            int var3) {
		 int var4 = this.mc.renderEngine.getTexture("/SoilMaker/gui/SoilMaker.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.renderEngine.bindTexture(var4);
	        int var5 = (this.width - this.xSize) / 2;
	        int var6 = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	        int var7;
	        if (this.tile.isWork())
	        {System.out.println("ran");
	            var7 = this.tile.getRemainingScaled();
	            this.drawTexturedModalRect(var5 + 9, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
	        }
	        var7 = this.tile.getCookProgressScaled();
	        this.drawTexturedModalRect(var5 + 89, var6 + 34, 176, 14, var7 + 1, 16);
	        
    
   
}
	

}
