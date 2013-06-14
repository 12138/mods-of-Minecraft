package Jimmynator.client;

import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import Jimmynator.client.render.RenderHourse;
import Jimmynator.client.render.RenderStaffBall;
import Jimmynator.client.render.RenderTileEntityOrb;
import Jimmynator.client.render.RenderTileEntityPan;
import Jimmynator.client.render.RenderTomahawk;
import Jimmynator.client.render.model.ModelHorse;
import Jimmynator.common.CommonProxy;
import Jimmynator.common.Jimmynator;
import Jimmynator.common.block.tileentity.TileEntityOrb;
import Jimmynator.common.block.tileentity.TileEntityPan;
import Jimmynator.common.entity.EntityHorse;
import Jimmynator.common.entity.EntityShuriken;
import Jimmynator.common.entity.EntityStaffDeathball;
import Jimmynator.common.entity.EntityStaffFireball;
import Jimmynator.common.entity.EntityStaffIceball;
import Jimmynator.common.entity.EntityStaffLifeball;
import Jimmynator.common.entity.EntityTomahawk;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTomahawk.class, new RenderTomahawk());
		RenderingRegistry.registerEntityRenderingHandler(EntityStaffFireball.class, new RenderStaffBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityStaffIceball.class, new RenderStaffBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityStaffLifeball.class, new RenderStaffBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityStaffDeathball.class, new RenderStaffBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderSnowball(Jimmynator.shuriken));
		RenderingRegistry.registerEntityRenderingHandler(EntityHorse.class, new RenderHourse(new ModelHorse(), 0.7F));
		//ClientRegistry.registerTileEntity(TileEntityOrb.class, "tileentityorb", new RenderTileEntityOrb());
ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOrb.class, new RenderTileEntityOrb());
ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPan.class, new RenderTileEntityPan());
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

}
