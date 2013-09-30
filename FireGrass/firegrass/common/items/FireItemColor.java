package firegrass.common.items;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firegrass.common.FireGrass;

public class FireItemColor extends Item {
	public static final String[] battolColorNames = new String[] {"red", "green", "blue","black", "brown",  "cyan", "gray","lightBlue", "lime","magenta","orange","pink",  "purple", "silver","white",  "yellow"};
	public static final String[] battolColorNamesCN = new String[] {"红", "绿", "蓝","黑", "棕",  "青", "灰","亮蓝", "黄绿","品红","橙","粉红",  "紫", "银白","白",  "黄"};
    public static final String[] kind = new String[] {"water_red",  "water_green","water_blue", "water_black","water_brown","water_cyan", "water_gray", "water_lightBlue","water_lime","water_magenta","water_orange", "water_pink", "water_purple",  "water_silver",  "water_white", "water_yellow"  };
  
	public FireItemColor(int par1) {
		super(par1);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	 int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		par3List.add(battolColorNamesCN[i]);
	}


	@SideOnly(Side.CLIENT)
    private Icon[] icons;

   

    @SideOnly(Side.CLIENT)

    @Override
    public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 15);
        return this.icons[j];
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	
         MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, false);

         if (movingobjectposition == null)
         {
             return par1ItemStack;
         }
         else
         {
             
             

             if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
             {
                 int i = movingobjectposition.blockX;
                 int j = movingobjectposition.blockY;
                 int k = movingobjectposition.blockZ;

                 if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
                 {
                     return par1ItemStack;
                 }

                
                     if (movingobjectposition.sideHit == 0)
                     {
                         --j;
                     }

                     if (movingobjectposition.sideHit == 1)
                     {
                         ++j;
                     }

                     if (movingobjectposition.sideHit == 2)
                     {
                         --k;
                     }

                     if (movingobjectposition.sideHit == 3)
                     {
                         ++k;
                     }

                     if (movingobjectposition.sideHit == 4)
                     {
                         --i;
                     }

                     if (movingobjectposition.sideHit == 5)
                     {
                         ++i;
                     }

                     if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
                     {
                         return par1ItemStack;
                     }
                     Material blockMaterial=par2World.getBlockMaterial(i, j, k);
                 	
                     
                 
                 	int damage = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		if(blockMaterial==Material.water&&par2World.getBlockMetadata(i, j, k) == 0){
			changeColor(i,j,k,damage,par2World);
			
		}
		else{
			return par1ItemStack;
		}
		}
             }
         if (par3EntityPlayer.capabilities.isCreativeMode)
         {
             return par1ItemStack;
         }
    	
    	return new ItemStack(FireGrass.fireGrassBottle);
        
    }
    
   
    
    private void changeColor(int x, int y, int z,int damage,World world){
    	world.setBlock(x, y, z, FireGrass.colorStillFluid[damage].blockID, 0, 2);
				
			
	}
    	
    	
    

    

    
   
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 16; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[kind.length];

        for (int i = 0; i < kind.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon(FireGrass.modName+":"+kind[i]);
        }
    }

}
