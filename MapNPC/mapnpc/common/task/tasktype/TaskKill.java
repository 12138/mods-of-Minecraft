package mapnpc.common.task.tasktype;

public class TaskKill extends TaskBase {
	
	public TaskKill(String taskName,String group, String context) {
		super(taskName, group);
		this.setContext(context);
	}

	@Override
	protected void setType() {
		this.type=TaskType.Kill;

	}

}
