package mapnpc.common.network;

import java.io.DataInputStream;

public abstract class PacketBase {

	abstract void  readClient(int id, DataInputStream data, Object[] extradata);


	abstract void readServer(int id, DataInputStream data, Object[] extradata);
	

	
}
