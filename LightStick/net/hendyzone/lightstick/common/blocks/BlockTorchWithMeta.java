package net.hendyzone.lightstick.common.blocks;

import java.util.Random;

import net.hendyzone.lightstick.common.Stick;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTorchWithMeta extends BlockTorch implements ITileEntityProvider{
	protected int light;
	public BlockTorchWithMeta(int par1) {
		super(par1);
		 this.isBlockContainer = true;
		 this.setHardness(0.0F);
		 this.setLightValue(0.9375F);
		 this.setStepSound(Block.soundWoodFootstep);
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		   TileEntityTorch tile=(TileEntityTorch) par1World.getBlockTileEntity(par2, par3, par4);
		if(tile!=null)
		   this.light=tile.getLight();
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
		  par1World.removeBlockTileEntity(par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon=par1IconRegister.registerIcon("lightstick:handheldTorch");
	}

	@Override
	public boolean onBlockEventReceived(World par1World, int par2, int par3,
			int par4, int par5, int par6) {
		
		 super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
		 TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
	        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
	}

	@Override
	protected void dropBlockAsItem_do(World par1World, int par2, int par3,
			int par4, ItemStack par5ItemStack) {
		 if (!par1World.isRemote && par1World.getGameRules().getGameRuleBooleanValue("doTileDrops"))
	        {
	            float f = 0.7F;
	            double d0 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d1 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d2 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	         
	            EntityItem entityitem =  new EntityItem(
		        		  par1World, (double)par2 + d0, (double)par3 + d1, (double)par4 + d2, new ItemStack(
		        				  Stick.handheldTorch,1,this.light));
	            entityitem.delayBeforeCanPickup = 10;
	            par1World.spawnEntityInWorld(entityitem);
	        
	        }
	}
	@Override
	 @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        int l = par1World.getBlockMetadata(par2, par3, par4);
	        double d0 = (double)((float)par2 + 0.5F);
	        double d1 = (double)((float)par3 + 0.7F);
	        double d2 = (double)((float)par4 + 0.5F);
	        double d3 = 0.2199999988079071D;
	        double d4 = 0.27000001072883606D;

	        if (l == 1)
	        {
	            par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	          //  par1World.spawnParticle("flame", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	        }
	        else if (l == 2)
	        {
	            par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	          //  par1World.spawnParticle("flame", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	        }
	        else if (l == 3)
	        {
	            par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
	          //  par1World.spawnParticle("flame", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
	        }
	        else if (l == 4)
	        {
	            par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
	           // par1World.spawnParticle("flame", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
	        }
	        else
	        {
	            par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	           // par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        }
	    }

	

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTorch();
	}

}
