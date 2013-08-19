package CookerCraft.common;

import CookerCraft.common.utils.InitItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCooker extends CreativeTabs {

	public CreativeTabCooker(String par1Str) {
		super(par1Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		
		return InitItem.tastyBread;
	}

}
