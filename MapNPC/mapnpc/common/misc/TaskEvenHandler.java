package mapnpc.common.misc;

import java.util.ArrayList;

import mapnpc.common.network.PacketSynTaskInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent.Load;

public class TaskEvenHandler {
	@ForgeSubscribe
	public void killEntity(LivingDeathEvent event) {

		// only in server
		if (event.source.damageType.equals("player")) {
			 EntityPlayer player=(EntityPlayer) ((EntityDamageSource) event.source).getEntity();
			 String entityName = EntityList.getEntityString(event.entity);
			 NBTTagCompound nbt =player.getEntityData();
			 if(nbt.hasKey(player.username)){
				 NBTTagCompound playerNBT=nbt.getCompoundTag(player.username);
				 if(playerNBT.hasKey("beginCount")){
						if (playerNBT.hasKey("entityCount")) {
							NBTTagCompound entityCount = playerNBT.getCompoundTag("entityCount");
							if (entityCount.hasKey(entityName)) {

								int count = entityCount.getInteger(entityName);
								count++;
								entityCount.removeTag(entityName);
								entityCount.setInteger(entityName, count);
							} else {
								entityCount.setInteger(entityName, 1);
							}
						} else {
							NBTTagCompound entityCount = new NBTTagCompound("entityCount");
							entityCount.setInteger(entityName, 1);
							playerNBT.setCompoundTag("entityCount", entityCount);
						}
						ArrayList<String> list = new ArrayList<String>();
						list.add(player.username);
						list.add(entityName);
						PacketSynTaskInfo.synTaskInfo(list,(EntityPlayer) ((EntityDamageSource) event.source).getEntity(), false,0);

				 }
				 else{
					 if (playerNBT.hasKey("entityCount")) {
						 playerNBT.removeTag("entityCount");
						}
					 return;
				 }
			 }
			
	
		}

	}

	@ForgeSubscribe
	public void loadTaskData(Load event) {
		DialogManager.instance.loadDialogXML( event.world);
		
	}


}
