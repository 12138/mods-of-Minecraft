package net.hendyzone.lightstick.common.entity;

import net.hendyzone.lightstick.common.Stick;
import net.hendyzone.lightstick.common.blocks.TileEntityTorch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class EntityTorch extends EntityThrowable {
	private int mate;
	private boolean small;
	public EntityTorch(World par1World,EntityLivingBase entity,int mate,boolean small) {
		super(par1World,entity);
	this.mate=mate;
	this.small=small;
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		FMLCommonHandler.instance().getEffectiveSide();


        if (!this.worldObj.isRemote)
        {
        	if(this.rand.nextInt(8) == 0){
        		ItemStack stack;
        		if(small){
        			stack=new ItemStack(Stick.smallHandheldTorch,  1,mate);
        			}
        		else{
        			stack=new ItemStack(Stick.handheldTorch,  1,mate);
               }
                EntityItem item=new EntityItem(this.worldObj);
                item.setEntityItemStack(stack);
                item.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
        		worldObj.spawnEntityInWorld(item);	}
        	else if(this.rand.nextInt(8) == 1||this.rand.nextInt(8) == 2){
        		ItemStack stack=new ItemStack(Stick.charredStick);
        		 EntityItem item=new EntityItem(this.worldObj);
                 item.setEntityItemStack(stack);
                 item.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         		worldObj.spawnEntityInWorld(item);	
        		
        	}
        	else{
        		int x=MathHelper.floor_double(this.posX);
        		int y=MathHelper.floor_double(this.posY);
        		int z=MathHelper.floor_double(this.posZ);
        		if(small){
        			worldObj.setBlock(x, y, z, Stick.smallHandheldTorchBlock.blockID);
        			}
        		else{
        			worldObj.setBlock(x, y, z, Stick.handheldTorchBlock.blockID);
               }
        		
        		TileEntity tile=worldObj.getBlockTileEntity(x, y, z);
        		if(tile!=null){
        			((TileEntityTorch)tile).setLight(mate);
        		}
        		
        	}

        }
        for (int j = 0; j < 8; ++j)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
		
		

	}

}
