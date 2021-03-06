package mapnpc.common.item;

import mapnpc.client.gui.GuiTaskBook;
import mapnpc.common.MapNPC;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemTaskBook extends Item {

	public ItemTaskBook(int par1) {
		super(par1);
		 this.setMaxStackSize(1);
	}
	


	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

		if(FMLCommonHandler.instance().getEffectiveSide().isClient())
		FMLClientHandler.instance().showGuiScreen(new GuiTaskBook(par3EntityPlayer));
        return par1ItemStack;
    }


	@Override
	public void registerIcons(IconRegister par1IconRegister) {
	this.itemIcon=par1IconRegister.registerIcon(MapNPC.assetsName+":tasklist");
	}


}
