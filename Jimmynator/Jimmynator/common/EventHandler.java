package Jimmynator.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


public class EventHandler {
	public static Random rand=new Random();


	@ForgeSubscribe
	public void setTorchOrHarestBlock(PlayerInteractEvent event){
		EntityPlayer player=event.entityPlayer;
		PlayerInteractEvent.Action action=event.action;
		if(player.dimension==-1){
			if(action==PlayerInteractEvent.Action.LEFT_CLICK_BLOCK){
				if(player.inventory.getCurrentItem()!=null&&player.inventory.getCurrentItem().getItem() instanceof ItemTool&&rand.nextInt(500)==1){
					player.worldObj.newExplosion(null, event.x, event.y, event.z,4.0F,true,true);
				}
			}
			else if(action==PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
				if(player.inventory.getCurrentItem()!=null&&player.inventory.getCurrentItem().getItem().itemID==Block.torchWood.blockID&&rand.nextInt(500)==1)
					player.worldObj.newExplosion(null, event.x, event.y, event.z,4.0F,true,true);
				
			}
			
			
		}
	}

}
