package Jimmynator.common.item.archer;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class ItemNetherBow extends ItemBow {
	 public static final String[] netherBowPullIconNameArray = new String[] {"netherBow_pull_0","netherBow_pull_1", "netherBow_pull_2", "netherBow_pull_3"};
	    @SideOnly(Side.CLIENT)
	    private Icon[] iconArray;
		private double damage=6; 

	public ItemNetherBow(int par1) {
		super(par1);
		this.setMaxDamage(2341);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,EntityPlayer par3EntityPlayer, int par4) {
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
		this.iconsetdefalut();
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

	        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return;
	        }
	        j = event.charge;

	        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

	        if (flag || (Jimmynator.proxy.isEquid(par3EntityPlayer, "archer")&&par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)))
	        {
	            float f = (float)j / 40.0F;
	            f = (f * f + f * 2.0F) / 3.0F;

	            if ((double)f < 0.1D)
	            {
	                return;
	            }

	            if (f > 1.0F)
	            {
	                f = 1.0F;
	            }

	            EntityArrow entityarrow1 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
	            EntityArrow entityarrow2 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
	            entityarrow2.setPosition(entityarrow2.posX, entityarrow2.posY+1, entityarrow2.posZ);
	            
	            EntityArrow entityarrow3 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
	            entityarrow3.setPosition(entityarrow3.posX, entityarrow3.posY+2, entityarrow3.posZ);

	            if (f == 1.0F)
	            {
	                entityarrow1.setIsCritical(true);
	                entityarrow2.setIsCritical(true);
	                entityarrow3.setIsCritical(true);
	            }
	            entityarrow1.setFire(100);
	            entityarrow2.setFire(100);
	            entityarrow3.setFire(100);
	            
	             entityarrow1.setDamage(this.damage);
	             entityarrow2.setDamage(this.damage);
	             entityarrow3.setDamage(this.damage);
	           
	            par1ItemStack.damageItem(2, par3EntityPlayer);
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	            
	                entityarrow1.canBePickedUp = 2;
	                entityarrow2.canBePickedUp = 2;
	                entityarrow3.canBePickedUp = 2;
	          
	                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
	                
	            

	            if (!par2World.isRemote)
	            {
	                par2World.spawnEntityInWorld(entityarrow1);
	                par2World.spawnEntityInWorld(entityarrow2);
	                par2World.spawnEntityInWorld(entityarrow3);
	            }
	        }
	}
	 @SideOnly(Side.CLIENT)
		private void iconsetdefalut() {
			
				this.iconIndex=this.iconArray[0];
			
		}

		 @SideOnly(Side.CLIENT)
			private void iconchange( int count) {
			 if(count>0){
					this.iconIndex=this.iconArray[1];
					}
						if(count>19){
					this.iconIndex=this.iconArray[2];
					}
						if(count>40){
					this.iconIndex=this.iconArray[3];
					}
				
			}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
			if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT){
				int j = this.getMaxItemUseDuration(stack) - count;
				this.iconchange(j);
			}
	
		
		super.onUsingItemTick(stack, player, count);
	}


	@Override
	public int getItemEnchantability() {
		
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+netherBowPullIconNameArray[0]);
		 this.iconArray = new Icon[netherBowPullIconNameArray.length];

		    for (int i = 0; i < this.iconArray.length; ++i)
		    {
		        this.iconArray[i] = par1IconRegister.registerIcon("Jimmynator:"+netherBowPullIconNameArray[i]);
		    }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon func_94599_c(int par1) {
		
		return this.iconArray[par1];
	}
}
