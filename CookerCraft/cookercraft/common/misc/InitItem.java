package cookercraft.common.misc;

import cookercraft.common.CookerCraft;
import cookercraft.common.blocks.CookerItemBamboow;
import cookercraft.common.items.CookerItem;
import cookercraft.common.items.CookerItemFood;
import cookercraft.common.items.CookerItemSeeds;
import net.minecraft.item.Item;

public class InitItem {

	public static Item semolina;
	public static Item paperBag;
	public static Item paperBagwithSemolina;
	public static Item paste;
	public static Item tastyBread;
	public static Item sweetPaste;
	public static Item sweetBread;
	public static Item chocolatePaste;
	public static Item chocolateBread;
	public static Item milkPaste;
	public static Item milkBread;
	public static Item sSliceofbread;
	public static Item SliceofBread;
	public static Item beefSandwich;
	public static Item meatSandwich;
	public static Item chickenSandwich;
	public static Item beefHamburger;
	public static Item meatHamburger;
	public static Item chickenHamburger;
	public static Item wildRice;
	public static Item rice;
	public static Item paddy;
	public static Item bamboowithPaddy;
	public static Item bamboowithRich;
	public static Item aCupofRice;
	public static Item cookedRice;
	public static Item bamboow;
	
	
	public void initItem() {
		InitItem.semolina=new CookerItem(Config.semolinaID).setUnlocalizedName("semolina");
        InitItem.paperBag=new CookerItem(Config.paperBagID).setUnlocalizedName("paperBag");
        InitItem.paperBagwithSemolina=new CookerItem(Config.paperBagwithSemolinaID).setUnlocalizedName("paperBagwithSemolina");
        InitItem.paste=new CookerItem(Config.pasteID).setUnlocalizedName("paste");
        InitItem.tastyBread=new CookerItemFood(Config.tastyBreadID, 6, false).setUnlocalizedName("tastyBread");
        InitItem.sweetPaste=new CookerItem(Config.sweetPasteID).setUnlocalizedName("sweetPaste");
        InitItem.sweetBread=new CookerItemFood(Config.sweetBreadID, 8, false).setUnlocalizedName("sweetBread");
        InitItem.chocolatePaste=new CookerItem(Config.chocolatePasteID).setUnlocalizedName("chocolatePaste");
        InitItem.chocolateBread=new CookerItemFood(Config.chocolateBreadID, 8, false).setUnlocalizedName("chocolateBread");
        InitItem.milkPaste=new CookerItem(Config.milkPasteID).setUnlocalizedName("milkPaste");
        InitItem.milkBread=new CookerItemFood(Config.milkBreadID, 8, false).setUnlocalizedName("milkBread");
        InitItem.sSliceofbread=new CookerItemFood(Config.sSliceofbreadID, 2, false).setUnlocalizedName("sSliceofbread");
        
        InitItem.SliceofBread=new CookerItemFood(Config.SliceofBreadID, 4, false).setUnlocalizedName("SliceofBread");
        InitItem.beefSandwich=new CookerItemFood(Config.beefSandwichID, 10, true).setUnlocalizedName("beefSandwich"); 
        InitItem.meatSandwich=new CookerItemFood(Config.meatSandwichID, 10, true).setUnlocalizedName("meatSandwich");
        InitItem.chickenSandwich=new CookerItemFood(Config.chickenSandwichID, 10, true).setUnlocalizedName("chickenSandwich");
        
        InitItem.beefHamburger=new CookerItemFood(Config.beefHamburgerID, 14, true).setUnlocalizedName("beefHamburger"); 
        InitItem.meatHamburger=new CookerItemFood(Config.meatHamburgerID, 14, true).setUnlocalizedName("meatHamburger");
        
        InitItem.chickenHamburger=new CookerItemFood(Config.chickenHamburgerID, 14, true).setUnlocalizedName("chickenHamburger"); 

        InitItem.wildRice=new CookerItemSeeds(Config.wildRiceID,Config.blockWildRiceID).setUnlocalizedName("wildRice");
        InitItem.rice=new CookerItemSeeds(Config.riceID,Config.blockRiceID).setUnlocalizedName("rice");
        InitItem.paddy=new CookerItem(Config.paddyID).setUnlocalizedName("paddy");
        InitItem.bamboowithPaddy=new CookerItem(Config.bamboowithPaddyID).setUnlocalizedName("bamboowithPaddy");
        InitItem.bamboowithRich=new CookerItemFood(Config.bamboowithRichID, 6, false).setUnlocalizedName("bamboowithRich");
        InitItem.aCupofRice=new CookerItem(Config.aCupofRiceID).setUnlocalizedName("aCupofRice");
        InitItem.cookedRice=new CookerItemFood(Config.cookedRiceID, 8, false).setUnlocalizedName("cookedRice").setContainerItem(Item.bowlEmpty);
        InitItem.bamboow=new CookerItemBamboow(Config.bamboowID).setUnlocalizedName("bamboow");
		registerItems();
	}


	private void registerItems() {
		CookerCraft.proxy.registerAndName(InitItem.semolina, "Semolina","小麦粉");
		CookerCraft.proxy.registerAndName(InitItem.paperBag, "Paper Bag","纸袋");
		CookerCraft.proxy.registerAndName(InitItem.paperBagwithSemolina, "Paper Bag with Semolina","小麦粉袋");
		CookerCraft.proxy.registerAndName(InitItem.paste, "Paste","面团");
		CookerCraft.proxy.registerAndName(InitItem.tastyBread, "Tasty Bread","精细面包");
		CookerCraft.proxy.registerAndName(InitItem.sweetPaste, "Sweet Paste","甜面团");
		CookerCraft.proxy.registerAndName(InitItem.sweetBread, "Sweet Bread","甜面包");
		CookerCraft.proxy.registerAndName(InitItem.chocolatePaste, "Chocolate Paste","巧克力面团");
		CookerCraft.proxy.registerAndName(InitItem.chocolateBread, "Chocolate Bread","巧克力面包");
		CookerCraft.proxy.registerAndName(InitItem.milkBread, "Milk Bread","牛奶面包");
		CookerCraft.proxy.registerAndName(InitItem.milkPaste, "Milk Paste","牛奶面团");
		CookerCraft.proxy.registerAndName(InitItem.sSliceofbread, "small Slice of Bread","小面包片");
		CookerCraft.proxy.registerAndName(InitItem.SliceofBread, "Slice of Bread","面包片");
		CookerCraft.proxy.registerAndName(InitItem.beefSandwich, "Beef Sandwich","牛肉三明治");
		CookerCraft.proxy.registerAndName(InitItem.meatSandwich, "Meat Sandwich","猪肉三明治");
		CookerCraft.proxy.registerAndName(InitItem.chickenSandwich, "Chicken Sandwich","鸡肉三明治");
		CookerCraft.proxy.registerAndName(InitItem.beefHamburger, "Beef Hamburger","牛肉汉堡");
		CookerCraft.proxy.registerAndName(InitItem.meatHamburger, "Meatbeef Hamburger","猪肉汉堡");
		CookerCraft.proxy.registerAndName(InitItem.chickenHamburger, "Chickenbeef Hamburger","鸡肉汉堡");
		CookerCraft.proxy.registerAndName(InitItem.wildRice, "Wild Rice","野生水稻");
		CookerCraft.proxy.registerAndName(InitItem.rice, "Rice","水稻");
		CookerCraft.proxy.registerAndName(InitItem.paddy, "Paddy","稻米");
		CookerCraft.proxy.registerAndName(InitItem.bamboowithPaddy, "Bamboo with Paddy","竹筒稻米");
		CookerCraft.proxy.registerAndName(InitItem.bamboowithRich, "Bamboo with Rich","竹筒饭");
		CookerCraft.proxy.registerAndName(InitItem.aCupofRice, "a Cup of Rice","一碗稻米");
		CookerCraft.proxy.registerAndName(InitItem.cookedRice, "Cooked Rice","米饭");
		CookerCraft.proxy.registerAndName(InitItem.bamboow, "Bamboow","竹子");
		
	}

}
