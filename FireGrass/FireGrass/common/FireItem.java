package FireGrass.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class FireItem extends Item {
private String UnocalizedName;
	public FireItem(int par1) {
		super(par1);
	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.func_94245_a(FireGrass.ModName+":"+this.UnocalizedName);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		this.UnocalizedName=par1Str;
		return super.setUnlocalizedName(par1Str);
	}

	

}
