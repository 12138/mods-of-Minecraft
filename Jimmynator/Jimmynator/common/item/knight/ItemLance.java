package Jimmynator.common.item.knight;

import Jimmynator.common.Jimmynator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemLance extends ItemSword {
	private int demage;
	public ItemLance(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setMaxDamage(3122);
		this.setCreativeTab(Jimmynator.tabJimmynator);
		this.demage=par2EnumToolMaterial.getDamageVsEntity();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public int getDamageVsEntity(Entity par1Entity) {
		return this.demage;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		
		  par1ItemStack.damageItem(2, par3EntityLiving);
		    if ((par2EntityLiving.ridingEntity != null))
		    {
		      par2EntityLiving.mountEntity(null);
		    }
		    return true;
	}
}
