package SoilMaker.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;


public class CommonProxy implements IGuiHandler{
	
	
	  public static String BLOCKS_PNG = "/SoilMaker/png/Blocks.png";
	  
public void registerRenderInformation()
{
}

public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
{
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if(tileEntity instanceof TileEntitySoilMaker){
            return new ContainerSoilMaker(player.inventory, (TileEntitySoilMaker) tileEntity);
    }
  return null;
}

public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if(tileEntity instanceof TileEntitySoilMaker){
            return new GuiSoilMaker(player.inventory, (TileEntitySoilMaker) tileEntity);
    }
	return null;
}
}