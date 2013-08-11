package CookerCraft.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CookerItem extends Item {
private String UnocalizedName;
	public CookerItem(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(CookerCraft.ModName+":"+this.UnocalizedName);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		this.UnocalizedName=par1Str;
		return super.setUnlocalizedName(par1Str);
	}

	

}
