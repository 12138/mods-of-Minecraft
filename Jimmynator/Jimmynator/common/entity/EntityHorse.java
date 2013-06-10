package Jimmynator.common.entity;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal {
private boolean isBeS=false;
private boolean leap;
//private final EntityAIControlledByPlayer aiControlledByPlayer;
	public EntityHorse(World par1World) {
		
		super(par1World);
        this.texture = "/mods/Jimmynator/textures/entity/horse.png";
        this.setSize(0.7F, 1.1F);
        
        this.getNavigator().setAvoidsWater(true);
        float f = 0.25F;
		 this.tasks.addTask(0, new EntityAISwimming(this));
	        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
//	        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F));
	        this.tasks.addTask(3, new EntityAIMate(this, f));
//	        this.tasks.addTask(4, new EntityAITempt(this, 0.3F, Item.carrotOnAStick.itemID, false));
	        this.tasks.addTask(4, new EntityAITempt(this, 0.3F, Item.wheat.itemID, false));
//	        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28F));
	        this.tasks.addTask(6, new EntityAIWander(this, f));
	        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	        this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
	if(this.isBeS){
		return "/mods/Jimmynator/textures/entity/horse2.png";
	}
	else{
		return "/mods/Jimmynator/textures/entity/horse.png";
		
	}
	}

	@Override
	public void updateRidden() {
		super.updateRidden();
		 if(ridingEntity.isDead)
	        {
	            ridingEntity = null;
	            return;
	        }
	        motionX = 0.0D;
	        motionZ = 0.0D;
	        motionY = 0.0D;
	        onUpdate();
	        //onUpdate();
	        if(ridingEntity == null)
	        {
	           return;
	        }
	        ridingEntity.updateRiderPosition();
	}



	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if(par1EntityPlayer.inventory.getCurrentItem()!=null&&this.isBeS==false&&par1EntityPlayer.inventory.getCurrentItem().itemID==Item.saddle.itemID){
			this.isBeS=true;
		}
		else if(this.isBeS==true&&this.ridingEntity==null){
			 if (!this.worldObj.isRemote)
	            {
				 if(Jimmynator.proxy.isEquid(par1EntityPlayer, "knight")||par1EntityPlayer.capabilities.isCreativeMode)
	                par1EntityPlayer.mountEntity(this);
	            }
		}
		else if(this.isBeS==true&&this.ridingEntity==par1EntityPlayer){

	            double d0 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
	            double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
	            this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
	        
		}
		return super.interact(par1EntityPlayer);
	}

//	@Override
//	public void onUpdate() {
//		super.onUpdate();
//		 if (this.riddenByEntity != null)
//         {
//             this.motionX += this.riddenByEntity.motionX ;
//             this.motionZ += this.riddenByEntity.motionZ ;
//         }
//	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 100;
	}
	@Override
	  public boolean canBeSteered()
	    {
	        return true;
	    }
	@Override
    public void moveEntity(double d, double d1, double d2)
    {
        if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer))
        {
              
              prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
              prevRotationPitch = rotationPitch = 0.0F;                                                           
                   motionX += riddenByEntity.motionX * 3.0D;
//                   motionY += riddenByEntity.motionY;
                    motionZ+= riddenByEntity.motionZ * 3.0D;
                EntityPlayer entityplayer = (EntityPlayer)riddenByEntity;
              if(((EntityPlayer)entityplayer).isJumping && !isJumping)
            {
                 motionY +=  0.3D;
            }
              
//                }
                if(isCollidedHorizontally)
                {
     
                            if(onGround)                    //else not fly
                            {
                               motionY += 0.6F;     //let it try to jump over obstacle
                            }
                }
 
            super.moveEntity(motionX, motionY, motionZ);
        }else
        {
           super.moveEntity(d, d1, d2);
        }
    }

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		// TODO Auto-generated method stub
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setBoolean("is", this.isBeS);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		// TODO Auto-generated method stub
		super.readEntityFromNBT(par1nbtTagCompound);
		this.isBeS=par1nbtTagCompound.getBoolean("is");
	}

}
