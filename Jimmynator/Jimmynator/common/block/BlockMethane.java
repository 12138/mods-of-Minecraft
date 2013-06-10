package Jimmynator.common.block;

import java.util.Random;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockMethane extends Block {
	public BlockMethane(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
	
		return null;
	}
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
if(par1World.getBlockId(par2, par3, par4)==Block.torchWood.blockID||par1World.getBlockId(par2, par3, par4)==Block.fire.blockID)
	par1World.newExplosion(null, par2, par3, par4, 6.0F,true,true);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		if(par5==Block.torchWood.blockID||par5==Block.fire.blockID||par5==0){
			par1World.newExplosion(null, par2, par3, par4,6.0F,true,true);
		}
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}





	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

	}

	@Override
	public int getRenderType() {
		return -1;
	}
}
