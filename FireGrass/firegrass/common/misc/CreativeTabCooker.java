package firegrass.common.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firegrass.common.FireGrass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCooker extends CreativeTabs {

	public CreativeTabCooker(String par1Str) {
		super(par1Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		
		return FireGrass.fireGrassFruit;
	}

}
