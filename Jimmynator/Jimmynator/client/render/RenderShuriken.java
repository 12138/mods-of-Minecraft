package Jimmynator.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Jimmynator.common.entity.EntityStaffBall;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;

public class RenderShuriken extends Render {
	
	Icon shuriken;

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		renderball(entity,d0,d1,d2,f,f1);
		}

		@Override
		public void updateIcons(IconRegister par1IconRegister) {
			this.shuriken=par1IconRegister.registerIcon("Jimmynator:shuriken");
			
			
		}

		private void renderball(Entity entity, double d0, double d1, double d2,
				float f, float f1) {		   
		
		   GL11.glPushMatrix();
		   this.loadTexture("/mods/Jimmynator/textures/items/shuriken.png");
		   Tessellator tessellator=Tessellator.instance;
		   GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		   GL11.glTranslatef((float)d0, (float)d1, (float)d2);
		   GL11.glScalef(0.5F, 0.5F, 0.5F);
//		   GL11.glColor3f(1.0F, 1.0F, 1.0F);
	       this.rendering(tessellator,shuriken);
		   GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		   GL11.glPopMatrix();
		   
		    }

	


	private void rendering( Tessellator tessellator,Icon icon)
	{
		float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double)(0.0F - f5), (double)(0.0F - f6), 0.0D, (double)f, (double)f3);
        tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0F - f6), 0.0D, (double)f1, (double)f3);
        tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0D, (double)f1, (double)f2);
        tessellator.addVertexWithUV((double)(0.0F - f5), (double)(f4 - f6), 0.0D, (double)f, (double)f2);
        
        tessellator.draw();
	}
}
