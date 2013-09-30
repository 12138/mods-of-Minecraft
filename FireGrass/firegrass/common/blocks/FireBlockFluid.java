package firegrass.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firegrass.common.FireGrass;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class FireBlockFluid extends BlockFluid {
	private Icon[] pic;
	protected int color;
	protected FireBlockFluid(int par1,int color) {
		super(par1, Material.water);
		this.setLightOpacity(3);
		this.setLightValue(1F);
		this.color=color;
	}
	
	@Override
	public Icon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.pic[16+color] : this.pic[color];
    }
	@Override
	 @SideOnly(Side.CLIENT)
	    public void registerIcons(IconRegister par1IconRegister)
	    {
		 this.pic=new Icon[32];
	       for(int i=0;i<32;i++){
	    	   if(i<16)
	            this.pic[i] = par1IconRegister.registerIcon(FireGrass.modName+":fireWater_"+i);
	    	   else
	    		 this.pic[i]=par1IconRegister.registerIcon(FireGrass.modName+":fireWater_flow_"+(i-16));
	    }
	    }
	
	

}
