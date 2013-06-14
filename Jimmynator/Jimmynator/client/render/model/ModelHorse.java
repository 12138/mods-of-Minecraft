
package Jimmynator.client.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelHorse extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer neck;
    ModelRenderer ear1;
    ModelRenderer ear2;
    ModelRenderer tail;
  
  public ModelHorse()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 32, 18);
      head.addBox(-2F, -12F, -8F, 4, 4, 10);
      head.setRotationPoint(0F, 7F, -6F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0.4014257F, 0.0F, 0.0F);
      body = new ModelRenderer(this, 0, 5);
      body.addBox(-4F, -10F, -7F, 8, 19, 8);
      body.setRotationPoint(0F, 6F, 2F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 1.570796F, 0.0F, 0.0F);
      leg1 = new ModelRenderer(this, 32, 4);
      leg1.addBox(-2F, 0F, 0F, 3, 11, 3);
      leg1.setRotationPoint(-2F, 13F, 7F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 32, 4);
      leg2.addBox(-1F, 0F, 0F, 3, 11, 3);
      leg2.setRotationPoint(2F, 13F, 7F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 32, 4);
      leg3.addBox(-2F, 0F, -3F, 3, 11, 3);
      leg3.setRotationPoint(-2F, 13F, -5F);
      leg3.setTextureSize(64, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 32, 4);
      leg4.addBox(-1F, 0F, -3F, 3, 11, 3);
      leg4.setRotationPoint(2F, 13F, -5F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 44, 3);
      neck.addBox(-2F, -8F, -3F, 4, 10, 5);
      neck.setRotationPoint(0F, 7F, -6F);
      neck.setTextureSize(64, 32);
      neck.mirror = true;
      setRotation(neck, 0.4014257F, 0.0F, 0.0F);
      ear1 = new ModelRenderer(this, 60, 30);
      ear1.addBox(1F, -13F, 0F, 1, 1, 1);
      ear1.setRotationPoint(0F, 7F, -6F);
      ear1.setTextureSize(64, 32);
      ear1.mirror = true;
      setRotation(ear1, 0.4014257F, 0.0F, 0.0F);
      ear2 = new ModelRenderer(this, 60, 30);
      ear2.addBox(-2F, -13F, 0F, 1, 1, 1);
      ear2.setRotationPoint(0F, 7F, -6F);
      ear2.setTextureSize(64, 32);
      ear2.mirror = true;
      setRotation(ear2,0.4014257F, 0.0F, 0.0F);
      tail = new ModelRenderer(this, 0, 1);
      tail.addBox(-1F, 0F, 0F, 2, 10, 2);
      tail.setRotationPoint(0F, 6F, 9F);
      tail.setTextureSize(64, 32);
      tail.mirror = true;
      setRotation(tail, 0.4014257F, 0.0F, 0.0F);
  }
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	    this.setRotationAngles(f, f1, f2, f3, f4, f5,entity);

//      if (this.isChild)
//      {
//          float f6 = 2.0F;
//          GL11.glPushMatrix();
//          GL11.glTranslatef(0.0F, this.field_78145_g * par7, this.field_78151_h * par7);
//          this.head.render(par7);
//          GL11.glPopMatrix();
//          GL11.glPushMatrix();
//          GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
//          GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
//          this.body.render(par7);
//          this.leg1.render(par7);
//          this.leg2.render(par7);
//          this.leg3.render(par7);
//          this.leg4.render(par7);
//          GL11.glPopMatrix();
//      }
//      else
//      {
    	  head.render(f5);
    	    body.render(f5);
    	    leg1.render(f5);
    	    leg2.render(f5);
    	    leg3.render(f5);
    	    leg4.render(f5);
    	    neck.render(f5);
    	    ear1.render(f5);
    	    ear2.render(f5);
    	    tail.render(f5);
//      }
//    super.render(entity, f, f1, f2, f3, f4, f5);

   
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
	        this.body.rotateAngleX = ((float)Math.PI / 2F);
	        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	    
  }


}
