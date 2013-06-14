package Jimmynator.common.item.food;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

public class JimmItemFood extends ItemFood {
	public JimmItemFood(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3,par4);
		this.setCreativeTab(Jimmynator.tabJimmynator);
		
	}
    
  
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}
	
}
