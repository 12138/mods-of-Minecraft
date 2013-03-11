package SoilMaker.client;

import net.minecraftforge.client.MinecraftForgeClient;
import SoilMaker.common.CommonProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;


public class ClientProxy extends CommonProxy
{
	
	
	@Override
	public  void registerRenderInformation()
	  {
	
	    MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
	  }
	
 

  public void Load(FMLInitializationEvent event)
  {}
}