package SoilMaker.common;

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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired=true, serverSideRequired=false)
@Mod(modid="SoilMaker", name="SoilMaker", version="1.0.0")
public class mod_SoilMaker{
@SidedProxy(clientSide="SoilMaker.client.ClientProxy", serverSide="SoilMaker.common.CommonProxy")

public static CommonProxy proxy;
 @Mod.Instance("SoilMaker")   
 public static mod_SoilMaker instance;
 

 public static Block SoilMaker;

 
 public int SoilMakerID;

 
 
 
 
 @Mod.EventHandler
 public void PreLoad(FMLPreInitializationEvent event){
	 Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	 
	  config.load();
	  
	  SoilMakerID=config.getBlock("SoilMaker",500).getInt();
	  
      config.save();
      
      SoilMaker = new BlockSoilMaker(SoilMakerID,false,1).setUnlocalizedName("soilmaker").func_111022_d("soilmaker").setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations);

 	 LanguageRegistry.addName(SoilMaker,"Soil Maker");
 	 LanguageRegistry.instance().addNameForObject(SoilMaker,"zh_CN","垃圾焚烧炉"); 
	 
	 
	 
 }
 
 
 @Mod.EventHandler
 public void Load(FMLInitializationEvent event)
 { 
	 
	 //registerTileEntity
	 GameRegistry.registerTileEntity(TileEntitySoilMaker.class, "TileEntitySoilMaker");
	 GameRegistry.registerBlock(SoilMaker,"SoilMaker");
	 GameRegistry.addRecipe(new ItemStack(mod_SoilMaker.SoilMaker,1), 
			 "xxx",
			 " y ",
			 'x', Item.ingotIron,
			 'y',Block.furnaceIdle
			 


			 );
	 
	 NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}
 
 }

 
 
 
 
 
 
 
 


