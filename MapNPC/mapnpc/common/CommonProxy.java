package mapnpc.common;

import mapnpc.common.entity.npc.EntityMapNPCTask;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	public void onLoad() {
		
	}
	public GuiIngame getcurrentGui(){
		return null;
	}
	
	public void registerItem(Item item) {
		GameRegistry.registerItem(item,item.getUnlocalizedName(), "MapNPC");
	}
	public void registerblock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	

	public void addName(Object self, String Commonname, String chinese) {
		LanguageRegistry.addName(self, Commonname);
		LanguageRegistry.instance().addNameForObject(self, "zh_CN", chinese);
	}
	public GuiScreen getNewGui(EntityPlayer player, EntityMapNPCTask lastNpc,int guiId) {
		return null;
	}
	public void loadTextures() {
		
		
	}


}
