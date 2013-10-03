package net.hendyzone.lightstick.client;

import net.hendyzone.lightstick.common.CommonProxy;
import net.hendyzone.lightstick.common.Stick;
import net.hendyzone.lightstick.common.blocks.TileEntityBonefire;
import net.hendyzone.lightstick.common.entity.EntityTorch;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void load() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTorch.class,new RenderHandleTourch(Stick.handheldTorch));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBonefire.class,new RenderBonefire());
	}

}
