package Jimmynator.common.item.ninja;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import Jimmynator.common.Jimmynator;
import Jimmynator.common.entity.EntityShuriken;
import Jimmynator.common.item.ItemJimmynator;

public class ItemShuriken extends ItemJimmynator {

	public ItemShuriken(int par1) {
		super(par1);
	this.setMaxStackSize(16);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		
	      if (Jimmynator.proxy.isEquid(par3EntityPlayer, "ninja")||par3EntityPlayer.capabilities.isCreativeMode)
	      {	      par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

//	      if (!par2World.)
	      {
	        par2World.spawnEntityInWorld(new EntityShuriken(par2World, par3EntityPlayer));
	      }
	    }

	    return par1ItemStack;
	}

}
