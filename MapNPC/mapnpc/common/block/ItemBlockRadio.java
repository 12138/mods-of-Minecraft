package mapnpc.common.block;

import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskPlace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemBlockRadio extends ItemBlock {

	public ItemBlockRadio(int par1) {
		super(par1);
		
	}
	



	@Override
	 public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		TileEntity tile=world.getBlockTileEntity(x, y, z);
		if(tile instanceof TileEntityRadio){
			TileEntityRadio radio=(TileEntityRadio) tile;
			radio.taskName=ItemBlockRadio.getTaskName(stack);

			radio.groupName=ItemBlockRadio.getTaskGroup(stack);
			TaskSavedData.forWorld(world).addTasktoList(radio.taskName, new TaskPlace(radio.taskName,radio.groupName,x,y,z), false, false);
			
//			if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
//			TaskSavedData.forWorld(world).startTaskGroup(player, radio.groupName);
			if(player.capabilities.isCreativeMode){
				player.inventory.consumeInventoryItem(stack.itemID);
			}
		}
		
		return true;
				
	}

	public static String getTaskName(ItemStack par1ItemStack) {
		NBTTagCompound var1= par1ItemStack.getTagCompound();
		if(var1==null){
			return null;
		}
		else{
			NBTTagCompound var2=var1.getCompoundTag("task");
			return var2==null?null:(var2.hasKey("task")?var2.getString("task"):null);
		}
		
	}
	 public static void setTaskName(ItemStack par1ItemStack, String par2){
		 NBTTagCompound var1 = par1ItemStack.getTagCompound();

	        if (var1 == null)
	        {
	            var1 = new NBTTagCompound();
	            par1ItemStack.setTagCompound(var1);
	        }

	        NBTTagCompound var2 = var1.getCompoundTag("task");

	        if (!var1.hasKey("task"))
	        {
	            var1.setCompoundTag("task", var2);
	        }

	        if (par2 != ""&&par2!=null)
	        {
	            var2.setString("task", par2);
	        }
	        else
	        {
	            var2.removeTag("task");
	        }
	 }
	 
	 public static String getTaskGroup(ItemStack par1ItemStack) {
			NBTTagCompound var1= par1ItemStack.getTagCompound();
			if(var1==null){
				return null;
			}
			else{
				NBTTagCompound var2=var1.getCompoundTag("group");
				return var2==null?null:(var2.hasKey("group")?var2.getString("group"):null);
			}
			
		}
		 public static void setTaskGroup(ItemStack par1ItemStack, String par2){
			 NBTTagCompound var1 = par1ItemStack.getTagCompound();

		        if (var1 == null)
		        {
		            var1 = new NBTTagCompound();
		            par1ItemStack.setTagCompound(var1);
		        }

		        NBTTagCompound var2 = var1.getCompoundTag("group");

		        if (!var1.hasKey("group"))
		        {
		            var1.setCompoundTag("group", var2);
		        }

		        if (par2 != ""&&par2!=null)
		        {
		            var2.setString("group", par2);
		        }
		        else
		        {
		            var2.removeTag("group");
		        }
		 }


}
