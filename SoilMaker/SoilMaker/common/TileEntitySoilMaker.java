package SoilMaker.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import cpw.mods.fml.common.registry.GameRegistry;



public class TileEntitySoilMaker extends TileEntity implements IInventory,
		ISidedInventory {
	private ItemStack[] SoilMakerCon = new ItemStack[11];
    //已经经过的燃烧时间
	public int durationTime = 0;
	
	public int continuanceTime = 0;
	public int curItemTime = 0;
	private boolean Is =false;

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		return this.SoilMakerCon.length;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return this.SoilMakerCon.length;
	}

	@Override
	public int getSizeInventory() {
		return SoilMakerCon.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {

		return this.SoilMakerCon[var1];
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.SoilMakerCon = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.SoilMakerCon.length) {
				this.SoilMakerCon[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

		this.durationTime = par1NBTTagCompound.getShort("durationTime");
		this.continuanceTime = par1NBTTagCompound.getShort("continuanceTime");
		this.curItemTime = getItemBurnTime(SoilMakerCon[0]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("durationTime", (short) this.durationTime);
		par1NBTTagCompound.setShort("continuanceTime",
				(short) this.continuanceTime);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.SoilMakerCon.length; ++var3) {
			if (this.SoilMakerCon[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.SoilMakerCon[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Items", var2);
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if (this.SoilMakerCon[var1] != null) {
			ItemStack var3;

			if (this.SoilMakerCon[var1].stackSize <= var2) {
				var3 = this.SoilMakerCon[var1];
				this.SoilMakerCon[var1] = null;
				return var3;
			} else {
				var3 = this.SoilMakerCon[var1].splitStack(var2);

				if (this.SoilMakerCon[var1].stackSize == 0) {
					this.SoilMakerCon[var1] = null;
				}

				return var3;
			}
		} else {
			return null;
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		boolean var1 = this.durationTime > 0;
		boolean var2 = false;
		if(!this.worldObj.isRemote){
		if (this.durationTime > 0) {
			--this.durationTime;
		}
		if (!this.worldObj.isRemote) {
			if(this.SoilMakerCon[0]!=null){
			if ((this.durationTime == 0 && this.CanTrans()&&isItemFuel(SoilMakerCon[0]))||(this.durationTime == 0 &&Is&&isItemFuel(SoilMakerCon[0]))) {
				this.curItemTime=this.durationTime = getItemBurnTime(this.SoilMakerCon[0]);
				if (this.durationTime > 0) { 
					var2 = true;
					

					if (this.SoilMakerCon[0] != null) {
						--this.SoilMakerCon[0].stackSize;

						if (this.SoilMakerCon[0].stackSize == 0) {
							this.SoilMakerCon[0] = this.SoilMakerCon[0]
									.getItem().getContainerItemStack(
											SoilMakerCon[0]);
						}
					}

				}
			}
			
			}
			if(isFull()&&CanTrans()&&isWork()&&continuanceTime==0){
				cleari();
				Is=true;
			}
			
			if (this.isWork()) {
				
				
				if(Is)
				++this.continuanceTime;

				if (this.continuanceTime == 200&&Is) {
					this.continuanceTime = 0;
					getSoil();
					Is=false;
					var2 = true;
				    
					
				}
			} 
			else {
				this.continuanceTime = 0;
			}

			if (var1 != this.durationTime > 0) {
				var2 = true;
				BlockSoilMaker.updateSoilMakerBlockState(this.durationTime > 0,
						this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		
		}
			
		

		if (var2) {
			this.onInventoryChanged();
		}
		

	}
	}
	//判断是否可以启动
	private boolean CanTrans() {
		if(this.isCanTrans(this.SoilMakerCon[1])&&
				this.isCanTrans(this.SoilMakerCon[2])&&
				this.isCanTrans(this.SoilMakerCon[3])&&
				this.isCanTrans(this.SoilMakerCon[4])&&
				this.isCanTrans(this.SoilMakerCon[5])&&
				this.isCanTrans(this.SoilMakerCon[6])&&
				this.isCanTrans(this.SoilMakerCon[7])&&
				this.isCanTrans(this.SoilMakerCon[8])&&
				this.isCanTrans(this.SoilMakerCon[9]))
			return true;
		else return false;
	}
//消耗转化物
	private void cleari() {
		for(int i =1;i<10;i++){
			if(this.SoilMakerCon[i]!=null){
			if(this.SoilMakerCon[i].stackSize==1)
				this.SoilMakerCon[i]=null;
			
			if(this.SoilMakerCon[i]!=null&&this.SoilMakerCon[i].stackSize>0)
				--this.SoilMakerCon[i].stackSize;
			}
		}
		
	
		
	}
		
	
//生成泥土
	private void getSoil() {
		if(this.SoilMakerCon[10]==null)
		this.SoilMakerCon[10]=new ItemStack(Block.dirt,1);
		else if(this.SoilMakerCon[10]!=null){
				this.SoilMakerCon[10].stackSize++;
				
		}
		
		
	
		}
		
	
//判断是否为可转化物
	private boolean isCanTrans(ItemStack par1ItemStack) {
		boolean boo =false;
		if(isFull()){
			int var1 = par1ItemStack.getItem().itemID;
            Item var2 = par1ItemStack.getItem();

            if (par1ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    boo = true;
                }
                if (var3 == Block.leaves)
                {
                    boo = true;
                }

                if (var3.blockMaterial == Material.wood)
                {
                	boo = true;
                }
                if(var3==Block.web)
                	boo = true;
               
                if(var3==Block.tallGrass)
                	boo = true;
                if(var3==Block.deadBush)
                	boo = true;
                if(var3==Block.cloth)
                	boo = true;
                if(var3==Block.ladder)
                	boo = true;
                if(var3==Block.cactus)
                	boo = true;
                if(var3==Block.cake)
                	boo = true;
                if(var3==Block.melon)
                	boo = true;
                if(var3==Block.waterlily)
                	boo = true;
                if(var3==Block.netherStalk)
                	boo = true;
                if(var3==Block.cocoaPlant)
                	boo = true;
                if(var3==Block.woodenButton)
                	boo = true;
               
                if(var3==Block.pumpkin)
                	boo = true;
                if(var3==Block.sapling)
                	boo = true;
                if(var3==Block.planks)
                	boo = true;
                if(var3==Block.plantYellow)
                	boo = true;
                if(var3==Block.plantRed)
                	boo = true;
                if(var3==Block.mushroomBrown)
                	boo = true;
                if(var3==Block.mushroomRed)
                	boo = true;
                if(var3==Block.reed)
                	boo = true;
                if(var3==Block.vine)
                	boo = true;
            }
            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD")) boo = true;
            if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD")) boo = true;
            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD")) boo = true;
            if (var2 instanceof ItemArmor && ((ItemArmor) var2).getArmorMaterial().equals("CLOTH")) boo = true;
            if (var2 instanceof ItemSeeds) boo = true;
            if (var2 instanceof ItemSeedFood) boo = true;
            if (var2 instanceof ItemFood) boo = true;
            if (var1 == Item.stick.itemID) boo = true;
            if (var1 == Item.sugar.itemID) boo = true;
            if (var1 == Item.wheat.itemID) boo = true;
            if (var1 == Item.rottenFlesh.itemID) boo = true;    
            if (var1 == Item.arrow.itemID) boo = true;    
            if (var1 == Item.bowlEmpty.itemID) boo = true;  
            if (var1 == Item.bowlSoup.itemID) boo = true;  
            if (var1 == Item.silk.itemID) boo = true;  
            if (var1 == Item.feather.itemID) boo = true;  
            if (var1 == Item.gunpowder.itemID) boo = true; 
            if (var1 == Item.painting.itemID) boo = true; 
            if (var1 == Item.sign.itemID) boo = true; 
            if (var1 == Item.doorWood.itemID) boo = true; 
            if (var1 == Item.leather.itemID) boo = true; 
            
            if (var1 == Item.brick.itemID) boo = true; 
            if (var1 == Item.clay.itemID) boo = true; 
            if (var1 == Item.reed.itemID) boo = true; 
            if (var1 == Item.paper.itemID) boo = true; 
            if (var1 == Item.book.itemID) boo = true; 
            if (var1 == Item.slimeBall.itemID) boo = true; 
            if (var1 == Item.egg.itemID) boo = true; 
            if (var1 == Item.fishingRod.itemID) boo = true; 
            if (var1 == Item.dyePowder.itemID) boo = true; 
            if (var1 == Item.bone.itemID) boo = true; 
            if (var1 == Item.cake.itemID) boo = true; 
            if (var1 == Item.enderPearl.itemID) boo = true; 
            
            if (var1 == Item.blazeRod.itemID) boo = true; 
            if (var1 == Item.ghastTear.itemID) boo = true; 
            if (var1 == Item.fermentedSpiderEye.itemID) boo = true; 
            
            if (var1 == Item.blazePowder.itemID) boo = true; 
            if (var1 == Item.writableBook.itemID) boo = true; 
            
            if (var1 == Item.writtenBook.itemID) boo = true; 
            
            if (var1 == Item.itemFrame.itemID) boo = true; 
            if (var1 == Item.carrotOnAStick.itemID) boo = true;
            
            
            
                    
            if(!boo)
            	return false;
           
			}
			
		
	
		
		return boo;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.SoilMakerCon[var1] != null) {
			ItemStack var2 = this.SoilMakerCon[var1];
			this.SoilMakerCon[var1] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		this.SoilMakerCon[par1] = par2ItemStack;

		if (par2ItemStack != null&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();

	}



	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {

		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	public int getRemainingScaled() {
		if(this.curItemTime==0)
			this.curItemTime=200;
		return this.durationTime*12 / curItemTime;

	}
	public int getCookProgressScaled() {
		return this.continuanceTime*24/200;
	}

	@Override
	public String getInvName() {
		return "Soil";
	}
	public boolean isWork(){
		return this.durationTime>0;
	}
	private boolean isFull(){
		for(int i=1;i<10;i++)
		if(this.SoilMakerCon[i]==null)
			return false;
		return true;
		
	}
	
	public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int var1 = par0ItemStack.getItem().itemID;
            Item var2 = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (var3.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }
            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD")) return 200;
            if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD")) return 200;
            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD")) return 200;
            if (var1 == Item.stick.itemID) return 100;
            if (var1 == Item.coal.itemID) return 1600;
            if (var1 == Item.bucketLava.itemID) return 20000;
            if (var1 == Block.sapling.blockID) return 100;
            if (var1 == Item.blazeRod.itemID) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }


	 public static boolean isItemFuel(ItemStack par0ItemStack)
	    {
	        return getItemBurnTime(par0ItemStack) > 0;
	    }

	

	

	


}
