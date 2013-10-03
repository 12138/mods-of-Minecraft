package net.hendyzone.lightstick.client;

import net.hendyzone.lightstick.common.blocks.ContainerBonefire;
import net.hendyzone.lightstick.common.blocks.TileEntityBonefire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {
		case 0:
			if (tileEntity instanceof TileEntityBonefire) {
				return new ContainerBonefire(player.inventory,
						(TileEntityBonefire) tileEntity);
			}
		
			
		default:
			return null;
		}
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {

		case 0:
			if (tileEntity instanceof TileEntityBonefire) {
				return new GuiBonefire(player.inventory,
						(TileEntityBonefire) tileEntity);
			}
		
		
		default:
			return null;
		}
	}

}
