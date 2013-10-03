package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import mapnpc.common.misc.PacketID;
import mapnpc.common.task.TaskSavedData;
import mapnpc.common.task.tasktype.TaskKill;
import mapnpc.common.task.tasktype.TaskPlace;
import mapnpc.common.task.tasktype.TaskSearch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketSynTaskInClient extends PacketBase {
	public static PacketSynTaskInClient instance = new PacketSynTaskInClient();

	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player = (EntityPlayer) extradata[0];
		World world = player.worldObj;
		readTaskList(data, world);
		readRewordList(data, world);
		readTaskkey(data, world);
		readTaskGroupkey(data, world);

	}

	private void readRewordList(DataInputStream data, World world) {
		int size;
		try {
			size = Integer.parseInt(data.readUTF());

			for (int i = 0; i < size; i++) {
				String groupName = data.readUTF();
				String reword = data.readUTF();

				TaskSavedData.forWorld(world).addRewordtoGroup(groupName,reword, false, false);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void readTaskGroupkey(DataInputStream data, World world) {
		int size;
		try {
			size = Integer.parseInt(data.readUTF());

			for (int i = 0; i < size; i++) {
				TaskSavedData.forWorld(world).addTasktoGroupKey(data.readUTF(),
						false, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readTaskkey(DataInputStream data, World world) {
		int size;
		try {
			size = Integer.parseInt(data.readUTF());

			for (int i = 0; i < size; i++) {
				TaskSavedData.forWorld(world).addTasktoKeySet(data.readUTF(),
						false, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readTaskList(DataInputStream data, World world) {
		int size;
		try {
			size = Integer.parseInt(data.readUTF());

			for (int i = 0; i < size; i++) {
				String name = data.readUTF();
				String group = data.readUTF();
				String type = data.readUTF();
				if (type.equals("Place")) {
					TaskSavedData.forWorld(world).addTasktoList(
							name,
							new TaskPlace(name, group, Integer.parseInt(data
									.readUTF()), Integer.parseInt(data
									.readUTF()), Integer.parseInt(data
									.readUTF())), false, false);
				} else if (type.equals("Search")) {
					TaskSavedData.forWorld(world).addTasktoList(name,
							new TaskSearch(name, group, data.readUTF()), false,
							false);
				} else if (type.equals("Kill")) {
					TaskSavedData.forWorld(world).addTasktoList(name,
							new TaskKill(name, group, data.readUTF()), false,
							false);
				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		// not use

	}

	public static void toClient(ArrayList<String> list) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.TOCLIENT);
			for (int i = 0; i < list.size(); i++)
				dos.writeUTF(list.get(i));
			dos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "MapNPC";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = false;
		PacketDispatcher.sendPacketToAllPlayers(pkt);
	}

}
