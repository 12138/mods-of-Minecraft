package firegrass.common;

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
import firegrass.common.blocks.FireBlockFlowing;
import firegrass.common.blocks.FireBlockFluid;
import firegrass.common.blocks.FireBlockGrass;
import firegrass.common.blocks.FireBlockStationary;
import firegrass.common.items.FireItem;
import firegrass.common.items.FireItemColor;
import firegrass.common.misc.CreativeTabCooker;
import firegrass.common.misc.WorldGenFireGrass;

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
	
	public static int fluidBeginId;
	
	/**
	 * Red 1
	 * Green 2
	 * Blue 3
	 * Brown 4
	 * Cyan 5
	 * Lightblue 6
	 * Lime 7
	 * Magenta 8
	 * Orange 9
	 * Pink 10
	 * Purple 11
	 * Silver 12
	 * White 13
	 * Yellow 14
	 * Gray 15
	 * */
	public static FireBlockFluid[] colorMovingFluid=new FireBlockFluid[16];
	/**
	 * Red 1
	 * Green 2
	 * Blue 3
	 * Brown 4
	 * Cyan 5
	 * Lightblue 6
	 * Lime 7
	 * Magenta 8
	 * Orange 9
	 * Pink 10
	 * Purple 11
	 * Silver 12
	 * White 13
	 * Yellow 14
	 * Gray 15
	 * */
	public static FireBlockFluid[] colorStillFluid=new FireBlockFluid[16];
	public static String[] colorList={
		"Red",
		"Green",
		"Blue",
		"Block",
		"Brown",
		"Cyan",
		"Lightblue",
		"Lime",
		"Magenta",
		"Orange",
		"Pink",
		"Purple",
		"Silver",
		"White",
		"Yellow",
		"Gray"};



	public static String modName = "firegrass";
	// CreativeTabs
	public static CreativeTabs tabFireGrass = new CreativeTabCooker("FireGrass");

	@Mod.Instance("FireGrass")
	public static FireGrass instance;

	@SidedProxy(clientSide = "firegrass.client.ClientProxy", serverSide = "firegrass.common.CommonProxy")
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
		
		fluidBeginId=config.getBlock("FluidBeginId", 1000, "Need 32 place,such as:1000~1031").getInt();
		
		config.save();
		fireGrassFruit = new FireItem(fireGrassFruitID).setUnlocalizedName("fireGrassFruit").setCreativeTab(tabFireGrass);

		fireGrass = new FireBlockGrass(fireGrassID).setUnlocalizedName("fireGrass").setCreativeTab(tabFireGrass);

		fireGrassBottleColor=new FireItemColor(fireGrassBottleColorID).setUnlocalizedName("fireGrassBottleColor").setCreativeTab(tabFireGrass);
		fireGrassBottle=new FireItem(fireGrassBottleID).setUnlocalizedName("fireGrassBottle").setCreativeTab(tabFireGrass);
		for(int i=0;i<16;i++){
			colorMovingFluid[i]=(FireBlockFluid) new FireBlockFlowing(fluidBeginId++, i).setUnlocalizedName("fireWaterMoving"+colorList[i]);
			colorStillFluid[i]=(FireBlockFluid) new FireBlockStationary(fluidBeginId++, i).setUnlocalizedName("fireWaterStill"+colorList[i]);
		}
	
		LanguageRegistry.instance().addNameForObject(fireGrass, "en_US","Fire Glass");
		LanguageRegistry.instance().addNameForObject(fireGrass, "zh_CN","荧光草");
		
		LanguageRegistry.instance().addNameForObject(fireGrassBottle, "en_US","Fire Bottle");
		LanguageRegistry.instance().addNameForObject(fireGrassBottle, "zh_CN","空染剂瓶");
		
		LanguageRegistry.instance().addNameForObject(fireGrassFruit, "en_US","Fire Glass's Fruit");
		LanguageRegistry.instance().addNameForObject(fireGrassFruit, "zh_CN","荧光草果实");

		
				
		LanguageRegistry.instance().addNameForObject(fireGrassBottleColor,"en_US", "Color Bottle");
		LanguageRegistry.instance().addNameForObject(fireGrassBottleColor,"zh_CN", "染剂瓶");
		LanguageRegistry.instance().addStringLocalization("itemGroup.FireGrass","zh_CN", "荧光草");
	}

	@Mod.EventHandler
	public void onLoad(FMLInitializationEvent event) {
		

		GameRegistry.registerBlock(fireGrass, "fireGrass");
		
		for(int i=0;i<16;i++){
			GameRegistry.registerBlock(colorMovingFluid[i],"fireWaterMoving"+colorList[i]);
			GameRegistry.registerBlock(colorStillFluid[i],"fireWaterStill"+colorList[i]);
		}
		
		GameRegistry.registerWorldGenerator(new WorldGenFireGrass());
		addRecipes();
		proxy.onLoad();
		
	}

	private void addRecipes() {
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