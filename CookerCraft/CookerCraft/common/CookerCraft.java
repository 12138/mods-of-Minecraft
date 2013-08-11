package CookerCraft.common ;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(serverSideRequired = false, clientSideRequired = true)
@Mod(modid = "CookerCraft", name = "CookerCraft", version = "1.0.0")
public class CookerCraft {
    //item
	public static Item semolina;
	public static int semolinaID;
	public static Item paperBag;
	public static int paperBagID;
	public static Item paperBagwithSemolina;
	public static int paperBagwithSemolinaID;
	public static Item paste;
	public static int pasteID;
	public static Item tastyBread;
	public static int tastyBreadID;
	public static Item sweetPaste;
	public static int sweetPasteID;
	public static Item sweetBread;
	public static int sweetBreadID;
	public static Item chocolatePaste;
	public static int chocolatePasteID;
	public static Item chocolateBread;
	public static int chocolateBreadID;
	public static Item milkPaste;
	public static int milkPasteID;
	public static Item milkBread;
	public static int milkBreadID;
	public static Item sSliceofbread;
	public static int sSliceofbreadID;
	public static Item SliceofBread;
	public static int SliceofBreadID;
	public static Item beefSandwich;
	public static int beefSandwichID;
	public static Item meatSandwich;
	public static int meatSandwichID;
	public static Item chickenSandwich;
	public static int chickenSandwichID;
	public static Item beefHamburger;
	public static int beefHamburgerID;
	public static Item meatHamburger;
	public static int meatHamburgerID;
	public static Item chickenHamburger;
	public static int chickenHamburgerID;
	public static Item wildRice;
	public static int wildRiceID;
	public static Item rice;
	public static int riceID;
	public static Item paddy;
	public static int paddyID;
	public static Item bamboowithPaddy;
	public static int bamboowithPaddyID;
	public static Item bamboowithRich;
	public static int bamboowithRichID;
	public static Item aCupofRice;
	public static int aCupofRiceID;
	public static Item cookedRice;
	public static int cookedRiceID;
	public static Item bamboow;
	public static int bamboowID;
    //block
	public static Block blockWildRice;
	public static int blockWildRiceID;
	public static Block blockRice;
	public static int blockRiceID;
	public static Block blockBamboow;
	public static int blockbamboowID;
	
	public static String ModName="cookercraft";
    //CreativeTabs 
	public static CreativeTabs CookerCraft	= new CreativeTabCooker("CookerCraft");
	
	@Mod.Instance("CookerCraft")
	public static CookerCraft instance;

	@SidedProxy(clientSide = "CookerCraft.client.ClientProxy", serverSide = "CookerCraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		semolinaID=config.getItem("semolina", 9000).getInt();
		paperBagID=config.getItem("paperBag", 9001).getInt();
		paperBagwithSemolinaID=config.getItem("paperBagwithSemolina", 9002).getInt();
		pasteID=config.getItem("paste", 9003).getInt();
		tastyBreadID=config.getItem("tastyBread", 9004).getInt();
		sweetPasteID=config.getItem("sweetPaste", 9005).getInt();
		sweetBreadID=config.getItem("sweetBread", 9006).getInt();
		chocolatePasteID=config.getItem("chocolatePaste", 9007).getInt();
		chocolateBreadID=config.getItem("chocolateBread", 9008).getInt();
		milkPasteID=config.getItem("milkPaste", 9009).getInt();
		milkBreadID=config.getItem("milkBread", 9010).getInt();
		sSliceofbreadID=config.getItem("sSliceofbread", 9011).getInt();
		SliceofBreadID=config.getItem("SliceofBread", 9012).getInt();
		beefSandwichID=config.getItem("beefSandwich", 9013).getInt();
		meatSandwichID=config.getItem("meatSandwich", 9014).getInt();
		chickenSandwichID=config.getItem("chickenSandwich", 9015).getInt();
		beefHamburgerID=config.getItem("beefHamburger", 9016).getInt();
		meatHamburgerID=config.getItem("meatHamburger", 9017).getInt();
		chickenHamburgerID=config.getItem("chickenHamburger", 9018).getInt();
		wildRiceID=config.getItem("wildRice", 9019).getInt();
		riceID=config.getItem("rice", 9020).getInt();
		paddyID=config.getItem("paddy", 9021).getInt();
		bamboowithPaddyID=config.getItem("bamboowithPaddy", 9022).getInt();
		bamboowithRichID=config.getItem("bamboowithRich", 9023).getInt();
		aCupofRiceID=config.getItem("aCupofRice", 9024).getInt();
		cookedRiceID=config.getItem("cookedRice", 9025).getInt();
		bamboowID=config.getItem("bamboow", 9026).getInt();
		
		blockWildRiceID=config.getBlock("blockWildRice", 1555).getInt();
		blockRiceID=config.getBlock("blockRice", 1556).getInt();
		blockbamboowID=config.getBlock("blockBamboow", 1557).getInt();
		
		config.save();
		semolina=new CookerItem(semolinaID).setUnlocalizedName("semolina").setCreativeTab(CookerCraft);
        paperBag=new CookerItem(paperBagID).setUnlocalizedName("paperBag").setCreativeTab(CookerCraft);
        paperBagwithSemolina=new CookerItem(paperBagwithSemolinaID).setUnlocalizedName("paperBagwithSemolina").setCreativeTab(CookerCraft);
        paste=new CookerItem(pasteID).setUnlocalizedName("paste").setCreativeTab(CookerCraft);
        tastyBread=new CookerItemFood(tastyBreadID, 6, false).setUnlocalizedName("tastyBread").setCreativeTab(CookerCraft);
        sweetPaste=new CookerItem(sweetPasteID).setUnlocalizedName("sweetPaste").setCreativeTab(CookerCraft);
        sweetBread=new CookerItemFood(sweetBreadID, 8, false).setUnlocalizedName("sweetBread").setCreativeTab(CookerCraft);
        chocolatePaste=new CookerItem(chocolatePasteID).setUnlocalizedName("chocolatePaste").setCreativeTab(CookerCraft);
        chocolateBread=new CookerItemFood(chocolateBreadID, 8, false).setUnlocalizedName("chocolateBread").setCreativeTab(CookerCraft);
        milkPaste=new CookerItem(milkPasteID).setUnlocalizedName("milkPaste").setCreativeTab(CookerCraft);
        milkBread=new CookerItemFood(milkBreadID, 8, false).setUnlocalizedName("milkBread").setCreativeTab(CookerCraft);
        sSliceofbread=new CookerItemFood(sSliceofbreadID, 2, false).setUnlocalizedName("sSliceofbread").setCreativeTab(CookerCraft);
        
        SliceofBread=new CookerItemFood(SliceofBreadID, 4, false).setUnlocalizedName("SliceofBread").setCreativeTab(CookerCraft);
        beefSandwich=new CookerItemFood(beefSandwichID, 10, true).setUnlocalizedName("beefSandwich").setCreativeTab(CookerCraft); 
        meatSandwich=new CookerItemFood(meatSandwichID, 10, true).setUnlocalizedName("meatSandwich").setCreativeTab(CookerCraft);
        chickenSandwich=new CookerItemFood(chickenSandwichID, 10, true).setUnlocalizedName("chickenSandwich").setCreativeTab(CookerCraft);
        
        beefHamburger=new CookerItemFood(beefHamburgerID, 14, true).setUnlocalizedName("beefHamburger").setCreativeTab(CookerCraft); 
        meatHamburger=new CookerItemFood(meatHamburgerID, 14, true).setUnlocalizedName("meatHamburger").setCreativeTab(CookerCraft);
        
        chickenHamburger=new CookerItemFood(chickenHamburgerID, 14, true).setUnlocalizedName("chickenHamburger").setCreativeTab(CookerCraft); 

        wildRice=new CookerItemSeeds(wildRiceID,blockWildRiceID).setUnlocalizedName("wildRice").setCreativeTab(CookerCraft);
        rice=new CookerItemSeeds(riceID,blockRiceID).setUnlocalizedName("rice").setCreativeTab(CookerCraft);
        paddy=new CookerItem(paddyID).setUnlocalizedName("paddy").setCreativeTab(CookerCraft);
        bamboowithPaddy=new CookerItem(bamboowithPaddyID).setUnlocalizedName("bamboowithPaddy").setCreativeTab(CookerCraft);
        bamboowithRich=new CookerItemFood(bamboowithRichID, 6, false).setUnlocalizedName("bamboowithRich").setCreativeTab(CookerCraft);
        aCupofRice=new CookerItem(aCupofRiceID).setUnlocalizedName("aCupofRice").setCreativeTab(CookerCraft);
        cookedRice=new CookerItemFood(cookedRiceID, 8, false).setUnlocalizedName("cookedRice").setContainerItem(Item.bowlEmpty).setCreativeTab(CookerCraft);
        bamboow=new CookerItemBamboow(bamboowID).setUnlocalizedName("bamboow").setCreativeTab(CookerCraft);
		
        //Defined blocks
		blockBamboow = new CookerBlockBamboow(blockbamboowID, 0).setUnlocalizedName("blockBamboow");
	    blockWildRice=new CookerBlockWildRich(blockWildRiceID).setUnlocalizedName("blockWildRice");
 	    blockRice=new CookerBlockRich(blockRiceID).setUnlocalizedName("blockRice");
 	 //registry blocks
 	 		GameRegistry.registerBlock(blockBamboow, "blockBamboow");
 	 		GameRegistry.registerBlock(blockWildRice, "blockWildRice");
 	 		GameRegistry.registerBlock(blockRice, "blockRice");
 	   CommonProxy.addName(semolina, "Semolina","小麦粉");
		CommonProxy.addName(paperBag, "Paper Bag","纸袋");
		CommonProxy.addName(paperBagwithSemolina, "Paper Bag with Semolina","小麦粉袋");
		CommonProxy.addName(paste, "Paste","面团");
		CommonProxy.addName(tastyBread, "Tasty Bread","精细面包");
		CommonProxy.addName(sweetPaste, "Sweet Paste","甜面团");
		CommonProxy.addName(sweetBread, "Sweet Bread","甜面包");
		CommonProxy.addName(chocolatePaste, "Chocolate Paste","巧克力面团");
		CommonProxy.addName(chocolateBread, "Chocolate Bread","巧克力面包");
		CommonProxy.addName(milkBread, "Milk Bread","牛奶面包");
		CommonProxy.addName(milkPaste, "Milk Paste","牛奶面团");
		CommonProxy.addName(sSliceofbread, "small Slice of Bread","小面包片");
		CommonProxy.addName(SliceofBread, "Slice of Bread","面包片");
		CommonProxy.addName(beefSandwich, "Beef Sandwich","牛肉三明治");
		CommonProxy.addName(meatSandwich, "Meat Sandwich","猪肉三明治");
		CommonProxy.addName(chickenSandwich, "Chicken Sandwich","鸡肉三明治");
		CommonProxy.addName(beefHamburger, "Beef Hamburger","牛肉汉堡");
		CommonProxy.addName(meatHamburger, "Meatbeef Hamburger","猪肉汉堡");
		CommonProxy.addName(chickenHamburger, "Chickenbeef Hamburger","鸡肉汉堡");
		CommonProxy.addName(wildRice, "Wild Rice","野生水稻");
		CommonProxy.addName(rice, "Rice","水稻");
		CommonProxy.addName(paddy, "Paddy","稻米");
		CommonProxy.addName(bamboowithPaddy, "Bamboo with Paddy","竹筒稻米");
		CommonProxy.addName(bamboowithRich, "Bamboo with Rich","竹筒饭");
		CommonProxy.addName(aCupofRice, "a Cup of Rice","一碗稻米");
		CommonProxy.addName(cookedRice, "Cooked Rice","米饭");
		CommonProxy.addName(bamboow, "Bamboow","竹子");
		CommonProxy.addName(blockBamboow, "Bamboow","竹子");
		CommonProxy.addName(blockWildRice, "Block Wild Rice","野生水稻");
		CommonProxy.addName(blockRice, "Block Rice","野生水稻");
		LanguageRegistry.instance().addStringLocalization("itemGroup.CookerCraft", "zh_CN", "厨艺");
	}

	@Mod.EventHandler
	public void onLoad(FMLInitializationEvent event) {

		
		MinecraftForge.EVENT_BUS.register(new CookerEventHandler());
		GameRegistry.registerWorldGenerator(new WorldGenBamboow());
		RegistryRecipes();
		SmeltRecipes();
		
		
		proxy.onLoad();
	}
	
	private void SmeltRecipes() {
		FurnaceRecipes.smelting().addSmelting(paste.itemID, new ItemStack(tastyBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(sweetPaste.itemID, new ItemStack(sweetBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(chocolatePaste.itemID, new ItemStack(chocolateBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(milkPaste.itemID, new ItemStack(milkBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(bamboowithPaddy.itemID, new ItemStack(bamboowithRich,1), 0F);
		FurnaceRecipes.smelting().addSmelting(aCupofRice.itemID, new ItemStack(cookedRice,1), 0F);
		
	}

	private void RegistryRecipes() {
		//TODO add some recipes
		GameRegistry.addShapelessRecipe(new ItemStack(semolina), new Object[]{
			Item.wheat
		});
		GameRegistry.addRecipe(new ItemStack(paperBag,5), new Object[]{
			"# #",
			" # ",
			Character.valueOf('#'),new ItemStack(Item.paper,1),
		});
		GameRegistry.addRecipe(new ItemStack(paperBagwithSemolina,1), new Object[]{
			"###",
			" X ",
			Character.valueOf('#'),new ItemStack(semolina,1),
			Character.valueOf('X'),new ItemStack(paperBag,1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(paste), new Object[]{
			paperBagwithSemolina,paperBagwithSemolina
		});
		GameRegistry.addShapelessRecipe(new ItemStack(sweetPaste), new Object[]{
			paste,Item.sugar
		});
		GameRegistry.addShapelessRecipe(new ItemStack(chocolatePaste), new Object[]{
			paste,Item.sugar,new ItemStack(Item.dyePowder,1,3)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(milkPaste), new Object[]{
			paste,Item.sugar,Item.bucketMilk
		});
		GameRegistry.addShapelessRecipe(new ItemStack(SliceofBread,2), new Object[]{
			tastyBread
		});
		GameRegistry.addRecipe(new ItemStack(meatHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.porkCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(beefHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.beefCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(chickenHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.chickenCooked,1)
		});
		
		GameRegistry.addRecipe(new ItemStack(meatSandwich.itemID,1,0), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.porkCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(beefSandwich,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.beefCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(chickenSandwich,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.chickenCooked,1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(sSliceofbread,8), new Object[]{
			tastyBread,tastyBread
		});
		GameRegistry.addShapelessRecipe(new ItemStack(paddy,1), new Object[]{
			rice
		});
		GameRegistry.addShapelessRecipe(new ItemStack(bamboowithPaddy,1), new Object[]{
			paddy,bamboow
		});
		GameRegistry.addShapelessRecipe(new ItemStack(aCupofRice,1), new Object[]{
			paddy,Item.bowlEmpty
		});
		
		
		
	}


}