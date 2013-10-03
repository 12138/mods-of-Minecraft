package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mapnpc.common.MapNPC;
import mapnpc.common.block.ItemBlockRadio;
import mapnpc.common.misc.PacketID;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketSpawnRadio extends PacketBase {
	public static PacketSpawnRadio instance=new PacketSpawnRadio();


	public static void spawnRadio(String name,String group,double x,double y,double z){

		ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SPAWNRADIO);
			dos.writeUTF(name);
			dos.writeUTF(group);
			dos.writeDouble(x);
			dos.writeDouble(y);
			dos.writeDouble(z);
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

	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		//not use
		
	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		try {
			String name=data.readUTF();
			String group=data.readUTF();
			double x=data.readDouble();
			double y=data.readDouble();
			double z=data.readDouble();
			EntityPlayer player=(EntityPlayer) extradata[0];
			World world=player.worldObj;
			ItemStack stack=new ItemStack(MapNPC.radio.blockID,1,0);
			ItemBlockRadio.setTaskName(stack, name);
			ItemBlockRadio.setTaskGroup(stack, group);
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}

}
