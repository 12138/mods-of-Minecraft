package Jimmynator.common.block;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Jimmynator.common.Jimmynator;
import Jimmynator.common.network.PacketSpawnItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockNetherOrb extends Block {
	Icon rock1;
	Icon rock2;
	private int meta;

	public BlockNetherOrb(int par1,int meta) {
		super(par1, Material.rock);
		this.meta=meta;
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.netherrack.blockID;
	}



	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer,
			int par3, int par4, int par5, int par6) {
		if(par2EntityPlayer.inventory.getCurrentItem()!=null&&par2EntityPlayer.inventory.getCurrentItem().itemID==Item.pickaxeGold.itemID){
			switch (this.meta) {
				case 0:
					//if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
						PacketSpawnItem.spawnItem(Jimmynator.nethercoal.itemID,0, 1, par3, par4, par5);
						par1World.setBlockToAir(par3, par4, par5);
						return;
				case 1:
					//if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
						PacketSpawnItem.spawnItem(Jimmynator.demonite.itemID,0, 1, par3, par4, par5);
//					par1World.spawnEntityInWorld(new EntityItem(par1World, par3, par4, par5, par8ItemStack))
						par1World.setBlockToAir(par3, par4, par5);
						return;
				case 2:
//					if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
						PacketSpawnItem.spawnItem(Jimmynator.luciferite.itemID,0, 1, par3, par4, par5);
						par1World.setBlockToAir(par3, par4, par5);
						return;
				default:
					break;
			}
		}
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		switch (this.meta) {
			case 0:
				return this.blockIcon;
			case 1:
				return this.rock1;
			case 2:
				return this.rock2;
			default:
				break;
		}
		return blockIcon;
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
	     this.blockIcon = par1IconRegister.registerIcon("Jimmynator:nethercoal");
	     this.rock1 = par1IconRegister.registerIcon("Jimmynator:blockDemonite");
	     this.rock2 = par1IconRegister.registerIcon("Jimmynator:luciferite");
	}



}
