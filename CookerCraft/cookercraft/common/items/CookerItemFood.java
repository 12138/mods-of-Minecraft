package cookercraft.common.items;

import cookercraft.common.CookerCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class CookerItemFood extends ItemFood {
	private String UnocalizedName;
	public CookerItemFood(int par1, int par2, boolean par3) {
		super(par1, par2,par3);
		this.setCreativeTab(CookerCraft.tabCookerCraft);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(CookerCraft.assetsName+":"+this.UnocalizedName);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		this.UnocalizedName=par1Str;
		return super.setUnlocalizedName(par1Str);
	}

}
