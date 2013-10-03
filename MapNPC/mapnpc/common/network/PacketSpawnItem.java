package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mapnpc.common.misc.PacketID;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketSpawnItem extends PacketBase {
	public static PacketSpawnItem instance=new PacketSpawnItem();
	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		try {
			
		int itemId=data.readInt();
		int meate=data.readInt();
		int count=data.readInt();
		double x=data.readDouble();
		double y;
		
			y = data.readDouble();
		
		double z=data.readDouble();
		EntityPlayer player=(EntityPlayer) extradata[0];
		World world=player.worldObj;
		world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(itemId,count,meate)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public static void spawnItem(int itemId,int meate,int count,double x,double y,double z){

		ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SPAWNITEM);
			dos.writeInt(itemId);
			dos.writeInt(meate);
			dos.writeInt(count);
			dos.writeDouble(x);
			dos.writeDouble(y);
			dos.writeDouble(z);
			
			
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
