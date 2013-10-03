package mapnpc.client.gui;

import mapnpc.client.gui.GuiRewordBook.Reword;
import mapnpc.common.task.TaskSavedData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiEditReword extends GuiScreen {

	private GuiTextField rewordTextField;
	GuiButton ok;
	private EntityPlayer player = null;
	private Reword reword;

	public GuiEditReword(EntityPlayer player, Reword reword) {
		this.player = player;
		this.reword = reword;
	}

	@Override
	public void updateScreen() {
		this.rewordTextField.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.drawString(fontRenderer, "@列表",  this.width / 2-20, 40,  0xFF5600);
		String tip="格式为：物品ID*物品数量; 每个奖励之间用;分割";
		this.drawString(fontRenderer,tip ,  (this.width-this.fontRenderer.getStringWidth(tip))/2, 80,  0xFF8242);
		this.rewordTextField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(this.ok = new GuiButton(0, this.width / 2 - 100,this.height / 4 + 96 + 12, "完成"));
		this.rewordTextField = new GuiTextField(this.fontRenderer,this.width / 2 - 50, 50, 100, 20);
		this.rewordTextField.setMaxStringLength(32767);
		this.rewordTextField.setFocused(true);

		this.ok.enabled = this.rewordTextField.getText().trim().length() > 0;

		if (TaskSavedData.forWorld(this.player.worldObj).getRewordinTask()
				.get((this.reword.getGroupName())) != null) {
			this.rewordTextField.setText(TaskSavedData.forWorld(this.player.worldObj).getRewordinTask().get(this.reword.getGroupName()));

		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {

		this.rewordTextField.textboxKeyTyped(par1, par2);

		this.ok.enabled = this.rewordTextField.getText().trim().length() > 0;

	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			if (par1GuiButton.id == 0) {

				if (this.rewordTextField.getText().isEmpty()) {
					player.addChatMessage("输入参数无效");
					return;
				}

				TaskSavedData.forWorld(this.player.worldObj).addRewordtoGroup(
						this.reword.getGroupName(), this.rewordTextField.getText(),
						true, true);

				GuiRewordBook guiBook = new GuiRewordBook(this.player);
				guiBook.currPage = reword.getPage();
				FMLClientHandler.instance().showGuiScreen(guiBook);

			}

		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.rewordTextField.mouseClicked(par1, par2, par3);

	}

}
