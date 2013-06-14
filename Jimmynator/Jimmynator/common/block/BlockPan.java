package Jimmynator.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Jimmynator.common.Jimmynator;
import Jimmynator.common.block.tileentity.TileEntityPan;
import Jimmynator.common.network.PacketPan;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPan extends BlockContainer {
	


	public BlockPan(int par1) {
		super(par1, Material.rock);
	this.setCreativeTab(Jimmynator.tabJimmynator);
	this.setLightValue(0.2F);
	this.setLightOpacity(3);
	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPan();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		TileEntity tile =par1World.getBlockTileEntity(par2, par3, par4);
		if(tile!=null&&tile instanceof TileEntityPan){
			TileEntityPan pan=(TileEntityPan) tile;
			if(pan.inPan==null){
				if(par5EntityPlayer.inventory.getCurrentItem()!=null){
					if(canCook(par5EntityPlayer.inventory.getCurrentItem())){
						pan.inPan=par5EntityPlayer.inventory.getCurrentItem().splitStack(1);
						PacketPan.panChange(par5EntityPlayer.inventory.getCurrentItem().itemID, par2, par3, par4);
						
						par5EntityPlayer.inventory.consumeInventoryItem(par5EntityPlayer.inventory.getCurrentItem().itemID);
					}
				}
				else{
					return true;
				}
			}
			else{
				if(!par1World.isRemote)
				par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, pan.inPan));
				pan.inPan=null;
				
			}
		}

		return true;
	}

	private boolean canCook(ItemStack currentItem) {
		ItemStack stack=FurnaceRecipes.smelting().getSmeltingResult(currentItem);
		if(stack==null){
			return false;
		}
		     if(stack.itemID==Item.chickenCooked.itemID||
				stack.itemID==Item.porkCooked.itemID||
				stack.itemID==Jimmynator.friedEgg.itemID||
				stack.itemID==Item.beefCooked.itemID||
				stack.itemID==Item.bakedPotato.itemID)
			return true;
		return false;
	}
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("tile.", ""));
	}
	
	 private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	    {
	        if (!par1World.isRemote)
	        {
	            int l = par1World.getBlockId(par2, par3, par4 - 1);
	            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
	            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
	            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
	            byte b0 = 0;

	            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
	            {
	                b0 = 1;
	            }

	            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
	            {
	                b0 = 0;
	            }

	            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
	            {
	                b0 = 3;
	            }

	            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
	            {
	                b0 = 2;
	            }

	            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
	        }
	    }
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {

        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving,
				par6ItemStack);
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}


}
