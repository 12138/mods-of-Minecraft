package mapnpc.client.gui;

import mapnpc.common.task.TaskSavedData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiNewTask extends GuiScreen {

    private GuiTextField nameTextField;
	GuiButton ok;
	EntityPlayer player;
	public GuiNewTask(EntityPlayer player) {
		this.player=player;
		
	}

	@Override
	public void updateScreen() {
	
		 this.nameTextField.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.drawString(fontRenderer, "@新任务名称",  this.width / 2 - 120, 50,  0xFF5600);
		this.nameTextField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

    @SuppressWarnings("unchecked")
	public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.ok = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, "OK"));
        this.nameTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 100, 20);
        this.nameTextField.setMaxStringLength(32767);
        this.nameTextField.setFocused(true);
        this.ok.enabled=this.nameTextField.getText().trim().length()>0;
       
    }
    @Override
    protected void keyTyped(char par1, int par2)
    { this.nameTextField.textboxKeyTyped(par1, par2);
       
       
        this.ok.enabled =this.nameTextField.getText().trim().length()>0;

    }

    @Override
	protected void actionPerformed(GuiButton par1GuiButton) {
    	 if (par1GuiButton.enabled)
         {
    		 if (par1GuiButton.id == 0)
             {
    			String name=this.nameTextField.getText();
    			TaskSavedData.forWorld(this.player.worldObj).addTasktoKeySet(name, true, true);
    				 GuiTaskMenu guiBook=new GuiTaskMenu(this.player);
    				 FMLClientHandler.instance().showGuiScreen(guiBook);
    			 }
    			

             }
    		 
         }
	

	@Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.nameTextField.mouseClicked(par1, par2, par3);
    }
}
