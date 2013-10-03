package cookercraft.common.misc;

import cookercraft.common.CookerCraft;
import cookercraft.common.blocks.CookerBlockBamboow;
import cookercraft.common.blocks.CookerBlockRich;
import cookercraft.common.blocks.CookerBlockWildRich;
import net.minecraft.block.Block;

public class InitBlock {

	public static Block blockWildRice;
	public static Block blockRice;
	public static Block blockBamboow;
	public void initBlock(){
		InitBlock.blockBamboow = new CookerBlockBamboow(Config.blockbamboowID, 0).setUnlocalizedName("blockBamboow");
	    InitBlock.blockWildRice=new CookerBlockWildRich(Config.blockWildRiceID).setUnlocalizedName("blockWildRice");
 	    InitBlock.blockRice=new CookerBlockRich(Config.blockRiceID).setUnlocalizedName("blockRice");
	
 	   registerBlocks();
	}
	private void registerBlocks() {
		CookerCraft.proxy.registerAndName(InitBlock.blockBamboow, "Bamboow","竹子");
		CookerCraft.proxy.registerAndName(InitBlock.blockWildRice, "Block Wild Rice","野生水稻");
		CookerCraft.proxy.registerAndName(InitBlock.blockRice, "Block Rice","野生水稻");
		
	}

}
