package Jimmynator.common.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Jimmynator.common.Jimmynator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemReed;

public class ItemOmelette extends ItemReed {

	public ItemOmelette(int par1, Block par2Block) {
		super(par1, par2Block);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}
}
