package soilmaker.common;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



public class ContainerSoilMaker extends Container{
	private TileEntitySoilMaker soilMaker;
	 private int lastcontinuanceTime = 0;
	    private int lastdurationTime = 0;
	    private int lastcurdurationTime = 0;
	   
	
	public ContainerSoilMaker(InventoryPlayer par1InventoryPlayer, TileEntitySoilMaker par2TileEntitySoilMaker){
		this.soilMaker = par2TileEntitySoilMaker;
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 0, 8, 53));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 1, 30, 17));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 2, 48, 17));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 3, 66, 17));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 4, 30, 35));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 5, 48, 35));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 6, 66, 35));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 7, 30, 53));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 8, 48, 53));
		this.addSlotToContainer(new Slot(par2TileEntitySoilMaker, 9, 67, 53));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntitySoilMaker, 10, 123, 35));
        
        int var1;
        
                
            
        
        for (var1 = 0; var1 < 3; ++var1)
        {
            for (int var2 = 0; var2 < 9; ++var2)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var2 + var1 * 9+9, 8 + var2 * 18, 84 + var1 * 18));
            }
        }
        
       

        for (var1 = 0; var1 < 9; ++var1)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var1, 8 + var1 * 18, 142));
        }
		
		 
        
	}
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.soilMaker.continuanceTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.soilMaker.durationTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.soilMaker.curItemTime);
        
    }
	@Override
	 public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();
	        Iterator<?> var1 = this.crafters.iterator();

	        while (var1.hasNext())
	        {
	            ICrafting var2 = (ICrafting)var1.next();

	            if (this.lastcontinuanceTime != this.soilMaker.continuanceTime)
	            {
	                var2.sendProgressBarUpdate(this, 0, this.soilMaker.continuanceTime);
	            }

	            if (this.lastdurationTime != this.soilMaker.durationTime)
	            {
	                var2.sendProgressBarUpdate(this, 1, this.soilMaker.durationTime);
	            }
	            if (this.lastcurdurationTime != this.soilMaker.curItemTime)
	            {
	                var2.sendProgressBarUpdate(this, 2, this.soilMaker.curItemTime);
	            }

	           
	        }

	        this.lastcontinuanceTime= this.soilMaker.continuanceTime;
	        this.lastdurationTime = this.soilMaker.durationTime;
	        this.lastcurdurationTime = this.soilMaker.curItemTime;
	        
	    }

	    @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int par1, int par2)
	    {
	        if (par1 == 0)
	        {
	            this.soilMaker.continuanceTime = par2;
	        }

	        if (par1 == 1)
	        {
	            this.soilMaker.durationTime = par2;
	        }
	        if (par1 == 2)
	        {
	            this.soilMaker.curItemTime = par2;
	        }


	        
	    }
	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	    {
		 ItemStack stack = null;
         Slot slotObject = (Slot) inventorySlots.get(par2);

         //null checks and checks if the item can be stacked (maxStackSize > 1)
         if (slotObject != null && slotObject.getHasStack()) {
                 ItemStack stackInSlot = slotObject.getStack();
                 stack = stackInSlot.copy();

                 //merges the item into player inventory since its in the tileEntity
                 if (par2 == 0) {
                         if (!mergeItemStack(stackInSlot, 1,
                                         inventorySlots.size(), true)) {
                                 return null;
                         }
                 //places it into the tileEntity is possible since its in the player inventory
                 } else if (!mergeItemStack(stackInSlot, 0, 1, false)) {
                         return null;
                 }

                 if (stackInSlot.stackSize == 0) {
                         slotObject.putStack(null);
                 } else {
                         slotObject.onSlotChanged();
                 }
         }

         return stack;
	    }
		@Override
		public boolean canInteractWith(EntityPlayer var1) {
			return true;
		}


}
