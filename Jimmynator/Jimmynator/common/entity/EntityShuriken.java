package Jimmynator.common.entity;

import Jimmynator.common.Jimmynator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShuriken extends EntityStaffBall {

	public EntityShuriken(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving, 1.0F);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void withEntity(MovingObjectPosition movingobjectposition) {
		movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity),3);	
		if(!worldObj.isRemote)
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, movingobjectposition.entityHit.posX, movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ, new ItemStack(Jimmynator.shuriken)));
		this.setDead();
	}

	@Override
	protected void withBlock(World worldObj, int xTile2, int yTile2, int zTile2) {
		if(!worldObj.isRemote)
		worldObj.spawnEntityInWorld(new EntityItem(worldObj, xTile2, yTile2, zTile2, new ItemStack(Jimmynator.shuriken)));
		this.setDead();

	}

}
