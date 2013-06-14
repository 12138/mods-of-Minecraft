package Jimmynator.common.anvil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryAnvResult implements IInventory {

    private ItemStack[] stackResult = new ItemStack[1];

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.stackResult[0];
    }

    @Override
    public String getInvName()
    {
        return "Result";
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.stackResult[0] != null)
        {
            ItemStack itemstack = this.stackResult[0];
            this.stackResult[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.stackResult[0] != null)
        {
            ItemStack itemstack = this.stackResult[0];
            this.stackResult[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.stackResult[0] = par2ItemStack;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged() {}

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }
    @Override
    public void openChest() {}
    @Override
    public void closeChest() {}

@Override
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }
}
