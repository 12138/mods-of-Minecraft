package Jimmynator.common.anvil;

import net.minecraft.item.ItemStack;

public class ShapedAnvilRecipes implements IAnvilRecipe {

	 private int recipeWidth;
	  private int recipeHeight;
	  private ItemStack[] recipeItems;
	  private ItemStack recipeOutput;
	  public final int recipeOutputItemID;

	  public ShapedAnvilRecipes(int i, int j, ItemStack[] aitemstack, ItemStack itemstack)
	  {
	    this.recipeOutputItemID = itemstack.itemID;
	    this.recipeWidth = i;
	    this.recipeHeight = j;
	    this.recipeItems = aitemstack;
	    this.recipeOutput = itemstack;
	  }

	  public ItemStack getRecipeOutput()
	  {
	    return this.recipeOutput;
	  }

	  public boolean matches(InventoryAnvil inventoryanvil)
	  {
	    for (int i = 0; i <= 5 - this.recipeWidth; i++)
	    {
	      for (int j = 0; j <= 3 - this.recipeHeight; j++)
	      {
	        if (checkMatch(inventoryanvil, i, j, true))
	        {
	          return true;
	        }
	        if (checkMatch(inventoryanvil, i, j, false))
	        {
	          return true;
	        }
	      }

	    }

	    return false;
	  }

	  private boolean checkMatch(InventoryAnvil inventoryanvil, int i, int j, boolean flag)
	  {
	    for (int k = 0; k < 5; k++)
	    {
	      for (int l = 0; l < 3; l++)
	      {
	        int i1 = k - i;
	        int j1 = l - j;
	        ItemStack itemstack = null;
	        if ((i1 >= 0) && (j1 >= 0) && (i1 < this.recipeWidth) && (j1 < this.recipeHeight))
	        {
	          if (flag)
	          {
	            itemstack = this.recipeItems[(this.recipeWidth - i1 - 1 + j1 * this.recipeWidth)];
	          }
	          else {
	            itemstack = this.recipeItems[(i1 + j1 * this.recipeWidth)];
	          }
	        }
	        ItemStack itemstack1 = inventoryanvil.getStackInRowAndColumn(k, l);
	        if ((itemstack1 != null) || (itemstack != null))
	        {
	          if (((itemstack1 == null) && (itemstack != null)) || ((itemstack1 != null) && (itemstack == null)))
	          {
	            return false;
	          }
	          if (itemstack.itemID != itemstack1.itemID)
	          {
	            return false;
	          }
	          if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage())
	          {
	            return false;
	          }
	        }
	      }
	    }

	    return true;
	  }

	  public ItemStack getCraftingResult(InventoryAnvil inventoryanvil)
	  {
	    return new ItemStack(this.recipeOutput.itemID, this.recipeOutput.stackSize, this.recipeOutput.getItemDamage());
	  }

	  public int getRecipeSize()
	  {
	    return this.recipeWidth * this.recipeHeight;
	  }

}
