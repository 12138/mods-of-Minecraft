package Jimmynator.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import Jimmynator.client.render.model.ModelOrb;
import Jimmynator.common.block.tileentity.TileEntityOrb;

public class RenderTileEntityOrb extends TileEntitySpecialRenderer {
	  private ModelOrb model=new ModelOrb();
	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityOrb((TileEntityOrb)par1TileEntity, par2, par4, par6, par8);
    }

	private void renderTileEntityOrb(TileEntityOrb par1TileEntity, double par2,
			double par4, double par6, float par8) {
		GL11.glPushMatrix();
		  this.bindTextureByName("/mods/Jimmynator/textures/tileentity/orb.png");
    	  GL11.glTranslatef((float)par2 + 0.5F, (float)par4+0.25F, (float)par6 + 0.5F);
    	  GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    	
		switch (par1TileEntity.getBlockMetadata()) {
			case 0:
				model.renderBody();
				break;
			case 1:
				model.renderBody();
				model.renderball();
				break;
			case 2:
				model.renderBody();
				model.renderfireball();
				break;
			case 3:
				model.renderBody();
				model.rendericeball();
				break;
			case 4:
				model.renderBody();
				model.renderlifeball();
				break;
			case 5:
				model.renderBody();
				model.renderdeathball();
				break;
			default:
				break;
		}
		
		GL11.glPopMatrix();
	}
	
}
