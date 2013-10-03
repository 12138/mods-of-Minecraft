package mapnpc.client;

import mapnpc.client.gui.GuiNPCDialog;
import mapnpc.client.render.RenderNPC;
import mapnpc.client.render.RenderRadio;
import mapnpc.common.CommonProxy;
import mapnpc.common.block.TileEntityRadio;
import mapnpc.common.entity.npc.EntityMapNPCTask;
import mapnpc.common.misc.GuiID;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void onLoad() {
		
		KeyBindingRegistry.registerKeyBinding(new DialogKeyHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityMapNPCTask.class,new RenderNPC());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class,new RenderRadio());
	}

	@Override
	public GuiIngame getcurrentGui() {
		return FMLClientHandler.instance().getClient().ingameGUI;
	}
	@Override
	public GuiScreen getNewGui(EntityPlayer player, EntityMapNPCTask lastNpc,int guiId) {
		switch (guiId) {
		case GuiID.GUINPCDIALOG:
			return new GuiNPCDialog(player,lastNpc);

		default:
			return null;
		}
		
	}

//	@Override
//	public void loadTextures()  {
//		System.out.println("begin load texture files");
//		
//	File dir=new File(MapNPC.dir+Resources.DIRTEXTURE);
//	if(!dir.exists()){
//		dir.mkdirs();
//		return;
//	}
//	String url=Minecraft.getMinecraftDir().getPath();
//	
//	try {
//		System.out.println(dir.getPath());
//		System.out.println(url+Resources.DIRTEXTURE);
//		this.copyDirectiory(dir.getPath(), url+Resources.DIRTEXTURE);
//	} catch (IOException e) {
//		
//		e.printStackTrace();
//		System.out.println("!!!files");
//	}
//	}
//	
//	 public void copyDirectiory(String sourceDir, String targetDir) throws IOException {
//	        // 新建目标目录
//	        (new File(targetDir)).mkdirs();
//	        // 获取源文件夹当前下的文件或目录
//	        File[] file = (new File(sourceDir)).listFiles();
//	        for (int i = 0; i < file.length; i++) {
//	            if (file[i].isFile()) {
//	                // 源文件
//	                File sourceFile = file[i];
//	                // 目标文件
//	                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
//	                copyFile(sourceFile, targetFile);
//	            }
//	            if (file[i].isDirectory()) {
//	                // 准备复制的源文件夹
//	                String dir1 = sourceDir + "/" + file[i].getName();
//	                // 准备复制的目标文件夹
//	                String dir2 = targetDir + "/" + file[i].getName();
//	                copyDirectiory(dir1, dir2);
//	            }
//	        }
//	    }
//	 
//	 public  void copyFile(File sourceFile, File targetFile) throws IOException {
//	        BufferedInputStream inBuff = null;
//	        BufferedOutputStream outBuff = null;
//	        try {
//	            // 新建文件输入流并对它进行缓冲
//	            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
//
//	            // 新建文件输出流并对它进行缓冲
//	            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
//
//	            // 缓冲数组
//	            byte[] b = new byte[1024 * 5];
//	            int len;
//	            while ((len = inBuff.read(b)) != -1) {
//	                outBuff.write(b, 0, len);
//	            }
//	            // 刷新此缓冲的输出流
//	            outBuff.flush();
//	        } finally {
//	            // 关闭流
//	            if (inBuff != null)
//	                inBuff.close();
//	            if (outBuff != null)
//	                outBuff.close();
//	        }
//	    }

}