package minecharacter.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minecharacter.MineCharacter;
import minecharacter.misc.Localization;
import minecharacter.network.PacketSpawnItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ItemNetherPickaxe extends ItemTool {

	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.railActivator};
	public ItemNetherPickaxe(int par1,
			EnumToolMaterial par3EnumToolMaterial) {
		super(par1, 3, par3EnumToolMaterial, blocksEffectiveAgainst);
		this.setCreativeTab(MineCharacter.tabMineCharacter);
		this.setMaxDamage(3321);
		
	}
	@Override
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ? (par1Block != Block.oreEmerald && par1Block != Block.blockEmerald ? (par1Block != Block.blockGold && par1Block != Block.oreGold ? (par1Block != Block.blockIron && par1Block != Block.oreIron ? (par1Block != Block.blockLapis && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true : (par1Block.blockMaterial == Material.iron ? true : par1Block.blockMaterial == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }
	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return Localization.localize(getUnlocalizedName(itemstack));
	}

	@Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }
	@Override
	public boolean hitEntity(ItemStack par1ItemStack,EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		par1ItemStack.damageItem(1, par3EntityLiving);
		return super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
	}
	
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z,
			EntityPlayer player) {
		World world=player.worldObj;
		
		ItemStack item=FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[world.getBlockId(X, Y, Z)]));
		if(item!=null&&world.getBlockMaterial(X, Y, Z).equals(Material.rock)){
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
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon=par1IconRegister.registerIcon("minecharacter:"+this.getUnlocalizedName().replace("item.", ""));
	}

}
