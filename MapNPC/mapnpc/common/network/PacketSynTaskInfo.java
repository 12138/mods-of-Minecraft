package mapnpc.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import mapnpc.common.misc.PacketID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketSynTaskInfo extends PacketBase {
	public static PacketSynTaskInfo instance = new PacketSynTaskInfo();

	@Override
	void readClient(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player = (EntityPlayer) extradata[0];
		try {
			int type = data.readInt();
			if (type == 0) {
				writeTaskInfoToClient(player, data);
			} else {
				writeTaskInfoToClient2(player, data);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void writeTaskInfoToClient2(EntityPlayer player,
			DataInputStream data) {
		try {
			String userName = data.readUTF();
			String taskName = data.readUTF();
			String groupName;

			groupName = data.readUTF();

			NBTTagCompound nbt = player.getEntityData();
			if (nbt.hasKey(userName)) {
				NBTTagCompound playerNBT = nbt.getCompoundTag(userName);
				if (!playerNBT.hasKey("taskGroup"))
					return;
				NBTTagCompound group = playerNBT.getCompoundTag("taskGroup");
				if (!group.getString("groupName").equals(groupName)) {
					return;
				}

				if (playerNBT.hasKey("completedPlaceTaskList")) {
					NBTTagCompound completedPlaceTaskList = playerNBT
							.getCompoundTag("completedPlaceTaskList");
					completedPlaceTaskList.removeTag(taskName);
					;
					completedPlaceTaskList.setBoolean(taskName, true);
				} else {
					NBTTagCompound completedPlaceTaskList = new NBTTagCompound();
					completedPlaceTaskList.setBoolean(taskName, true);
					playerNBT.setCompoundTag("completedPlaceTaskList",
							completedPlaceTaskList);

				}
				player.addChatMessage("完成分支任务:"+taskName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeTaskInfoToClient(EntityPlayer player, DataInputStream data) {
		try {
			String playerName = data.readUTF();
			String entityName = data.readUTF();
			NBTTagCompound nbt = player.getEntityData();
			NBTTagCompound playerNBT = nbt.getCompoundTag(playerName);
			if (playerNBT.hasKey("beginCount")) {
				if (playerNBT.hasKey("entityCount")) {
					NBTTagCompound entityCount = playerNBT
							.getCompoundTag("entityCount");
					if (entityCount.hasKey(entityName)) {

						int count = entityCount.getInteger(entityName);
						count++;
						entityCount.removeTag(entityName);
						entityCount.setInteger(entityName, count);
					} else {
						entityCount.setInteger(entityName, 1);
					}
				} else {
					NBTTagCompound entityCount = new NBTTagCompound(
							"entityCount");
					entityCount.setInteger(entityName, 1);
					playerNBT.setCompoundTag("entityCount", entityCount);
				}

			} else {
				if (playerNBT.hasKey("entityCount")) {
					playerNBT.removeTag("entityCount");
				}
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	void readServer(int id, DataInputStream data, Object[] extradata) {
		EntityPlayer player;
		try {
			data.readInt();
			player = FMLCommonHandler.instance().getMinecraftServerInstance()
					.getConfigurationManager()
					.getPlayerForUsername(data.readUTF());
			
			writeTaskInfoToServer(player, data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeTaskInfoToServer(EntityPlayer player, DataInputStream data) {
		try {
			NBTTagCompound nbt = player.getEntityData();
			if (nbt.hasKey(player.username)) {
				nbt.removeTag(player.username);
			}
			NBTTagCompound playerNBT = new NBTTagCompound(player.username);
			if (playerNBT.hasKey("beginCount"))
				playerNBT.removeTag("beginCount");
			playerNBT.setBoolean("beginCount", true);

			if (playerNBT.hasKey("taskGroup")) {
				playerNBT.removeTag("taskGroup");

			}
			NBTTagCompound group = new NBTTagCompound();

			group.setString("groupName", data.readUTF());

			Short taskNum = Short.valueOf(data.readUTF());
			group.setShort("taskNum", taskNum);
			for (short i = 0; i < taskNum; i++) {
				NBTTagCompound task = new NBTTagCompound("task");
				task.setString("taskName", data.readUTF());
				task.setString("taskType", data.readUTF());
				task.setString("taskContext", data.readUTF());
				group.setCompoundTag("task_" + i, task);

			}

			playerNBT.setCompoundTag("taskGroup", group);
			nbt.setCompoundTag(playerNBT.getName(), playerNBT);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void synTaskInfo(ArrayList<String> list, EntityPlayer player,
			Boolean toServer, int type1) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(PacketID.SYNTASK);
			dos.writeInt(type1);
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
		if (toServer)
			PacketDispatcher.sendPacketToServer(pkt);
		else
			PacketDispatcher.sendPacketToPlayer(pkt, (Player) player);

	}
}
