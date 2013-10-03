package mapnpc.client.gui;

import java.util.ArrayList;
import java.util.List;

import mapnpc.client.gui.component.GuiButtonNextPage;
import mapnpc.common.misc.Resources;
import mapnpc.common.task.TaskSavedData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiRewordBook extends GuiScreen {

	private int cenBookWidth = 81;
	private int sideBookWidth = 36;
	private int senBookWidth = 28;
	private int bookImageHeight = 192;
	private int bookTotalPages = 1;
	public int currPage = 1;
	private List<Reword> rewordList = new ArrayList<Reword>();
	EntityPlayer player;

	public GuiRewordBook(EntityPlayer par1EntityPlayer) {
		this.player = par1EntityPlayer;
		initReword();

	}

	private void initReword() {
		rewordList.clear();
		int i = 1;
		int page = 1;
		for (String temp : TaskSavedData.forWorld(this.player.worldObj)
				.getTaskinGroupKey()) {
			if (i > 5) {
				i = 1;
				page++;
			}

			if (TaskSavedData.forWorld(this.player.worldObj).getRewordinTask()
					.containsKey(temp)) {
				rewordList.add(new Reword(page, temp, i, true));
			} else {
				rewordList.add(new Reword(page, temp, i, false));
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
		String bCon = "修改";

		switch (this.getButtonNumIncurPage()) {
		case 5:
			this.buttonList.add(new GuiButton(12, this.width / 2 + 20, 25 * 5,
					40, 20, bCon));
		case 4:
			this.buttonList.add(new GuiButton(10, this.width / 2 + 20, 25 * 4,
					40, 20, bCon));
		case 3:
			this.buttonList.add(new GuiButton(8, this.width / 2 + 20, 25 * 3,
					40, 20, bCon));
		case 2:
			this.buttonList.add(new GuiButton(6, this.width / 2 + 20, 25 * 2,
					40, 20, bCon));
		case 1:
			this.buttonList.add(new GuiButton(4, this.width / 2 + 20, 25, 40,
					20, bCon));
		default:
			break;
		}

	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private Reword getcurReword(int no) {
		for (Reword reword : rewordList) {
			if (reword.getPage() == this.currPage && reword.getNoIncur() == no)
				return reword;

		}
		return null;

	}

	private int getButtonNumIncurPage() {
		int i = 0;
		for (Reword reword : rewordList) {
			if (reword.getPage() == this.currPage)
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
			case 4:
				editReword(1);
				break;
			case 6:
				editReword(2);
				break;
			case 8:
				editReword(3);
				break;
			case 10:
				editReword(4);
				break;
			case 12:
				editReword(5);
				break;
			default:
				break;
			}

		}
	}

	private void editReword(int i) {

		FMLClientHandler.instance().showGuiScreen(
				new GuiEditReword(player, this.getcurReword(i)));

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
		String taskName = "任务Group名称";
		fontRenderer.drawString(taskName,
				side1 + 31 - fontRenderer.getStringWidth(taskName) / 2, 10, 1);
		drawContext(side1, side2);
		super.drawScreen(par1, par2, par3);
	}

	private void drawContext(int side1, int side2) {
		int i = 0;

		for (Reword reword : rewordList) {

			if (reword.getPage() == this.currPage) {
				fontRenderer.drawString(reword.getGroupName(), side1 + 10,
						25 + i * 25, 4210752);
				if (reword.isSet()) {
//					String groupName = TaskSavedData.forWorld(this.player.worldObj).getRewordinTask().get(reword.getGroupName());
//					fontRenderer.drawString(groupName,
//							side1 + 91 - fontRenderer.getStringWidth(groupName) / 2,
//							25 + i * 25, 4210752);
					fontRenderer.drawString("设置",
							side1+ 91 - fontRenderer.getStringWidth("设置") / 2,
							25 + i * 25, 
							4210752);

				} else {
					fontRenderer.drawString("未设置",
									side1+ 91 - fontRenderer.getStringWidth("未设置") / 2,
									25 + i * 25, 
									4210752);
				}
				i++;
			}
		}
	}

	class Reword {
		private int page;
		private String groupName;
		private boolean isSet;
		private int noIncur;

		public int getNoIncur() {
			return noIncur;
		}

		public void setNoIncur(int noIncur) {
			this.noIncur = noIncur;
		}

		public Reword(int page, String groupName, int no, boolean isSet) {

			this.page = page;
			this.groupName = groupName;
			this.isSet = isSet;
			this.noIncur = no;

		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public boolean isSet() {
			return isSet;
		}

		public void setSet(boolean isSet) {
			this.isSet = isSet;
		}

	}
}