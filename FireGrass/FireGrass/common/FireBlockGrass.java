package FireGrass.common;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class FireBlockGrass extends BlockFlower {
	

	protected FireBlockGrass(int par1) {
		super(par1);
		this.setLightValue(0.6F);
		this.setLightOpacity(2);
		
	}
	
	
	
	
	 @Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		
		return EnumPlantType.Plains;
	}




	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return FireGrass.fireGrassFruitID+256;
    }




	@SideOnly(Side.CLIENT)
	    public void func_94332_a(IconRegister par1IconRegister)
	    {
		 this.field_94336_cN=par1IconRegister.func_94245_a(FireGrass.ModName+":fireGrass");
	    
	    }
	

}
