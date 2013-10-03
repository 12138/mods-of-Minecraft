package mapnpc.common.task.tasktype;

import net.minecraft.util.ChunkCoordinates;

public class TaskPlace extends TaskBase {

	ChunkCoordinates cooedinates = null;

	public TaskPlace(String taskName, String groupName, int x, int y, int z) {
		super(taskName, groupName);
		this.cooedinates = new ChunkCoordinates(x, y, z);
	}

	@Override
	protected void setType() {
		this.type = TaskType.Place;

	}

	@Override
	public String getContext() {
		return "";
	}

	public void changePos(int x, int y, int z) {

		this.cooedinates = new ChunkCoordinates(x, y, z);
	}

	public ChunkCoordinates getPos() {
		return this.cooedinates;

	}

}
