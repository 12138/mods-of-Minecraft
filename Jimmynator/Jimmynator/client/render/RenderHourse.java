package Jimmynator.client.render;

import Jimmynator.common.entity.EntityHorse;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;

public class RenderHourse extends RenderLiving {

	public RenderHourse(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
        this.setRenderPassModel(par1ModelBase);
	}
	    public void renderHourse(EntityHorse par1EntityPig, double par2, double par4, double par6, float par8, float par9)
	    {
	        super.doRenderLiving(par1EntityPig, par2, par4, par6, par8, par9);
	    }


@Override
	    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	    {
	        this.renderHourse((EntityHorse)par1EntityLiving, par2, par4, par6, par8, par9);
	    }

@Override
	    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	    {
	        this.renderHourse((EntityHorse)par1Entity, par2, par4, par6, par8, par9);
	    }
}
