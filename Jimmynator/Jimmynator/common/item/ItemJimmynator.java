package Jimmynator.common.item;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemJimmynator extends Item {

	public ItemJimmynator(int par1) {
		super(par1);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}
}
