package net.hendyzone.lightstick.common.items;

import net.hendyzone.lightstick.common.Stick;
import net.hendyzone.lightstick.common.blocks.TileEntityTorch;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSmallTorch extends ItemBlockTorch {

	public ItemSmallTorch(int par1) {
		super(par1);
		this.setMaxDamage(30);
		this.small=true;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

        int i1 = par3World.getBlockId(par4, par5, par6);

        if (i1 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(par3World, par4, par5, par6)))
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if (par5 == 255 && Stick.smallHandheldTorchBlock.blockMaterial.isSolid())
        {
            return false;
        }
        else if (par3World.canPlaceEntityOnSide(Stick.smallHandheldTorchBlock.blockID, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack))
        {
            Block block = Block.blocksList[Stick.smallHandheldTorchBlock.blockID];
            int j1 = this.getMetadata(par1ItemStack.getItemDamage());
            int k1 = Block.blocksList[Stick.smallHandheldTorchBlock.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, j1);

            if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, k1))
            {
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon=par1IconRegister.registerIcon("lightstick:smallHandheldTorch");
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ, int metadata) {
		if (!world.setBlock(x, y, z,Stick.smallHandheldTorchBlock.blockID, metadata, 3))
		   {
		       return false;
		   }

		   if (world.getBlockId(x, y, z) ==Stick.smallHandheldTorchBlock.blockID)
		   {
			   
		       Block.blocksList[Stick.smallHandheldTorchBlock.blockID].onBlockPlacedBy(world, x, y, z, player, stack);
		       Block.blocksList[Stick.smallHandheldTorchBlock.blockID].onPostBlockPlaced(world, x, y, z, metadata);
		       if(world.getBlockTileEntity(x, y, z).getBlockType().blockID==Stick.smallHandheldTorchBlock.blockID){
		    	   TileEntityTorch tile=(TileEntityTorch) world.getBlockTileEntity(x, y, z);
		    	   tile.setLight(stack.getItemDamage());
		       }
		       if(stack.getItemDamage()!=0)
		    	   stack.setItemDamage(0);
		       
		   }

		   return true;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		if(!par2World.isRemote){
			if(par1ItemStack.getItemDamage()==this.getMaxDamage())
				give=true;
			super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
			if(give){
			par2World.spawnEntityInWorld(new EntityItem(par2World, par3Entity.posX, par3Entity.posY, par3Entity.posZ,new ItemStack(Stick.charredStick)));
			par1ItemStack.damageItem(1, (EntityLiving) par3Entity);
			give=false;
			}
			}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {
		entity.setFire(50);
		stack.damageItem(1, player);
		return true;
	}

}
