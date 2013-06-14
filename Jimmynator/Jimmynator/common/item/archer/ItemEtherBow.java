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

public class ItemEtherBow extends ItemBow {
	 public static final String[] etherBowPullIconNameArray = new String[] {"etherBow_pull_0","etherBow_pull_1", "etherBow_pull_2", "etherBow_pull_3"};
	    @SideOnly(Side.CLIENT)
	    private Icon[] iconArray; 
	    private double damage=10; 
	public ItemEtherBow(int par1) {
		super(par1);
		this.setMaxDamage(3122);
		this.setCreativeTab(Jimmynator.tabJimmynator);
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
						if(count>20){
					this.iconIndex=this.iconArray[2];
					}
						if(count>30){
					this.iconIndex=this.iconArray[3];
					}
				
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
	            float f = (float)j / 30.0F;
	            f = (f * f + f * 2.0F) / 3.0F;

	            if ((double)f < 0.1D)
	            {
	                return;
	            }

	            if (f > 1.0F)
	            {
	                f = 1.0F;
	            }

	            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

	            if (f == 1.0F)
	            {
	                entityarrow.setIsCritical(true);
	            }

	            
	             entityarrow.setDamage(this.damage);
	           
	            par1ItemStack.damageItem(2, par3EntityPlayer);
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	            
	                entityarrow.canBePickedUp = 2;
	           
	                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
	            

	            if (!par2World.isRemote)
	            {
	                par2World.spawnEntityInWorld(entityarrow);
	            }
	        }
	}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT){
			if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT){
				int j = this.getMaxItemUseDuration(stack) - count;
				this.iconchange(j);
			}
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
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+etherBowPullIconNameArray[0]);
		 this.iconArray = new Icon[etherBowPullIconNameArray.length];

		    for (int i = 0; i < this.iconArray.length; ++i)
		    {
		        this.iconArray[i] = par1IconRegister.registerIcon("Jimmynator:"+etherBowPullIconNameArray[i]);
		    }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon func_94599_c(int par1) {
		
		return this.iconArray[par1];
	}



}
