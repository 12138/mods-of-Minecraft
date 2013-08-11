package CookerCraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.FMLCommonHandler;

public class CookerEventHandler {
	
	
	@ForgeSubscribe
	  public void dropWildRich(PlayerInteractEvent event){
		FMLCommonHandler.instance().getEffectiveSide();
	 int x=event.x;
	 int y=event.y;
	 int z=event.z;
	 EntityPlayer player=event.entityPlayer;
	 WorldServer world=FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(player.dimension);
	int blockid=world.getBlockId(x, y, z);
	 
	  if(blockid==Block.reed.blockID&&event.action==PlayerInteractEvent.Action.LEFT_CLICK_BLOCK){
		  Random ran =new Random();
		  if(ran.nextInt(15)==0){
		   EntityItem item= new EntityItem(world, x, y+ran.nextInt(1), z, new ItemStack(CookerCraft.wildRice,1));
		  world.spawnEntityInWorld(item);
		  }
			  
	  }
	}

}
