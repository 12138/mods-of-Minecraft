package Jimmynator.common.entity;

import Jimmynator.common.Jimmynator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShuriken extends EntityThrowable {

	public EntityShuriken(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	this.ignoreFrustumCheck = true;
	}
	

//	@Override
//	protected void withEntity(MovingObjectPosition movingobjectposition) {
//		
//	}
//
//	@Override
//	protected void withBlock(World worldObj, int xTile2, int yTile2, int zTile2) {
//		if(!worldObj.isRemote)
//		worldObj.spawnEntityInWorld(new EntityItem(worldObj, xTile2, yTile2, zTile2, new ItemStack(Jimmynator.shuriken)));
//		this.setDead();
//
//	}


	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if(movingobjectposition.entityHit!=null){
		movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this),3);	
		if(!worldObj.isRemote)
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, movingobjectposition.entityHit.posX, movingobjectposition.entityHit.posY+1, movingobjectposition.entityHit.posZ, new ItemStack(Jimmynator.shuriken)));
		this.setDead();
		}
		else{
			if(!worldObj.isRemote)
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ, new ItemStack(Jimmynator.shuriken)));
				this.setDead();
		}
	}

}
