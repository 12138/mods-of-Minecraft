package Jimmynator.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Jimmynator.common.Jimmynator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockAir extends Block {

	public BlockAir(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	this.setCreativeTab(Jimmynator.tabJimmynator);	
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
	public int idDropped(int par1, Random par2Random, int par3) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		// TODO Auto-generated method stub
		return super.idPicked(par1World, par2, par3, par4);
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
		// TODO Auto-generated method stub
		return -1;
	}
	

}
