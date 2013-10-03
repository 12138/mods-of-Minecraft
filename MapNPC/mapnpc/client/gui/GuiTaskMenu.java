package mapnpc.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import mapnpc.client.gui.component.GuiButtonNextPage;
import mapnpc.common.misc.Resources;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiTaskMenu extends GuiScreen {

	private int cenBookWidth = 81;
	private int sideBookWidth = 36;
	private int senBookWidth = 28;
	private int bookImageHeight = 192;
	private int bookTotalPages = 1;
	public int currPage = 1;
	private List<TaskItem> itemList = new ArrayList<TaskItem>();
	EntityPlayer player;

	public GuiTaskMenu(EntityPlayer par1EntityPlayer) {
		this.player=par1EntityPlayer;
		initTask();
		
	}

	private void initTask() {
		itemList.clear();
		int i = 1;
		int page = 1;
		for (String temp : TaskSavedData.forWorld(this.player.worldObj).getTaskinKeySet()) {
			if (i > 5) {
				i = 1;
				page++;
			}

			if (TaskSavedData.forWorld(this.player.worldObj).getTaskinList().containsKey(temp)) {
				itemList.add(new TaskItem(page, temp, i, true,""));
			} else {
				itemList.add(new TaskItem(page, temp, i, false,""));
			}

			i++;
		}
		bookTotalPages = page;

	}

	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100,
				4 + this.bookImageHeight, 200, 20, StatCollector
						.translateToLocal("gui.done")));

		int i = this.width / 2 - this.sideBookWidth - this.senBookWidth
				- this.cenBookWidth + 15;
		int j = this.width / 2 + this.senBookWidth + this.cenBookWidth;
		byte b0 = 2;
		this.buttonList.add(new GuiButtonNextPage(1, j, b0 + 154, true));
		this.buttonList.add(new GuiButtonNextPage(2, i, b0 + 154, false));
		
		this.buttonList.add(new GuiButton(13, this.width/2+ 150, 15,
				40, 20, "添加"));
		String bCon = "修改";
		String delete = "删除";
		switch (this.getButtonNumIncurPage()) {
		case 5:
			this.buttonList.add(new GuiButton(12, this.width / 2 + 20, 25 * 5,
					40, 20, bCon));
			this.buttonList.add(new GuiButton(11, this.width / 2 + 90, 25 * 5,
					40, 20, delete));
		case 4:
			this.buttonList.add(new GuiButton(10, this.width / 2 + 20, 25 * 4,
					40, 20, bCon));
			this.buttonList.add(new GuiButton(9, this.width / 2 + 90, 25 * 4,
					40, 20, delete));
		case 3:
			this.buttonList.add(new GuiButton(8, this.width / 2 + 20, 25 * 3,
					40, 20, bCon));
			this.buttonList.add(new GuiButton(7, this.width / 2 + 90, 25 * 3,
					40, 20, delete));
		case 2:
			this.buttonList.add(new GuiButton(6, this.width / 2 + 20, 25 * 2,
					40, 20, bCon));
			this.buttonList.add(new GuiButton(5, this.width / 2 + 90, 25 * 2,
					40, 20, delete));
		case 1:
			this.buttonList.add(new GuiButton(4, this.width / 2 + 20, 25, 40,
					20, bCon));
			this.buttonList.add(new GuiButton(3, this.width / 2 + 90, 25, 40,
					20, delete));
		default:
			break;
		}

	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private TaskItem getcurItem(int no) {
		for (TaskItem item : itemList) {
			if (item.getPage() == this.currPage && item.getNoIncur() == no)
				return item;

		}
		return null;

	}

	private int getButtonNumIncurPage() {
		int i = 0;
		for (TaskItem item : itemList) {
			if (item.getPage() == this.currPage)
				i++;

		}
		return i;
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {

		if (par1GuiButton.enabled) {

			switch (par1GuiButton.id) {
			case 0:
				this.mc.displayGuiScreen(new GuiSelectEdit(this.player));
				break;
			case 1:
				if (this.currPage < this.bookTotalPages) {
					++this.currPage;
					this.initGui();
				}
				break;
			case 2:
				if (this.currPage > 1) {
					--this.currPage;
					this.initGui();
					
				}
				break;
			case 3:
				removeItem(1);
				break;
			case 5:
				removeItem(2);
				break;
			case 7:
				removeItem(3);
				break;
			case 9:
				removeItem(4);
				break;
			case 11:
				removeItem(5);
				break;
				
			case 4:
				editTask(1);
				break;
			case 6:
				editTask(2);
				break;
			case 8:
				editTask(3);
				break;
			case 10:
				editTask(4);
			case 12:
				editTask(5);
				
				break;
			case 13:
				addRask();
				break;
			default:
				break;
			}
			

		}
	}

	private void addRask() {
		FMLClientHandler.instance().showGuiScreen(new GuiNewTask(player));
		
	}

	private void editTask(int i) {

FMLClientHandler.instance().showGuiScreen(new GuiEditTask(player,this.getcurItem(i)));
		
	}

	private void removeItem(int i) {
		TaskItem item;
		item = this.getcurItem(i);
		if (item != null) {
			if (item.isSet) {
				if (TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(item.getName()) != null){
					TaskSavedData.forWorld(this.player.worldObj).removeTask(item.getName());
					}
				
			}
		}
		
		this.initTask();
		this.initGui();

	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(Resources.GUI_BOOK);
		int sen1 = this.width / 2 - this.senBookWidth;
		int sen2 = this.width / 2;
		int cen1 = sen1 - this.cenBookWidth;
		int cen2 = sen2 + this.senBookWidth;
		int side1 = cen1 - this.sideBookWidth;
		int side2 = cen2 + this.cenBookWidth;
		byte b0 = 2;
		// draw book gui
		this.drawTexturedModalRect(sen1, b0, 184, 0, this.senBookWidth,
				this.bookImageHeight);
		this.drawTexturedModalRect(sen2, b0, 41, 0, this.senBookWidth,
				this.bookImageHeight);
		this.drawTexturedModalRect(cen1, b0, 85, 0, this.cenBookWidth,
				this.bookImageHeight);
		this.drawTexturedModalRect(cen2, b0, 85, 0, this.cenBookWidth,
				this.bookImageHeight);
		this.drawTexturedModalRect(side1, b0, 0, 0, this.sideBookWidth,
				this.bookImageHeight);
		this.drawTexturedModalRect(side2, b0, 220, 0, this.sideBookWidth,
				this.bookImageHeight);
		String taskName = "任务名称";
		fontRenderer.drawString(taskName,
				side1 + 31 - fontRenderer.getStringWidth(taskName) / 2, 10, 1);
		String taskType = "任务类型";
		fontRenderer.drawString(taskType,
				side1 + 91 - fontRenderer.getStringWidth(taskName) / 2, 10, 1);

		drawContext(side1, side2);
		super.drawScreen(par1, par2, par3);
	}

	private void drawContext(int side1, int side2) {
		int i = 0;

		for (TaskItem item : itemList) {

			if (item.getPage() == this.currPage) {
				fontRenderer.drawString(item.getName(), side1 + 10,
						25 + i * 25, 4210752);
				if (item.isSet()) {
					try {
						
				
					TaskBase task = TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(item.getName());
//					if(task==null){
//						System.out.println("NULL");
//					}
					String type = task.getType().name();
					fontRenderer.drawString(type,
							side1 + 91 - fontRenderer.getStringWidth(type) / 2,
							25 + i * 25, 4210752);
					} catch (Exception e) {
						for(Entry<String, TaskBase> temp:TaskSavedData.forWorld(this.player.worldObj).getTaskinList().entrySet()){
							System.out.println(temp.getKey());
							System.out.println(temp.getValue().getName());
						}
						e.printStackTrace();
					}
					} else {
					fontRenderer.drawString("未设置",side1+ 91- fontRenderer.getStringWidth("未设置") / 2,25 + i * 25, 4210752);
				}
				i++;
			}
		}
	}

	class TaskItem {
		private int page;
		private String name;
		private boolean isSet;
		private int noIncur;
		private String group;

		public int getNoIncur() {
			return noIncur;
		}

		public void setNoIncur(int noIncur) {
			this.noIncur = noIncur;
		}

		public TaskItem(int page, String name, int no, boolean isSet,String group) {

			this.page=page;
			this.name=name;
			this.isSet=isSet;
			this.noIncur=no;
			this.group=group;

		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isSet() {
			return isSet;
		}

		public void setSet(boolean isSet) {
			this.isSet = isSet;
		}

		public String getGroup() {
			
			return this.group;
		}
		public void setGroup(String group) {
			this.group=group;
		}
	}
}
