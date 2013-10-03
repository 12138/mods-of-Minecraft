package mapnpc.client.gui;

import java.util.ArrayList;

import mapnpc.common.entity.npc.EntityMapNPCTask;
import mapnpc.common.misc.NPC;
import mapnpc.common.misc.NPC.Dialog;
import mapnpc.common.misc.Resources;
import mapnpc.common.misc.Utils;
import mapnpc.common.network.PacketNPCData;
import mapnpc.common.network.PacketSpawnItem;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskBase;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskPlace;
import mapnpc.common.task.tasktype.TaskSearch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class GuiNPCDialog extends GuiScreen {
	public EntityPlayer player;
	public EntityMapNPCTask npcInDialog;
	public int sumOfOptionNum = 0;
	private int curDialog = 1;
	private int curOption = 1;
	private NPC.Dialog dialog = null;
	ScaledResolution res = null;

	private FontRenderer fontrenderer = null;
	private NPC npc = null;

	public GuiNPCDialog(EntityPlayer player, EntityMapNPCTask npcInDialog) {

		fontrenderer = Minecraft.getMinecraft().fontRenderer;
		this.player = player;
		this.npcInDialog = npcInDialog;
		this.loadInfo();

	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {

		res = new ScaledResolution(mc.gameSettings, mc.displayWidth,mc.displayHeight);
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		if (this.npc != null) {
			this.drawDefaultBackground();
			mc.renderEngine.bindTexture(Resources.GUI_DIALOG);
			if (!hasInTaskGroup()) {
				dialog = npc.getDialog(String.valueOf(curDialog));
				if (dialog != null) {
					this.sumOfOptionNum = dialog.getOptionNum();
					if (dialog.hasOption()) {

						
						drawDialogFrame(width, height, dialog, true);
						drawContextCover(width, height);
						String diaText = dialog.getContent();
						drawContext(width, height, diaText, true);
						drawOption(width, height, dialog, dialog.getOptionNum());

					} else {

						drawDialogFrame(width, height, dialog, false);
						String diaText = dialog.getContent();
						drawContext(width, height, diaText, true);

					}
				} else {
					this.setDefalut();
				}
			} else {
				
				String diaplayText = "你是要提交任务:"+ this.npcInDialog.getCurTakGroup() + "?";
				drawcomDialogFrame(width, height, diaplayText);
				drawContextCover(width, height);

			}

		}
	}

	private void drawcomDialogFrame(int width, int height, String diaplayText) {
		// draw cover
		drawContextCover(width, height);
		// draw frame
		this.sumOfOptionNum = 2;
		int number = 3;

		for (int i = 0; i < number; i++)
		Utils.drawTexturedModalRectWisely(240,150, width / 2 - 120, i*75, 0, 0,240,75);
		// draw option context
		this.drawString(this.fontrenderer, diaplayText, width / 2 - 120 + 6,12, 0xFFFFFF);
		this.drawString(this.fontrenderer, "是", width / 2 - 120 + 6,75 + 4 + 8, 0xFFFFFF);
		this.drawString(this.fontrenderer, "否", width / 2 - 120 + 6,75 * 2 + 4 + 8, 0xFFFFFF);

	}

	private boolean hasInTaskGroup() {
		String groupName = this.npcInDialog.getCurTakGroup();
		NBTTagCompound nbt = this.player.getEntityData();
		if (nbt.hasKey(this.player.username)) {

			NBTTagCompound playerNBT = nbt.getCompoundTag(this.player.username);
			NBTTagCompound taskGroup = playerNBT.getCompoundTag("taskGroup");
			if (taskGroup.hasNoTags())
				return false;
			String taskGroupName = taskGroup.getString("groupName");
			if (groupName.equals(taskGroupName)) {

				return true;
			}
			return false;

		}
		return false;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private void drawOption(int width, int height, Dialog dialog, int optionNum) {
		for (int i = 1; i <= optionNum; i++) {
			String temp = dialog.getOptions(String.valueOf(i)).getContent();
			int l = temp.length();
			int limilW = 26;
			int num = 0;
			if (l > limilW) {
				num = (l / limilW) + (l % limilW == 0 ? 0 : 1);
				int sence = limilW;

				for (int j = 0; j < num; j++) {
					String temp1 = null;
					if (j == num - 1) {
						temp = temp.substring(0 + j * sence);
					} else {
						temp = temp.substring(0 + j * sence, (j + 1) * sence);
					}
					this.drawString(this.fontrenderer, temp1,width / 2 - 120 + 6, 75 * i + 4 + 8 + 10 * j,0xFFFFFF);
				}
			} else
				this.drawString(this.fontrenderer, temp, width / 2 - 120 + 6,75 * i + 4 + 8, 0xFFFFFF);
		}

	}

	private void drawContextCover(int width, int height) {
		mc.renderEngine.bindTexture(Resources.GUI_DIALOG);
		Utils.drawTexturedModalRectWisely(240,150, width / 2 - 120, curOption *75, 0, 225,240,75);
	}

	private void drawDialogFrame(int width, int height, Dialog dialog,
			boolean hasOption) {
		
		if (hasOption) {
			int number = dialog.getOptionNum() + 1;

			for (int i = 0; i < number; i++)
			Utils.drawTexturedModalRectWisely(240,150, width / 2 - 120, i*75, 0, 0,240,75);

		} else {
			Utils.drawTexturedModalRectWisely(240,150, width / 2 - 120, 0, 0, 0,240,75);

		}

	}

	private void drawContext(int width, int height, String diaText, boolean full) {
		int l = diaText.length();
		int limilW = 26;
		int num = 0;
		if (l > limilW) {
			num = (l / limilW) + (l % limilW == 0 ? 0 : 1);
			int sence = limilW;

			for (int i = 0; i < num; i++) {
				String temp = null;
				if (i == num - 1) {
					temp = diaText.substring(0 + i * sence);
				} else {
					temp = diaText.substring(0 + i * sence, (i + 1) * sence);
				}
				this.drawString(this.fontrenderer, temp, width / 2 - 120 + 6,
						4 + 10 * i, 0xFFFFFF);
			}
		} else
			this.drawString(this.fontrenderer, diaText, width / 2 - 120 + 6, 4,
					0xFFFFFF);
//this.fontrenderer.drawSplitString(diaText, width / 2 - 120 + 6, 4, 240, 0xFFFFFF);
		
	}

	public void preOption() {
		this.curOption--;
		if (curOption < 1)
			curOption = sumOfOptionNum;
	}

	public void nestOption() {
		this.curOption++;
		if (curOption > sumOfOptionNum)
			curOption = 1;
	}

	@Override
	public void onGuiClosed() {
		saveDialogStatusInNPC();
	}

	public void proceed() {
		if (hasInTaskGroup()) {
			if (this.curOption == 1) {
				if (referTask()) {
					this.npcInDialog.setMarkType(0);
					PacketNPCData.transData(3, this.npcInDialog.entityId,
							String.valueOf(0));
					reword();
					this.mc.displayGuiScreen((GuiScreen) null);
				} else {
					this.player.addChatMessage("任务未完成");
				}

			} else if (this.curOption == 2) {
				this.mc.displayGuiScreen((GuiScreen) null);
			}
			this.curOption = 1;
		} else {
			if (this.dialog != null) {
				if (this.dialog.hasOption()) {
					if (dialog.getOptions(String.valueOf(this.curOption))
							.hasTask()) {
						String to = dialog.getTo();
						this.curDialog = Integer.parseInt(to);
						String taskName = dialog.getOptions(
								String.valueOf(this.curOption)).getTask();
						TaskSavedData.forWorld(this.player.worldObj)
								.startTaskGroup(this.player, taskName);
						saveDialogStatusInNPC();
						if (taskName != null)
							this.player.addChatMessage("接受任务: " + taskName);
						this.npcInDialog.setMarkType(1);
						PacketNPCData.transData(3, this.npcInDialog.entityId,
								String.valueOf(1));
						this.npcInDialog.setCurTakGroup(taskName);
						PacketNPCData.transData(2, this.npcInDialog.entityId,
								taskName);

						if (this.curDialog == -1) {
							this.npcInDialog.setCanTalk(0);
							PacketNPCData.transData(5,
									this.npcInDialog.entityId,
									String.valueOf(0));
						}
						this.mc.displayGuiScreen((GuiScreen) null);
					}
					if (dialog.getOptions(String.valueOf(this.curOption))
							.hasReply()) {
						String to = dialog.getOptions(
								String.valueOf(this.curOption)).getTo();
						this.curDialog = Integer.parseInt(to);
						String reply = dialog.getOptions(
								String.valueOf(this.curOption)).getReply();
						saveDialogStatusInNPC();
						if (reply != null) {
							handlerReply(reply);
						}

						if (this.curDialog == -1) {
							this.npcInDialog.setCanTalk(0);
							PacketNPCData.transData(5,
									this.npcInDialog.entityId,
									String.valueOf(0));

						}

						this.mc.displayGuiScreen((GuiScreen) null);
						return;
					}
					String to = dialog.getOptions(
							String.valueOf(this.curOption)).getTo();
					if (this.curDialog == -1) {
						this.npcInDialog.setCanTalk(0);
						PacketNPCData.transData(5, this.npcInDialog.entityId,
								String.valueOf(0));
						this.mc.displayGuiScreen((GuiScreen) null);
						return;
					}
					if (to.equals("0")) {
						this.mc.displayGuiScreen((GuiScreen) null);
					} else
						this.curDialog = Integer.parseInt(to);
				} else {
					if (this.dialog.getTo().equals("0")) {
						this.mc.displayGuiScreen((GuiScreen) null);
					} else {
						String to = dialog.getTo();
						this.curDialog = Integer.parseInt(to);
						if (this.curDialog == -1) {
							this.npcInDialog.setCanTalk(0);
							PacketNPCData.transData(5,
									this.npcInDialog.entityId,
									String.valueOf(0));
							this.mc.displayGuiScreen((GuiScreen) null);
						}
					}
				}

				this.curOption = 1;
			}
		}

	}

	private void handlerReply(String reply) {
		if (reply.equals("angry")) {
			this.npcInDialog.setAngry(1);
			PacketNPCData.transData(4, this.npcInDialog.entityId,
					String.valueOf(1));
		}

	}

	private void reword() {

		this.player.addChatMessage("恭喜你，完成任务:"
				+ this.npcInDialog.getCurTakGroup());

		String reword = TaskSavedData.forWorld(this.player.worldObj)
				.getRewordinTask().get(this.npcInDialog.getCurTakGroup());
		this.npcInDialog.setCurTakGroup("");
		PacketNPCData.transData(2, this.npcInDialog.entityId, "");
		if (reword == null)
			return;
		String[] any1 = Utils.cutString(reword);
		for (int i = 0; i < any1.length - 1; i++) {
			String[] any2 = Utils.cutString2(any1[i]);
			int itemId = Integer.parseInt(any2[0]);
			if (Item.itemsList[itemId] != null) {
				PacketSpawnItem.spawnItem(itemId, 0, Integer.parseInt(any2[1]),
						this.player.posX, this.player.posY, this.player.posZ);
			}

		}

	}

	private boolean referTask() {
		ArrayList<TaskBase> tasklist = TaskSavedData.forWorld(
				this.player.worldObj).getTaskList(
				this.npcInDialog.getCurTakGroup());
		boolean pass = true;
		for (int i = 0; i < tasklist.size(); i++) {
			switch (tasklist.get(i).getType()) {
			case Kill:
				pass = TaskBase.killed(this.player, (TaskKill) tasklist.get(i));
				break;
			case Search:
				pass = TaskBase.searched(this.player,
						(TaskSearch) tasklist.get(i));
				break;
			case Place:
				pass = TaskBase
						.placed(this.player, (TaskPlace) tasklist.get(i));
				break;

			default:
				break;
			}
			if (!pass)
				return false;
		}
		return true;

	}

	private void saveDialogStatusInNPC() {
		this.npcInDialog.setCurDialog(curDialog);
		PacketNPCData.transData(0, this.npcInDialog.entityId,String.valueOf(this.curDialog));
		this.npcInDialog.setCurOption(curOption);
		PacketNPCData.transData(1, this.npcInDialog.entityId,String.valueOf(this.curOption));

	}

	private void loadInfo() {

		this.curDialog = this.npcInDialog.getCurDialog();
		this.curOption = this.npcInDialog.getCurOption();

	}

	public void setDefalut() {
		this.curDialog = 1;
		this.curOption = 1;
		this.dialog = null;
		this.sumOfOptionNum = 0;
	}

}
