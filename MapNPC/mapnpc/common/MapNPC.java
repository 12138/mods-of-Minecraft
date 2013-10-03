package mapnpc.common;

import java.io.File;

import mapnpc.common.block.BlockRadio;
import mapnpc.common.block.ItemBlockRadio;
import mapnpc.common.block.TileEntityRadio;
import mapnpc.common.entity.npc.EntityMapNPCTask;
import mapnpc.common.item.ItemSpawn;
import mapnpc.common.item.ItemTaskBook;
import mapnpc.common.item.ItemTaskMenu;
import mapnpc.common.misc.TaskEvenHandler;
import mapnpc.common.network.PacketHandler;
import mapnpc.common.task.PlayerTracker;
import mapnpc.common.task.TaskSavedData;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(serverSideRequired = true, clientSideRequired = true, packetHandler = PacketHandler.class, channels = { "MapNPC" })
@Mod(modid = "MapNPC", name = "MapNPC", version = "1.0.2")
public class MapNPC {
	
	public static String modName="MapNPC";
	public static String assetsName="mapnpc";

	public static Item spawn;
	public static int spawnId;

	public static Item taskMenu;
	public static int taskMenuId;
	
	public static Item taskBook;
	public static int taskBookId;

	public static Block radio;
	public static int radioId;
	
	public static File dir=null;
	
	@Mod.Instance("MapNPC")
	public static MapNPC instance;

	@SidedProxy(clientSide = "mapnpc.client.ClientProxy", serverSide = "mapnpc.common.CommonProxy")
	public static CommonProxy proxy;
	public TaskSavedData taskData;

	@Mod.PreInit
	public void preLoad(FMLPreInitializationEvent event) {
		
		final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		MapNPC.dir=event.getModConfigurationDirectory().getParentFile();
		config.load();
		spawnId = config.getItem("Spawn", 10000).getInt();
		radioId = config.getBlock("Radio", 300).getInt();
		taskMenuId = config.getItem("Task Menu", 10001).getInt();
		taskBookId= config.getItem("Task Book", 10002).getInt();
		config.save();
		
		spawn = new ItemSpawn(spawnId).setUnlocalizedName("spawn").setCreativeTab(CreativeTabs.tabTools);
		radio = new BlockRadio(radioId).setUnlocalizedName("radio").setCreativeTab(CreativeTabs.tabTools);
		taskMenu = new ItemTaskMenu(taskMenuId).setUnlocalizedName("taskmenu").setCreativeTab(CreativeTabs.tabTools);
		taskBook = new ItemTaskBook(taskBookId).setUnlocalizedName("taskbook").setCreativeTab(CreativeTabs.tabTools);
		
		LanguageRegistry.instance().addNameForObject(spawn, "zh_CN", "NPC生成工具");
		LanguageRegistry.instance().addNameForObject(spawn, "en_US","NPC spawn tool");

		LanguageRegistry.instance().addNameForObject(radio, "zh_CN", "收音机");
		LanguageRegistry.instance().addNameForObject(radio, "en_US", "Radio");
		
		LanguageRegistry.instance().addNameForObject(taskMenu, "zh_CN", "任务目录");
		LanguageRegistry.instance().addNameForObject(taskMenu, "en_US","Task Menu");

		LanguageRegistry.instance().addNameForObject(taskBook, "zh_CN", "任务书");
		LanguageRegistry.instance().addNameForObject(taskBook, "en_US","Task Book");
		
		proxy.loadTextures();
	}

	@Mod.Init
	public void onLoad(FMLInitializationEvent event) {
		EntityRegistry.registerGlobalEntityID(EntityMapNPCTask.class, "taskNPC",EntityRegistry.findGlobalUniqueEntityId());
		proxy.registerItem(spawn);
		proxy.registerItem(taskMenu);
		proxy.registerItem(taskBook);
		GameRegistry.registerBlock(radio, ItemBlockRadio.class,radio.getUnlocalizedName(), "MapNPC");
		GameRegistry.registerTileEntity(TileEntityRadio.class,"tileentityradio");
		MinecraftForge.EVENT_BUS.register(new TaskEvenHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		proxy.onLoad();
	}



}