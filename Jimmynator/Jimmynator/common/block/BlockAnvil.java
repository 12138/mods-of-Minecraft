package Jimmynator.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAnvil extends Block {
	Icon top;
	Icon bottom;
	Icon side1;
	Icon side2;
	Icon side3;
	Icon side4;

	public BlockAnvil(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(Jimmynator.tabJimmynator);
		// TODO Auto-generated constructor stub
	}

	@Override	
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		switch (par1) {
			case 0:
				return bottom;
			case 1:
				return top;
			case 2:
				return side1;
			case 3:
				return side2;
			case 4:
				return side3;
			case 5:
				return side4;
				

			default:
				break;
		}
		return top;
	}



	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote)
	    {
	      return true;
	    }
	    par5EntityPlayer.openGui(Jimmynator.instance, 0, par1World, par2, par3, par4);
	    return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
	this.top=par1IconRegister.registerIcon("Jimmynator:anvil_0");
	this.bottom=par1IconRegister.registerIcon("Jimmynator:anvil_1");
	this.side1=par1IconRegister.registerIcon("Jimmynator:anvil_2");
	this.side2=par1IconRegister.registerIcon("Jimmynator:anvil_3");
	this.side3=par1IconRegister.registerIcon("Jimmynator:anvil_4");
	this.side4=par1IconRegister.registerIcon("Jimmynator:anvil_5");
	
	}

}
