package mapnpc.common.misc;

import java.util.HashMap;
import java.util.Map;

public class NPC {
	private String name;
	private Map<String, Dialog> dialogs = new HashMap<String, Dialog>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Dialog getDialog(String id) {

		return dialogs.get(id);
	}

	public Dialog setDialog(String id, Dialog dialog) {

		return dialogs.put(id, dialog);
	}
	public class Dialog{
		private Map<String, Option> options = new HashMap<String, Option>();
		protected String id;
		protected String to;

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		private int optionNum = 0;
		private String content;

		public Option getOptions(String id) {
			return options.get(id);
		}

		public void setOptions(String id, Option option) {
			this.options.put(id, option);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public void optionNumPlus() {
			optionNum++;
		}

		public int getOptionNum() {
			return this.optionNum;
		}

		public boolean hasOption() {
			return optionNum > 0;
		}
		
		public class Option extends Dialog{
			protected String id;
			private String content;
			protected String to;
			private boolean hasTask = false;
			private boolean hasReply = false;
			private String task;
			private String reply;
			public String getReply() {
				return reply;
			}

			public void setReply(String reply) {
				this.reply = reply;
			}

			

			public void setHasTask() {
				hasTask = true;

			}
			public void setHasReply() {
				hasReply = true;

			}

			public String getTask() {
				return task;
			}

			public void setTask(String task) {
				this.task = task;
			}

			public boolean hasTask() {
				return hasTask;

			}
			public boolean hasReply() {
				return hasReply;

			}

			
			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

		
		}
	}
}
