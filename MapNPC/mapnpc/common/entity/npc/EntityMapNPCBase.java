package mapnpc.common.entity.npc;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.INpc;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityMapNPCBase extends EntityAgeable implements INpc{

	public EntityMapNPCBase(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(15, "");// name
		super.entityInit();
	}

	public void setNPCName(String name) {
	
		this.dataWatcher.updateObject(15, name);
	}

	@Override
	public String getEntityName() {
		return this.dataWatcher.getWatchableObjectString(15) == null ? "unknown"
				: this.dataWatcher.getWatchableObjectString(15);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
	
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setString("NPCname", this.getEntityName());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		this.setNPCName(par1nbtTagCompound.getString("NPCname"));
		
	
	}


}