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

public class PacketSpawnNPC extends PacketBase {
	public static PacketSpawnNPC instance=new PacketSpawnNPC();

	@Override
	void readClient(int id, DataInputStream dis, Object[] extradata) {
		//not used

	}

	@Override
	void readServer(int id, DataInputStream dis, Object[] extradata) {
	
	try {
		int  x=dis.readInt();
		int  y=dis.readInt();
		int  z=dis.readInt();
		String npcName=dis.readUTF();
		int npcType=dis.readInt();
		String fileName=dis.readUTF();
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		EntityMapNPCTask npc=new EntityMapNPCTask(world);
		npc.setPosition(x, y+2, z);
		npc.setNPCName(npcName);
		npc.setType(npcType);
		npc.setFile(fileName);
		world.spawnEntityInWorld(npc);
	} catch (IOException e) {
		e.printStackTrace();
	}

	
	}
	public static void spawnNPC(int x,int y,int z,String name,int id,String fileName){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SPAWNNPC);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeUTF(name);
			dos.writeInt(id);
			dos.writeUTF(fileName);
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
