package mapnpc.client.gui;

import mapnpc.client.gui.GuiTaskMenu.TaskItem;
import mapnpc.common.network.PacketSpawnRadio;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskSearch;
import mapnpc.common.task.tasktype.TaskType;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiEditTask extends GuiScreen {
	
	 private GuiTextField nameTextField;
    private GuiTextField typeTextField;
    private GuiTextField contextTextField;
    private GuiTextField groupTextField;
	GuiButton ok;
	private EntityPlayer player=null;
	private TaskItem task;
	public GuiEditTask(EntityPlayer player, TaskItem curItem) {
		this.player=player;
		this.task=curItem;
		
	}

	@Override
	public void updateScreen() {
		 this.nameTextField.updateCursorCounter();
		 this.groupTextField.updateCursorCounter();
		 this.contextTextField.updateCursorCounter();
		 this.typeTextField.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.drawString(fontRenderer, "@任务名称",  this.width / 2-150+50-this.fontRenderer.getStringWidth("@任务名称")/2, 20,  0xFF5600);
		this.nameTextField.drawTextBox();
		this.drawString(fontRenderer, "@任务所属任务组",  this.width / 2+50+50-this.fontRenderer.getStringWidth("@任务所属任务组")/2, 20,  0xFF5600);
		this.groupTextField.drawTextBox();
		this.drawString(fontRenderer, "@任务类型",  this.width / 2-150+50-this.fontRenderer.getStringWidth("@任务类型")/2, 90,  0xFF5600);
		this.typeTextField.drawTextBox();
		this.drawString(fontRenderer, "@任务内容,地点类留空",  this.width / 2+50+50-this.fontRenderer.getStringWidth("@任务内容,地点类留空")/2, 90,  0xFF5600);
		this.contextTextField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

    @SuppressWarnings("unchecked")
	public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.ok = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, "完成"));
       
        this.nameTextField= new GuiTextField(this.fontRenderer, this.width / 2 -150, 30, 100, 20);
        this.nameTextField.setMaxStringLength(32767);
        this.nameTextField.setFocused(true);
        
        this.groupTextField = new GuiTextField(this.fontRenderer, this.width / 2 + 50, 30, 100, 20);
        this.groupTextField.setMaxStringLength(32767);
       
        this.typeTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 100, 100, 20);
        this.typeTextField.setMaxStringLength(32767);
       
        this.contextTextField= new GuiTextField(this.fontRenderer, this.width / 2 + 50, 100, 100, 20);
        this.contextTextField.setMaxStringLength(32767);
        
        this.ok.enabled=this.typeTextField.getText().trim().length()>0;
        this.nameTextField.setText(this.task.getName());
        if(TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get((this.task.getName()))!=null){
			this.typeTextField.setText(TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(this.task.getName()).getType().toString());
			this.contextTextField.setText(TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(this.task.getName()).getContext());
			this.groupTextField.setText(TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(this.task.getName()).getGroup());
			
		}
    }
    @Override
    protected void keyTyped(char par1, int par2)
    { 
    	this.nameTextField.textboxKeyTyped(par1, par2);
        this.groupTextField.textboxKeyTyped(par1, par2);
        this.typeTextField.textboxKeyTyped(par1, par2);
        this.contextTextField.textboxKeyTyped(par1, par2);
       
        this.ok.enabled =this.typeTextField.getText().trim().length()>0;

    }

    @Override
	protected void actionPerformed(GuiButton par1GuiButton) {
    	 if (par1GuiButton.enabled)
         {
    		 if (par1GuiButton.id == 0)
             {
    			 if(!this.nameTextField.getText().equals(this.task.getName())){
    				TaskSavedData.forWorld(this.player.worldObj).getTaskinKeySet().remove(this.task.getName());
    				 this.task.setName(this.nameTextField.getText());
    				 TaskSavedData.forWorld(this.player.worldObj).getTaskinKeySet().add(this.nameTextField.getText());
    			 }
    			 if(!TaskSavedData.forWorld(this.player.worldObj).getTaskinGroupKey().contains(this.groupTextField.getText())){
    				 player.addChatMessage("输入参数无效");
					 return;
    			 }
    			 else{
    				 this.task.setGroup(this.groupTextField.getText());
    			 }
    			 if(this.typeTextField.getText().equals(TaskType.Place.name())){
    				 spawnRadio();
    				 GuiTaskMenu guiBook=new GuiTaskMenu(this.player);
    				 guiBook.currPage=task.getPage();
//    				 TaskSavedData.forWorld(this.player.worldObj).removeTask(this.task.getName());

    				 FMLClientHandler.instance().showGuiScreen(guiBook);
    			 }
    			 else{
    				 if(this.contextTextField.getText().isEmpty()){
    					 player.addChatMessage("输入参数无效");
    					 return;
    					 }
    				 if( TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(this.task.getName())!=null)
    				 TaskSavedData.forWorld(this.player.worldObj).getTaskinList().get(this.task.getName()).setContext(this.contextTextField.getText());
    				if(this.typeTextField.getText().equals(TaskType.Search.name())){
    					
    				TaskSavedData.forWorld(this.player.worldObj).addTasktoList(this.task.getName(), new TaskSearch(this.task.getName(), this.task.getGroup(),this.contextTextField.getText()), true, true);	
    				}
    				else if(this.typeTextField.getText().equals(TaskType.Kill.name())){
        				TaskSavedData.forWorld(this.player.worldObj).addTasktoList(this.task.getName(), new TaskKill(this.task.getName(), this.task.getGroup(),this.contextTextField.getText()), true, true);
    				}
    				
    				 GuiTaskMenu guiBook=new GuiTaskMenu(this.player);
    				 guiBook.currPage=task.getPage();
//    				 TaskSavedData.getTaskinList().remove(this.task.getName());
//    					TaskSavedData.getTaskinKeySet().remove(this.task.getName());
    				 //BUG
    				 FMLClientHandler.instance().showGuiScreen(guiBook);
    				 
    			 }

             }
    		 
         }
	}

	
	private void spawnRadio() {
		PacketSpawnRadio.spawnRadio(this.task.getName(),this.task.getGroup(),this.player.posX,this.player.posY, this.player.posZ);
	}

	@Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.nameTextField.mouseClicked(par1, par2, par3);
        this.groupTextField.mouseClicked(par1, par2, par3);
        this.contextTextField.mouseClicked(par1, par2, par3);
        this.typeTextField.mouseClicked(par1, par2, par3);
    }
}
