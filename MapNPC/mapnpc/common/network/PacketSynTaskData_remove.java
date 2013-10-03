package mapnpc.common.network;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mapnpc.common.misc.PacketID;
import mapnpc.common.task.TaskSavedData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;



public class PacketSynTaskData_remove extends PacketBase {
	public static PacketSynTaskData_remove instance=new PacketSynTaskData_remove();
	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		//not use

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		String taskName;
		try {
			taskName = data.readUTF();
			TaskSavedData.forWorld(world).getTaskinList().remove(taskName);
		TaskSavedData.forWorld(world).getTaskinKeySet().remove(taskName);
		TaskSavedData.forWorld(world).markDirty();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	public static void removeTask(String taskName){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SYNREMOVE);
			dos.writeUTF(taskName);
		
			dos.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "MapNPC";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = false;
		
		PacketDispatcher.sendPacketToServer(pkt);
	}

}
