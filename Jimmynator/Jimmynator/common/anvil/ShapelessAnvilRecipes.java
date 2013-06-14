package Jimmynator.common.anvil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ShapelessAnvilRecipes implements IAnvilRecipe {

	 private final ItemStack recipeOutput;
	  private final List recipeItems;

	  public ShapelessAnvilRecipes(ItemStack itemstack, List list)
	  {
	    this.recipeOutput = itemstack;
	    this.recipeItems = list;
	  }

	  public ItemStack getRecipeOutput()
	  {
	    return this.recipeOutput;
	  }

	  public boolean matches(InventoryAnvil inventoryanvil)
	  {
	    ArrayList arraylist = new ArrayList(this.recipeItems);
	    int i = 0;

	    while (i < 3)
	    {
	      for (int j = 0; j < 5; j++)
	      {
	    	  ItemStack itemstack = inventoryanvil.getStackInRowAndColumn(j, i);
	        if (itemstack != null)
	        {
	          boolean flag = false;
	          Iterator iterator = arraylist.iterator();

	          while (iterator.hasNext())
	          {
	        	  ItemStack itemstack1 = (ItemStack)iterator.next();
	            if ((itemstack.itemID == itemstack1.itemID) && ((itemstack1.getItemDamage() == -1) || (itemstack.getItemDamage() == itemstack1.getItemDamage())))
	            {
	              flag = true;
	              arraylist.remove(itemstack1);
	            }
	          }
	          if (!flag)
	          {
	            return false;
	          }
	        }
	      }
	      i++;
	    }
	    return arraylist.isEmpty();
	  }

	  public ItemStack getCraftingResult(InventoryAnvil inventoryanvil)
	  {
	    return this.recipeOutput.copy();
	  }

	  public int getRecipeSize()
	  {
	    return this.recipeItems.size();
	  }

}
