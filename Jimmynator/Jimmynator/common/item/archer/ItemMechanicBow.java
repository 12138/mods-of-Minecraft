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

public class ItemMechanicBow extends ItemBow {
	 public static final String[] mechanicBowPullIconNameArray = new String[] {"mechanicBow_pull_0","mechanicBow_pull_1", "mechanicBow_pull_2", "mechanicBow_pull_3"};
	    @SideOnly(Side.CLIENT)
	    private Icon[] iconArray; 

	public ItemMechanicBow(int par1) {
		super(par1);
		this.setMaxDamage(251);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,EntityPlayer par3EntityPlayer, int par4) {
	if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
		 iconsetdefalut();
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
	            float f = (float)j / 20.0F;
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

	            
	             entityarrow.setDamage(entityarrow.getDamage() *0.75D);
	           
	            par1ItemStack.damageItem(1, par3EntityPlayer);
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	            
	                entityarrow.canBePickedUp = 2;
	          
	                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
	            

	            if (!par2World.isRemote)
	            {
	                par2World.spawnEntityInWorld(entityarrow);
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
					if(count>6){
				this.iconIndex=this.iconArray[2];
				}
					if(count>9){
				this.iconIndex=this.iconArray[3];
				}
			
		}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
this.iconchange(count);
		super.onUsingItemTick(stack, player, count);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		
		return 36000;
	}

	@Override
	public int getItemEnchantability() {
		
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+mechanicBowPullIconNameArray[0]);
		 this.iconArray = new Icon[mechanicBowPullIconNameArray.length];

		    for (int i = 0; i < this.iconArray.length; ++i)
		    {
		        this.iconArray[i] = par1IconRegister.registerIcon("Jimmynator:"+mechanicBowPullIconNameArray[i]);
		    }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon func_94599_c(int par1) {
		
		return this.iconArray[par1];
	}



	
}
