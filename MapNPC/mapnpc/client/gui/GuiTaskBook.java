package mapnpc.client.gui;

import mapnpc.client.gui.component.GuiButtonNextPage;
import mapnpc.common.misc.Resources;
import mapnpc.common.misc.Utils;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskBase;
import mapnpc.common.task.tasktype.TaskPlace;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiTaskBook extends GuiScreen {
	EntityPlayer player;
	private int taskWidth = 192;
	private int taskHeight = 192;
	private int curPage = 0;
	private int maxPage = 0;

	public GuiTaskBook(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		
		this.drawTask();
	}

	private void drawTask() {
		this.mc.renderEngine.bindTexture(Resources.GUI_TASK);
//		this.drawTexturedModalRect((this.width - this.taskWidth) / 2, 20, 0, 0,this.taskWidth, this.taskHeight);
		Utils.drawTexturedModalRectWisely(this.taskWidth, this.taskHeight, (this.width - this.taskWidth) / 2, 20, 0, 0,this.taskWidth, this.taskHeight);
		this.drawTaskList();
		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		String usertext=this.player.username+"的任务书";
		this.fontRenderer.drawString(usertext, this.width /4- this.fontRenderer.getStringWidth(usertext)+30, 2, 16744192);
		GL11.glPopMatrix();

	}

	private void drawTaskList() {
		NBTTagCompound nbt = this.player.getEntityData();
		if(nbt.hasKey(this.player.username)){
			NBTTagCompound playerNBT = nbt.getCompoundTag(this.player.username);
			if (!playerNBT.hasKey("taskGroup")) {
				return;
			} else {
				NBTTagCompound taskGroup = playerNBT.getCompoundTag("taskGroup");
				//draw common begin
				String taskGroupName = taskGroup.getString("groupName");
				GL11.glPushMatrix();
				GL11.glScalef(2.0F, 2.0F, 2.0F);
				this.fontRenderer.drawString(taskGroupName, this.width / 4- this.fontRenderer.getStringWidth(taskGroupName), 20, 16744192);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				this.fontRenderer.drawString("分支名:", this.width / 2-this.taskWidth/4- this.fontRenderer.getStringWidth("分支名"), 80, 16744192);
				this.fontRenderer.drawString("描述:", this.width /2-this.taskWidth/4- this.fontRenderer.getStringWidth("描述"), 100, 16744192);
				GL11.glPopMatrix();

				
				//draw common end
				this.maxPage = taskGroup.getShort("taskNum")-1;

				NBTTagCompound task = taskGroup.getCompoundTag("task_"+ this.curPage);
				if (task == null)
					return;
				String taskName = task.getString("taskName");
				this.fontRenderer.drawString(taskName, this.width / 2 - this.fontRenderer.getStringWidth(taskGroupName),80, 16744192);
				drawContext(taskName, task.getString("taskType"),task.getString("taskContext"));
			

			}
		}
		else{return;}
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {

		super.initGui();

		 this.buttonList.add(new GuiButtonNextPage(0, (this.width+this.taskWidth) / 2, this.taskHeight-10, true));
		 this.buttonList.add(new GuiButtonNextPage(1,  (this.width-this.taskWidth) / 2-25, this.taskHeight-10, false));

	}

	private void drawContext(String taskName, String taskType,
			String taskContext) {//WAIT add tip when complete
		
		if (taskType.equals("Place")) {
			TaskBase place = TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(taskName);
			if (place != null && place instanceof TaskPlace) {
				ChunkCoordinates pos = ((TaskPlace) place).getPos();
				if (pos != null) {
					this.fontRenderer.drawString("你需要到(" + pos.posX + ","
							+ pos.posY + "," + pos.posZ + ")",
							this.width / 4 + 60, 110, 16744192);
				}
			}
			if(TaskBase.placed(this.player, (TaskPlace) place)){
				
			}

		} else if (taskType.equals("Kill")) {

			String[] any1 = Utils.cutString(taskContext);
			this.fontRenderer.drawString("你需要杀死:", this.width / 4 + 60, 110, 16744192);
			for (int i = 0; i < any1.length - 1; i++) {
				String[] temp = Utils.cutString2(any1[i]);
				String name = StatCollector.translateToLocal("entity."
						+ temp[0] + ".name");
				this.fontRenderer.drawString(name + ":" + temp[1],
						this.width / 4 + 60, 120 + i * 10, 1);

			}
		} else if (taskType.equals("Search")) {
	
			String[] any1 = Utils.cutString(taskContext);
			this.fontRenderer
					.drawString("你需要get:", this.width / 4 + 60, 110, 16744192);
			for (int i = 0; i < any1.length - 1; i++) {
				String[] temp = Utils.cutString2(any1[i]);
				try {
					int itemId = Integer.parseInt(temp[0]);
					Item item=Item.itemsList[itemId];
					if(item!=null){
					String itemName = item.getUnlocalizedName();
					String locakizedName = itemName == null ? "": StatCollector.translateToLocal(itemName);
					if(locakizedName.contains(".")){
					locakizedName = itemName == null ? "": StatCollector.translateToLocal(itemName+".name");
					}
					this.fontRenderer.drawString(locakizedName + ":" + temp[1],this.width / 4 + 60, 120 + i * 10, 16744192);
					}else
					{this.fontRenderer.drawString("unknow" + ":" + temp[1],this.width / 4 + 60, 120 + i * 10, 16744192);}
				} catch (Exception e) {
					String locakizedName = StatCollector.translateToLocal("item."+temp[0]+".name");
					if(locakizedName.contains(".")){
						locakizedName =StatCollector.translateToLocal("tile."+temp[0]+".name");
						}
					this.fontRenderer.drawString(locakizedName + ":" + temp[1],this.width / 4 + 60, 120 + i * 10, 16744192);

				}


			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		 if (par1GuiButton.enabled)
	        {
	            if (par1GuiButton.id == 0)
	            {
	            	if(this.curPage<this.maxPage)
	                this.curPage++;
	            }
	            else if (par1GuiButton.id == 1)
	            {
	            	if(this.curPage>0)
		                this.curPage--;
	            }
	            
	    }
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	

}
