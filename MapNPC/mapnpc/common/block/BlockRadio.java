package mapnpc.common.block;

import java.util.ArrayList;

import mapnpc.common.MapNPC;
import mapnpc.common.network.PacketSynTaskInfo;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRadio extends BlockContainer {

	public BlockRadio(int par1) {
		super(par1, Material.clay);
		this.setLightValue(0.1F);
		this.setLightOpacity(1);
		this.setHardness(0.7F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {

		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileEntityRadio();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		if (tile!=null&&tile instanceof TileEntityRadio) {
			TileEntityRadio radio = (TileEntityRadio) tile;
			TaskBase task = TaskSavedData.forWorld(par1World).getTaskinList().get(radio.taskName);
			if(task!=null){
				//completed place task
				completedPlaceTask(par5EntityPlayer,task.getName(),task.getGroup());
				return true;
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4,
				par5EntityPlayer, par6, par7, par8, par9);
	}
/**
 * only in server
 */
	private void completedPlaceTask(EntityPlayer par5EntityPlayer, String taskName,
			String groupName) {
		
		NBTTagCompound nbt=par5EntityPlayer.getEntityData();
		if(nbt.hasKey(par5EntityPlayer.username)){
			NBTTagCompound playerNBT=nbt.getCompoundTag(par5EntityPlayer.username);
			if(!playerNBT.hasKey("taskGroup"))
				return;
			NBTTagCompound group=playerNBT.getCompoundTag("taskGroup");
			if(!group.getString("groupName").equals(groupName)){
				return;
			}

			if(playerNBT.hasKey("completedPlaceTaskList")){
				NBTTagCompound completedPlaceTaskList=playerNBT.getCompoundTag("completedPlaceTaskList");
				completedPlaceTaskList.removeTag(taskName);;
				completedPlaceTaskList.setBoolean(taskName, true);
			}
			else{
				NBTTagCompound completedPlaceTaskList=new NBTTagCompound();
				completedPlaceTaskList.setBoolean(taskName, true);
				playerNBT.setCompoundTag("completedPlaceTaskList", completedPlaceTaskList);
			
			}
			ArrayList<String> list=new ArrayList<String>();
			list.add(par5EntityPlayer.username);
			list.add(taskName);
			list.add(groupName);
			PacketSynTaskInfo.synTaskInfo(list, par5EntityPlayer, false, 1);
		}
		else
			return;
		
		
		
		
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon=par1IconRegister.registerIcon(MapNPC.assetsName+":radio");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

}
