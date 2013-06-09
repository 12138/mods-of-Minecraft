package FireGrass.common;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
	public boolean loadedMod = false;

	public void onLoad() {
	}
	
	
	
	
	public void registerItem(Item item) {
		GameRegistry.registerItem(item,item.getUnlocalizedName(), "CookerCraft");
	}
	
	public static void addName(Object self, String Commonname, String chinese) {
		LanguageRegistry.addName(self, Commonname);
		LanguageRegistry.instance().addNameForObject(self, "zh_CN", chinese);
	}


		
	
	
	

}
