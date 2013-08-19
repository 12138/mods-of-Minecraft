package CookerCraft.common.block;

import CookerCraft.common.utils.InitItem;

public class CookerBlockRich extends CookerBlockWildRich{

	public CookerBlockRich(int par1) {
		super(par1);
		
	}

	@Override
	protected int getSeedItem() {
		
		return  InitItem.rice.itemID;
	}

}
