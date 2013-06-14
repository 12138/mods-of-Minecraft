package Jimmynator.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityHorse extends EntityAnimal {
	  private int jumpTicks;
	public EntityHorse(World par1World) {
		
		super(par1World);
        this.texture = "/mods/Jimmynator/textures/entity/horse.png";
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.setSize(0.7F, 1.1F);
        setTamed(false);

	}
	
	@Override
	public boolean canBeSteered() {
		
		return true;
	}


	public boolean getIsTamed()
	  {
		 return this.getDataWatcher().getWatchableObjectByte(16) == 1;
	  }



	public void setTamed(boolean flag)
	  {
	    byte input = (byte)(flag ? 1 : 0);
	    this.getDataWatcher().updateObject(16, Byte.valueOf(input));
	  }


	

	@Override
	public void onLivingUpdate() {
super.onLivingUpdate();


if(riddenByEntity!=null){
EntityPlayer entityRider = (EntityPlayer)riddenByEntity;
this.rotationYaw = this.prevRotationYaw = entityRider.rotationYaw;
this.rotationYawHead = this.prevRotationYawHead = entityRider.rotationYawHead;
this.rotationPitch = this.prevRotationPitch = entityRider.rotationPitch;
}
	}


	@Override
	public void updateRidden() {
		  if (ridingEntity.isDead)
          {
                  ridingEntity = null;
                  return;
          }
          motionX = 0.0D;
          motionY = 0.0D;
          motionZ = 0.0D;
          onUpdate();
          if (ridingEntity == null)
          {
                  return;
          }
    
          if(riddenByEntity == null){
           return;
          }
          ridingEntity.updateRiderPosition();
          riddenByEntity.rotationYaw = ridingEntity.rotationYaw - ridingEntity.prevRotationYaw;
          riddenByEntity.rotationPitch = ridingEntity.rotationPitch - ridingEntity.prevRotationPitch;
          double d = riddenByEntity.rotationYaw * 0.05D;
          double d1 = riddenByEntity.rotationPitch *0.05D;
          float f = 10F;
          if (d > (double)f)
          {
                  d = f;
          }
          if (d < (double)(-f))
          {
                  d = -f;
          }
          if (d1 > (double)f)
          {
                  d1 = f;
          }
          if (d1 < (double)(-f))
          {
                  d1 = -f;
          }
          riddenByEntity.rotationYaw -= d;
          riddenByEntity.rotationPitch -= d1;
          ridingEntity.rotationYaw += d;
          ridingEntity.rotationPitch += d1;


	}


	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.riddenByEntity!=null){

            
            if(riddenByEntity instanceof EntityLiving){
                    this.rotationYawHead = ((EntityLiving)riddenByEntity).rotationYawHead;
            }
            this.motionX += this.riddenByEntity.motionX*5;
            this.motionZ += this.riddenByEntity.motionZ*5;
            
            this.rotationYaw = this.prevRotationYaw = riddenByEntity.rotationYaw;
            this.rotationYawHead = this.prevRotationYawHead = riddenByEntity.getRotationYawHead();
            this.rotationPitch = this.prevRotationPitch = riddenByEntity.rotationPitch;
            if(((EntityPlayer)riddenByEntity).isJumping ){
            	this.jump(); 
                    }
    }

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	
	} 

	@Override
	protected void jump() {
	
		super.jump();
		if(this.riddenByEntity==null){
			return;
			
		}
		else{
			EntityPlayer player=(EntityPlayer)this.riddenByEntity;
			if(player.isJumping){
				this.motionY+=0.431246F;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
	if(this.getIsTamed()){
		return "/mods/Jimmynator/textures/entity/horse2.png";
	}
	else{
		return "/mods/Jimmynator/textures/entity/horse.png";
		
	}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	    this.getDataWatcher().addObject(16, Byte.valueOf((byte)0));

	}


	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if(par1EntityPlayer.inventory.getCurrentItem()!=null&&!this.getIsTamed()&&par1EntityPlayer.inventory.getCurrentItem().itemID==Item.saddle.itemID){
			this.setTamed(true);
		}
		else if(this.getIsTamed()&&this.ridingEntity==null){
			 if (!this.worldObj.isRemote)
	            {
				 if(Jimmynator.proxy.isEquid(par1EntityPlayer, "knight")||par1EntityPlayer.capabilities.isCreativeMode)
	                par1EntityPlayer.mountEntity(this);
	            }
		}
		
		return super.interact(par1EntityPlayer);
	}


	@Override
	public int getMaxHealth() {
		return 50;
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setBoolean("is", this.getIsTamed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		this.setTamed(par1nbtTagCompound.getBoolean("is"));
	}

	  @Override
	public void moveEntityWithHeading(float f, float f1) {
		if (this.riddenByEntity == null)
		    {
		      super.moveEntityWithHeading(f,f1);
		      return;
		    }
		double d0;

	    if (this.handleWaterMovement())
	    {
	    	 d0 = this.posY;
	            this.moveFlying(f, f, this.isAIEnabled() ? 0.04F : 0.02F);
	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            this.motionX *= 0.800000011920929D;
	            this.motionY *= 0.800000011920929D;
	            this.motionZ *= 0.800000011920929D;
	            this.motionY -= 0.02D;

	            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
	            {
	                this.motionY = 0.30000001192092896D;
	            }

	    }
	    else if (this.handleLavaMovement())
	    {
            d0 = this.posY;
            this.moveFlying(f, f1, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
            this.motionY -= 0.02D;

            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
            {
                this.motionY = 0.30000001192092896D;
            }
	    }
	    else
	    {
            float f2 = 0.91F;

            if (this.onGround)
            {
                f2 = 0.54600006F;
                int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (i > 0)
                {
                    f2 = Block.blocksList[i].slipperiness * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;
            if (this.onGround)
            {
                if (this.isAIEnabled())
                {
                    f4 = this.getAIMoveSpeed();
                }
                else
                {
                    f4 = this.landMovementFactor;
                }

                f4 *= f3;
            }
            else
            {
                f4 = this.jumpMovementFactor;
            }

            this.moveFlying(f, f1, f4);
            f2 = 0.91F;
            if (this.onGround)
            {
                f2 = 0.54600006F;
                int j = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (j > 0)
                {
                    f2 = Block.blocksList[j].slipperiness * 0.91F;
                }
            }
            if (this.isOnLadder())
            {
                float f5 = 0.15F;

                if (this.motionX < (double)(-f5))
                {
                    this.motionX = (double)(-f5);
                }

                if (this.motionX > (double)f5)
                {
                    this.motionX = (double)f5;
                }

                if (this.motionZ < (double)(-f5))
                {
                    this.motionZ = (double)(-f5);
                }

                if (this.motionZ > (double)f5)
                {
                    this.motionZ = (double)f5;
                }

                this.fallDistance = 0.0F;

               
                this.motionY = 0.0D;
            }
            this.motionY -= 0.08D;
            this.motionY *= 0.9800000190734863D;
            this.motionX *= (double)f2;
            this.motionZ *= (double)f2;
	}
	    this.prevLimbYaw = this.limbYaw;
        d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F)
        {
            f6 = 1.0F;
        }

        this.limbYaw += (f6 - this.limbYaw) * 0.4F;
        this.limbSwing += this.limbYaw;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}




}
