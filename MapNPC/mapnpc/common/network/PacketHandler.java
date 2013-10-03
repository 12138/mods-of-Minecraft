package mapnpc.common.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import mapnpc.common.misc.PacketID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayInputStream bais = new ByteArrayInputStream(packet.data);
		DataInputStream dis=new DataInputStream(bais);
		int id = 0;
		try {
			id = dis.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object[] extradata = { (EntityPlayer) player };
		switch (FMLCommonHandler.instance().getEffectiveSide()) {
		case SERVER: {
			switch (id) {
				case PacketID.SPAWNNPC:
					PacketSpawnNPC.instance.readServer(id, dis,
							extradata);
					break;
				case PacketID.SPAWNRADIO:
					PacketSpawnRadio.instance.readServer(id, dis,
							extradata);
					break;
				case PacketID.SYNTASK:
					PacketSynTaskInfo.instance.readServer(id, dis,
							extradata);
					break;
				case PacketID.SYNDATA:
					PacketSynTaskData_add.instance.readServer(id, dis,
							extradata);
					break;

				case PacketID.SYNREMOVE:
					PacketSynTaskData_remove.instance.readServer(id, dis,
							extradata);
					break;
				case PacketID.NPCDATA:
					PacketNPCData.instance.readServer(id, dis,
							extradata);
					break;

				case PacketID.SPAWNITEM:
					PacketSpawnItem.instance.readServer(id, dis,
							extradata);
					break;
			}
			break;
		}
		case CLIENT: {
			switch (id) {
			case PacketID.SYNTASK:
				PacketSynTaskInfo.instance.readClient(id, dis,
						extradata);
				break;
			case PacketID.SYNDATA:
				PacketSynTaskData_add.instance.readClient(id, dis,
						extradata);
				break;
			case PacketID.TOCLIENT:
				PacketSynTaskInClient.instance.readClient(id, dis,
						extradata);
				break;

			}
			break;
		}
		default:
			break;

		}
	}

	

}
