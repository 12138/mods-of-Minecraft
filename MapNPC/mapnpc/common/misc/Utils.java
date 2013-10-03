package mapnpc.common.misc;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Utils {

	public static String[] cutString(String str) {
		return str.split(";", 10);
	}

	public static String[] cutString2(String str) {
		return str.split("\\*", 2);

	}
	
	
	public static boolean hasItem(EntityPlayer player,int itemId, int needCount) {
		ItemStack[] main = player.inventory.mainInventory;
		int count = 0;
		for (ItemStack stack : main) {
			if (stack!=null&&stack.getItem().itemID == itemId) {
				count += stack.stackSize;
			}
		}
		if (count < needCount)
			return false;
		return true;
	}
	
	public static  void drawTexturedModalRectWisely(int texWidth,int texHeight,int x, int y, int u, int v, int areaWidth, int areaHeight)
	{
	float perW = 1 / (float)texWidth;
	float perH = 1 / (float)texHeight;
	Tessellator tessellator = Tessellator.instance;
	tessellator.startDrawingQuads();
	tessellator.addVertexWithUV((double)(x + 0), (double)(y + areaHeight), (double)0, (double)((float)(u + 0) * perW), (double)((float)(v + areaHeight) * perH));
	tessellator.addVertexWithUV((double)(x + areaWidth), (double)(y + areaHeight), (double)0, (double)((float)(u + areaWidth) * perW), (double)((float)(v + areaHeight) * perH));
	tessellator.addVertexWithUV((double)(x + areaWidth), (double)(y + 0), (double)0, (double)((float)(u + areaWidth) * perW), (double)((float)(v + 0) * perH));
	tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)0, (double)((float)(u + 0) * perW), (double)((float)(v + 0) * perH));
	tessellator.draw();
	}

}
