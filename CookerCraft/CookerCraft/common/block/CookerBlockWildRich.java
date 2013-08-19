package CookerCraft.common.block;

import java.util.ArrayList;
import java.util.Random;

import CookerCraft.common.CookerCraft;
import CookerCraft.common.utils.InitItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class CookerBlockWildRich extends BlockCrops {
	protected Icon[] rice;

	public CookerBlockWildRich(int par1) {
		super(par1);

	}

	@Override
	protected int getSeedItem() {
		return InitItem.wildRice.itemID;
	}

	@Override
	protected int getCropItem() {
		return InitItem.rice.itemID;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
			int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5,
				par6, 0);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z,
				metadata, fortune);

		if (metadata >= 7) {
			for (int n = 0; n < 3 + fortune; n++) {
				if (world.rand.nextInt(15) <= metadata) {
					ret.add(new ItemStack(this.getSeedItem(), 1, 0));
				}
			}
		}

		return ret;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		Random ran = new Random();
		if (par1 == 7) {
			if (ran.nextInt(3) == 0)
				return this.getCropItem();
			else
				return this.getSeedItem();
		}
		return this.getSeedItem();
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return this.getSeedItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if (par2 < 0 || par2 > 7) {
			par2 = 7;
		}

		return this.rice[par2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.rice = new Icon[8];

		for (int i = 0; i < this.rice.length; ++i) {
			this.rice[i] = par1IconRegister.registerIcon(CookerCraft.ModId
					+ ":paddy_" + i);
		}
	}

}
