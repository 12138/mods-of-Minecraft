package net.hendyzone.lightstick.common.blocks;

import java.util.Random;

import net.hendyzone.lightstick.common.Stick;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBonefire extends BlockContainer {

	public BlockBonefire(int par1) {
		super(par1, Material.rock);
		this.setBlockBounds(0, 0, 0, 1, (float) 0.5, 1);
	    this.setTickRandomly(true);
	    this.setHardness(0.5F);
	    
	
	}

	@Override
	public boolean isOpaqueCube(){ 
		return false;
		}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		int m=par1World.getBlockMetadata(par2, par3, par4);
		if(!(m%2==0)){
	        double d0 = (double)((float)par2 + 0.5F);
	        double d1 = (double)((float)par3 + 0.7F);
	        double d2 = (double)((float)par4 + 0.5F);
	        double d3 = 0.2199999988079071D;
	        double d4 = 0.07000001072883606D;
	            par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("flame", d0,    d1,    d2, 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("flame", d0+d4, d1+d3, d2+d4, 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("flame", d0+d4, d1+d3, d2-d4, 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("flame", d0+d4, d1+d4, d2-d4, 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("flame", d0+d4, d1+d4, d2+d4, 0.0D, 0.0D, 0.0D);
	           
	    
	        }
	}
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		Random ran=new Random();
       
            TileEntityBonefire tileentitybonefire = (TileEntityBonefire)par1World.getBlockTileEntity(par2, par3, par4);

            if (tileentitybonefire != null&&!par1World.isRemote)
            {
            	
                for (int j1 = 0; j1 < tileentitybonefire.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentitybonefire.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = ran.nextFloat() * 0.8F + 0.1F;
                        float f1 = ran.nextFloat() * 0.8F + 0.1F;
                        float f2 = ran.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = ran.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)ran.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)ran.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)ran.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                par1World.func_96440_m(par2, par3, par4, par5);
            }
      

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		if(!par1World.isRemote){
			int m=6-par1World.getBlockMetadata(par2, par3, par4)/2;
			
        	ItemStack stack=new ItemStack(Stick.charredStick,m);
            EntityItem item=new EntityItem(par1World, par2, par3, par4, stack);
            par1World.spawnEntityInWorld(item);
			}
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x,
			int y, int z) {
		System.out.println("removeBlockByPlayer");
		return super.removeBlockByPlayer(world, player, x, y, z);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	
	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#dropBlockAsItem_do(net.minecraft.world.World, int, int, int, net.minecraft.item.ItemStack)
	 */
	@Override
	protected void dropBlockAsItem_do(World par1World, int par2, int par3,
			int par4, ItemStack par5ItemStack) {
		
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int m=world.getBlockMetadata(x, y, z);
		if(m%2==0)
			 return 0;
		else
			return (int) (((15*0.9375F))-(m/12.0)*6);
		
	}

	

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		int l=par1World.getBlockMetadata(par2, par3, par4);
		if(l%2==0&&par5EntityPlayer.inventory.getCurrentItem()!=null&&par5EntityPlayer.inventory.getCurrentItem().itemID==Item.flintAndSteel.itemID){
		
			TileEntity tileentity=par1World.getBlockTileEntity(par2, par3, par4);
			if(tileentity!=null&&(tileentity instanceof TileEntityBonefire)){
				(( TileEntityBonefire)tileentity).burntick=20*10;
				}
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l+1, 2);
			return true;
		}
		if(par5EntityPlayer.inventory.getCurrentItem()!=null&&par5EntityPlayer.inventory.getCurrentItem().itemID==Stick.firePoker.itemID){
			if(l<=9)
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l+2, 2);
			if(!par1World.isRemote&&par1World.getBlockMetadata(par2, par3, par4)<=9){
				if(l%2==0)
					par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, new ItemStack(Stick.charredStick,1)));
				else{
					par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, new ItemStack(Block.torchWood,1)));
				}
				par5EntityPlayer.inventory.getCurrentItem().damageItem(1, par5EntityPlayer);
			}return true;
		}
		if(par5EntityPlayer.inventory.getCurrentItem()!=null&&par5EntityPlayer.inventory.getCurrentItem().itemID==Item.stick.itemID){
			if(l>1){
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l-2, 2);
			par5EntityPlayer.inventory.getCurrentItem().stackSize--;
			}
			return true;
		}

        if (par1World.isRemote)
        {
            return true;
        }
        else 
        {
        	if(!(l%2==0)){
            TileEntityBonefire tileentityBonefire = (TileEntityBonefire)par1World.getBlockTileEntity(par2, par3, par4);

            if (tileentityBonefire != null&&(tileentityBonefire.burntick>0||tileentityBonefire.burningremain>0))
            {
//            	if(!par1World.isRemote)
            	par5EntityPlayer.openGui(Stick.instance,0, par1World, par2,
						par3, par4);
            }
            }

            return true;
        }
		
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
	
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon=par1IconRegister.registerIcon("lightstick:bonefire");
	}


	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileEntityBonefire();
	}

}
