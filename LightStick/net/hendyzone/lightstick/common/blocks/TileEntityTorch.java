package net.hendyzone.lightstick.common.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorch extends TileEntity {
	public int light=0;




	public int getLight() {
		return light;
	}



	public void setLight(int light) {
		this.light = light;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		// TODO Auto-generated method stub
		super.readFromNBT(par1nbtTagCompound);
		if(!par1nbtTagCompound.hasKey("light")){
			this.light=0;
		}
		else{
			this.light=par1nbtTagCompound.getInteger("light");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		// TODO Auto-generated method stub
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("light", this.light);
	}






}
