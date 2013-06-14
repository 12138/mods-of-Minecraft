package Jimmynator.common;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import Jimmynator.common.block.BlockAir;
import Jimmynator.common.block.BlockAnvil;
import Jimmynator.common.block.BlockNetherOrb;
import Jimmynator.common.block.BlockOmelette;
import Jimmynator.common.block.BlockOrb;
import Jimmynator.common.block.BlockPan;
import Jimmynator.common.block.BlockSponge;
import Jimmynator.common.block.BlockWheat;
import Jimmynator.common.block.tileentity.TileEntityOrb;
import Jimmynator.common.block.tileentity.TileEntityPan;
import Jimmynator.common.entity.EntityHorse;
import Jimmynator.common.item.ItemBat;
import Jimmynator.common.item.ItemHammer;
import Jimmynator.common.item.ItemJimmynator;
import Jimmynator.common.item.ItemNetherAxe;
import Jimmynator.common.item.ItemNetherPickaxe;
import Jimmynator.common.item.JimmItemArmor;
import Jimmynator.common.item.archer.ItemEtherBow;
import Jimmynator.common.item.archer.ItemMechanicBow;
import Jimmynator.common.item.archer.ItemNetherBow;
import Jimmynator.common.item.food.ItemBeer;
import Jimmynator.common.item.food.ItemOmelette;
import Jimmynator.common.item.food.JimmItemFood;
import Jimmynator.common.item.grim.ItemLucifer;
import Jimmynator.common.item.grim.ItemReaper;
import Jimmynator.common.item.knight.ItemExcalibur;
import Jimmynator.common.item.knight.ItemLance;
import Jimmynator.common.item.knight.ItemMattock;
import Jimmynator.common.item.knight.ItemMjolnir;
import Jimmynator.common.item.knight.ItemTomahawk;
import Jimmynator.common.item.mage.ItemStaff;
import Jimmynator.common.item.ninja.ItemShuriken;
import Jimmynator.common.item.ninja.ItemSmokeGrenade;
import Jimmynator.common.network.PacketHandler;
import Jimmynator.common.world.WorldGenerator;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@NetworkMod(serverSideRequired = false, clientSideRequired = true,packetHandler=PacketHandler.class,channels={"Jimmynator"})
@Mod(modid = "Jimmynator", name = "Jimmynator", version = "2.5.0")
public class Jimmynator implements IFuelHandler {
	public static final EnumArmorMaterial KNIGHT = EnumHelper.addArmorMaterial("KNIGHT", 33, new int[] { 5, 15, 10, 5 }, 30);
	  public static final EnumArmorMaterial GRIM = EnumHelper.addArmorMaterial("GRIM", -1, new int[] { 0, 0, 0, 0 }, 30);
	  public static final EnumToolMaterial ETHER = EnumHelper.addToolMaterial("ETHER", 3, 3122, 15.0F, 9, 30);
	  public static final EnumToolMaterial NETHER = EnumHelper.addToolMaterial("NETHER", 3, 2341, 15.0F, 10, 5);
	  public static final EnumToolMaterial NINJA = EnumHelper.addToolMaterial("NINJA", 3, 635, 15.0F, 14, 15);
	//item
	public static Item woodHammer;
	public static Item stoneHammer;
	public static Item ironHammer;
	public static Item goldHammer;
	public static Item diamondHammer;
	
	public static Item fireStaff;
	public static Item iceStaff;
	public static Item lifeStaff;
	public static Item deathStaff;
	
	public static Item orb;
	public static Item fireOrb;
	public static Item iceOrb;
	public static Item lifeOrb;
	public static Item deathOrb;
	
	public static ItemArmor knightHelmet;
	public static ItemArmor knightChest;
	public static ItemArmor knightLegs;
	public static ItemArmor knightBoots;
	
	public static ItemArmor grimHelmet;
	public static ItemArmor grimChest;
	public static ItemArmor grimLegs;
	public static ItemArmor grimBoots;
	
	public static ItemArmor mageHelmet;
	public static ItemArmor mageChest;
	public static ItemArmor  mageLegs;
	public static ItemArmor mageBoots;
	
	public static ItemArmor archerHelmet;
	public static ItemArmor archerChest;
	public static ItemArmor archerLegs;
	public static ItemArmor archerBoots;
	
	public static ItemArmor ninjaHelmet;
	public static ItemArmor ninjaChest;
	public static ItemArmor ninjaLegs;
	public static ItemArmor ninjaBoots;
	
	public static Item rune;
	public static Item fireRune;
	public static Item iceRune;
	public static Item lifeRune;
	public static Item deathRune;
	
	public static Item soul;
	public static Item lucifer;
	public static Item reaper;
	public static Item mechanicBow;
	public static Item etherBow;
	public static Item netherBow;

	
	public static Item excalibur;
	public static Item lance;
	public static Item mjolnir;
	public static Item mattock;
	public static Item tomahawk;
	
	public static ItemFood beer;
	
	
	public static Item excaliburHandle;
	public static Item excaliburBlade;
	public static Item luciferHandle;
	public static Item luciferBlade;
	public static Item netherHandle;
	public static Item reaperBlade;
	public static Item mattockBlade;
	
	public static Item mjolnirHandle;
	public static Item mjolnirBlade;
	public static Item netherAxeBlade;
	public static Item netherPickaxeBlade;	
	public static Item lanceHandle;
	public static Item lanceBlade;	
	public static Item tomahawkBlade;
	public static Item magicPowder;	
	
	public static Item bat;
	public static ItemFood friedEgg;
	public static Item omelette;
	public static Item magicString;
	public static Item nethercoal;
	public static Item demonite;
	public static Item luciferite;
	public static Item etherealIngot;
	public static Item firePowder;
	public static Item icePowder;
	public static Item lifePowder;
	public static Item deathPowder;
	public static Item etherealPowder;
	public static Item smokeGrenade;
	public static Item shuriken;
	
	public static Item ninjaSteel;
	public static Item katana;
	public static Item katanaHandle;
	public static Item katanaBlade;
	public static Item quiver;
	
	public static Item netherAxe;
	public static Item netherPickaxe;
	
	
	public static Block blockOmelette;
	public static Block blockNethercoal;
	public static Block blockDemonite;
	public static Block blockLuciferite;
	public static Block blockWheat;
	public static Block blockOrb;
	
	public static Block sponge;
	public static Block air;
	public static Block pan;
	public static Block blockAnvil;
	
	public static int woodHammerId;
	public static int stoneHammerId;
	public static int ironHammerId;
	public static int goldHammerId;
	public static int diamondHammerId;
	
	
	public static int orbId;
	public static int fireOrbId;
	public static int iceOrbId;
	public static int lifeOrbId;
	public static int deathOrbId;
	
	public static int fireStaffId;
	public static int iceStaffId;
	public static int lifeStaffId;
	public static int deathStaffId;

	public static int knightHelmetId;
	public static int knightChestId;
	public static int knightLegsId;
	public static int knightBootsId;
	
	public static int grimHelmetId;
	public static int grimChestId;
	public static int grimLegsId;
	public static int grimBootsId;
	
	public static int mageHelmetId;
	public static int mageChestId;
	public static int  mageLegsId;
	public static int mageBootsId;
	
	public static int archerHelmetId;
	public static int archerChestId;
	public static int archerLegsId;
	public static int archerBootsId;
	
	public static int ninjaHelmetId;
	public static int ninjaChestId;
	public static int ninjaLegsId;
	public static int ninjaBootsId;
	
	public static int  runeId;
	public static int fireRuneId;
	public static int iceRuneId;
	public static int lifeRuneId;
	public static int deathRuneId;
	
	public static int soulId;
	public static int luciferId;
	public static int reaperId;
	public static int mechanicBowId;
	public static int etherBowId;
	public static int netherBowId;
	
	public static int excaliburId;
	public static int lanceId;
	public static int mjolnirId;
	public static int mattockId;
	public static int tomahawkId;
	
	public static int beerId;
	
	public static int excaliburHandleId;
	public static int excaliburBladeId;
	public static int luciferHandleId;
	public static int luciferBladeId;
	public static int netherHandleId;
	public static int reaperBladeId;
	public static int mattockBladeId;
	
	public static int mjolnirHandleId;
	public static int mjolnirBladeId;
	public static int netherAxeBladeId;
	public static int netherPickaxeBladeId;	
	public static int lanceHandleId;
	public static int lanceBladeId;	
	public static int tomahawkBladeId;
	public static int magicPowderId;	
	
	public static int batId;
	public static int friedEggId;
	public static int omeletteId;
	public static int magicStringId;
	public static int nethercoalId;
	public static int demoniteId;
	public static int luciferiteId;
	public static int etherealIngotId;
	public static int firePowderId;
	public static int icePowderId;
	public static int lifePowderId;
	public static int deathPowderId;
	public static int etherealPowderId;
	public static int smokeGrenadeId;
	public static int shurikenId;
	public static int ninjaSteelId;
	public static int katanaId;
	public static int katanaHandleId;
	public static int katanaBladeId;
	public static int quiverId;
	public static int netherAxeId;
	public static int netherPickaxeId;
	
	public static int blockOmeletteId;
	
	public static int blockNethercoalId;
	public static int blockDemoniteId;
	public static int blockLuciferiteId;
	public static int blockWheatId;
	public static int blockOrbId;
	
	public static int spongeId;
	public static int airId;
	public static int panId;
	public static int blockAnvilId;

	 //CreativeTabs 
		public static CreativeTabs tabJimmynator = new CreativeTabJimmynator(CreativeTabs.getNextID(),"Jimmynator");
		@Mod.Instance("Jimmynator")
		public static Jimmynator instance;

		@SidedProxy(clientSide = "Jimmynator.client.ClientProxy", serverSide = "Jimmynator.common.CommonProxy")
		public static CommonProxy proxy;
	
	@PreInit
	public void preInitMod(FMLPreInitializationEvent event){
		final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		woodHammerId=config.getItem("Wood Hammer", 11000).getInt();
		stoneHammerId=config.getItem("Stone Hammer", 11001).getInt();
		ironHammerId=config.getItem("Iron Hammer", 11002).getInt();
		goldHammerId=config.getItem("Gold Hammer", 11003).getInt();
		diamondHammerId=config.getItem("Diamond Hammer", 11004).getInt();
		
		orbId=config.getItem("Orb", 11009).getInt();
		fireOrbId=config.getItem("Fire Orb", 11010).getInt();
		iceOrbId=config.getItem("Ice Orb", 11011).getInt();
		lifeOrbId=config.getItem("Life Orb", 11012).getInt();
		deathOrbId=config.getItem("Death Orb", 11013).getInt();
		
		fireStaffId=config.getItem("Fire Staff", 11005).getInt();
		iceStaffId=config.getItem("Ice Staff", 11006).getInt();
		lifeStaffId=config.getItem("Life Staff", 11007).getInt();
		deathStaffId=config.getItem("Death Staff", 11008).getInt();
		
		knightHelmetId=config.getItem("Knight Helmet", 11009).getInt();
		knightChestId=config.getItem("Knight Chest", 11010).getInt();
		knightLegsId=config.getItem("Knight Legs", 11011).getInt();
		knightBootsId=config.getItem("Knight Boots", 11012).getInt();
		
		grimHelmetId=config.getItem("Grim Helmet", 11013).getInt();
		grimChestId=config.getItem("Grim Chest", 11014).getInt();
		grimLegsId=config.getItem("Grim Legs", 11015).getInt();
		grimBootsId=config.getItem("Grim Boots", 11016).getInt();
		
		mageHelmetId=config.getItem("Mage Helmet", 11017).getInt();
		mageChestId=config.getItem("Mage Chest", 11018).getInt();
		mageLegsId=config.getItem("Mage Legs", 11019).getInt();
		mageBootsId=config.getItem("Mage Boots", 11020).getInt();
		
		archerHelmetId=config.getItem("Archer Helmet", 11021).getInt();
		archerChestId=config.getItem("Archer Chest", 11022).getInt();
		archerLegsId=config.getItem("Archer Legs", 11023).getInt();
		archerBootsId=config.getItem("Archer Boots", 11024).getInt();
		
		ninjaHelmetId=config.getItem("Ninja Helmet", 11025).getInt();
		ninjaChestId=config.getItem("Ninja Chest", 11026).getInt();
		ninjaLegsId=config.getItem("Ninja Legs", 11027).getInt();
		ninjaBootsId=config.getItem("Ninja Boots", 11028).getInt();
		
		runeId=config.getItem("Rune", 11029).getInt();
		fireRuneId=config.getItem("Fire Rune", 11030).getInt();
		iceRuneId=config.getItem("Ice Rune", 11031).getInt();
		lifeRuneId=config.getItem("Life Rune", 11032).getInt();
		deathRuneId=config.getItem("Death Rune", 11033).getInt();
		
		soulId=config.getItem("Soul", 11034).getInt();
		luciferId=config.getItem("Lucifer", 11035).getInt();
		reaperId=config.getItem("Reaper",11036).getInt();
		mechanicBowId=config.getItem("Mechanic Bow", 11037).getInt();
		etherBowId=config.getItem("Ether Bow", 11038).getInt();
		netherBowId=config.getItem("Nether Bow", 11039).getInt();
		
		excaliburId=config.getItem("Excalibur", 11040).getInt();
		lanceId=config.getItem("Lance", 11041).getInt();
		mjolnirId =config.getItem("Mjolnr", 11042).getInt();
		mattockId=config.getItem("Mattock", 11043).getInt();
		tomahawkId=config.getItem("Tomahawk", 11044).getInt();
		
		beerId=config.getItem("beer", 11045).getInt();
		
		excaliburHandleId=config.getItem("Excalibur Handle", 11046).getInt();
		excaliburBladeId=config.getItem("Excalibur Blade", 11047).getInt();
		luciferHandleId=config.getItem("Lucifer Handle", 11048).getInt();
		luciferBladeId=config.getItem("Lucifer Blade", 11049).getInt();
		netherHandleId=config.getItem("Nether Handle", 11050).getInt();
		reaperBladeId=config.getItem("Reaper Blade", 11051).getInt();
		mattockBladeId=config.getItem("Mattock Blade", 11052).getInt();
		
		mjolnirHandleId=config.getItem("Mjolnir Handle", 11053).getInt();
		mjolnirBladeId=config.getItem("Mjolnir Blade", 11054).getInt();
		netherAxeBladeId=config.getItem("Nether Axe Blade", 11055).getInt();
		netherPickaxeBladeId=config.getItem("Nether Pickaxe Blade", 11056).getInt();	
		lanceHandleId=config.getItem("Lance Handle", 11057).getInt();
		lanceBladeId=config.getItem("Lance Blade", 11058).getInt();	
		tomahawkBladeId=config.getItem("Tomahawk Blade", 11059).getInt();
		magicPowderId=config.getItem("Magic PowderId", 11060).getInt();
		batId=config.getItem("Bat", 11061).getInt();
		friedEggId=config.getItem("Fried Egg",11062).getInt();
		omeletteId=config.getItem("Omelette", 11063).getInt();
		magicStringId=config.getItem("Magic String", 11064).getInt();
		nethercoalId=config.getItem("Nethercoal", 11065).getInt();
		demoniteId=config.getItem("Demonite", 11066).getInt();
		luciferiteId=config.getItem("Luciferite", 11067).getInt();
		etherealIngotId=config.getItem("Ethereal Ingot", 11068).getInt();
		firePowderId=config.getItem("fire Powder", 11069).getInt();
		icePowderId=config.getItem("Ice Powder", 11070).getInt();
		lifePowderId=config.getItem("Life Powder", 11071).getInt();
		deathPowderId=config.getItem("Death Powder", 11072).getInt();
		etherealPowderId=config.getItem("Ethereal Powder", 11073).getInt();
		smokeGrenadeId=config.getItem("Smoke Grenade", 11074).getInt();
		shurikenId=config.getItem("Shuriken", 11075).getInt();
		ninjaSteelId=config.getItem("Ninja Steel", 11076).getInt();
		katanaId=config.getItem("Katana", 11077).getInt();
		katanaHandleId=config.getItem("Katana Handle", 11078).getInt();
		katanaBladeId=config.getItem("Katana Blade", 11079).getInt();
	    quiverId=config.getItem("Quiver", 11080).getInt();
	    netherAxeId=config.getItem(" netherAxe", 11081).getInt();
	    netherPickaxeId=config.getItem("netherPickaxe", 11082).getInt();
	    
	    blockOmeletteId=config.getBlock("Block Omelette", 1234).getInt();
	    blockNethercoalId=config.getBlock("Block Nethercoal", 1235).getInt();
	    blockDemoniteId=config.getBlock("Block Demonite", 1236).getInt();
	    blockLuciferiteId=config.getBlock("Block Luciferite", 1237).getInt();
	    blockWheatId=config.getBlock("Wheat", 1238).getInt();
	    blockOrbId=config.getBlock("Block Orb", 1239).getInt();
		 spongeId=config.getBlock("Block sponge", 1240).getInt();
		 airId=config.getBlock("Block air", 1241).getInt();
		 panId=config.getBlock("Block pan", 1242).getInt();
		 blockAnvilId=config.getBlock("Block anvil", 1243).getInt();
	
		config.save();
	}
	@Init
	public void InitMod(FMLInitializationEvent event){
MinecraftForge.EVENT_BUS.register(new EventHandler());
//EntityRegistry.registerModEntity(EntityTomahawk.class, "tomahawk", EntityRegistry.findGlobalUniqueEntityId(), this, 128, 1, true);
//EntityRegistry.registerModEntity(EntityStaffFireball.class, "fireball", EntityRegistry.findGlobalUniqueEntityId(), this, 128, 1, true);
//EntityRegistry.registerModEntity(EntityStaffIceball.class, "iceball", EntityRegistry.findGlobalUniqueEntityId(), this, 128, 1, true);
//EntityRegistry.registerModEntity(EntityStaffLifeball.class, "lifeball", EntityRegistry.findGlobalUniqueEntityId(), this, 128, 1, true);
//EntityRegistry.registerModEntity(EntityStaffDeathball.class, "deathball", EntityRegistry.findGlobalUniqueEntityId(), this, 128, 1, true);
		woodHammer=new ItemHammer(woodHammerId,EnumToolMaterial.WOOD).setUnlocalizedName("woodhammer");
		stoneHammer=new ItemHammer(stoneHammerId,EnumToolMaterial.STONE).setUnlocalizedName("stonehammer");
		ironHammer=new ItemHammer(ironHammerId,EnumToolMaterial.IRON).setUnlocalizedName("ironhammer");
		goldHammer=new ItemHammer(goldHammerId,EnumToolMaterial.GOLD).setUnlocalizedName("goldhammer");
		diamondHammer=new ItemHammer(diamondHammerId, EnumToolMaterial.EMERALD).setUnlocalizedName("diamondhammer");
		
		orb=new ItemJimmynator(orbId).setUnlocalizedName("orb");
		fireOrb=new ItemJimmynator(fireOrbId).setUnlocalizedName("fireOrb").setContainerItem(orb);
		iceOrb=new ItemJimmynator(iceOrbId).setUnlocalizedName("iceOrb").setContainerItem(orb);
		lifeOrb=new ItemJimmynator(lifeOrbId).setUnlocalizedName("lifeOrb").setContainerItem(orb);
		deathOrb=new ItemJimmynator(deathOrbId).setUnlocalizedName("deathOrb").setContainerItem(orb);

		fireStaff=new ItemStaff(fireStaffId,1).setUnlocalizedName("fireStaff");
		iceStaff=new ItemStaff(iceStaffId,2).setUnlocalizedName("iceStaff");
		lifeStaff=new ItemStaff(lifeStaffId,3).setUnlocalizedName("lifeStaff");
		deathStaff=new ItemStaff(deathStaffId,4).setUnlocalizedName("deathStaff");
		
		knightHelmet=(ItemArmor) new JimmItemArmor(knightHelmetId, KNIGHT,  proxy.addArmor("knight"), 0).setUnlocalizedName("knightHelmet");
		knightChest=(ItemArmor) new JimmItemArmor(knightChestId, KNIGHT, proxy.addArmor("knight"), 1).setUnlocalizedName("knightChest");
		knightLegs=(ItemArmor) new JimmItemArmor(knightLegsId, KNIGHT, proxy.addArmor("knight"), 2).setUnlocalizedName("knightLegs");
		knightBoots=(ItemArmor) new JimmItemArmor(knightBootsId, KNIGHT, proxy.addArmor("knight"), 3).setUnlocalizedName("knightBoots");

		grimHelmet=(ItemArmor) new JimmItemArmor(grimHelmetId, GRIM, proxy.addArmor("grim"), 0).setUnlocalizedName("grimHelmet");
		grimChest=(ItemArmor) new JimmItemArmor(grimChestId, GRIM, proxy.addArmor("grim"), 1).setUnlocalizedName("grimChest");
		grimLegs=(ItemArmor) new JimmItemArmor(grimLegsId, GRIM, proxy.addArmor("grim"), 2).setUnlocalizedName("grimLegs");
		grimBoots=(ItemArmor) new JimmItemArmor(grimBootsId, GRIM, proxy.addArmor("grim"), 3).setUnlocalizedName("grimBoots");
//		
		mageHelmet=(ItemArmor) new JimmItemArmor(mageHelmetId, GRIM, proxy.addArmor("mage"), 0).setUnlocalizedName("mageHelmet");
		mageChest=(ItemArmor) new JimmItemArmor(mageChestId, GRIM, proxy.addArmor("mage"), 1).setUnlocalizedName("mageChest");
		mageLegs=(ItemArmor) new JimmItemArmor(mageLegsId, GRIM, proxy.addArmor("mage"), 2).setUnlocalizedName("mageLegs");
		mageBoots=(ItemArmor) new JimmItemArmor(mageBootsId, GRIM, proxy.addArmor("mage"), 3).setUnlocalizedName("mageBoots");
		
		archerHelmet=(ItemArmor) new JimmItemArmor(archerHelmetId, GRIM, proxy.addArmor("archer"), 0).setUnlocalizedName("archerHelmet");
		archerChest=(ItemArmor) new JimmItemArmor(archerChestId, GRIM, proxy.addArmor("archer"), 1).setUnlocalizedName("archerChest");
		archerLegs=(ItemArmor) new JimmItemArmor(archerLegsId, GRIM, proxy.addArmor("archer"), 2).setUnlocalizedName("archerLegs");
		archerBoots=(ItemArmor) new JimmItemArmor(archerBootsId, GRIM, proxy.addArmor("archer"), 3).setUnlocalizedName("archerBoots");
		
		ninjaHelmet=(ItemArmor) new JimmItemArmor(ninjaHelmetId, GRIM, proxy.addArmor("ninja"), 0).setUnlocalizedName("ninjaHelmet");
		ninjaChest=(ItemArmor) new JimmItemArmor(ninjaChestId, GRIM, proxy.addArmor("ninja"), 1).setUnlocalizedName("ninjaChest");
		ninjaLegs=(ItemArmor) new JimmItemArmor(ninjaLegsId, GRIM, proxy.addArmor("ninja"), 2).setUnlocalizedName("ninjaLegs");
		ninjaBoots=(ItemArmor) new JimmItemArmor(ninjaBootsId, GRIM, proxy.addArmor("ninja"), 3).setUnlocalizedName("ninjaBoots");
//		
		rune=new ItemJimmynator(runeId).setUnlocalizedName("rune");
		fireRune=new ItemJimmynator(fireRuneId).setUnlocalizedName("fireRune");
		iceRune=new ItemJimmynator(iceRuneId).setUnlocalizedName("iceRune");
		lifeRune=new ItemJimmynator(lifeRuneId).setUnlocalizedName("lifeRune");
		deathRune=new ItemJimmynator(deathRuneId).setUnlocalizedName("deathRune");
		
		soul=new ItemJimmynator(soulId).setUnlocalizedName("soul");
		lucifer=new ItemLucifer(luciferId, NETHER).setUnlocalizedName("lucifer");
		reaper=new ItemReaper(reaperId,NETHER).setUnlocalizedName("reaper");
		mechanicBow=new ItemMechanicBow(mechanicBowId).setUnlocalizedName("mechanicBow");
		etherBow=new ItemEtherBow(etherBowId).setUnlocalizedName("etherBow");
		netherBow=new ItemNetherBow(netherBowId).setUnlocalizedName("netherBow");
		
		excalibur=new ItemExcalibur(excaliburId, ETHER).setUnlocalizedName("excalibur");
		lance=new ItemLance(lanceId,ETHER).setUnlocalizedName("lance");
		mjolnir=new ItemMjolnir(mjolnirId, ETHER).setUnlocalizedName("mjolnir");
		mattock=new ItemMattock(mattockId, ETHER).setUnlocalizedName("mattock");
		tomahawk=new ItemTomahawk(tomahawkId,ETHER).setUnlocalizedName("tomahawk");
		
		beer=(ItemFood) new ItemBeer(beerId,4, 1.0F, false).setAlwaysEdible().setPotionEffect(9, 20*10, 0, 0.33F).setUnlocalizedName("beer");
		
		excaliburHandle=new ItemJimmynator(excaliburHandleId).setUnlocalizedName("excaliburHandle");
		excaliburBlade=new ItemJimmynator(excaliburBladeId).setUnlocalizedName("excaliburBlade");
		luciferHandle=new ItemJimmynator(luciferHandleId).setUnlocalizedName("luciferHandle");
		luciferBlade=new ItemJimmynator(luciferBladeId).setUnlocalizedName("luciferBlade");
		netherHandle=new ItemJimmynator(netherHandleId ).setUnlocalizedName("netherHandle");
		reaperBlade=new ItemJimmynator(reaperBladeId ).setUnlocalizedName("reaperBlade");
		mattockBlade=new ItemJimmynator(mattockBladeId ).setUnlocalizedName("mattockBlade");
		
		mjolnirHandle=new ItemJimmynator(mjolnirHandleId).setUnlocalizedName("mjolnirHandle");
		mjolnirBlade=new ItemJimmynator(mjolnirBladeId).setUnlocalizedName("mjolnirBlade");
		netherAxeBlade=new ItemJimmynator(netherAxeBladeId).setUnlocalizedName("netherAxeBlade");
		netherPickaxeBlade=new ItemJimmynator(netherPickaxeBladeId).setUnlocalizedName("netherPickaxeBlade");	
		lanceHandle=new ItemJimmynator(lanceHandleId).setUnlocalizedName("lanceHandle");
		lanceBlade=new ItemJimmynator(lanceBladeId).setUnlocalizedName("lanceBlade");	
		tomahawkBlade=new ItemJimmynator(tomahawkBladeId).setUnlocalizedName("tomahawkBlade");
		magicPowder=new ItemJimmynator(magicPowderId).setUnlocalizedName("magicPowder");
		
		bat=new ItemBat(batId).setUnlocalizedName("bat");
		friedEgg=(ItemFood) new JimmItemFood(friedEggId, 3, 0.1F, false).setUnlocalizedName("friedEgg");
		
		magicString=new ItemJimmynator(magicStringId).setUnlocalizedName("magicString");
		nethercoal=new ItemJimmynator(nethercoalId).setUnlocalizedName("nethercoal");
	    demonite=new ItemJimmynator(demoniteId).setUnlocalizedName("demonite");
	    luciferite=new ItemJimmynator(luciferiteId).setUnlocalizedName("luciferite");
	    etherealIngot=new ItemJimmynator(etherealIngotId).setUnlocalizedName("etherealIngot");
	   
	    firePowder=new ItemJimmynator(firePowderId).setUnlocalizedName("firePowder");
	   icePowder=new ItemJimmynator(icePowderId).setUnlocalizedName("icePowder");
	    lifePowder=new ItemJimmynator(lifePowderId).setUnlocalizedName("lifePowder");
	    deathPowder=new ItemJimmynator(deathPowderId).setUnlocalizedName("deathPowder");
	    etherealPowder=new ItemJimmynator(etherealPowderId).setUnlocalizedName("etherealPowder");
		smokeGrenade=new ItemSmokeGrenade(smokeGrenadeId).setUnlocalizedName("smokeGrenade");
	    shuriken=new ItemShuriken(shurikenId).setUnlocalizedName("shuriken");
	    ninjaSteel=new ItemJimmynator(ninjaSteelId).setUnlocalizedName("ninjaSteel");
		katana=new ItemExcalibur(katanaId, NINJA).setUnlocalizedName("katana");
		katanaHandle=new ItemJimmynator(katanaHandleId).setUnlocalizedName("katanaHandle");
		katanaBlade=new ItemJimmynator(katanaBladeId).setUnlocalizedName("katanaBlade");
	    quiver=new ItemJimmynator(quiverId).setUnlocalizedName("quiver");
	    netherAxe=new ItemNetherAxe(netherAxeId,NETHER).setUnlocalizedName("netherAxe");
	    netherPickaxe=new ItemNetherPickaxe(netherPickaxeId,NETHER).setUnlocalizedName("netherPickaxe");
	   
	    blockOmelette=new BlockOmelette(blockOmeletteId).setUnlocalizedName("blockOmelette");
	    omelette=new ItemOmelette(omeletteId, blockOmelette).setUnlocalizedName("omelette");
	    
	    blockNethercoal=new BlockNetherOrb(blockNethercoalId, 0).setUnlocalizedName("blockNethercoal");
	    blockDemonite=new BlockNetherOrb(blockDemoniteId, 1).setUnlocalizedName("blockDemonite");
	    blockLuciferite=new BlockNetherOrb(blockLuciferiteId, 2).setUnlocalizedName("blockLuciferite");
	  blockWheat=new BlockWheat(blockWheatId).setUnlocalizedName("blockWheat");
	  blockOrb=new BlockOrb(blockOrbId).setUnlocalizedName("blockOrb");
	  air=new BlockAir(airId, Material.air).setUnlocalizedName("air");
	   sponge=new BlockSponge(spongeId);
	   Block.blocksList[Block.sponge.blockID]=null;
	   Block.blocksList[Block.sponge.blockID]=sponge;
	   pan=new BlockPan(panId).setUnlocalizedName("pan");
      blockAnvil=new BlockAnvil(blockAnvilId, Material.rock).setUnlocalizedName("blockAnvil");
	  GameRegistry.registerTileEntity(TileEntityOrb.class, "tileentityorb");
	  GameRegistry.registerTileEntity(TileEntityPan.class, "tileentitypan");
	  GameRegistry.registerWorldGenerator(new WorldGenerator());
	  int redColor = (255 << 16);
	    int orangeColor = (255 << 16)+ (200 << 8);
	  EntityRegistry.registerGlobalEntityID(EntityHorse.class, "hourse", EntityRegistry.findGlobalUniqueEntityId(),redColor,orangeColor);
	  EntityRegistry.addSpawn(EntityHorse.class, 20, 0, 10, EnumCreatureType.creature, BiomeGenBase.plains);
	  NetworkRegistry.instance().registerGuiHandler(this, proxy);
		
//		  registerBlockAndItsName(methane, "Block fthercoal", "ff煤矿");
	  registerBlockAndItsName(blockNethercoal, "Block Nethercoal", "地狱煤矿");
	    registerBlockAndItsName(blockDemonite, "Block Demonite", "地狱矿石");
	    registerBlockAndItsName(blockLuciferite, "Block Luciferite", "撒旦钻石矿");
	    registerBlockAndItsName(blockWheat, "Block Wheat", "麦垛");
	    registerBlockAndItsName(blockOrb, "Block Orb", "魔法台");
//	    registerBlockAndItsName(air, "Block Air", "空气");
	    registerBlockAndItsName(pan, "Pan", "煎锅");
	    registerBlockAndItsName(blockAnvil, "Anvil", "打铁台");
	    
	    registerItemAndItsName(woodHammer, "Wood Hammer", "木锤");
		registerItemAndItsName(stoneHammer, "Stone Hammer","石锤");
		registerItemAndItsName(ironHammer, "Iron Hammer", "铁锤");
		registerItemAndItsName(goldHammer, "Gold Hammer", "黄金锤");
		registerItemAndItsName(diamondHammer, "Diamond Hammer","钻石锤");
		
		registerItemAndItsName(orb, "Orb", "玻璃球");
		registerItemAndItsName(fireOrb, "Orb", "火焰宝珠");
		registerItemAndItsName(iceOrb, "Ice Orb", "寒冰宝珠");
		registerItemAndItsName(lifeOrb, "Life Orb", "生命宝珠");
		registerItemAndItsName(deathOrb, "Death Orb", "死亡宝珠");
		
		registerItemAndItsName(fireStaff, "Fire Staff", "火焰法杖");
		registerItemAndItsName(iceStaff, "Ice Staff", "冰冻法杖");
		registerItemAndItsName(lifeStaff, "Life Staff", "生命法杖");
		registerItemAndItsName(deathStaff, "Death Staff", "死灵法杖");
		
		registerItemAndItsName(knightHelmet, "Knight Helmet", "骑士头盔");
		registerItemAndItsName(knightChest, "Knight Chest", "骑士胸甲");
		registerItemAndItsName(knightLegs, "Knight Legs", "骑士护腿");
		registerItemAndItsName(knightBoots, "Knight Boots", "骑士长靴");
		
		registerItemAndItsName(grimHelmet, "Grim Helmet", "死灵头巾");
		registerItemAndItsName(grimChest, "Grim Chest", "死灵衣");
		registerItemAndItsName(grimLegs, "Grim Legs", "死灵护腿");
		registerItemAndItsName(grimBoots, "Grim Boots", "死灵长靴");
		
		registerItemAndItsName(mageHelmet, "Mage Helmet", "法师头巾");
		registerItemAndItsName(mageChest, "Mage Chest", "法师布衣");
		registerItemAndItsName(mageLegs, "Mage Legs", "法师长裤");
		registerItemAndItsName(mageBoots, "Mage Boots", "法师长靴");
		
		registerItemAndItsName(archerHelmet, "Archer Helmet", "弓箭手之帽");
		registerItemAndItsName(archerChest, "Archer Chest", "弓箭手之衣");
		registerItemAndItsName(archerLegs, "Archer Legs", "弓箭手之裤");
		registerItemAndItsName(archerBoots, "Archer Boots", "弓箭手之靴");
		
		registerItemAndItsName(ninjaHelmet, "Ninja Helmet", "刺客头盔");
		registerItemAndItsName(ninjaChest, "Ninja Chest", "刺客胸甲");
		registerItemAndItsName(ninjaLegs, "Ninja Legs", "刺客护腿");
		registerItemAndItsName(ninjaBoots, "Ninja Boots", "刺客长靴");
		
		registerItemAndItsName(rune, "Rune","基础符文");
		registerItemAndItsName(fireRune, "Fire Rune","火焰符文");
		registerItemAndItsName(iceRune, "Ice Rune","冰冻符文");
		registerItemAndItsName(lifeRune, "Life Rune","生命符文");
		registerItemAndItsName(deathRune, "Death Rune","亡灵符文");
		
		registerItemAndItsName(soul, "Soul","灵魂");
		registerItemAndItsName(lucifer, "Lucifer","路西法之剑");
		registerItemAndItsName(reaper, "Reaper","死神之镰");
		
		registerItemAndItsName(mechanicBow, "Mechanic Bow","机械弓");
		registerItemAndItsName(etherBow, "Ether Bow","以太弓");
		registerItemAndItsName(netherBow, "Nether Bow","地狱弓");
		
		registerItemAndItsName(excalibur, "Excalibur","胜利与誓约之剑");
		registerItemAndItsName(lance, "Lance","雷霆长枪");
		registerItemAndItsName(mjolnir, "Mjolnir","雷霆之锤");
		registerItemAndItsName(mattock, "Mattock", "空灵镐");
		registerItemAndItsName(tomahawk, "Tomahawk", "空灵飞斧");
		
		registerItemAndItsName(beer, "Beer", "啤酒");
		
		registerItemAndItsName(excaliburHandle, "Excalibur Handle", "胜利与誓约之剑剑柄");
		registerItemAndItsName(excaliburBlade, "Excalibur Blade", "胜利与誓约之剑剑刃");
		registerItemAndItsName(luciferHandle, "Lucifer Handle", "路西法剑柄");
		registerItemAndItsName(luciferBlade, "Lucifer Blade", "路西法剑刃");
		registerItemAndItsName(netherHandle, "Nether Handle", "地狱柄");
		registerItemAndItsName(reaperBlade, "Reaper Blade", "死神镰之刃");
		registerItemAndItsName(mattockBlade, "Mattock Blade", "空灵稿柄");
		registerItemAndItsName(mjolnirHandle, "Mjolnir Handle", "雷神之锤锤柄");
		
		registerItemAndItsName(mjolnirBlade, "Mjolnir Blade", "雷神之锤头部");
		registerItemAndItsName(netherAxeBlade, "Nether Axe Blade", "地狱斧柄");
		registerItemAndItsName(netherPickaxeBlade, "Nether Pickaxe Blade", "地狱镐柄");
		registerItemAndItsName(lanceHandle, "Lance Handle", "雷霆长枪枪柄");
		
		registerItemAndItsName(lanceBlade, "Lance Blade", "雷霆长枪枪刃");
		registerItemAndItsName(tomahawkBlade, "Tomahawk Blade", "空灵飞斧之刃");
		registerItemAndItsName(magicPowder, "Magic Powder", "魔法之尘");
		registerItemAndItsName(bat, "Bat", "棒球棍");
		registerItemAndItsName(friedEgg, "Fired Egg", "煎蛋");
		registerItemAndItsName(omelette, "Omelette", "煎蛋卷");

		registerItemAndItsName(magicString,"Magic String","魔法丝绸");
		registerItemAndItsName(nethercoal,"Nether Coal","地狱煤");
		registerItemAndItsName(demonite,"Demonite","地狱锭");
		registerItemAndItsName(luciferite,"Luciferite","撒旦钻石");
		registerItemAndItsName(etherealIngot, "etherealIngot", "空灵锭");
		

		registerItemAndItsName(firePowder,"Fire Powder","火焰之尘");
		registerItemAndItsName(icePowder,"Ice Powder","寒冰之尘");
		registerItemAndItsName(lifePowder,"Life Powder","生命之尘");
		registerItemAndItsName(deathPowder,"Death Powder","死亡之尘");
		registerItemAndItsName(etherealPowder, "Ethereal Powder", "空灵之尘");
		
		registerItemAndItsName(smokeGrenade, "Smoke Grenade", "烟雾弹");
		registerItemAndItsName(shuriken, "Shuriken","忍者镖");
		
		registerItemAndItsName(ninjaSteel, "Ninja Steel","忍者铁");
		registerItemAndItsName(katana, "Katana","武士刀");
		registerItemAndItsName(katanaHandle, "Katana Handle","武士刀柄");
		registerItemAndItsName(katanaBlade, "Katana Blade","武士刀刃");
		registerItemAndItsName(quiver, "Quiver","箭筒");
		 registerItemAndItsName(netherAxe, "Nether Axe", "地狱斧");
		 registerItemAndItsName(netherPickaxe, "Nether Pickaxe", "地狱稿");
		
		
		MinecraftForge.setToolClass(mattock, "pickaxe", 4);
		MinecraftForge.setToolClass(tomahawk, "axe", 4);
		MinecraftForge.setToolClass(netherAxe, "axe", 4);
		MinecraftForge.setToolClass(netherPickaxe, "pickaxe", 4);
		LanguageRegistry.instance().addStringLocalization("itemGroup.Jimmynator", "zh_CN", "职业mod");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Jimmynator", "en_US", "Jimmynator");
		proxy.registerRenderInformation();
		initRepice();
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);

		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);}
	private void initRepice() {
		 GameRegistry.addShapedRecipe(new ItemStack(Block.ice), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), Block.blockSnow });

		    GameRegistry.addShapedRecipe(new ItemStack(woodHammer, 1), new Object[] { " XX", "//X", " XX", Character.valueOf('/'),Item.stick, Character.valueOf('X'), Block.planks });

		    GameRegistry.addShapedRecipe(new ItemStack(stoneHammer, 1), new Object[] { " XX", "//X", " XX", Character.valueOf('/'), Item.stick, Character.valueOf('X'), Block.cobblestone });

		    GameRegistry.addShapedRecipe(new ItemStack(ironHammer, 1), new Object[] { " XX", "//X", " XX", Character.valueOf('/'), Item.stick, Character.valueOf('X'), Item.ingotIron });

		    GameRegistry.addShapedRecipe(new ItemStack(diamondHammer, 1), new Object[] { " XX", "//X", " XX", Character.valueOf('/'), Item.stick, Character.valueOf('X'), Item.diamond });

		    GameRegistry.addShapedRecipe(new ItemStack(goldHammer, 1), new Object[] { " XX", "//X", " XX", Character.valueOf('/'), Item.stick, Character.valueOf('X'), Item.ingotGold });

		    GameRegistry.addShapedRecipe(new ItemStack(fireStaff, 1), new Object[] { " X ", " / ", " / ", Character.valueOf('/'), Item.stick, Character.valueOf('X'), fireOrb });

		    GameRegistry.addShapedRecipe(new ItemStack(iceStaff, 1), new Object[] { " X ", " / ", " / ", Character.valueOf('/'), Item.stick, Character.valueOf('X'), iceOrb });

		    GameRegistry.addShapedRecipe(new ItemStack(lifeStaff, 1), new Object[] { " X ", " / ", " / ", Character.valueOf('/'), Item.stick, Character.valueOf('X'), lifeOrb });

		    GameRegistry.addShapedRecipe(new ItemStack(deathStaff, 1), new Object[] { " X ", " / ", " / ", Character.valueOf('/'), Item.stick, Character.valueOf('X'), deathOrb });

		    GameRegistry.addShapedRecipe(new ItemStack(orb, 4), new Object[] { " X ", "X X", " X ", Character.valueOf('X'), Block.glass });

		    GameRegistry.addShapedRecipe(new ItemStack(bat, 1), new Object[] { "  X", " X ", "/  ", Character.valueOf('/'), Item.stick, Character.valueOf('X'), Block.planks});

		    GameRegistry.addShapedRecipe(new ItemStack(blockAnvil, 1), new Object[] { "XXX", " X ", "XXX", Character.valueOf('X'), Item.ingotIron });

		    GameRegistry.addShapedRecipe(new ItemStack(blockWheat, 1, 0), new Object[] { "XX", "XX", Character.valueOf('X'), Item.wheat });

		    GameRegistry.addShapedRecipe(new ItemStack(omelette, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'),Item.egg });

		    GameRegistry.addShapedRecipe(new ItemStack(pan, 1), new Object[] { "  /", "XX ", "XX ", Character.valueOf('X'), Item.ingotIron, Character.valueOf('/'), Item.stick });

		    GameRegistry.addShapedRecipe(new ItemStack(beer, 1), new Object[] { "X", "V", Character.valueOf('X'), Item.wheat, Character.valueOf('V'), new ItemStack(Item.potion, 1, 0) });

		    GameRegistry.addShapedRecipe(new ItemStack(quiver, 1), new Object[] { "//X", "/X ", "X  ", Character.valueOf('X'), Item.arrow, Character.valueOf('/'), Item.silk });

		    GameRegistry.addShapedRecipe(new ItemStack(blockOrb, 1), new Object[] { "X X", "XXX", Character.valueOf('X'),Block.obsidian });

		    GameRegistry.addShapelessRecipe(new ItemStack(magicPowder, 1), new Object[] { Item.redstone, Item.lightStoneDust });

		    GameRegistry.addShapelessRecipe(new ItemStack(magicString, 1), new Object[] { magicPowder, Item.silk });

		    GameRegistry.addShapelessRecipe(new ItemStack(iceOrb, 1), new Object[] { icePowder, orb });

		    GameRegistry.addShapelessRecipe(new ItemStack(fireOrb, 1), new Object[] { firePowder, orb });

		    GameRegistry.addShapelessRecipe(new ItemStack(lifeOrb, 1), new Object[] { lifePowder, orb });

		    GameRegistry.addShapelessRecipe(new ItemStack(deathOrb, 1), new Object[] { deathPowder, orb });

		    GameRegistry.addShapelessRecipe(new ItemStack(icePowder, 4), new Object[] { Block.ice, magicPowder });

		    GameRegistry.addShapelessRecipe(new ItemStack(firePowder, 4), new Object[] { Item.blazePowder, magicPowder });

		    GameRegistry.addShapelessRecipe(new ItemStack(lifePowder, 4), new Object[] { Item.emerald, magicPowder });

		    GameRegistry.addShapelessRecipe(new ItemStack(deathPowder, 4), new Object[] { Item.eyeOfEnder, magicPowder });

		    GameRegistry.addShapelessRecipe(new ItemStack(rune, 1), new Object[] { Block.stone });

		    GameRegistry.addShapelessRecipe(new ItemStack(Item.wheat, 2), new Object[] { blockWheat });
		
		    
		    GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(Jimmynator.friedEgg), 0.1F);
		    GameRegistry.addSmelting(Block.slowSand.blockID, new ItemStack(Jimmynator.soul), 0.1F);
//		    GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(Jimmynator.), 0.1F);
		    GameRegistry.addSmelting(Jimmynator.etherealPowder.itemID, new ItemStack(Jimmynator.etherealIngot), 0.1F);
	GameRegistry.registerFuelHandler(this);
	}
	private void registerBlockAndItsName(Block block, String en,String ch) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
		LanguageRegistry.instance().addNameForObject(block, "en_US", en);
		LanguageRegistry.instance().addNameForObject(block, "zh_CN", ch);
		
	}
	private void registerItemAndItsName(Item item,String en,String ch) {
		GameRegistry.registerItem(item, item.getUnlocalizedName(), "Jimmynator");
		LanguageRegistry.instance().addNameForObject(item, "en_US", en);
		LanguageRegistry.instance().addNameForObject(item, "zh_CN", ch);
	}
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == nethercoal.itemID)
			return 1000;
			else
			return 0;
	}

}
