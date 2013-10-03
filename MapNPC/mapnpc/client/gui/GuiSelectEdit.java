package mapnpc.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiSelectEdit extends GuiScreen {
	EntityPlayer player;
	public GuiSelectEdit(EntityPlayer player) {
		this.player=player;

	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			if (par1GuiButton.id == 0) {

				GuiTaskMenu guiBook = new GuiTaskMenu(this.player);
				FMLClientHandler.instance().showGuiScreen(guiBook);
			}
			else if(par1GuiButton.id == 1) {
				GuiRewordBook guiBook = new GuiRewordBook(this.player);
				FMLClientHandler.instance().showGuiScreen(guiBook);
			}
			else if(par1GuiButton.id == 2) {
			
				FMLClientHandler.instance().showGuiScreen(null);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 4 - 100,
				this.height / 4 , "编辑任务列表"));
		this.buttonList.add(new GuiButton(1, this.width / 4 + 100,
				this.height / 4 , "编辑奖励列表"));
		this.buttonList.add(new GuiButton(2, this.width / 4,
				this.height / 2 , "完成"));

	}
}
