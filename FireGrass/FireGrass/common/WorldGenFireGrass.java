package FireGrass.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFireGrass implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	switch (world.provider.dimensionId) {
	case 0:
		generateSurface(world, random, chunkX * 16, chunkZ * 16);
		break;
	default:
		break;

	}
	}

	 public boolean generateSurface(World par1World, Random par2Random, int par3, int par5)
	    {
			for (int var6 = 0; var6 < 20; ++var6) {
				int var8;
				int var7 = par3 + par2Random.nextInt(16);
				if(par2Random.nextInt(1)==0)
				var8 = par1World.getTopSolidOrLiquidBlock(par3, par5)+par2Random.nextInt(1);
				else
					var8 = 50+par2Random.nextInt(20);
				int var9 = par5 + par2Random.nextInt(16);

				if (par1World.isAirBlock(var7, var8, var9)) {
					int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);
					for (int var11 = 0; var11 < var10; ++var11) {
						if (FireGrass.fireGrass.canBlockStay(par1World, var7,
								var8 + var11, var9)) {
							par1World.setBlock(var7, var8 + var11, var9,
									FireGrass.fireGrass.blockID,0,3);
						}
					}
				}
			}
//			for (int var6 = 0; var6 < 20; ++var6) {
//				int var8;
//				int var7 = par3 + par2Random.nextInt(16);
//				if(par2Random.nextInt(1)==0)
//				var8 = par1World.getTopSolidOrLiquidBlock(par3, par5)+par2Random.nextInt(1);
//				else
//					var8 = 50+par2Random.nextInt(20);
//				int var9 = par5 + par2Random.nextInt(16);
//
//				if (par1World.isAirBlock(var7, var8, var9)) {
//					int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);
//					for (int var11 = 0; var11 < var10; ++var11) {
//						if (CookerCraft.blockBamboow.canBlockStay(par1World, var7,
//								var8 + var11, var9)) {
//							par1World.setBlockAndMetadataWithNotify(var7, var8 + var11, var9,
//									CookerCraft.blockBamboow.blockID,0,3);
//						}
//					}
//				}
//			}

	        return true;
	    }

}
