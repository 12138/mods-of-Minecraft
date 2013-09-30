package firegrass.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FireBlockStationary extends FireBlockFluid {

	public FireBlockStationary(int par1,int color)
    {
        super(par1,color);
        this.setTickRandomly(true);
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return this.blockMaterial != Material.lava;
    }

   
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);

        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            this.setNotStationary(par1World, par2, par3, par4);
        }
    }

    /**
     * Changes the block ID to that of an updating fluid.
     */
    private void setNotStationary(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, this.blockID - 1, l, 2);
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID - 1, this.tickRate(par1World));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.blockMaterial == Material.lava)
        {
            int l = par5Random.nextInt(3);
            int i1;
            int j1;

            for (i1 = 0; i1 < l; ++i1)
            {
                par2 += par5Random.nextInt(3) - 1;
                ++par3;
                par4 += par5Random.nextInt(3) - 1;
                j1 = par1World.getBlockId(par2, par3, par4);

              if (Block.blocksList[j1].blockMaterial.blocksMovement())
                {
                    return;
                }
            }

            if (l == 0)
            {
                i1 = par2;
                j1 = par4;

              
            }
        }
    }

    
}
