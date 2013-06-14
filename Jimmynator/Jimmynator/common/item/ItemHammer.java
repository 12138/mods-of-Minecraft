package Jimmynator.common.item;

import java.util.List;
import java.util.Random;

import Jimmynator.common.Jimmynator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemHammer extends ItemTool {
	
	private int weaponDamage;
	static Block[] effBlock={};
	protected Random ran=new Random();
	public ItemHammer(int par1, EnumToolMaterial par3EnumToolMaterial) {
		super(par1,0, par3EnumToolMaterial, effBlock);
		this.weaponDamage=2+par3EnumToolMaterial.getDamageVsEntity();
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}

	@Override
	public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack) {
		return weaponDamage;
	
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 1.0F;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		par1ItemStack.damageItem(1, par3EntityLiving);
		par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), this.weaponDamage);
		return false;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
				return false;
		
	}

	

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		 return 3600*20;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
	    float f = j / 20.0F;
	    if (f > 1.0F)
	    {
	      aoe(par1ItemStack, par2World, par3EntityPlayer);
	      par3EntityPlayer.swingItem();
	    }
		
	}

	private void aoe(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
	    if (par3EntityPlayer.isDead)
	    {
	      return;
	    }
	    double d6 = 5.0D;
	    double d7 = 5.0D;
	    List<Entity> list = Jimmynator.proxy.findTarget(par2World, AxisAlignedBB.getBoundingBox(par3EntityPlayer.posX - d6, par3EntityPlayer.posY - d7, par3EntityPlayer.posZ - d6, par3EntityPlayer.posX + d6, par3EntityPlayer.posY + d7, par3EntityPlayer.posZ + d6),par3EntityPlayer);
	    for (int k2 = 0; k2 < list.size(); k2++)
	    {
	        EntityLiving entity = (EntityLiving)list.get(k2);
	        double d5 = entity.getDistanceToEntity(par3EntityPlayer);
	        if (d5 <= 8.0D)
	        {
	          par1ItemStack.damageItem(2, par3EntityPlayer);
	          entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityPlayer), this.weaponDamage/2);
//	          hitEntity(par1ItemStack, entity, par3EntityPlayer);
//	          EntityLightningBolt lighting=new EntityLightningBolt(par2World, entity.posX, entity.posY, entity.posZ);
//	          par2World.spawnEntityInWorld(lighting);
	          par2World.playSoundEffect(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, "random.explode", 4.0F, (1.0F + (par2World.rand.nextFloat() - par2World.rand.nextFloat()) * 0.2F) * 0.7F);
	        }
	      
	    }
	}


	protected void back(EntityLiving entity, EntityPlayer player) {
		entity.motionX=0;
		entity.motionY=0;
		entity.motionZ=0;
		entity.motionX+=ran.nextGaussian();
		entity.motionY+=ran.nextGaussian();
		entity.motionZ+=ran.nextGaussian();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		 
	  par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        return par1ItemStack;
	}
	@Override
	 public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.bow;
	    }
	
	


}