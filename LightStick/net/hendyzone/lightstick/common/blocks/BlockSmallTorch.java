package net.hendyzone.lightstick.common.blocks;

import net.hendyzone.lightstick.common.Stick;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSmallTorch extends BlockTorchWithMeta {

	public BlockSmallTorch(int par1) {
		super(par1);
		 this.setLightValue(0.6375F);
		
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTorch();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon=par1IconRegister.registerIcon("lightstick:smallHandheldTorch");
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
		        				  Stick.smallHandheldTorch,1,this.light));
	            entityitem.delayBeforeCanPickup = 10;
	            par1World.spawnEntityInWorld(entityitem);
	        
	        }
	}
	
	
	
	

}
