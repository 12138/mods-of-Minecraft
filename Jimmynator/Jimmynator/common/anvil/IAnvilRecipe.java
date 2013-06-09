package Jimmynator.common.anvil;

import net.minecraft.item.ItemStack;

public abstract interface IAnvilRecipe {
	  public abstract boolean matches(InventoryAnvil paramInventoryAnvil);

	  public abstract ItemStack getCraftingResult(InventoryAnvil paramInventoryAnvil);

	  public abstract int getRecipeSize();

	  public abstract ItemStack getRecipeOutput();

}
