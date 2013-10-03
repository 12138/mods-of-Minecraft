package mapnpc.common.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mapnpc.common.network.PacketSynTaskData_add;
import mapnpc.common.network.PacketSynTaskData_remove;
import mapnpc.common.network.PacketSynTaskInfo;
import mapnpc.common.task.tasktype.TaskBase;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskPlace;
import mapnpc.common.task.tasktype.TaskSearch;
import mapnpc.common.task.tasktype.TaskType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class TaskSavedData extends WorldSavedData {

	private  Map<String, TaskBase> taskList = new HashMap<String, TaskBase>();
	private  Set<String> taskKey = new HashSet<String>();
	private  Set<String> taskGroupKey = new HashSet<String>();
	private  Map<String, String> rewordList = new HashMap<String, String>();

	final static String key = "MapNPC_Task_Data";

	
	public static TaskSavedData forWorld(World world) {
		MapStorage storage = world.mapStorage;
		TaskSavedData result = (TaskSavedData)storage.loadData(TaskSavedData.class, key);
		if (result == null) {
			result = new TaskSavedData(key);
			storage.setData(key, result);
		}
		return result;
				
	}

	public  void addTasktoGroupKey(String taskGroupKey, boolean needSyn,boolean toServer) {
		this.taskGroupKey .add(taskGroupKey);
		if(needSyn)
		PacketSynTaskData_add.synData(toServer,3,taskGroupKey);
		this.markDirty();
	}
	
	public  void addRewordtoGroup(String groupName ,String reword ,boolean needSyn,boolean toServer) {
		this.rewordList.put(groupName, reword);
		
			if(needSyn)
			PacketSynTaskData_add.synData(toServer,4,groupName,reword);
		
		this.markDirty();
	}


	


	public  void addTasktoKeySet(String taskKey, boolean needSyn,boolean toServer) {
		this.taskKey .add(taskKey);
		if(needSyn)
		PacketSynTaskData_add.synData(toServer,2,taskKey);
		this.markDirty();
	}

	public  void addTasktoList(String taskName ,TaskBase task ,boolean needSyn,boolean toServer) {
		this.taskList.put(taskName, task);
		
		if(task.getType().toString().equals("Place")){
			ChunkCoordinates pos=((TaskPlace)task).getPos();
			if(needSyn)
			PacketSynTaskData_add.synData(toServer,1, "Place",taskName,task.getGroup(),String.valueOf(pos.posX),String.valueOf(pos.posY),String.valueOf(pos.posZ));
		
		}
		else if(task.getType().toString().equals("Search")){
			if(needSyn)
			PacketSynTaskData_add.synData(toServer,1,"Search", taskName,task.getGroup(),task.getContext());
		}
		else if(task.getType().toString().equals("Kill")){
			if(needSyn)
			PacketSynTaskData_add.synData(toServer,1, "Kill",taskName,task.getGroup(),task.getContext());
		}
		this.markDirty();
	}

	public  Set<String> getTaskinGroupKey() {
		return taskGroupKey;
	}

	public  Set<String> getTaskinKeySet() {
		return taskKey;
	}




	public  Map<String, TaskBase> getTaskinList() {
		return taskList;
	}

	public  Map<String, String> getRewordinTask() {
		return rewordList;
	}

	public  ArrayList<TaskBase> getTaskList(String taskGroupName) {
		ArrayList<TaskBase> taskList = new ArrayList<TaskBase>();
		for (String taskName : this.getTaskinList().keySet()) {
			if (this.getTaskinList().get(taskName).getGroup()
					.equals(taskGroupName)) {
				taskList.add(this.getTaskinList().get(taskName));
			}
		}
		return taskList;
	}
	public void startTaskGroup(EntityPlayer player, String taskGroupName) {
		ArrayList<String> list = new ArrayList<String>();
		NBTTagCompound nbt = player.getEntityData();
		if(nbt.hasKey(player.username)){
			nbt.removeTag(player.username);
		}
		NBTTagCompound playerNBT = new NBTTagCompound(player.username);
		list.add(player.username);
		if (playerNBT.hasKey("beginCount"))
			playerNBT.removeTag("beginCount");
		playerNBT.setBoolean("beginCount", true);
		
		if (playerNBT.hasKey("taskGroup")) {
			playerNBT.removeTag("taskGroup");

		}
		
		NBTTagCompound group = new NBTTagCompound();

		group.setString("groupName", taskGroupName);
		list.add(taskGroupName);
		ArrayList<TaskBase> taskList = getTaskList(taskGroupName);
		Short taskNum = (short) taskList.size();
		group.setShort("taskNum", taskNum);
		list.add(String.valueOf(taskNum));
		for (short i = 0; i < taskNum; i++) {
			NBTTagCompound task = new NBTTagCompound("task");
			task.setString("taskName", taskList.get(i).getName());
			list.add(taskList.get(i).getName());
			 task.setString("taskType", taskList.get(i).getType().toString());
			 list.add(taskList.get(i).getType().toString());
			 task.setString("taskContext", taskList.get(i).getContext());
			 list.add(taskList.get(i).getContext());
			group.setCompoundTag("task_" + i, task);

		}
		playerNBT.setCompoundTag("taskGroup", group);
		nbt.setCompoundTag(playerNBT.getName(), playerNBT);
		PacketSynTaskInfo.synTaskInfo(list, player, true,1);
	}


	public TaskSavedData(String par1Str) {
		super(par1Str);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		if(nbttagcompound.hasKey("nbtTaskList")){
			NBTTagCompound nbtTaskList=nbttagcompound.getCompoundTag("nbtTaskList");
			int listSize=nbtTaskList.getInteger("listSize");
			for(int i=0;i<listSize;i++){
				NBTTagCompound nbtTempMap = nbtTaskList.getCompoundTag("task_"+i);
				String name=nbtTempMap.getString("name");
				String groupName=nbtTempMap.getString("group");
				String type=nbtTempMap.getString("type");
				if(type.equals("Place")){
					this.addTasktoList(name, new TaskPlace(name, groupName, nbtTempMap.getInteger("x"), nbtTempMap.getInteger("y"), nbtTempMap.getInteger("z")), true, false);
				}
				else if(type.equals("Search")){
					this.addTasktoList(name,new TaskSearch(name, groupName, nbtTempMap.getString("context")), true, false);
				}
				else if(type.equals("Kill")){
					this.addTasktoList(name,new TaskKill(name, groupName, nbtTempMap.getString("context")), true, false);
				}
			}
		}
		
		if(nbttagcompound.hasKey("nbtGroupReword")){
			NBTTagCompound nbtGroupReword=nbttagcompound.getCompoundTag("nbtGroupReword");
			int listSize=nbtGroupReword.getInteger("nbtGroupRewordSize");
			for(int i=0;i<listSize;i++){
				NBTTagCompound nbtTempMap = nbtGroupReword.getCompoundTag("GroupReword_"+i);
				String name=nbtTempMap.getString("name");
				String reword=nbtTempMap.getString("reword");
				this.addRewordtoGroup(name, reword, true, false);
			
			}
		}

		
		
		
		if(nbttagcompound.hasKey("nbtTaskKey")){
			NBTTagCompound nbtTaskKey=nbttagcompound.getCompoundTag("nbtTaskKey");
			int taskkeySize=nbtTaskKey.getInteger("taskkeySize");
			for(int i=0;i<taskkeySize;i++){
				this.addTasktoKeySet(nbtTaskKey.getString("task_"+i), true, false);
			}
		}
		
		if(nbttagcompound.hasKey("nbtTaskGroupKey")){
			NBTTagCompound nbtTaskKey=nbttagcompound.getCompoundTag("nbtTaskGroupKey");
			int taskkeySize=nbtTaskKey.getInteger("taskGroupKeySize");
			for(int i=0;i<taskkeySize;i++){
				this.addTasktoGroupKey(nbtTaskKey.getString("taskGroup_"+i),true, false);
			}
		}


	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {

		NBTTagCompound nbtTaskList = new NBTTagCompound("nbtTaskList");
		int k=0;
		nbtTaskList.setInteger("listSize", this.getTaskinList().size());
		for (Entry<String, TaskBase> tem : this.getTaskinList().entrySet()) {
			NBTTagCompound nbtTempMap = new NBTTagCompound("task_"+k);
			nbtTempMap.setString("name",tem.getKey());
			nbtTempMap.setString("group",tem.getValue().getGroup());
			nbtTempMap.setString("type", tem.getValue().getType().toString());
			if (tem.getValue().getType().equals(TaskType.Place)) {
				TaskPlace place = (TaskPlace) tem.getValue();
				ChunkCoordinates pos = place.getPos();
				nbtTempMap.setInteger("x", pos.posX);
				nbtTempMap.setInteger("y", pos.posY);
				nbtTempMap.setInteger("z", pos.posZ);
			} else if (tem.getValue().getType().equals(TaskType.Search)) {
				TaskSearch search = (TaskSearch) tem.getValue();
				nbtTempMap.setString("context", search.getContext());
			} else if (tem.getValue().getType().equals(TaskType.Kill)) {
				TaskKill kill = (TaskKill) tem.getValue();
				nbtTempMap.setString("context", kill.getContext());
			}
			nbtTaskList.setCompoundTag(nbtTempMap.getName(), nbtTempMap);
			k++;

		}
		nbttagcompound.setCompoundTag(nbtTaskList.getName(), nbtTaskList);
		
		NBTTagCompound nbtGroupReword = new NBTTagCompound("nbtGroupReword");
		int l=0;
		nbtGroupReword.setInteger("nbtGroupRewordSize", this.getRewordinTask().size());
		for (Entry<String, String> tem : this.getRewordinTask().entrySet()) {
			NBTTagCompound nbtTempMap = new NBTTagCompound("GroupReword_"+l);
			nbtTempMap.setString("name",tem.getKey());
			nbtTempMap.setString("reword",tem.getValue());
			nbtGroupReword.setCompoundTag(nbtTempMap.getName(), nbtTempMap);
			l++;

		}
		nbttagcompound.setCompoundTag(nbtGroupReword.getName(), nbtGroupReword);
		
		NBTTagCompound nbtTaskKey = new NBTTagCompound("nbtTaskKey");
		int i = 0;
		nbtTaskKey.setInteger("taskkeySize", this.getTaskinKeySet().size());
		for (String tem : this.getTaskinKeySet()) {
			nbtTaskKey.setString("task_" + i, tem);
			i++;
		}
		nbttagcompound.setCompoundTag(nbtTaskKey.getName(), nbtTaskKey);
		
		NBTTagCompound nbtTaskGroupKey = new NBTTagCompound("nbtTaskGroupKey");
		int j = 0;
		nbtTaskGroupKey.setInteger("taskGroupKeySize", this.getTaskinGroupKey().size());
		for (String tem : this.getTaskinGroupKey()) {
			nbtTaskGroupKey.setString("taskGroup_" + j, tem);
			j++;
		}
		nbttagcompound.setCompoundTag(nbtTaskGroupKey.getName(), nbtTaskGroupKey);
		
		

	}
	
	
	public void removeTask(String taskName){
		this.taskKey.remove(taskName);
		this.taskList.remove(taskName);
		PacketSynTaskData_remove.removeTask(taskName);
		this.markDirty();
	}
	
	


}
