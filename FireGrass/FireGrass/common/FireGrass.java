package FireGrass.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(serverSideRequired = true, clientSideRequired = true)
@Mod(modid = "FireGrass", name = "FireGrass", version = "1.0.0")
public class FireGrass {

	public static Item fireGrassFruit;
	public static int fireGrassFruitID;
	
	public static Item fireGrassBottle;
	public static int fireGrassBottleID;
	
	public static Item fireGrassBottleColor;
	public static int fireGrassBottleColorID;
	
	public static Block fireGrass;
	public static int fireGrassID;
	public static FireBlockFluid fireWaterMovingRed;
	public static int fireWaterMovingRedID;
	public static FireBlockFluid fireWaterStillRed;
	public static int fireWaterStillRedID;

	public static FireBlockFluid fireWaterMovingGreen;
	public static int fireWaterMovingGreenID;
	public static FireBlockFluid fireWaterStillGreen;
	public static int fireWaterStillGreenID;

	public static FireBlockFluid fireWaterMovingBlue;
	public static int fireWaterMovingBlueID;
	public static FireBlockFluid fireWaterStillBlue;
	public static int fireWaterStillBlueID;

	public static FireBlockFluid fireWaterMovingBlack;
	public static int fireWaterMovingBlackID;
	public static FireBlockFluid fireWaterStillBlack;
	public static int fireWaterStillBlackID;

	public static FireBlockFluid fireWaterMovingBrown;
	public static int fireWaterMovingBrownID;
	public static FireBlockFluid fireWaterStillBrown;
	public static int fireWaterStillBrownID;

	public static FireBlockFluid fireWaterMovingCyan;
	public static int fireWaterMovingCyanID;
	public static FireBlockFluid fireWaterStillCyan;
	public static int fireWaterStillCyanID;

	public static FireBlockFluid fireWaterMovingLIGHTBLUE;
	public static int fireWaterMovingLIGHTBLUEID;
	public static FireBlockFluid fireWaterStillLIGHTBLUE;
	public static int fireWaterStillLIGHTBLUEID;

	public static FireBlockFluid fireWaterMovingLime;
	public static int fireWaterMovingLimeID;
	public static FireBlockFluid fireWaterStillLime;
	public static int fireWaterStillLimeID;

	public static FireBlockFluid fireWaterMovingMagenta;
	public static int fireWaterMovingMagentaID;
	public static FireBlockFluid fireWaterStillMagenta;
	public static int fireWaterStillMagentaID;

	public static FireBlockFluid fireWaterMovingOrange;
	public static int fireWaterMovingOrangeID;
	public static FireBlockFluid fireWaterStillOrange;
	public static int fireWaterStillOrangeID;

	public static FireBlockFluid fireWaterMovingPink;
	public static int fireWaterMovingPinkID;
	public static FireBlockFluid fireWaterStillPink;
	public static int fireWaterStillPinkID;

	public static FireBlockFluid fireWaterMovingPurple;
	public static int fireWaterMovingPurpleID;
	public static FireBlockFluid fireWaterStillPurple;
	public static int fireWaterStillPurpleID;

	public static FireBlockFluid fireWaterMovingSilver;
	public static int fireWaterMovingSilverID;
	public static FireBlockFluid fireWaterStillSilver;
	public static int fireWaterStillSilverID;

	public static FireBlockFluid fireWaterMovingWhite;
	public static int fireWaterMovingWhiteID;
	public static FireBlockFluid fireWaterStillWhite;
	public static int fireWaterStillWhiteID;

	public static FireBlockFluid fireWaterMovingYellow;
	public static int fireWaterMovingYellowID;
	public static FireBlockFluid fireWaterStillYellow;
	public static int fireWaterStillYellowID;

	public static FireBlockFluid fireWaterMovingGray;
	public static int fireWaterMovingGrayID;
	public static FireBlockFluid fireWaterStillGray;
	public static int fireWaterStillGrayID;

	public static String ModName = "firegrass";
	// CreativeTabs
	public static CreativeTabs FireGrass = new CreativeTabCooker("FireGrass");

	@Mod.Instance("FireGrass")
	public static FireGrass instance;

	@SidedProxy(clientSide = "FireGrass.client.ClientProxy", serverSide = "FireGrass.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		final Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		
		fireGrassFruitID = config.getItem("fireGrassFruit", 10030).getInt();
		fireGrassBottleID=config.getItem("fireGrassBattle", 10031).getInt();
		fireGrassBottleColorID=config.getItem("fireGrassBottleColor", 10032).getInt();
		fireGrassID = config.getBlock("fireGrass", 999).getInt();
		
		fireWaterMovingRedID = config.getBlock("fireWaterMovingRed", 1000).getInt();
		fireWaterStillRedID = config.getBlock("fireWaterStillRed", 1001).getInt();

		fireWaterMovingGreenID = config.getBlock("fireWaterMovingGreen",1002).getInt();
		fireWaterStillGreenID = config.getBlock("fireWaterStillGreen", 1003).getInt();

		fireWaterMovingBlueID = config.getBlock("fireWaterMovingBlue", 1004).getInt();
		fireWaterStillBlueID = config.getBlock("fireWaterStillBlue", 1005).getInt();

		fireWaterMovingBlackID = config.getBlock("fireWaterMovingBlack", 1006).getInt();
		fireWaterStillBlackID = config.getBlock("fireWaterStillBlack", 1007).getInt();

		fireWaterMovingBrownID = config.getBlock("fireWaterMovingBrown", 1008).getInt();
		fireWaterStillBrownID = config.getBlock("fireWaterStillBrown", 1009).getInt();

		fireWaterMovingCyanID = config.getBlock("fireWaterMovingCyan", 1010).getInt();
		fireWaterStillCyanID = config.getBlock("fireWaterStillCyan", 1011).getInt();

		fireWaterMovingLIGHTBLUEID = config.getBlock("fireWaterMovingLIGHTBLUE", 1012).getInt();
		fireWaterStillLIGHTBLUEID = config.getBlock("fireWaterStillLIGHTBLUE",1013).getInt();

		fireWaterMovingLimeID = config.getBlock("fireWaterMovingLime", 1014).getInt();
		fireWaterStillLimeID = config.getBlock("fireWaterStillLime", 1015).getInt();

		fireWaterMovingMagentaID = config.getBlock("fireWaterMovingMagenta",1016).getInt();
		fireWaterStillMagentaID = config.getBlock("fireWaterStillMagenta", 1017).getInt();

		fireWaterMovingOrangeID = config.getBlock("fireWaterMovingOrange", 1018).getInt();
		fireWaterStillOrangeID = config.getBlock("fireWaterStillOrange", 1019).getInt();

		fireWaterMovingPinkID = config.getBlock("fireWaterMovingPink", 1020).getInt();
		fireWaterStillPinkID = config.getBlock("fireWaterStillPink", 1021).getInt();

		fireWaterMovingPurpleID = config.getBlock("fireWaterMovingPurple", 1022).getInt();
		fireWaterStillPurpleID = config.getBlock("fireWaterStillPurple", 1023).getInt();

		fireWaterMovingSilverID = config.getBlock("fireWaterMovingSilver", 1024).getInt();
		fireWaterStillSilverID = config.getBlock("fireWaterStillSilver", 1025).getInt();

		fireWaterMovingWhiteID = config.getBlock("fireWaterMovingWhite", 1026).getInt();
		fireWaterStillWhiteID = config.getBlock("fireWaterStillWhite", 1027).getInt();

		fireWaterMovingYellowID = config.getBlock("fireWaterMovingYellow", 1028).getInt();
		fireWaterStillYellowID = config.getBlock("fireWaterStillYellow", 1029).getInt();

		fireWaterMovingGrayID = config.getBlock("fireWaterMovingGray", 1030).getInt();
		fireWaterStillGrayID = config.getBlock("fireWaterStillGray", 1031).getInt();

		config.save();
		fireGrassFruit = new FireItem(fireGrassFruitID).setUnlocalizedName("fireGrassFruit").setCreativeTab(FireGrass);

		fireGrass = new FireBlockGrass(fireGrassID).setUnlocalizedName("fireGrass").setCreativeTab(FireGrass);

		fireGrassBottleColor=new FireItemColor(fireGrassBottleColorID).setUnlocalizedName("fireGrassBottleColor").setCreativeTab(FireGrass);
		fireGrassBottle=new FireItem(fireGrassBottleID).setUnlocalizedName("fireGrassBottle").setCreativeTab(FireGrass);
		fireWaterMovingRed = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingRedID, ColorList.RED).setUnlocalizedName("fireWaterMovingRed");
		fireWaterStillRed = (FireBlockFluid) new FireBlockStationary(fireWaterStillRedID, ColorList.RED).setUnlocalizedName("fireWaterStillRed");

		fireWaterMovingGreen = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingGreenID, ColorList.GREEN).setUnlocalizedName("fireWaterMovingGreen");
		fireWaterStillGreen = (FireBlockFluid) new FireBlockStationary(fireWaterStillGreenID, ColorList.GREEN).setUnlocalizedName("fireWaterStillGreen");

		fireWaterMovingBlue = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingBlueID, ColorList.BLUE).setUnlocalizedName("fireWaterMovingBlue");
		fireWaterStillBlue = (FireBlockFluid) new FireBlockStationary(fireWaterStillBlueID, ColorList.BLUE).setUnlocalizedName("fireWaterStillBlue");

		fireWaterMovingBlack = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingBlackID, ColorList.BLACK).setUnlocalizedName("fireWaterMovingBlack");
		fireWaterStillBlack = (FireBlockFluid) new FireBlockStationary(fireWaterStillBlackID, ColorList.BLACK).setUnlocalizedName("fireWaterStillBlack");

		fireWaterMovingBrown = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingBrownID, ColorList.BROWN).setUnlocalizedName("fireWaterMovingBrown");
		fireWaterStillBrown = (FireBlockFluid) new FireBlockStationary(fireWaterStillBrownID, ColorList.BROWN).setUnlocalizedName("fireWaterStillBrown");

		fireWaterMovingCyan = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingCyanID, ColorList.CYAN).setUnlocalizedName("fireWaterMovingCyan");
		fireWaterStillCyan = (FireBlockFluid) new FireBlockStationary(fireWaterStillCyanID, ColorList.CYAN).setUnlocalizedName("fireWaterStillCyan");

		fireWaterMovingLIGHTBLUE = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingLIGHTBLUEID, ColorList.LIGHT_BLUE).setUnlocalizedName("fireWaterMovingLIGHTBLUE");
		fireWaterStillLIGHTBLUE = (FireBlockFluid) new FireBlockStationary(fireWaterStillLIGHTBLUEID, ColorList.LIGHT_BLUE).setUnlocalizedName("fireWaterStillLIGHTBLUE");

		fireWaterMovingLime = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingLimeID, ColorList.LIME).setUnlocalizedName("fireWaterMovingLime");
		fireWaterStillLime = (FireBlockFluid) new FireBlockStationary(fireWaterStillLimeID, ColorList.LIME).setUnlocalizedName("fireWaterStillLime");

		fireWaterMovingMagenta = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingMagentaID, ColorList.MAGENTA).setUnlocalizedName("fireWaterMovingMagenta");
		fireWaterStillMagenta = (FireBlockFluid) new FireBlockStationary(fireWaterStillMagentaID, ColorList.MAGENTA).setUnlocalizedName("fireWaterStillMagenta");

		fireWaterMovingOrange = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingOrangeID, ColorList.ORANGE).setUnlocalizedName("fireWaterMovingOrange");
		fireWaterStillOrange = (FireBlockFluid) new FireBlockStationary(fireWaterStillOrangeID, ColorList.ORANGE).setUnlocalizedName("fireWaterStillOrange");

		fireWaterMovingPink = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingPinkID, ColorList.PINK).setUnlocalizedName("fireWaterMovingPink");
		fireWaterStillPink = (FireBlockFluid) new FireBlockStationary(fireWaterStillPinkID, ColorList.PINK).setUnlocalizedName("fireWaterStillPink");

		fireWaterMovingPurple = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingPurpleID, ColorList.PURPLE).setUnlocalizedName("fireWaterMovingPurple");
		fireWaterStillPurple = (FireBlockFluid) new FireBlockStationary(fireWaterStillPurpleID, ColorList.PURPLE).setUnlocalizedName("fireWaterStillPurple");

		fireWaterMovingSilver = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingSilverID, ColorList.SILVER).setUnlocalizedName("fireWaterMovingSilver");
		fireWaterStillSilver = (FireBlockFluid) new FireBlockStationary(fireWaterStillSilverID, ColorList.SILVER).setUnlocalizedName("fireWaterStillSilver");

		fireWaterMovingWhite = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingWhiteID, ColorList.WHITE).setUnlocalizedName("fireWaterMovingWhite");
		fireWaterStillWhite = (FireBlockFluid) new FireBlockStationary(fireWaterStillWhiteID, ColorList.WHITE).setUnlocalizedName("fireWaterStillWhite");

		fireWaterMovingYellow = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingYellowID, ColorList.YELLOW).setUnlocalizedName("fireWaterMovingYellow");
		fireWaterStillYellow = (FireBlockFluid) new FireBlockStationary(fireWaterStillYellowID, ColorList.YELLOW).setUnlocalizedName("fireWaterStillYellow");

		fireWaterMovingGray = (FireBlockFluid) new FireBlockFlowing(fireWaterMovingGrayID, ColorList.GRAY).setUnlocalizedName("fireWaterMovingGray");
		fireWaterStillGray = (FireBlockFluid) new FireBlockStationary(fireWaterStillGrayID, ColorList.GRAY).setUnlocalizedName("fireWaterStillGray");

		LanguageRegistry.instance().addNameForObject(fireGrass, "en_US","Fire Glass");
		LanguageRegistry.instance().addNameForObject(fireGrass, "zh_CN","荧光草");
		
		LanguageRegistry.instance().addNameForObject(fireGrassBottle, "en_US","Fire Bottle");
		LanguageRegistry.instance().addNameForObject(fireGrassBottle, "zh_CN","空染剂瓶");
		
		LanguageRegistry.instance().addNameForObject(fireGrassFruit, "en_US","Fire Glass's Fruit");
		LanguageRegistry.instance().addNameForObject(fireGrassFruit, "zh_CN","荧光草果实");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingRed,"en_US", "0");
		LanguageRegistry.instance().addNameForObject(fireWaterStillRed,"en_US", "0");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingGreen,"en_US", "1");
		LanguageRegistry.instance().addNameForObject(fireWaterStillGreen,"en_US", "1");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingBlue,"en_US", "2");
		LanguageRegistry.instance().addNameForObject(fireWaterStillBlue,"en_US", "2");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingBlack,"en_US", "3");
		LanguageRegistry.instance().addNameForObject(fireWaterStillBlack,"en_US", "3");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingBrown,"en_US", "4");
		LanguageRegistry.instance().addNameForObject(fireWaterStillBrown,"en_US", "4");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingCyan,"en_US", "5");
		LanguageRegistry.instance().addNameForObject(fireWaterStillCyan,"en_US", "5");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingLIGHTBLUE,"en_US", "6");
		LanguageRegistry.instance().addNameForObject(fireWaterStillLIGHTBLUE,"en_US", "6");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingLime,"en_US", "7");
		LanguageRegistry.instance().addNameForObject(fireWaterStillLime,"en_US", "7");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingMagenta,"en_US", "8");
		LanguageRegistry.instance().addNameForObject(fireWaterStillMagenta,"en_US", "8");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingOrange,"en_US", "9");
		LanguageRegistry.instance().addNameForObject(fireWaterStillOrange,"en_US", "9");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingPink,"en_US", "10");
		LanguageRegistry.instance().addNameForObject(fireWaterStillPink,"en_US", "10");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingPurple,"en_US", "11");
		LanguageRegistry.instance().addNameForObject(fireWaterStillPurple,"en_US", "11");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingSilver,"en_US", "12");
		LanguageRegistry.instance().addNameForObject(fireWaterStillSilver,"en_US", "12");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingWhite,"en_US", "13");
		LanguageRegistry.instance().addNameForObject(fireWaterStillWhite,"en_US", "13");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingYellow,"en_US", "14");
		LanguageRegistry.instance().addNameForObject(fireWaterStillYellow,"en_US", "14");

		LanguageRegistry.instance().addNameForObject(fireWaterMovingGray,"en_US", "15");
		LanguageRegistry.instance().addNameForObject(fireWaterStillGray,"en_US", "15");
		
		LanguageRegistry.instance().addNameForObject(fireGrassBottleColor,"en_US", "Color Bottle");
		LanguageRegistry.instance().addNameForObject(fireGrassBottleColor,"zh_CN", "染剂瓶");
		LanguageRegistry.instance().addStringLocalization("itemGroup.FireGrass","zh_CN", "荧光草");
	}

	@Mod.EventHandler
	public void onLoad(FMLInitializationEvent event) {
		

		GameRegistry.registerBlock(fireGrass, "fireGrass");
		GameRegistry.registerBlock(fireWaterMovingRed, "fireWaterMovingRed");
		GameRegistry.registerBlock(fireWaterStillRed, "fireWaterStillRed");

		GameRegistry.registerBlock(fireWaterStillGreen, "fireWaterStillGreen");
		GameRegistry.registerBlock(fireWaterMovingGreen, "fireWaterMovingGreen");

		GameRegistry.registerBlock(fireWaterStillBlue, "fireWaterStillBlue");
		GameRegistry.registerBlock(fireWaterMovingBlue, "fireWaterMovingBlue");

		GameRegistry.registerBlock(fireWaterStillBlack, "fireWaterStillBlack");
		GameRegistry.registerBlock(fireWaterMovingBlack, "fireWaterMovingBlack");

		GameRegistry.registerBlock(fireWaterStillBrown, "fireWaterStillBrown");
		GameRegistry.registerBlock(fireWaterMovingBrown, "fireWaterMovingBrown");

		GameRegistry.registerBlock(fireWaterStillCyan, "fireWaterStillCyan");
		GameRegistry.registerBlock(fireWaterMovingCyan, "fireWaterMovingCyan");

		GameRegistry.registerBlock(fireWaterStillLIGHTBLUE,"fireWaterStillLIGHTBLUE");
		GameRegistry.registerBlock(fireWaterMovingLIGHTBLUE,"fireWaterMovingLIGHTBLUE");

		GameRegistry.registerBlock(fireWaterStillLime, "fireWaterStillLime");
		GameRegistry.registerBlock(fireWaterMovingLime, "fireWaterMovingLime");

		GameRegistry.registerBlock(fireWaterStillMagenta,"fireWaterStillMagenta");
		GameRegistry.registerBlock(fireWaterMovingMagenta,"fireWaterMovingMagenta");

		GameRegistry.registerBlock(fireWaterStillOrange, "fireWaterStillOrange");
		GameRegistry.registerBlock(fireWaterMovingOrange,"fireWaterMovingOrange");

		GameRegistry.registerBlock(fireWaterStillPink, "fireWaterStillPink");
		GameRegistry.registerBlock(fireWaterMovingPink, "fireWaterMovingPink");

		GameRegistry.registerBlock(fireWaterStillPurple, "fireWaterStillPurple");
		GameRegistry.registerBlock(fireWaterMovingPurple,"fireWaterMovingPurple");

		GameRegistry.registerBlock(fireWaterStillSilver, "fireWaterStillSilver");
		GameRegistry.registerBlock(fireWaterMovingSilver,"fireWaterMovingSilver");

		GameRegistry.registerBlock(fireWaterStillWhite, "fireWaterStillWhite");
		GameRegistry.registerBlock(fireWaterMovingWhite, "fireWaterMovingWhite");

		GameRegistry.registerBlock(fireWaterStillYellow, "fireWaterStillYellow");
		GameRegistry.registerBlock(fireWaterMovingYellow,"fireWaterMovingYellow");

		GameRegistry.registerBlock(fireWaterStillGray, "fireWaterStillGray");
		GameRegistry.registerBlock(fireWaterMovingGray, "fireWaterMovingGray");

		GameRegistry.registerWorldGenerator(new WorldGenFireGrass());
		recipes();
		proxy.onLoad();
		
	}

	private void recipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,0), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,1),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,1), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,2),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,2), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,4),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,3), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,0),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,4), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,3),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,5), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,6),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,6), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,8),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,7), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,12),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,8), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,10),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,9), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,13),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,10), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,14),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,11), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,9),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,12), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,5),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,13), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,7),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,14), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,15),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,15), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,11),fireGrassBottle,fireGrassFruit
		});
		GameRegistry.addShapelessRecipe(new ItemStack(fireGrassBottleColor,1,2), new Object[]{
			new ItemStack(Item.dyePowder.itemID,1,4),fireGrassBottle,fireGrassFruit
		});
		
		GameRegistry.addRecipe(new ItemStack(fireGrassBottle,1), new Object[]{
			" X ",
			"# #",
			" # ",
			Character.valueOf('#'),new ItemStack(Block.glass,1),
			Character.valueOf('X'),new ItemStack(Block.thinGlass,1)
		});
		
		
	}

}