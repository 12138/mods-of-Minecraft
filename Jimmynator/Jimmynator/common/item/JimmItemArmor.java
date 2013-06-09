package Jimmynator.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Jimmynator.common.Jimmynator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class JimmItemArmor extends ItemArmor implements IArmorTextureProvider {

	public JimmItemArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}

	@Override
	  public String getArmorTextureFile(ItemStack itemstack)
	  {
	    if ((itemstack.itemID == Jimmynator.knightHelmet.itemID) ||(itemstack.itemID == Jimmynator.knightChest.itemID) || (itemstack.itemID == Jimmynator.knightBoots.itemID))
	    {
	      return "/mods/Jimmynator/textures/armor/knight_1.png";
	    }
	    if (itemstack.itemID == Jimmynator.knightLegs.itemID)
	    {
	      return "/mods/Jimmynator/textures/armor/knight_2.png";
	    }
	    if  ((itemstack.itemID == Jimmynator.grimHelmet.itemID) ||(itemstack.itemID == Jimmynator.grimChest.itemID) || (itemstack.itemID == Jimmynator.grimBoots.itemID))
	    {
	      return "/mods/Jimmynator/textures/armor/grim_1.png";
	    }
	    if  (itemstack.itemID == Jimmynator.grimLegs.itemID)
	    {
	      return "/mods/Jimmynator/textures/armor/grim_2.png";
	    }
	    if ((itemstack.itemID == Jimmynator.mageHelmet.itemID) ||(itemstack.itemID == Jimmynator.mageChest.itemID) || (itemstack.itemID == Jimmynator.mageBoots.itemID))
	    {
	      return "/mods/Jimmynator/textures/armor/mage_1.png";
	    }
	    if  (itemstack.itemID == Jimmynator.mageLegs.itemID)
	    {
	      return "/mods/Jimmynator/textures/armor/mage_2.png";
	    }
	    if ((itemstack.itemID == Jimmynator.archerHelmet.itemID) ||(itemstack.itemID == Jimmynator.archerChest.itemID) || (itemstack.itemID == Jimmynator.archerBoots.itemID))
	    {
	      return "/mods/Jimmynator/textures/armor/archer_1.png";
	    }
	    if  (itemstack.itemID == Jimmynator.archerLegs.itemID)
	    {
	      return "/mods/Jimmynator/textures/armor/archer_2.png";
	    }
	    if((itemstack.itemID == Jimmynator.ninjaHelmet.itemID) ||(itemstack.itemID == Jimmynator.ninjaChest.itemID) || (itemstack.itemID == Jimmynator.ninjaBoots.itemID))
	    {
	      return "/mods/Jimmynator/textures/armor/ninja_1.png";
	    }
	    if (itemstack.itemID == Jimmynator.ninjaLegs.itemID)
	    {
	      return "/mods/Jimmynator/textures/armor/ninja_2.png";
	    }
	    return "";
	  }

}
