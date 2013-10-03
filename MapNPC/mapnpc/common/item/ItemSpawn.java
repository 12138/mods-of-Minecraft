package mapnpc.common.item;

import mapnpc.client.gui.GuiSpawnNPC;
import mapnpc.common.MapNPC;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawn extends Item {

	public ItemSpawn(int par1) {
		super(par1);
		
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
//	par2EntityPlayer.openGui(MapNPC.instance, 0, par3World, par4, par5, par6);
	if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
		FMLClientHandler.instance().showGuiScreen(new GuiSpawnNPC(par2EntityPlayer,par4,par5,par6));
	}
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5,
				par6, par7, par8, par9, par10);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon=par1IconRegister.registerIcon(MapNPC.assetsName+":"+this.getUnlocalizedName().replaceAll("item.", ""));
	}


}
