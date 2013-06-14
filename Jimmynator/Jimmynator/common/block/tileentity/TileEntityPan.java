package Jimmynator.common.block.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPan extends TileEntity {
	public ItemStack inPan;
	private int cooked;
	@Override
	public void updateEntity() {
//		System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
		if(this.worldObj.getBlockId(xCoord, yCoord-1, zCoord)==Block.fire.blockID&&inPan!=null){
			this.cooked++;
			
		}
		else{
			this.cooked=0;
		}
		if(cooked==200){
			cookItem();
		}
		super.updateEntity();
	//	System.out.println(this.cooked);
		
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		System.out.println(FMLCommonHandler.instance().getEffectiveSide().name());
		super.readFromNBT(par1nbtTagCompound);
 this.cooked=par1nbtTagCompound.getShort("cooked");

       NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Items");
       
       for (int i = 0; i < nbttaglist.tagCount(); ++i)
       {
           NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
           byte b0 = nbttagcompound1.getByte("Slot");

           if (b0 >= 0 && b0 < 1)
           {
               this.inPan = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           }
       }
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setShort("cooked", (short)this.cooked);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < 1; ++i)
        {
            if (this.inPan != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inPan.writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }


	}

	private void cookItem() {
		ItemStack stack=FurnaceRecipes.smelting().getSmeltingResult(inPan);
		this.inPan=stack.copy();
		this.cooked=0;
		
	}
}
