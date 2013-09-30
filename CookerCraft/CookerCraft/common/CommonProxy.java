package cookercraft.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {


	public void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName(),CookerCraft.modName);
	}

	public void registerBlock(Block block) {
		GameRegistry.registerBlock(block, ItemBlock.class,block.getUnlocalizedName(), CookerCraft.modName);
	}
	public void registerAndName(Object self, String Commonname, String chinese) {
		if(self instanceof Item){
			registerItem((Item) self);
		}
		else if(self instanceof Block){
			registerBlock((Block) self);
		}
		LanguageRegistry.addName(self, Commonname);
		LanguageRegistry.instance().addNameForObject(self, "zh_CN", chinese);
	}

}
