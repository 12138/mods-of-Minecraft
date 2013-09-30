package cookercraft.common.misc;

import java.io.File;

import net.minecraftforge.common.Configuration;


public class Config {

	
	//ITEM
	public static int semolinaID;
	public static int paperBagID;
	public static int paperBagwithSemolinaID;
	public static int pasteID;
	public static int tastyBreadID;
	public static int sweetPasteID;
	public static int sweetBreadID;
	public static int chocolatePasteID;
	public static int chocolateBreadID;
	public static int milkPasteID;
	public static int milkBreadID;
	public static int sSliceofbreadID;
	public static int SliceofBreadID;
	public static int beefSandwichID;
	public static int meatSandwichID;
	public static int chickenSandwichID;
	public static int beefHamburgerID;
	public static int meatHamburgerID;
	public static int chickenHamburgerID;
	public static int wildRiceID;
	public static int riceID;
	public static int paddyID;
	public static int bamboowithPaddyID;
	public static int bamboowithRichID;
	public static int aCupofRiceID;
	public static int cookedRiceID;
	public static int bamboowID;
	
	//BLOCK
	public static int blockWildRiceID;
	public static int blockRiceID;
	public static int blockbamboowID;

	public Config(File file) {
		final Configuration config=new Configuration(file);
		hendlerId(config);
		
	}

	private void hendlerId(Configuration config) {
		config.load();
		Config.semolinaID=config.getItem("semolina", 9000).getInt();
		Config.paperBagID=config.getItem("paperBag", 9001).getInt();
		Config.paperBagwithSemolinaID=config.getItem("paperBagwithSemolina", 9002).getInt();
		Config.pasteID=config.getItem("paste", 9003).getInt();
		Config.tastyBreadID=config.getItem("tastyBread", 9004).getInt();
		Config.sweetPasteID=config.getItem("sweetPaste", 9005).getInt();
		Config.sweetBreadID=config.getItem("sweetBread", 9006).getInt();
		Config.chocolatePasteID=config.getItem("chocolatePaste", 9007).getInt();
		Config.chocolateBreadID=config.getItem("chocolateBread", 9008).getInt();
		Config.milkPasteID=config.getItem("milkPaste", 9009).getInt();
		Config.milkBreadID=config.getItem("milkBread", 9010).getInt();
		Config.sSliceofbreadID=config.getItem("sSliceofbread", 9011).getInt();
		Config.SliceofBreadID=config.getItem("SliceofBread", 9012).getInt();
		Config.beefSandwichID=config.getItem("beefSandwich", 9013).getInt();
		Config.meatSandwichID=config.getItem("meatSandwich", 9014).getInt();
		Config.chickenSandwichID=config.getItem("chickenSandwich", 9015).getInt();
		Config.beefHamburgerID=config.getItem("beefHamburger", 9016).getInt();
		Config.meatHamburgerID=config.getItem("meatHamburger", 9017).getInt();
		Config.chickenHamburgerID=config.getItem("chickenHamburger", 9018).getInt();
		Config.wildRiceID=config.getItem("wildRice", 9019).getInt();
		Config.riceID=config.getItem("rice", 9020).getInt();
		Config.paddyID=config.getItem("paddy", 9021).getInt();
		Config.bamboowithPaddyID=config.getItem("bamboowithPaddy", 9022).getInt();
		Config.bamboowithRichID=config.getItem("bamboowithRich", 9023).getInt();
		Config.aCupofRiceID=config.getItem("aCupofRice", 9024).getInt();
		Config.cookedRiceID=config.getItem("cookedRice", 9025).getInt();
		Config.bamboowID=config.getItem("bamboow", 9026).getInt();
		
		Config.blockWildRiceID=config.getBlock("blockWildRice", 1555).getInt();
		Config.blockRiceID=config.getBlock("blockRice", 1556).getInt();
		Config.blockbamboowID=config.getBlock("blockBamboow", 1557).getInt();
	
		config.save();
		
	}


}
