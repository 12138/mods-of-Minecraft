package mapnpc.client.render;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import mapnpc.client.model.ModelNPC;
import mapnpc.common.entity.npc.EntityMapNPCTask;
import mapnpc.common.misc.Resources;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

public class RenderNPC extends RenderBiped {
	private int tick = 0;
	private float sR = 0;
	private int black = 1;
	
//	BufferedImage bi;
	// ModelNPC modelNPC;
	FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;

	public RenderNPC() {
		super(new ModelNPC(0.5F), 0.5F, 1.0F);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);

		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);

		renderMark(par1EntityLiving, par2, par4, par6, par8, par9);
		renderName(par1EntityLiving, par2, par4, par6, par8, par9);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);

	}



	private void renderName(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4 + 2.5F, (float) par6);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glScalef(0.05F, 0.05F, 0.05F);
		fontrenderer
				.drawString(par1EntityLiving.getEntityName(), -fontrenderer
						.getStringWidth(par1EntityLiving.getEntityName()) / 2,
						0, black);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

	}

	@Override
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
		 ItemStack itemstack =null;
		 EntityMapNPCTask entity=(EntityMapNPCTask) par1EntityLiving;
			int itemId=entity.getType();
			if(Item.itemsList[itemId]!=null){
				itemstack=new ItemStack(Item.itemsList[itemId]);
			}
        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        float f2;

        if (itemstack != null)
        {
            GL11.glPushMatrix();

            if (this.mainModel.isChild)
            {
                f2 = 0.5F;
                GL11.glTranslatef(0.0F, 0.625F, 0.0F);
                GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(f2, f2, f2);
            }

            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

            if (itemstack.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
            {
                f2 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f2 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f2, -f2, f2);
            }
            else if (itemstack.itemID == Item.bow.itemID)
            {
                f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack.itemID].isFull3D())
            {
                f2 = 0.625F;

                if (Item.itemsList[itemstack.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.func_82422_c();
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f2 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f2, f2, f2);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, 0);

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++)
                {
                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, x);
                }
            }

            GL11.glPopMatrix();
        }
	}

	private void renderMark(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		
		EntityMapNPCTask entity=(EntityMapNPCTask) par1EntityLiving;
		if(entity.getMarkType()==1){
			this.loadTexture(Resources.NPC_TASK_EMARK_MAIN);
			GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4 + 3, (float) par6);
		tick++;
		if (tick > 5) {
			tick = 0;
			sR += 1;
		}
		if (sR > 360)
			sR = 0;
		GL11.glRotatef(sR, 0, 1, 0);
		Tessellator tessellator = new Tessellator();
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0, -0.5D, -0.5D, 1, 1);
		tessellator.addVertexWithUV(0, 0.5D, -0.5D, 1, 0);
		tessellator.addVertexWithUV(0, 0.5D, 0.5D, 0, 0);
		tessellator.addVertexWithUV(0, -0.5D, 0.5D, 0, 1);
		tessellator.draw();
		GL11.glPopMatrix();
			
		}
		else{
//			this.loadTexture(Resources.NPC_TASK_UNDONE);
		}
		
		

	}

	@Override
	protected void func_98190_a(EntityLiving par1EntityLiving) {
		if (par1EntityLiving instanceof EntityMapNPCTask) {
			EntityMapNPCTask type1=(EntityMapNPCTask) par1EntityLiving;
			  this.loadTexture((Resources.TEXTURE_TYPE1+type1.getFile()+".png"));
		}

	}


	

}
