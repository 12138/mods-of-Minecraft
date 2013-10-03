package minecharacter.misc;

import java.util.Random;

import minecharacter.MineCharacter;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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
	
	@ForgeSubscribe
	public void knightHit(AttackEntityEvent event){
		EntityPlayer player=event.entityPlayer;
		if(MineCharacter.proxy.isEquid(player, "knight")){
			if(player.ridingEntity!=null&&player.ridingEntity instanceof EntityHorse){
				event.target.attackEntityFrom(DamageSource.causePlayerDamage(player),2);
		}	
	}
	}
	
	@ForgeSubscribe
	public void canntFouseonAssassin(LivingSetAttackTargetEvent event){
		if(event.target instanceof EntityPlayer){
			EntityPlayer player=(EntityPlayer) event.target;
			if(MineCharacter.proxy.isEquid(player, "assassin")&&player.isPotionActive(Potion.invisibility)){
				((EntityLiving) event.entityLiving).setAttackTarget(null);
				
			}
		}
		
	}
}
