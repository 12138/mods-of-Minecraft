package Jimmynator.common.network;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import Jimmynator.common.block.tileentity.TileEntityPan;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;



public class PacketPan extends PacketBase {

	public static PacketPan instance=new PacketPan();
	@Override
	void readClient(int id, ByteArrayDataInput data, Object[] extradata) {
		

	}

	@Override
	void readServer(int id, ByteArrayDataInput data, Object[] extradata) {

		int itemid=data.readInt();
		int x=data.readInt();
		int y=data.readInt();
		int z=data.readInt();
		EntityPlayer player= (EntityPlayer) extradata[0];
		
		TileEntity tile=player.worldObj.getBlockTileEntity(x, y, z);
		if(tile instanceof TileEntityPan){
			TileEntityPan pan=(TileEntityPan) tile;
			if(itemid!=-1)
			pan.inPan=new ItemStack(itemid,1,0);
			else{
				pan.inPan=null;
			}
		}


	}
	

	public static void panChange(int itemId,int x,int y,int z){

		ByteArrayOutputStream bos = new ByteArrayOutputStream(110);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(5);
			dos.writeInt(itemId);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
	
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "Jimmynator";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = false;
		PacketDispatcher.sendPacketToAllPlayers(pkt);
		
	}

}
