package Jimmynator.common.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Jimmynator.common.Jimmynator;
import Jimmynator.common.network.PacketSpawnItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.RecipesIngots;
import net.minecraft.world.World;

public class ItemNetherAxe extends ItemTool {
	 public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};
	public ItemNetherAxe(int par1,EnumToolMaterial par3EnumToolMaterial) {
		super(par1, 3, par3EnumToolMaterial, blocksEffectiveAgainst);
		this.setCreativeTab(Jimmynator.tabJimmynator);
		this.setMaxDamage(3321);
		
	}
	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		par1ItemStack.damageItem(1, par3EntityLiving);
		return super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
	}
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z,
			EntityPlayer player) {
		World world=player.worldObj;
		
		ItemStack item=FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[world.getBlockId(X, Y, Z)]));
		if(item!=null&&world.getBlockMaterial(X, Y, Z).equals(Material.wood)){
			itemstack.damageItem(2, player);
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
			PacketSpawnItem.spawnItem(item.itemID,item.getItemDamage(), item.stackSize, X,Y,Z);
			world.setBlockToAir(X, Y, Z);
			return true;
		}
		return false;
	    
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex=par1IconRegister.registerIcon("Jimmynator:"+this.getUnlocalizedName().replace("item.", ""));
	}

}
