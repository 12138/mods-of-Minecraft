package mapnpc.common.task.tasktype;

import mapnpc.common.misc.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;


public abstract class TaskBase {
	
	protected String taskName="";
	protected String context="";
	protected String group="";
	protected TaskType type;
	
	public TaskBase(String taskName, String groupName) {
		this.taskName = taskName;
		this.group=groupName;
		this.setType();
	}

	public void changeName(String name){
		this.taskName=name;
	}
	public String getName(){
		return this.taskName;
	}

	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * 子类赋值
	 * */
	protected abstract void setType();
	
	public TaskType getType() {
		return type;
	}
	
	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	
	
	public static boolean killed(EntityPlayer player,TaskKill taskKill) {
		NBTTagCompound nbt = player.getEntityData();
		if(!nbt.hasKey(player.username)){
			return false;
		}
		NBTTagCompound playerNBT = nbt.getCompoundTag(player.username);
		if (!playerNBT.hasKey("entityCount"))
			return false;
		NBTTagCompound entityCount = playerNBT.getCompoundTag("entityCount");
		String context = taskKill.getContext();
		String[] any1 = Utils.cutString(context);
		for (int i = 0; i < any1.length - 1; i++) {
			String[] any2 = Utils.cutString2(any1[i]);
			if (entityCount.getInteger(any2[0]) != Integer.parseInt(any2[1])) {
				return false;
			}

		}

		return true;
	}
	
	public static  boolean placed(EntityPlayer player,TaskPlace taskPlace) {
		NBTTagCompound nbt = player.getEntityData();
		if(!nbt.hasKey(player.username)){
			return false;
		}
		NBTTagCompound playerNBT = nbt.getCompoundTag(player.username);
		if (!playerNBT.hasKey("completedPlaceTaskList"))
			return false;
		NBTTagCompound completedPlaceTaskList = playerNBT
				.getCompoundTag("completedPlaceTaskList");
		if (completedPlaceTaskList.hasKey(taskPlace.getName())) {
			return completedPlaceTaskList.getBoolean(taskPlace.getName());
		}
		return false;
	}

	public static boolean searched(EntityPlayer player,TaskSearch taskSearch) {
		String context = taskSearch.getContext();
		String[] any1 = Utils.cutString(context);
		for (int i = 0; i < any1.length - 1; i++) {
			String[] any2 = Utils.cutString2(any1[i]);
			int itemId = Integer.parseInt(any2[0]);
			boolean has = Utils.hasItem(player,itemId, Integer.parseInt(any2[1]));
			if (!has)
				return false;

		}
		return true;
	}

	
}
