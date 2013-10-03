package mapnpc.client.gui;

import mapnpc.common.network.PacketSpawnNPC;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

public class GuiSpawnNPC extends GuiScreen {
	 /** Text field containing the npc'name */
    private GuiTextField nameTextField;
    private GuiTextField fileTextField;
    private GuiTextField typeTextField;
	GuiButton ok;
	private EntityPlayer player=null;
	private int x;
	private int y;
	private int z;

	public GuiSpawnNPC(EntityPlayer player, int x, int y, int z) {
		this.player=player;
		this.x=x;
		this.y=y;
		this.z=z;
	}

	@Override
	public void updateScreen() {
		 this.typeTextField.updateCursorCounter();
		 this.nameTextField.updateCursorCounter();
		 this.fileTextField.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		
		this.drawString(fontRenderer, "@NPC显示名称、对话代号",  this.width / 2 - 150, 40,  0xFF5600);
		this.nameTextField.drawTextBox();
		
		this.drawString(fontRenderer, "@手上物品的ID",  this.width / 2-25, 80,  0xFF5600);
		this.typeTextField.drawTextBox();
		
		this.fontRenderer.drawString("@皮肤名称",  this.width / 2 + 80, 40, 0xFF5600);
		this.fileTextField.drawTextBox();
		
		super.drawScreen(par1, par2, par3);
	}

    @SuppressWarnings("unchecked")
	public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.ok = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, "完成"));
        this.nameTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 100, 20);
        this.nameTextField.setMaxStringLength(32767);
        this.nameTextField.setFocused(true);
        this.fileTextField= new GuiTextField(this.fontRenderer, this.width / 2 + 50, 60, 100, 20);
        this.fileTextField.setMaxStringLength(32767);
        this.typeTextField= new GuiTextField(this.fontRenderer, this.width / 2 - 50, 100, 100, 20);
        this.typeTextField.setMaxStringLength(32767); 
        this.ok.enabled=(this.typeTextField.getText().trim().length() > 0)&&(this.nameTextField.getText().trim().length()>0);
    }
    @Override
    protected void keyTyped(char par1, int par2)
    { this.nameTextField.textboxKeyTyped(par1, par2);
        this.typeTextField.textboxKeyTyped(par1, par2);
        this.fileTextField.textboxKeyTyped(par1, par2);
       
        this.ok.enabled = (this.fileTextField.getText().trim().length() > 0)&&(this.typeTextField.getText().trim().length() > 0)&&(this.nameTextField.getText().trim().length()>0);

    }

    @Override
	protected void actionPerformed(GuiButton par1GuiButton) {
    	 if (par1GuiButton.enabled)
         {
    		 if (par1GuiButton.id == 0)
             {
    			if(transInt(this.typeTextField.getText())){
                 PacketSpawnNPC.spawnNPC(this.x, this.y, this.z, this.nameTextField.getText(), Integer.parseInt(this.typeTextField.getText()),this.fileTextField.getText());

                 this.mc.displayGuiScreen((GuiScreen)null);
                 }
    			else{
    				player.addChatMessage("输入参数无效");
    				
    			}
             }
         }
	}

	private boolean transInt(String text) {
		for (int i = 0; i < text.length(); i++) {
		    if (!Character.isDigit(text.charAt(i))) {
		        return false;
		    }
		}
		return true;
	}

	/**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.typeTextField.mouseClicked(par1, par2, par3);
        this.nameTextField.mouseClicked(par1, par2, par3);

        this.fileTextField.mouseClicked(par1, par2, par3);}

}
