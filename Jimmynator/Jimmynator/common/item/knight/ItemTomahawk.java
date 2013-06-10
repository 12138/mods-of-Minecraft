package Jimmynator.common.item.knight;

import Jimmynator.common.Jimmynator;
import Jimmynator.common.entity.EntityTomahawk;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemTomahawk extends ItemTool {
	 public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};
	public ItemTomahawk(int par1,EnumToolMaterial par3EnumToolMaterial) {
		super(par1, 3, par3EnumToolMaterial, blocksEffectiveAgainst);
		this.setCreativeTab(Jimmynator.tabJimmynator);
	}
	
	@Override
	 public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	    {
	        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	    }
	

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		 return 3600*20;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
	    float f = j / 20.0F;
	    if (f > 1.0F)
	    {
	        EntityTomahawk entityTomahawk= new EntityTomahawk(par2World, par1ItemStack, par3EntityPlayer, f * 2.0F);
	       par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	        par3EntityPlayer.inventory.consumeInventoryItem(Jimmynator.tomahawk.itemID);
//        if (!par2World.isRemote)
	        {
	          if(par2World.spawnEntityInWorld(entityTomahawk)){
	        	//  System.out.println("spawn");
	          }
	        }
	    }
		
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
	


	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}

}
