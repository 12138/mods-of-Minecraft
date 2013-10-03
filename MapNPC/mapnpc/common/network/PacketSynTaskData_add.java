package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mapnpc.common.misc.PacketID;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskPlace;
import mapnpc.common.task.tasktype.TaskSearch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketSynTaskData_add extends PacketBase {
	public static PacketSynTaskData_add instance=new PacketSynTaskData_add();
	

	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		try {
			int dataType=data.readInt();
			if(dataType==1){
				String taskType=data.readUTF();
				if(taskType.equals("Place"))
				TaskSavedData.forWorld(world).addTasktoList(data.readUTF(), 
						new TaskPlace(data.readUTF(), data.readUTF(), 
								Integer.parseInt(data.readUTF()),Integer.parseInt(data.readUTF()),Integer.parseInt(data.readUTF())),
								false, false);
				else if(taskType.equals("Search")){
					TaskSavedData.forWorld(world).addTasktoList(data.readUTF(), 
							new TaskSearch(data.readUTF(), data.readUTF(),data.readUTF()),
									false, false);
				}
				else if(taskType.equals("Kill")){
					TaskSavedData.forWorld(world).addTasktoList(data.readUTF(), 
							new TaskKill(data.readUTF(), data.readUTF(),data.readUTF()),
									false, false);
				}
			}
			else if(dataType ==2){
				TaskSavedData.forWorld(world).addTasktoKeySet(data.readUTF(), false, false);
			}
			else if(dataType==3){
				TaskSavedData.forWorld(world).addTasktoGroupKey(data.readUTF(), false, false);
			}
			else if(dataType==4){
				TaskSavedData.forWorld(world).addRewordtoGroup(data.readUTF(), data.readUTF(),  false, false);
			}
		} catch (IOException e) {
	
			e.printStackTrace();
		}

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		try {
			int dataType=data.readInt();
			if(dataType==1){
				String taskType=data.readUTF();
				String taskName=data.readUTF();
				if(taskType.equals("Place"))
				TaskSavedData.forWorld(world).addTasktoList(taskName, 
						new TaskPlace(taskName, data.readUTF(), 
								Integer.parseInt(data.readUTF()),Integer.parseInt(data.readUTF()),Integer.parseInt(data.readUTF())),
								false, false);
				else if(taskType.equals("Search")){
					TaskSavedData.forWorld(world).addTasktoList(taskName, 
							new TaskSearch(taskName, data.readUTF(),data.readUTF()),
									false, false);
				}
				else if(taskType.equals("Kill")){
					TaskSavedData.forWorld(world).addTasktoList(taskName, 
							new TaskKill(taskName, data.readUTF(),data.readUTF()),
									false, false);
				}
			}
			else if(dataType ==2){
				TaskSavedData.forWorld(world).addTasktoKeySet(data.readUTF(), false, false);
			}
			else if(dataType==3){
				TaskSavedData.forWorld(world).addTasktoGroupKey(data.readUTF(), false, false);
			}
			else if(dataType==4){
				TaskSavedData.forWorld(world).addRewordtoGroup(data.readUTF(), data.readUTF(),  false, false);
			}
		} catch (IOException e) {
	
			e.printStackTrace();
		}


	}
	public static void synData(boolean toServer,int datatype,String...data){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SYNDATA);
			dos.writeInt(datatype);
			if(datatype==1){
				dos.writeUTF(data[0]);
				dos.writeUTF(data[1]);
				dos.writeUTF(data[2]);
				if(data[0].equals("Place")){
					dos.writeUTF(data[3]);
					dos.writeUTF(data[4]);
					dos.writeUTF(data[5]);
				}
				else {
					dos.writeUTF(data[3]);
				}
			
				
				
			}
			else if(datatype==4){
				dos.writeUTF(data[0]);
				dos.writeUTF(data[1]);
			}
			else {
				dos.writeUTF(data[0]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "MapNPC";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = false;
		if (toServer)
			PacketDispatcher.sendPacketToServer(pkt);
		
		else
			PacketDispatcher.sendPacketToAllPlayers(pkt);
	}

}
