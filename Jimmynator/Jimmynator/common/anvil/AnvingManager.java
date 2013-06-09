package Jimmynator.common.anvil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import Jimmynator.common.Jimmynator;
public class AnvingManager {
	private static final AnvingManager instance = new AnvingManager();
	  private List recipes;

	  public static final AnvingManager getInstance()
	  {
	    return instance;
	  }

	  private AnvingManager()
	  {
	    this.recipes = new ArrayList();

	    addRecipe(new ItemStack(Jimmynator.excaliburBlade, 1), new Object[] { "     ", "DXDDD", "     ", Character.valueOf('X'), Item.diamond, Character.valueOf('D'), Jimmynator.etherealIngot });

	    addRecipe(new ItemStack(Jimmynator.excaliburHandle, 1), new Object[] { "DDXDD", "  D  ", "  X  ", Character.valueOf('X'), Item.diamond, Character.valueOf('D'), Jimmynator.etherealIngot });

	    addRecipe(new ItemStack(Jimmynator.excalibur, 1), new Object[] { " X", "D ", Character.valueOf('X'), Jimmynator.excaliburBlade, Character.valueOf('D'), Jimmynator.excaliburHandle });

	    addRecipe(new ItemStack(Jimmynator.luciferBlade, 1), new Object[] { " D D ", "DDDDX", " D D ", Character.valueOf('X'), Jimmynator.luciferite, Character.valueOf('D'), Jimmynator.demonite });

	    addRecipe(new ItemStack(Jimmynator.luciferHandle, 1), new Object[] { "DDXDD", "  D  ", "  X  ", Character.valueOf('X'), Jimmynator.luciferite, Character.valueOf('D'), Jimmynator.demonite });

	    addRecipe(new ItemStack(Jimmynator.lucifer, 1), new Object[] { " X", "D ", Character.valueOf('X'), Jimmynator.luciferBlade, Character.valueOf('D'), Jimmynator.luciferHandle });

	    addRecipe(new ItemStack(Jimmynator.reaperBlade, 1), new Object[] { "DDDDD", "XXXXX", Character.valueOf('X'), Jimmynator.luciferite, Character.valueOf('D'), Jimmynator.demonite });

	    addRecipe(new ItemStack(Jimmynator.netherHandle, 1), new Object[] { "     ", "XDDDX", "     ", Character.valueOf('X'), Jimmynator.luciferite, Character.valueOf('D'), Jimmynator.demonite });

	    addRecipe(new ItemStack(Jimmynator.reaper, 1), new Object[] { " X", "D ", Character.valueOf('X'), Jimmynator.reaperBlade, Character.valueOf('D'), Jimmynator.netherHandle });

	    addRecipe(new ItemStack(Jimmynator.mattockBlade, 1), new Object[] { "XDDDD", "  DDD", "    D", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'), Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.mattock, 1), new Object[] { " D", "X ", Character.valueOf('X'), Item.diamond, Character.valueOf('D'), Jimmynator.mattockBlade });

	    addRecipe(new ItemStack(Jimmynator.tomahawkBlade, 1), new Object[] { "XDDDD", "XD   ", "X    ", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.tomahawk, 1), new Object[] { " D", "X ", Character.valueOf('X'), Item.diamond, Character.valueOf('D'), Jimmynator.tomahawkBlade });

	    addRecipe(new ItemStack(Jimmynator.mjolnirBlade, 1), new Object[] { "XDDDX", "DDDDD", "XDDDX", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.mjolnirHandle, 1), new Object[] { "VVVVV", "XDDDD", "VVVVV", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond, Character.valueOf('V'), Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.mjolnir, 1), new Object[] { " D", "X ", Character.valueOf('X'), Jimmynator.mjolnirHandle, Character.valueOf('D'), Jimmynator.mjolnirBlade });

	    addRecipe(new ItemStack(Jimmynator.netherAxeBlade, 1), new Object[] { "XDDDD", "XDD D", " XD  ", Character.valueOf('D'), Jimmynator.demonite, Character.valueOf('X'), Jimmynator.luciferite });

	    addRecipe(new ItemStack(Jimmynator.netherAxe, 1), new Object[] { " D", "X ", Character.valueOf('X'), Jimmynator.netherHandle, Character.valueOf('D'), Jimmynator.netherAxeBlade });

	    addRecipe(new ItemStack(Jimmynator.netherPickaxeBlade, 1), new Object[] { "XDDDX", "  DDX", "    X", Character.valueOf('D'), Jimmynator.demonite, Character.valueOf('X'), Jimmynator.luciferite });

	    addRecipe(new ItemStack(Jimmynator.netherPickaxe, 1), new Object[] { " D", "X ", Character.valueOf('X'), Jimmynator.netherHandle, Character.valueOf('D'), Jimmynator.netherPickaxeBlade });

	    addRecipe(new ItemStack(Jimmynator.lanceBlade, 1), new Object[] { "     ", "XDXDX", "     ", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.lanceHandle, 1), new Object[] { "   D ", "XDXDX", "   D ", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.lance, 1), new Object[] { "D ", " X", Character.valueOf('X'), Jimmynator.lanceHandle, Character.valueOf('D'), Jimmynator.lanceBlade });

	    addRecipe(new ItemStack(Jimmynator.mechanicBow, 1), new Object[] { " DDD ", "D   D", "VVVVV", Character.valueOf('D'), Item.ingotIron, Character.valueOf('V'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.etherBow, 1), new Object[] { " DXD ", "D   D", "VVVVV", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond, Character.valueOf('V'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.netherBow, 1), new Object[] { " DXD ", "D   D", "VVVVV", Character.valueOf('D'), Jimmynator.demonite, Character.valueOf('X'), Jimmynator.luciferite, Character.valueOf('V'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.shuriken, 4), new Object[] { " X ", "XXX", " X ", Character.valueOf('X'), Jimmynator.ninjaSteel });

	    addRecipe(new ItemStack(Jimmynator.katanaBlade, 1), new Object[] { "     ", "XXXXX", "     ", Character.valueOf('X'), Jimmynator.ninjaSteel });

	    addRecipe(new ItemStack(Jimmynator.katanaHandle, 1), new Object[] { "XXXX", " XX ", " XX ", Character.valueOf('X'), Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.katana, 1), new Object[] { " D", "X ", Character.valueOf('D'), Jimmynator.katanaBlade, Character.valueOf('X'), Jimmynator.katanaHandle });

	    addRecipe(new ItemStack(Jimmynator.smokeGrenade, 4), new Object[] { " DDD ", "DXVXD", " DDD ", Character.valueOf('X'), Item.coal, Character.valueOf('V'), Item.ingotIron, Character.valueOf('D'), Jimmynator.ninjaSteel });

	    addRecipe(new ItemStack(Jimmynator.knightChest, 1), new Object[] { "DXDXD", " DXD ", " DDD ", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.knightHelmet, 1), new Object[] { " DXD ", "DXDXD", "D   D", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.knightLegs, 1), new Object[] { "DXDDX", "   DD", "DXDDX", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.knightBoots, 1), new Object[] { " D D ", " X X ", "DD DD", Character.valueOf('D'), Jimmynator.etherealIngot, Character.valueOf('X'),Item.diamond });

	    addRecipe(new ItemStack(Jimmynator.grimChest, 1), new Object[] { "DDDDD", " DDD ", " DDD ", Character.valueOf('D'), Jimmynator.soul });

	    addRecipe(new ItemStack(Jimmynator.grimHelmet, 1), new Object[] { " DDD ", "DDDDD", "D   D", Character.valueOf('D'), Jimmynator.soul });

	    addRecipe(new ItemStack(Jimmynator.grimLegs, 1), new Object[] { "DDDDD", "   DD", "DDDDD", Character.valueOf('D'), Jimmynator.soul });

	    addRecipe(new ItemStack(Jimmynator.grimBoots, 1), new Object[] { " D D ", " D D ", "DD DD", Character.valueOf('D'), Jimmynator.soul });

	    addRecipe(new ItemStack(Jimmynator.mageChest, 1), new Object[] { "DDDDD", " DDD ", " DDD ", Character.valueOf('D'), Jimmynator.magicString });

	    addRecipe(new ItemStack(Jimmynator.mageHelmet, 1), new Object[] { " DDD ", "DDDDD", "D   D", Character.valueOf('D'), Jimmynator.magicString });

	    addRecipe(new ItemStack(Jimmynator.mageLegs, 1), new Object[] { "DDDDD", "   DD", "DDDDD", Character.valueOf('D'), Jimmynator.magicString });

	    addRecipe(new ItemStack(Jimmynator.mageBoots, 1), new Object[] { " D D ", " D D ", "DD DD", Character.valueOf('D'), Jimmynator.magicString });

	    addRecipe(new ItemStack(Jimmynator.archerChest, 1), new Object[] { "DDDDD", " DXD ", " DDD ", Character.valueOf('D'),Item.silk, Character.valueOf('X'), Jimmynator.quiver });

	    addRecipe(new ItemStack(Jimmynator.archerHelmet, 1), new Object[] { " DDD ", "DDDDD", "D   D", Character.valueOf('D'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.archerLegs, 1), new Object[] { "DDDDD", "   DD", "DDDDD", Character.valueOf('D'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.archerBoots, 1), new Object[] { " D D ", " D D ", "DD DD", Character.valueOf('D'),Item.silk });

	    addRecipe(new ItemStack(Jimmynator.ninjaChest, 1), new Object[] { "DDDDD", " DDD ", " DDD ", Character.valueOf('D'), new ItemStack(Block.cloth, 1, 15) });

	    addRecipe(new ItemStack(Jimmynator.ninjaHelmet, 1), new Object[] { " DDD ", "DDDDD", "D   D", Character.valueOf('D'), new ItemStack(Block.cloth, 1, 15) });

	    addRecipe(new ItemStack(Jimmynator.ninjaLegs, 1), new Object[] { "DDDDD", "   DD", "DDDDD", Character.valueOf('D'), new ItemStack(Block.cloth, 1, 15) });

	    addRecipe(new ItemStack(Jimmynator.ninjaBoots, 1), new Object[] { " D D ", " D D ", "DD DD", Character.valueOf('D'), new ItemStack(Block.cloth, 1, 15) });

//	    addRecipe(new ItemStack(yr.aA, 1), new Object[] { "X   D", "DDDDD", "XXXXX", Character.valueOf('D'), yr.aF, Character.valueOf('X'), pb.J });
	    //BUG
	  }

	  void addRecipe(ItemStack itemstack, Object ... par2ArrayOfObj)
	  {
	    String s = "";
	    int i = 0;
	    int j = 0;
	    int k = 0;
	    if ((par2ArrayOfObj[i] instanceof String[]))
	    {
	      String[] as = (String[])par2ArrayOfObj[(i++)];
	      for (int l = 0; l < as.length; l++)
	      {
	        String s2 = as[l];
	        k++;
	        j = s2.length();
	        s = s + s2;
	      }
	    }
	    else
	    {
	      while ((par2ArrayOfObj[i] instanceof String))
	      {
	        String s1 = (String)par2ArrayOfObj[(i++)];
	        k++;
	        j = s1.length();
	        s = s + s1;
	      }
	    }
	    HashMap hashmap = new HashMap();
	    for (; i < par2ArrayOfObj.length; i += 2)
	    {
	      Character character = (Character)par2ArrayOfObj[i];
	      ItemStack itemstack1 = null;
	      if ((par2ArrayOfObj[(i + 1)] instanceof Item))
	      {
	        itemstack1 = new ItemStack((Item)par2ArrayOfObj[(i + 1)]);
	      }
	      else if ((par2ArrayOfObj[(i + 1)] instanceof Block))
	      {
	        itemstack1 = new ItemStack((Block)par2ArrayOfObj[(i + 1)], 1, -1);
	      }
	      else if ((par2ArrayOfObj[(i + 1)] instanceof ItemStack))
	      {
	        itemstack1 = (ItemStack)par2ArrayOfObj[(i + 1)];
	      }
	      hashmap.put(character, itemstack1);
	    }

	    ItemStack[] aitemstack = new ItemStack[j * k];
	    for (int i1 = 0; i1 < j * k; i1++)
	    {
	      char c = s.charAt(i1);
	      if (hashmap.containsKey(Character.valueOf(c)))
	      {
	        aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).copy();
	      }
	      else {
	        aitemstack[i1] = null;
	      }
	    }

	    this.recipes.add(new ShapedAnvilRecipes(j, k, aitemstack, itemstack));
	  }

	  void addShapelessRecipe(ItemStack itemstack, Object[] aobj)
	  {
	    ArrayList arraylist = new ArrayList();
	    Object[] aobj1 = aobj;
	    int i = aobj1.length;
	    for (int j = 0; j < i; j++)
	    {
	      Object obj = aobj1[j];
	      if ((obj instanceof ItemStack))
	      {
	        arraylist.add(((ItemStack)obj).copy());
	      }
	      else if ((obj instanceof Item))
	      {
	        arraylist.add(new ItemStack((Item)obj));
	      }
	      else if ((obj instanceof Item))
	      {
	        arraylist.add(new ItemStack((Item)obj));
	      }
	      else
          {
              if (!(obj instanceof Block))
              {
                  throw new RuntimeException("Invalid shapeless recipy!");
              }

              arraylist.add(new ItemStack((Block)obj));
          }
	    }

	    this.recipes.add(new ShapelessAnvilRecipes(itemstack, arraylist));
	  }

	  public ItemStack findMatchingRecipe(InventoryAnvil inventoryanvil)
	  {
	    

	    for (int k = 0; k < this.recipes.size(); k++)
	    {
	      IAnvilRecipe irecipe = (IAnvilRecipe)this.recipes.get(k);
	      if (irecipe.matches(inventoryanvil))
	      {
	        return irecipe.getCraftingResult(inventoryanvil);
	      }
	    }

	    return null;
	  }

	  public List getRecipeList()
	  {
	    return this.recipes;
	  }
}
