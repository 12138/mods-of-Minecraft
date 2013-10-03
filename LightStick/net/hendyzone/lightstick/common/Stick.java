package net.hendyzone.lightstick.common;



import net.hendyzone.lightstick.client.GuiHandler;
import net.hendyzone.lightstick.common.blocks.BlockBonefire;
import net.hendyzone.lightstick.common.blocks.BlockSmallTorch;
import net.hendyzone.lightstick.common.blocks.BlockTorchWithMeta;
import net.hendyzone.lightstick.common.blocks.TileEntityBonefire;
import net.hendyzone.lightstick.common.blocks.TileEntityTorch;
import net.hendyzone.lightstick.common.items.ItemBlockTorch;
import net.hendyzone.lightstick.common.items.ItemCharredStick;
import net.hendyzone.lightstick.common.items.ItemFirePoker;
import net.hendyzone.lightstick.common.items.ItemSmallTorch;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
@NetworkMod(serverSideRequired = true, clientSideRequired = true)
@Mod(modid = "Stick", name = "Stick", version = "1.2.0")
public class Stick implements ICraftingHandler {
	public static Item handheldTorch;
	public static int handheldTorchId;
	public static Item charredStick;
	public static int charredStickId;
	public static Block handheldTorchBlock;
	public static int handheldTorchBlockId;
	public static Item smallHandheldTorch;
	public static int smallHandheldTorchId;
	public static Block smallHandheldTorchBlock;
	public static int smallHandheldTorchBlockId;
	public static Block bonefire;
	public static int bonefireId;
	
	public static Item firePoker;
	public static int firePokerId;
	
	static Achievement getFirePoer;
	@Mod.Instance("Stick")
	public static Stick instance;
	

	@SidedProxy(clientSide = "net.hendyzone.lightstick.client.ClientProxy", serverSide = "net.hendyzone.lightstick.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		handheldTorchId=config.getItem("手持火把", 10109).getInt(10109);
		charredStickId = config.getItem("烧焦的木棍", 10100).getInt(10100);
		smallHandheldTorchId=config.getItem("小型手持火把", 10109).getInt(10111);
		firePokerId=config.getItem("火钳", 10112).getInt(10112);
		handheldTorchBlockId=config.getBlock("手持火把方块", 2222).getInt(2222);
		smallHandheldTorchBlockId=config.getBlock("小型手持火把方块", 2223).getInt(2223);
		bonefireId		=config.getBlock("篝火", 2224).getInt(2224);
		config.save();
		
		handheldTorch = new ItemBlockTorch(handheldTorchId).setUnlocalizedName("handheldTorch").setCreativeTab(CreativeTabs.tabTools);
		handheldTorchBlock=new BlockTorchWithMeta(handheldTorchBlockId).setUnlocalizedName("handheldTorch");
		smallHandheldTorch=new ItemSmallTorch(smallHandheldTorchId).setUnlocalizedName("smallHandheldTorch").setCreativeTab(CreativeTabs.tabTools);
		smallHandheldTorchBlock=new BlockSmallTorch(smallHandheldTorchBlockId).setUnlocalizedName("smallHandheldTorch");
		bonefire= new BlockBonefire(bonefireId).setUnlocalizedName("bonefire").setCreativeTab(CreativeTabs.tabTools);	
		charredStick = new ItemCharredStick(charredStickId);
		firePoker=new ItemFirePoker(firePokerId).setUnlocalizedName("firePoker").setCreativeTab(CreativeTabs.tabTools);
		LanguageRegistry.addName(charredStick, "烧焦的木棒");
		LanguageRegistry.addName(handheldTorch, "手持火把");
		LanguageRegistry.addName(firePoker, "火钳");
		LanguageRegistry.addName(smallHandheldTorch, "小型火把");
		LanguageRegistry.addName(bonefire,"篝火");
		GameRegistry.registerTileEntity(TileEntityTorch.class, "LSTileEntityTorch");
		GameRegistry.registerTileEntity(TileEntityBonefire.class, "LSTileEntityBonefire");
		GameRegistry.registerBlock(bonefire, "bonefire");
		
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		

		GameRegistry.addRecipe(new ItemStack(Item.stick, 1), new Object[] {
				"**", "**", Character.valueOf('*'),
				Stick.charredStick });
		GameRegistry.addRecipe(new ItemStack(handheldTorch, 4), new Object[] {
			"***", " # "," # ",
			Character.valueOf('*'),Item.coal, 
			Character.valueOf('#'),Block.wood
			});
		GameRegistry.addRecipe(new ItemStack(smallHandheldTorch, 1), new Object[] {
			" * ", " # "," # ",
			Character.valueOf('*'),Item.coal, 
			Character.valueOf('#'),Block.wood });
		GameRegistry.addRecipe(new ItemStack(bonefire, 1), new Object[] {
			"***", "* *","***", Character.valueOf('*'),
			Item.stick });
		GameRegistry.addRecipe(new ItemStack(firePoker, 1), new Object[] {
			"* *", " # ","# #", 
			Character.valueOf('*'),Item.ingotIron,
			Character.valueOf('#'),Item.stick
			});
		GameRegistry.registerCraftingHandler(this);
		getFirePoer = new Achievement(2001, "getFirePoer", 1, -2, firePoker,null).registerAchievement();
		proxy.load();
		addAchievementName();
	}
	
	private void addAchievementName()
	{
	        LanguageRegistry.instance().addStringLocalization("achievement.getFirePoer", "zh_CN", "火钳刘明！");
	        LanguageRegistry.instance().addStringLocalization("achievement.getFirePoer.desc", "zh_CN", "制造一把火钳。");
	}



	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,IInventory craftMatrix) {
		if(item.itemID==firePoker.itemID)
			   player.addStat(getFirePoer, 1);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
	
	}


}
