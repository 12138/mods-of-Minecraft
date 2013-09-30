package firegrass.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firegrass.common.FireGrass;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class FireItem extends Item {
private String UnocalizedName;
	public FireItem(int par1) {
		super(par1);
	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon=par1IconRegister.registerIcon(FireGrass.modName+":"+this.UnocalizedName);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		this.UnocalizedName=par1Str;
		return super.setUnlocalizedName(par1Str);
	}

	

}
