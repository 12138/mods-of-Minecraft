package net.hendyzone.lightstick.client;

import net.hendyzone.lightstick.common.CommonProxy;
import net.hendyzone.lightstick.common.blocks.TileEntityBonefire;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderBonefire extends TileEntitySpecialRenderer {

    private ModelBonefire modelBonefire = new ModelBonefire();

    public void renderTileEntityBonefireAt(TileEntityBonefire par1TileEntityBonefire , double par2, double par4, double par6, float par8)
    {
    	  this.bindTexture(CommonProxy.MODEL_BONEFIRE);
       
    	GL11.glEnable(GL11.GL_CULL_FACE);
    	  
    	GL11.glPushMatrix();
    	  GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 2.4F, (float)par6 + 0.5F);
    	  GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    	
    	  domodel(par1TileEntityBonefire.getBlockMetadata());
    
       
        
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
/*
 * 0 2 4 6 8 10 nl
 * 1 3 5 7 9 11 l
 * 8 7 6 5 4 3  num
 
 * */
    private void domodel(int blockMetadata) {
    	switch(blockMetadata){
    		case 0:
    		case 1:
    			this.modelBonefire.render8(0.1F);
    		break;
    		case 2:
    		case 3:
    			this.modelBonefire.render7(0.1F);
    		break;
    		case 4:
    		case 5:
    			this.modelBonefire.render6(0.1F);
    		break;
    		case 6:
    		case 7:
    			this.modelBonefire.render5(0.1F);
    		break;
    		case 8:
    		case 9:
    			this.modelBonefire.render4(0.1F);
    		break;
    		case 10:
    		case 11:
    			this.modelBonefire.render3(0.1F);
    		break;
    		
    		
    		
    	}
	}

	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBonefireAt((TileEntityBonefire )par1TileEntity, par2, par4, par6, par8);
    }

}
