package net.hendyzone.lightstick.common.blocks;

import net.hendyzone.lightstick.common.Stick;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityBonefire extends TileEntity implements ISidedInventory{
	 private ItemStack[] BonefireItemStacks = new ItemStack[5];
	 public int burningremain;
	 public int itemburntime;
	 public int cookedtime;
	 public int burnedneed=500;
	 public int burntick;
	 


	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		

        super.readFromNBT(par1nbtTagCompound);
        NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Items");
        this.BonefireItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.BonefireItemStacks.length)
            {
                this.BonefireItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.burningremain = par1nbtTagCompound.getShort("burningremain");
        this.cookedtime = par1nbtTagCompound.getShort("cookedtime");
        
        this.itemburntime = TileEntityFurnace.getItemBurnTime(this.BonefireItemStacks[1])+500;

      
	}


	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		 super.writeToNBT(par1nbtTagCompound);
	        par1nbtTagCompound.setShort("burningremain", (short)this.burningremain);
	        par1nbtTagCompound.setShort("cookedtime", (short)this.cookedtime);
	        NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.BonefireItemStacks.length; ++i)
	        {
	            if (this.BonefireItemStacks[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.BonefireItemStacks[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        par1nbtTagCompound.setTag("Items", nbttaglist);

	}

	@Override
	public void updateEntity() {
		Roundwater();
		   boolean flag = false;
		if(this.burntick>0)
		this.burntick--;
		
		  if (!this.worldObj.isRemote){
			  
		if(burningremain==0){
		if(BonefireItemStacks[0]==null){
			if(BonefireItemStacks[1]!=null&&TileEntityFurnace.getItemBurnTime(BonefireItemStacks[1])>0)
			{	BonefireItemStacks[0]=BonefireItemStacks[1].copy();
			BonefireItemStacks[1]=null;
			}
			else if(BonefireItemStacks[2]!=null&&TileEntityFurnace.getItemBurnTime(BonefireItemStacks[2])>0){
				BonefireItemStacks[0]=BonefireItemStacks[2].copy();
				BonefireItemStacks[2]=null;
			}	
		}
		
		 if(BonefireItemStacks[0]!=null&&TileEntityFurnace.getItemBurnTime(BonefireItemStacks[0])>0){
			this.itemburntime=TileEntityFurnace.getItemBurnTime(BonefireItemStacks[0])+500;
			BonefireItemStacks[0].stackSize--;
			if(BonefireItemStacks[0].stackSize==0){
				BonefireItemStacks[0]=null;
			}
			this.burningremain=this.itemburntime;
		}
		}
		if(this.burntick==0&&this.burningremain==0){
			int l=this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			if(!(l%2==0))
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l-1, 2);
			
		}
		//cook food
		if(this.isBurning()&&canCook()){
		this.cookedtime++;
		if(this.cookedtime==this.burnedneed+this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord)*50){
			this.cookedtime=0;
			cookFood();
			flag=true;
			}
		}

		}
		if (this.burningremain>0){
			this.burningremain--;
		}
		 if (flag)
	        {
	            this.onInventoryChanged();
	        }
		
		
		}



	private void cookFood() {
		if (this.canCook())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.BonefireItemStacks[3]);

            if (this.BonefireItemStacks[4] == null)
            {
                this.BonefireItemStacks[4] = itemstack.copy();
            }
            else if (this.BonefireItemStacks[4].isItemEqual(itemstack))
            {
                BonefireItemStacks[4].stackSize += itemstack.stackSize;
            }

            --this.BonefireItemStacks[3].stackSize;

            if (this.BonefireItemStacks[3].stackSize <= 0)
            {
                this.BonefireItemStacks[3] = null;
            }
        }
	}


	private boolean canCook() {
		if(this.BonefireItemStacks[3]==null){
			this.cookedtime=0;
		return false;}
		else{
		
	        
	            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.BonefireItemStacks[3]);
	            if (itemstack == null) return false;
	            if(itemstack.itemID!=Item.porkCooked.itemID&&itemstack.itemID!=Item.beefCooked.itemID&&
	            		itemstack.itemID!=Item.chickenCooked.itemID&&itemstack.itemID!=Item.fishCooked.itemID&&
	            		itemstack.itemID!=Item.bakedPotato.itemID)
	            	return false;
	            if (this.BonefireItemStacks[4] == null) return true;
	            if (!this.BonefireItemStacks[4].isItemEqual(itemstack)) return false;
	            int result = BonefireItemStacks[4].stackSize + itemstack.stackSize;
	            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
	        
		}
	}


	private void Roundwater() {

		if(this.worldObj.getBlockMaterial(xCoord+1, yCoord, zCoord)==Material.water||this.worldObj.getBlockMaterial(xCoord+1, yCoord, zCoord)==Material.lava||
		   this.worldObj.getBlockMaterial(xCoord-1, yCoord, zCoord)==Material.water||this.worldObj.getBlockMaterial(xCoord-1, yCoord, zCoord)==Material.lava||
		   this.worldObj.getBlockMaterial(xCoord, yCoord+1, zCoord)==Material.water||this.worldObj.getBlockMaterial(xCoord, yCoord+1, zCoord)==Material.lava||
		   this.worldObj.getBlockMaterial(xCoord, yCoord, zCoord+1)==Material.water||this.worldObj.getBlockMaterial(xCoord, yCoord, zCoord+1)==Material.lava||
		   this.worldObj.getBlockMaterial(xCoord, yCoord, zCoord-1)==Material.water||this.worldObj.getBlockMaterial(xCoord, yCoord, zCoord-1)==Material.lava){
			Stick.bonefire.breakBlock(worldObj, xCoord, yCoord, zCoord, Stick.bonefire.blockID, worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
			if(!this.worldObj.isRemote){
			int m=4-this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord)/2;
			if(m<0)
				m=0;
        	ItemStack stack=new ItemStack(Stick.charredStick,m);
            EntityItem item=new EntityItem(this.worldObj, xCoord, yCoord, zCoord, stack);
            this.worldObj.spawnEntityInWorld(item);
			}
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			
	}
	}



	@Override
	public int getBlockMetadata() {
		return super.getBlockMetadata();
	}

	@Override
	public int getSizeInventory() {
		return this.BonefireItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.BonefireItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

        if (this.BonefireItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.BonefireItemStacks[i].stackSize <= j)
            {
                itemstack = this.BonefireItemStacks[i];
                this.BonefireItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.BonefireItemStacks[i].splitStack(j);

                if (this.BonefireItemStacks[i].stackSize == 0)
                {
                    this.BonefireItemStacks[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

        if (this.BonefireItemStacks[i] != null)
        {
            ItemStack itemstack = this.BonefireItemStacks[i];
            this.BonefireItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

        this.BonefireItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() {
		return "篝火";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		 return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public boolean isBurning(){
		return this.burningremain>0?true:false;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}





	public int getBurnTimeRemainingScaled() {
		
		return (int) (14*((float)this.burningremain/(float)this.itemburntime));
	}


	public int getCookProgressScaled() {
		return (int) (24*((float)this.cookedtime/(float)(this.burnedneed+this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord)*50)));
	}


	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i==4?false:true;
	}


	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		
		return null;
	}


	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// PALN Auto-generated method stub
		return false;
	}


	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		 return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}
	

}
