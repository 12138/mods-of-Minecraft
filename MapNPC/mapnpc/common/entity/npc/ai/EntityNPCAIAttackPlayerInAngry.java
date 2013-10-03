package mapnpc.common.entity.npc.ai;

import mapnpc.common.entity.npc.EntityMapNPCBase;
import mapnpc.common.entity.npc.EntityMapNPCTask;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public  class EntityNPCAIAttackPlayerInAngry extends EntityAIBase {
	EntityMapNPCTask npc;
    World worldObj;
    EntityLiving entityTarget;

    /**
     * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
     */
    int attackTick;
    float field_75440_e;
    boolean field_75437_f;

    /** The PathEntity of our entity. */
    PathEntity entityPathEntity;
    Class<?> classTarget;
    private int field_75445_i;
	public EntityNPCAIAttackPlayerInAngry(EntityMapNPCBase npc, Class<?> par2Class, float par3, boolean par4) {
		  this(npc, par3, par4);
	        this.classTarget = par2Class;
	}


    public EntityNPCAIAttackPlayerInAngry(EntityMapNPCBase npc, float par2, boolean par3)
    {
        this.attackTick = 0;
        this.npc = (EntityMapNPCTask) npc;
        this.worldObj = npc.worldObj;
        this.field_75440_e = par2;
        this.field_75437_f = par3;
        this.setMutexBits(3);
    }

	@Override
	public boolean shouldExecute() {
		EntityLiving entityliving = this.npc.getAttackTarget();

		if (entityliving == null) {
			return false;
		} else if (this.classTarget != null
				&& !this.classTarget.isAssignableFrom(entityliving.getClass())) {
			return false;
		} else {
			if(this.npc.getAngry()==1){
			this.entityTarget = entityliving;
			this.entityPathEntity = this.npc.getNavigator()
					.getPathToEntityLiving(this.entityTarget);
			return this.entityPathEntity != null;}
			else
				return false;
		}
	}
	
	@Override
	 public boolean continueExecuting()
    {
        EntityLiving entityliving = this.npc.getAttackTarget();
        return entityliving == null ? false : 
        	(!this.entityTarget.isEntityAlive() ? false :
        		(!this.field_75437_f ? !this.npc.getNavigator().noPath() : 
        			this.npc.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ))));
    }
	
	@Override
	public void startExecuting()
    {
        this.npc.getNavigator().setPath(this.entityPathEntity, this.field_75440_e);
        this.field_75445_i = 0;
    }
	
	 public void resetTask()
	    {
	        this.entityTarget = null;
	        this.npc.getNavigator().clearPathEntity();
	    }

	    /**
	     * Updates the task
	     */
	    public void updateTask()
	    {
	        this.npc.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);

	        if ((this.field_75437_f || this.npc.getEntitySenses().canSee(this.entityTarget)) && --this.field_75445_i <= 0)
	        {
	            this.field_75445_i = 4 + this.npc.getRNG().nextInt(7);
	            this.npc.getNavigator().tryMoveToEntityLiving(this.entityTarget, this.field_75440_e);
	        }

	        this.attackTick = Math.max(this.attackTick - 1, 0);
	        double d0 = (double)(this.npc.width * 2.0F * this.npc.width * 2.0F);

	        if (this.npc.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= d0)
	        {
	            if (this.attackTick <= 0)
	            {
	                this.attackTick = 20;

	                if (this.npc.getHeldItem() != null)
	                {
	                    this.npc.swingItem();
	                }

	                this.npc.attackEntityAsMob(this.entityTarget);
	            }
	        }
	    }

}
