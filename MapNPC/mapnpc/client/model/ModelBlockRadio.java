package mapnpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockRadio extends ModelBase
{
  //fields
    ModelRenderer shell;
    ModelRenderer fFrameU;
    ModelRenderer fFrameD;
    ModelRenderer fFrameR;
    ModelRenderer knob;
    ModelRenderer antenna;
    ModelRenderer button1;
    ModelRenderer button2;
    ModelRenderer fFrameL;
  
  public ModelBlockRadio()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      shell = new ModelRenderer(this, 29, 30);
      shell.addBox(0F, 1F, 0F, 10, 7, 7);
      shell.setRotationPoint(-5F, 16F, -2F);
      shell.setTextureSize(128, 128);
      shell.mirror = true;
      setRotation(shell, 0F, 0F, 0F);
      fFrameU = new ModelRenderer(this, 39, 14);
      fFrameU.addBox(0F, -1F, -3F, 10, 1, 1);
      fFrameU.setRotationPoint(-5F, 18F, 0F);
      fFrameU.setTextureSize(128, 128);
      fFrameU.mirror = true;
      setRotation(fFrameU, 0F, 0F, 0F);
      fFrameD = new ModelRenderer(this, 39, 14);
      fFrameD.addBox(-5F, 23F, -3F, 10, 1, 1);
      fFrameD.setRotationPoint(0F, 0F, 0F);
      fFrameD.setTextureSize(128, 128);
      fFrameD.mirror = true;
      setRotation(fFrameD, 0F, 0F, 0F);
      fFrameR = new ModelRenderer(this, 39, 14);
      fFrameR.addBox(4F, 19F, -3F, 1, 5, 1);
      fFrameR.setRotationPoint(0F, -1F, 0F);
      fFrameR.setTextureSize(128, 128);
      fFrameR.mirror = true;
      setRotation(fFrameR, 0F, 0F, 0F);
      knob = new ModelRenderer(this, 42, 5);
      knob.addBox(0F, -2F, 0F, 1, 2, 2);
      knob.setRotationPoint(5F, 22F, 1F);
      knob.setTextureSize(128, 128);
      knob.mirror = true;
      setRotation(knob, 0F, 0F, 0F);
      antenna = new ModelRenderer(this, 29, 8);
      antenna.addBox(2F, 0F, 2F, 1, 9, 1);
      antenna.setRotationPoint(0F, 11F, -1F);
      antenna.setTextureSize(128, 128);
      antenna.mirror = true;
      setRotation(antenna, 0.3490659F, 0F, 0F);
      button1 = new ModelRenderer(this, 0, 0);
      button1.addBox(0F, -1F, 0F, 1, 1, 1);
      button1.setRotationPoint(0F, 22F, -3F);
      button1.setTextureSize(128, 128);
      button1.mirror = true;
      setRotation(button1, 0F, 0F, 0F);
      button2 = new ModelRenderer(this, 0, 0);
      button2.addBox(-3F, 0F, 0F, 1, 1, 1);
      button2.setRotationPoint(5F, 21F, -3F);
      button2.setTextureSize(128, 128);
      button2.mirror = true;
      setRotation(button2, 0F, 0F, 0F);
      fFrameL = new ModelRenderer(this, 39, 14);
      fFrameL.addBox(0F, 0F, 0F, 1, 5, 1);
      fFrameL.setRotationPoint(-5F, 18F, -3F);
      fFrameL.setTextureSize(128, 128);
      fFrameL.mirror = true;
      setRotation(fFrameL, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    shell.render(f5);
    fFrameU.render(f5);
    fFrameD.render(f5);
    fFrameR.render(f5);
    knob.render(f5);
    antenna.render(f5);
    button1.render(f5);
    button2.render(f5);
    fFrameL.render(f5);
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

public void renderThis(float f) {
	 shell.render(f);
	    fFrameU.render(f);
	    fFrameD.render(f);
	    fFrameR.render(f);
	    knob.render(f);
	    antenna.render(f);
	    button1.render(f);
	    button2.render(f);
	    fFrameL.render(f);
	
}

}
