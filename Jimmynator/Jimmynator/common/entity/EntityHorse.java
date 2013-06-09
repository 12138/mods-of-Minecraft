package Jimmynator.common.entity;

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
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal {

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
	        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28F));
	        this.tasks.addTask(6, new EntityAIWander(this, f));
	        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	        this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		
		return super.interact(par1EntityPlayer);
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 100;
	}

}
