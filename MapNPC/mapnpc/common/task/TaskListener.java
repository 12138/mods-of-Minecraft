package mapnpc.common.task;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import mapnpc.common.network.PacketSynTaskInClient;
import mapnpc.common.task.tasktype.TaskBase;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskPlace;
import mapnpc.common.task.tasktype.TaskSearch;
import mapnpc.common.task.tasktype.TaskType;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TaskListener implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// not use

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		World world = (World) tickData[0];
		syn(world);

	}

	private void syn(World world) {
		ArrayList<String> list=new ArrayList<String>();
		
		HashMap<String, TaskBase> taskMap=(HashMap<String, TaskBase>) TaskSavedData.forWorld(world).getTaskinList();
		list.add(String.valueOf(taskMap.size()));
		for (Entry<String, TaskBase> tem : taskMap.entrySet()) {
			list.add(tem.getKey());
			list.add(tem.getValue().getGroup());
			list.add(tem.getValue().getType().toString());
			
			if (tem.getValue().getType().equals(TaskType.Place)) {
				TaskPlace place = (TaskPlace) tem.getValue();
				ChunkCoordinates pos = place.getPos();
				list.add(String.valueOf(pos.posX));
				list.add(String.valueOf(pos.posY));
				list.add(String.valueOf(pos.posZ));
			} else if (tem.getValue().getType().equals(TaskType.Search)) {
				TaskSearch search = (TaskSearch) tem.getValue();
				list.add(search.getContext());
			} else if (tem.getValue().getType().equals(TaskType.Kill)) {
				TaskKill kill = (TaskKill) tem.getValue();
				list.add(kill.getContext());
			}
		}
		
		HashMap<String, String> rewordMap=(HashMap<String, String>) TaskSavedData.forWorld(world).getRewordinTask();
		list.add(String.valueOf(rewordMap.size()));
		for (Entry<String, String> tem : rewordMap.entrySet()) {
			list.add(tem.getKey());
			list.add(tem.getValue());
		}
		
		
		Set<String> ketSet=(Set<String>) TaskSavedData.forWorld(world).getTaskinKeySet();
		list.add(String.valueOf(ketSet.size()));
		
		for (String tem : ketSet) {
			list.add(tem);
		}
		
		Set<String> ketGroupSet=(Set<String>) TaskSavedData.forWorld(world).getTaskinGroupKey();
		list.add(String.valueOf(ketGroupSet.size()));
	
		for (String tem : ketGroupSet) {
			list.add(tem);
		
		}

		
		PacketSynTaskInClient.toClient(list);

		
	}

	@Override
	public EnumSet<TickType> ticks() {

		return EnumSet.of((TickType.WORLD));
	}

	@Override
	public String getLabel() {

		return "task Syn";
	}

}
