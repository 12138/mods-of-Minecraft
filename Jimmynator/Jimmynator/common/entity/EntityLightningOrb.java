package Jimmynator.common.entity;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import Jimmynator.common.Jimmynator;
import Jimmynator.common.network.PacketSpawnItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLightningOrb extends EntityLightningBolt {
	 /**
     * Declares which state the lightning bolt is in. Whether it's in the air, hit the ground, etc.
     */
    private int lightningState;

    /**
     * A random long that is used to change the vertex of the lightning rendered in RenderLightningBolt
     */
    public long boltVertex = 0L;

    /**
     * Determines the time before the EntityLightningBolt is destroyed. It is a random integer decremented over time.
     */
    private int boltLivingTime;
    private int type;
	public EntityLightningOrb(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
        this.setLocationAndAngles(par2, par4, par6, 0.0F, 0.0F);
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;

	}
	public EntityLightningOrb(World par1World, double par2, double par4,
			double par6,int type) {
		this(par1World,par2,par4,par6);
		this.type=type;

	}
	@Override
	public void onUpdate() {
		 if (this.lightningState == 2)
	        {
	            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
	            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
	        }

	        --this.lightningState;

	        if (this.lightningState < 0)
	        {
	            if (this.boltLivingTime == 0)
	            {
	                this.setDead();
	            }
	            else if (this.lightningState < -this.rand.nextInt(10))
	            {
	                --this.boltLivingTime;
	                this.lightningState = 1;
	                this.boltVertex = this.rand.nextLong();

	        
	            }
	        }

	        if (this.lightningState >= 0)
	        {
	            if (this.worldObj.isRemote)
	            {
	                this.worldObj.lastLightningBolt = 2;
	            }
	            else
	            {
	                double d0 = 1.0D;
	                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

	                for (int l = 0; l < list.size(); ++l)
	                {
	                    Entity entity = (Entity)list.get(l);
	                    if(entity instanceof EntityItem){
	                    	System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
	                    	if (((EntityItem)entity).getEntityItem().itemID == Jimmynator.magicPowder.itemID){
//	                    		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
//	                    		PacketSpawnItem.spawnItem(Jimmynator.etherealPowder.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.etherealPowder.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
	                    			entity.setDead();
	                    		this.setDead();
	                    	}
	                    	else if(((EntityItem)entity).getEntityItem().itemID == Item.ingotIron.itemID){
//	                    		System.out.println(((EntityItem)entity).getEntityItem().stackSize);
//	                    		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
//	                    		PacketSpawnItem.spawnItem(Jimmynator.ninjaSteel.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
////	                    		else{
////	                    		if(!this.worldObj.isRemote)
////	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.ninjaSteel.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize)));
////	                    		}
	                    		this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.ninjaSteel.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
////	                    		System.out.println("spawn true");
////	                    		System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
	                    		
////	                    		else{
////	                    			System.out.println("spawn false");
////	                    			System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
////	                    		}
	                    		entity.setDead();
	                    		this.setDead();
	                    		
	                    	}
	                    		
	           
	                    	else if(((EntityItem)entity).getEntityItem().itemID == Jimmynator.rune.itemID){
	                    		if(this.type==1){
//	                    			if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
//	                    			PacketSpawnItem.spawnItem(Jimmynator.fireRune.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.fireRune.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
		                    		entity.setDead();
		                    		this.setDead();
	                    			
	                    		}
	                    		if(this.type==2){
//	                    			PacketSpawnItem.spawnItem(Jimmynator.iceRune.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.iceRune.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
		                    		entity.setDead();
		                    		this.setDead();
	                    		}
	                    		if(this.type==3){
//	                    			PacketSpawnItem.spawnItem(Jimmynator.lifeRune.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.lifeRune.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
		                    		entity.setDead();
		                    		this.setDead();
	                    		}
	                    		if(this.type==4){
//	                    			PacketSpawnItem.spawnItem(Jimmynator.deathRune.itemID, 0, ((EntityItem)entity).getEntityItem().stackSize, this.posX, this.posY, this.posZ);
	                    			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj,this.posX, this.posY, this.posZ, new ItemStack(Jimmynator.deathRune.itemID, ((EntityItem)entity).getEntityItem().stackSize,0)));
		                    		entity.setDead();
		                    		this.setDead();
	                    		}
	                    }
	                    	 break;
	                }
	                    }}
	            }
	        }
	

}
