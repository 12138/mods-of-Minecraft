package CookerCraft.common;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
public class CookerBlockBamboow extends BlockReed {

	protected CookerBlockBamboow(int par1, int par2) {
		super(par1);
		
	}
	@Override
   public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
   {
       if (par1World.isAirBlock(par2, par3 + 1, par4))
       {
           int var6;

           for (var6 = 1; par1World.getBlockId(par2, par3 - var6, par4) == this.blockID; ++var6)
           {
               ;
           }

           if (var6 < 8)
           {
               int var7 = par1World.getBlockMetadata(par2, par3, par4);

               if (var7 == 15)
               {
                   par1World.setBlock(par2, par3 + 1, par4, this.blockID,0,2);
                   par1World.setBlockMetadataWithNotify(par2, par3, par4, 0,2);
               }
               else
               {
                   par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 + 1,2);
               }
           }
       }
   }

   /**
    * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
    */
   public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
   {
       Block block = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
       return (block != null && this.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
   }
@Override
   /**
    * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
    * their own) Args: x, y, z, neighbor blockID
    */
   public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
   {
	   if (!this.canBlockStay(par1World, par2, par3, par4))
       {
           this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
           par1World.setBlock(par2, par3, par4, 0,0,0);
       }
   }
  

  

   @SuppressWarnings("incomplete-switch")
@Override
public boolean canSustainPlant(World world, int x, int y, int z,ForgeDirection direction, IPlantable plant) {
	   int plantID = plant.getPlantID(world, x, y + 1, z);
	   EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
       int setBlock=world.getBlockId(x, y, z);
       if (plantID == CookerCraft.blockBamboow.blockID && setBlock == CookerCraft.blockBamboow.blockID)
       {
           return true;
       }

       

       if ( setBlock == Block.grass.blockID || setBlock == Block.dirt.blockID || setBlock == Block.tilledField.blockID)
       {
           return true;
       }

       switch (plantType)
       {
           case Beach:
               boolean isBeachNoWater = (setBlock == Block.grass.blockID || setBlock == Block.dirt.blockID);
               
               return isBeachNoWater;
       }

       return false;
}
/**
    * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
    */
   public boolean canBlockStay(World par1World, int par2, int par3, int par4)
   {
       return this.canPlaceBlockAt(par1World, par2, par3, par4);
   }

   /**
    * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
    * cleared to be reused)
    */
   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
   {
       return null;
   }

   /**
    * Returns the ID of the items to drop on destruction.
    */
   public int idDropped(int par1, Random par2Random, int par3)
   {
	   return CookerCraft.bamboow.itemID;
   }

   /**
    * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
    * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
    */
   public boolean isOpaqueCube()
   {
       return false;
   }

   /**
    * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
    */
   public boolean renderAsNormalBlock()
   {
       return false;
   }

   /**
    * The type of render function that is called for this block
    */
   public int getRenderType()
   {
       return 1;
   }

   @SideOnly(Side.CLIENT)

   /**
    * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
    */
   public int idPicked(World par1World, int par2, int par3, int par4)
   {
       return CookerCraft.bamboow.itemID;
   }

   @Override
   public EnumPlantType getPlantType(World world, int x, int y, int z)
   {
       return EnumPlantType.Beach;
   }

   @Override
   public int getPlantID(World world, int x, int y, int z)
   {
       return blockID;
   }


@Override
@SideOnly(Side.CLIENT)
public void registerIcons(IconRegister par1IconRegister) {
	this.blockIcon=par1IconRegister.registerIcon(CookerCraft.ModName+":blockBamboow");
}
@Override
   public int getPlantMetadata(World world, int x, int y, int z)
   {
       return world.getBlockMetadata(x, y, z);
   }
}
