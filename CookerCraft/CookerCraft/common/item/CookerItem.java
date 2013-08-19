package CookerCraft.common.item;

import CookerCraft.common.CookerCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CookerItem extends Item {
private String UnocalizedName;
	public CookerItem(int par1) {
		super(par1);
		this.setCreativeTab(CookerCraft.tabCookerCraft);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(CookerCraft.ModId+":"+this.UnocalizedName);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		this.UnocalizedName=par1Str;
		return super.setUnlocalizedName(par1Str);
	}

	

}
