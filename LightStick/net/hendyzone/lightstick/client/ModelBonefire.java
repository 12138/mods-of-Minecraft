package net.hendyzone.lightstick.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBonefire extends ModelBase
{
  //fields
    ModelRenderer stick1;
    ModelRenderer stick2;
    ModelRenderer stick3;
    ModelRenderer stick4;
    ModelRenderer stick5;
    ModelRenderer stick6;
    ModelRenderer stick7;
    ModelRenderer stick8;
  
  public ModelBonefire()
  {
    textureWidth = 16;
    textureHeight = 16;
    
      stick1 = new ModelRenderer(this, 0, 0);
      stick1.addBox(0F, 0F, 0F, 1, 6, 1);
      stick1.setRotationPoint(0F, 20F, 1F);
      stick1.setTextureSize(16, 16);
      stick1.mirror = true;
      setRotation(stick1, 0.8421988F, 0F, 0F);
      stick2 = new ModelRenderer(this, 0, 0);
      stick2.addBox(0F, 0F, 0F, 1, 6, 1);
      stick2.setRotationPoint(1F, 19F, -2F);
      stick2.setTextureSize(16, 16);
      stick2.mirror = true;
      setRotation(stick2, -0.8980494F, -0.8922867F, 0F);
      stick3 = new ModelRenderer(this, 0, 0);
      stick3.addBox(0F, 0F, 0F, 1, 6, 1);
      stick3.setRotationPoint(-1F, 19F, 0F);
      stick3.setTextureSize(16, 16);
      stick3.mirror = true;
      setRotation(stick3, -0.8980494F, 0.8551081F, 0F);
      stick4 = new ModelRenderer(this, 0, 0);
      stick4.addBox(0F, 0F, 0F, 1, 6, 1);
      stick4.setRotationPoint(0F, 19F, -2F);
      stick4.setTextureSize(16, 16);
      stick4.mirror = true;
      setRotation(stick4, -0.7807508F, 0F, 0F);
      stick5 = new ModelRenderer(this, 0, 0);
      stick5.addBox(0F, 0F, 0F, 1, 6, 1);
      stick5.setRotationPoint(1F, 20F, 1F);
      stick5.setTextureSize(16, 16);
      stick5.mirror = true;
      setRotation(stick5, 0.8551081F, 0.9666439F, 0F);
      stick6 = new ModelRenderer(this, 0, 0);
      stick6.addBox(0F, 0F, 0F, 1, 6, 1);
      stick6.setRotationPoint(-1F, 19F, 1F);
      stick6.setTextureSize(16, 16);
      stick6.mirror = true;
      setRotation(stick6, -0.8980494F, 2.082002F, 0F);
      stick7 = new ModelRenderer(this, 0, 0);
      stick7.addBox(0F, 0F, 0F, 1, 6, 1);
      stick7.setRotationPoint(-1F, 19F, 2F);
      stick7.setTextureSize(16, 16);
      stick7.mirror = true;
      setRotation(stick7, -0.8980494F, 2.602503F, 0F);
      stick8 = new ModelRenderer(this, 0, 0);
      stick8.addBox(0F, 0F, 0F, 1, 6, 1);
      stick8.setRotationPoint(2F, 20F, -1F);
      stick8.setTextureSize(16, 16);
      stick8.mirror = true;
      setRotation(stick8, 0F, 0F, -0.7063936F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    stick1.render(f5);
    stick2.render(f5);
    stick3.render(f5);
    stick4.render(f5);
    stick5.render(f5);
    stick6.render(f5);
    stick7.render(f5);
    stick8.render(f5);
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
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

public void render3(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);

}
public void render4(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);
	    stick4.render(f5);

}
public void render5(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);
	    stick4.render(f5);
	    stick5.render(f5);

}
public void render6(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);
	    stick4.render(f5);
	    stick5.render(f5);
	    stick6.render(f5);

}
public void render7(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);
	    stick4.render(f5);
	    stick5.render(f5);
	    stick6.render(f5);
	    stick7.render(f5);

}

public void render8(float f5) {
	 stick1.render(f5);
	    stick2.render(f5);
	    stick3.render(f5);
	    stick4.render(f5);
	    stick5.render(f5);
	    stick6.render(f5);
	    stick7.render(f5);
	    stick8.render(f5);
}

}
