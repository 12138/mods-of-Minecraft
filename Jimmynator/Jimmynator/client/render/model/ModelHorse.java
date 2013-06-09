
package Jimmynator.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHorse extends ModelBase
{
  //fields
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
    textureWidth = 128;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 56, 18);
      head.addBox(-2F, -12F, -8F, 4, 4, 10);
      head.setRotationPoint(0F, 7F, -6F);
      head.setTextureSize(128, 32);
      head.mirror = true;
      setRotation(head, 0.0070062F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-4F, -10F, -7F, 8, 8, 19);
      body.setRotationPoint(0F, 15F, -2F);
      body.setTextureSize(128, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 0);
      leg1.addBox(-2F, 0F, 0F, 3, 11, 3);
      leg1.setRotationPoint(-2F, 13F, 7F);
      leg1.setTextureSize(128, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 0);
      leg2.addBox(-1F, 0F, 0F, 3, 11, 3);
      leg2.setRotationPoint(2F, 13F, 7F);
      leg2.setTextureSize(128, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 0, 0);
      leg3.addBox(-2F, 0F, -3F, 3, 11, 3);
      leg3.setRotationPoint(-2F, 13F, -5F);
      leg3.setTextureSize(128, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 0);
      leg4.addBox(-1F, 0F, -3F, 3, 11, 3);
      leg4.setRotationPoint(2F, 13F, -5F);
      leg4.setTextureSize(128, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 36, 3);
      neck.addBox(-2F, -8F, -3F, 4, 10, 5);
      neck.setRotationPoint(0F, 7F, -6F);
      neck.setTextureSize(128, 32);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      ear1 = new ModelRenderer(this, 77, 0);
      ear1.addBox(1F, -13F, 0F, 1, 1, 1);
      ear1.setRotationPoint(0F, 7F, -6F);
      ear1.setTextureSize(128, 32);
      ear1.mirror = true;
      setRotation(ear1, 0F, 0F, 0F);
      ear2 = new ModelRenderer(this, 77, 0);
      ear2.addBox(-2F, -13F, 0F, 1, 1, 1);
      ear2.setRotationPoint(0F, 7F, -6F);
      ear2.setTextureSize(128, 32);
      ear2.mirror = true;
      setRotation(ear2, 0.0070062F, 0F, 0F);
      tail = new ModelRenderer(this, 54, 1);
      tail.addBox(-1F, 0F, 0F, 2, 10, 2);
      tail.setRotationPoint(0F, 6F, 9F);
      tail.setTextureSize(128, 32);
      tail.mirror = true;
      setRotation(tail, 0.0070062F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
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
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

}
