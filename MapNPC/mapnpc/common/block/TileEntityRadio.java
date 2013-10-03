package mapnpc.common.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRadio extends TileEntity {
	public String taskName = "";
	public String groupName = "";

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		taskName = par1nbtTagCompound.getString("task");
		groupName = par1nbtTagCompound.getString("group");
	
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setString("task", taskName);
		par1nbtTagCompound.setString("group", groupName);

	}

}
