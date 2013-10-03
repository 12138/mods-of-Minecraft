package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mapnpc.common.entity.npc.EntityMapNPCTask;
import mapnpc.common.misc.PacketID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketNPCData extends PacketBase {
	public static PacketNPCData instance=new PacketNPCData();

	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		//not use

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		try {
			int datatype=data.readInt();
			int entityId=data.readInt();
			String EntityData=data.readUTF();
		
		if(world.getEntityByID(entityId) instanceof EntityMapNPCTask){
			EntityMapNPCTask npc=(EntityMapNPCTask) world.getEntityByID(entityId) ;
			switch (datatype) {
			case 0:
				npc.setCurDialog(Integer.parseInt(EntityData));
				break;
			case 1:
				npc.setCurOption(Integer.parseInt(EntityData));
				break;
			case 2:
				npc.setCurTakGroup(EntityData);
				break;
			case 3:
				npc.setMarkType(Integer.parseInt(EntityData));
				break;
			case 4:
				npc.setAngry(Integer.parseInt(EntityData));
				break;
			case 5:
				npc.setCanTalk(Integer.parseInt(EntityData));
				break;
			default:
				break;
			}
		}

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	/**
	 * 0:CurDialog
	 * 1:CurOption
	 * 2:TakGroup
	 * 3:marktype
	 * 4:angry
	 * 5:cantalk
	 * */
public static void transData(int dataType,int id,String data){
	ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
	DataOutputStream dos = new DataOutputStream(bos);
	try {
		dos.writeInt(PacketID.NPCDATA);
		dos.writeInt(dataType);
		dos.writeInt(id);
		dos.writeUTF(data);
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
