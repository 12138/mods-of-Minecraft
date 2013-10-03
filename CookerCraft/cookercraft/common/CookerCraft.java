package cookercraft.common ;
import cookercraft.common.misc.Config;
import cookercraft.common.misc.CookerEventHandler;
import cookercraft.common.misc.CreativeTabCooker;
import cookercraft.common.misc.InitBlock;
import cookercraft.common.misc.InitItem;
import cookercraft.common.misc.WorldGenBamboow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(serverSideRequired = false, clientSideRequired = true)
@Mod(modid = "CookerCraft", name = "CookerCraft", version = "1.1.0")
public class CookerCraft {
    public static String assetsName="cookercraft";

    public static String modName="CookerCraft";
    //CreativeTabs 
	public static CreativeTabs tabCookerCraft	= new CreativeTabCooker("CookerCraft");
	
	@Mod.Instance("CookerCraft")
	public static CookerCraft instance;

	@SidedProxy(clientSide = "cookercraft.client.ClientProxy", serverSide = "cookercraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		new Config(event.getSuggestedConfigurationFile());
		new InitBlock().initBlock();
		new InitItem().initItem();
	
		LanguageRegistry.instance().addStringLocalization("itemGroup.CookerCraft", "zh_CN", "厨艺");
		LanguageRegistry.instance().addStringLocalization("itemGroup.CookerCraft", "en_US", "Cooker Craft");
	}

	@Mod.EventHandler
	public void onLoad(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CookerEventHandler());
		GameRegistry.registerWorldGenerator(new WorldGenBamboow());
		RegistryRecipes();
		SmeltRecipes();
	}
	
	private void SmeltRecipes() {
		FurnaceRecipes.smelting().addSmelting(InitItem.paste.itemID, new ItemStack(InitItem.tastyBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(InitItem.sweetPaste.itemID, new ItemStack(InitItem.sweetBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(InitItem.chocolatePaste.itemID, new ItemStack(InitItem.chocolateBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(InitItem.milkPaste.itemID, new ItemStack(InitItem.milkBread,2), 0F);
		FurnaceRecipes.smelting().addSmelting(InitItem.bamboowithPaddy.itemID, new ItemStack(InitItem.bamboowithRich,1), 0F);
		FurnaceRecipes.smelting().addSmelting(InitItem.aCupofRice.itemID, new ItemStack(InitItem.cookedRice,1), 0F);
		
	}

	private void RegistryRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.semolina), new Object[]{
			Item.wheat
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.paperBag,5), new Object[]{
			"# #",
			" # ",
			Character.valueOf('#'),new ItemStack(Item.paper,1),
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.paperBagwithSemolina,1), new Object[]{
			"###",
			" X ",
			Character.valueOf('#'),new ItemStack(InitItem.semolina,1),
			Character.valueOf('X'),new ItemStack(InitItem.paperBag,1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.paste), new Object[]{
			InitItem.paperBagwithSemolina,InitItem.paperBagwithSemolina
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.sweetPaste), new Object[]{
			InitItem.paste,Item.sugar
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.chocolatePaste), new Object[]{
			InitItem.paste,Item.sugar,new ItemStack(Item.dyePowder,1,3)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.milkPaste), new Object[]{
			InitItem.paste,Item.sugar,Item.bucketMilk
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.SliceofBread,2), new Object[]{
			InitItem.tastyBread
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.meatHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.porkCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.beefHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.beefCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.chickenHamburger,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.SliceofBread,1),
			Character.valueOf('X'),new ItemStack(Item.chickenCooked,1)
		});
		
		GameRegistry.addRecipe(new ItemStack(InitItem.meatSandwich.itemID,1,0), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.porkCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.beefSandwich,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.beefCooked,1)
		});
		GameRegistry.addRecipe(new ItemStack(InitItem.chickenSandwich,1), new Object[]{
			" # ",
			" X ",
			" # ",
			Character.valueOf('#'),new ItemStack(InitItem.sSliceofbread,1),
			Character.valueOf('X'),new ItemStack(Item.chickenCooked,1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.sSliceofbread,8), new Object[]{
			InitItem.tastyBread,InitItem.tastyBread
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.paddy,1), new Object[]{
			InitItem.rice
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.bamboowithPaddy,1), new Object[]{
			InitItem.paddy,InitItem.bamboow
		});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItem.aCupofRice,1), new Object[]{
			InitItem.paddy,Item.bowlEmpty
		});
		
		
		
	}


}