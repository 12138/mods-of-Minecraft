package CookerCraft.common;

public class CookerBlockRich extends CookerBlockWildRich{

	protected CookerBlockRich(int par1) {
		super(par1);
		
	}

	@Override
	protected int getSeedItem() {
		
		return  CookerCraft.rice.itemID;
	}

}
