package SoilMaker.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockSoilMaker extends BlockContainer {
	private Random SoilMakerRand = new Random();
	private static boolean keepSoilMakerInventory = false;
	private static boolean isAct ;
	

	public BlockSoilMaker(int par1, boolean par2,int par3) {
		super(par1, Material.rock);
		isAct=par2;
		this.setStepSound(soundStoneFootstep);
		this.blockIndexInTexture = par3;
	}
	public int idDropped(int par1, Random par2Random, int par3) {
		return mod_SoilMaker.SoilMaker.blockID;
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3,
			int par4) {
		if (!par1World.isRemote) {
			int var5 = par1World.getBlockId(par2, par3, par4 - 1);
			int var6 = par1World.getBlockId(par2, par3, par4 + 1);
			int var7 = par1World.getBlockId(par2 - 1, par3, par4);
			int var8 = par1World.getBlockId(par2 + 1, par3, par4);
			byte var9 = 3;

			if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6]) {
				var9 = 3;
			}

			if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5]) {
				var9 = 2;
			}

			if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8]) {
				var9 = 5;
			}

			if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7]) {
				var9 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
		}
	}

	

	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return this.blockIndexInTexture + 1;
        }
        else if(par5 == 0){
		return this.blockIndexInTexture + 2;}
		else
        {
            int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            return par5 != var6 ? this.blockIndexInTexture : this.blockIndexInTexture - 1;
        }
        
        
    }
	
	public int getBlockTextureFromSide(int par1) {
		return par1 == 1 ? this.blockIndexInTexture + 1
				: (par1 == 0 ? this.blockIndexInTexture + 2
						: (par1 == 3 ? this.blockIndexInTexture - 1
								: this.blockIndexInTexture));
	}
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		
        if (isAct)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            float var7 = (float)par2 + 0.5F;
            float var8 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float var9 = (float)par4 + 0.5F;
            float var10 = 0.52F;
            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;

            if (var6 == 4)
            {
                par1World.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
               
            }
            else if (var6 == 5)
            {
                par1World.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
                
            }
            else if (var6 == 2)
            {
                par1World.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
                
            }
            else if (var6 == 3)
            {
                par1World.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
                
            }
        }
    }

	public boolean onBlockActivated(World par1World, int par2, int par3,int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		} else {
			TileEntitySoilMaker var10 = (TileEntitySoilMaker) par1World.getBlockTileEntity(par2, par3, par4);

			if (var10 != null) {
				par5EntityPlayer.openGui(mod_SoilMaker.instance, 0, par1World, par2,
						par3, par4);
			}

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		
          
		return new TileEntitySoilMaker();

	}

	public static void updateSoilMakerBlockState(boolean par0, World par1World,
			int par2, int par3, int par4) {
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
		keepSoilMakerInventory = true;

		if (par0) {
			isAct=true;
		} else {
			isAct=false;
			
		}

		keepSoilMakerInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var5);

		if (var6 != null) {
			var6.validate();
			par1World.setBlockTileEntity(par2, par3, par4, var6);
		}
		
			
		
}
		

	
	
	 public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		int var6 = MathHelper
				.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (var6 == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
		}

		if (var6 == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
		}

		if (var6 == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
		}

		if (var6 == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
		}
	}

	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		if (!keepSoilMakerInventory) {
			TileEntitySoilMaker var7 = (TileEntitySoilMaker) par1World
					.getBlockTileEntity(par2, par3, par4);

			if (var7 != null) {
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
					ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null) {
						float var10 = this.SoilMakerRand.nextFloat() * 0.8F + 0.1F;
						float var11 = this.SoilMakerRand.nextFloat() * 0.8F + 0.1F;
						float var12 = this.SoilMakerRand.nextFloat() * 0.8F + 0.1F;

						while (var9.stackSize > 0) {
							int var13 = this.SoilMakerRand.nextInt(21) + 10;

							if (var13 > var9.stackSize) {
								var13 = var9.stackSize;
							}

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World,
									(double) ((float) par2 + var10),
									(double) ((float) par3 + var11),
									(double) ((float) par4 + var12),
									new ItemStack(var9.itemID, var13,
											var9.getItemDamage()));

							if (var9.hasTagCompound()) {
								var14.func_92014_d().setTagCompound((NBTTagCompound) var9
										.getTagCompound().copy());
							}

							float var15 = 0.05F;
							var14.motionX = (double) ((float) this.SoilMakerRand
									.nextGaussian() * var15);
							var14.motionY = (double) ((float) this.SoilMakerRand
									.nextGaussian() * var15 + 0.2F);
							var14.motionZ = (double) ((float) this.SoilMakerRand
									.nextGaussian() * var15);
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	
	public String getTextureFile()
    {
            return CommonProxy.BLOCKS_PNG;
    }
}
