package mapnpc.common.task.tasktype;

public class TaskSearch extends TaskBase {
	
	
	public TaskSearch(String taskName,String group, String context) {
		super(taskName, group);
		this.setContext(context);
	}

	@Override
	protected void setType() {
		this.type=TaskType.Search;

	}

}
