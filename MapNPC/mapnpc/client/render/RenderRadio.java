package mapnpc.client.render;

import mapnpc.client.model.ModelBlockRadio;
import mapnpc.common.misc.Resources;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderRadio extends TileEntitySpecialRenderer {
	 private ModelBlockRadio modelBlockRadio = new ModelBlockRadio();
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		this.bindTextureByName(Resources.TEXTURE_RADIO);
		GL11.glEnable(GL11.GL_CULL_FACE);
  	  
    	GL11.glPushMatrix();
    	  GL11.glTranslatef((float)d0 + 0.5F, (float)d1+1.2F, (float)d2 + 0.5F);
    	  GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    	
        ((ModelBlockRadio) this.modelBlockRadio).renderThis(0.05F);
       
        
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
	}

}
