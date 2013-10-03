package atomicstryker.dynamiclights.client.modules;

import java.util.EnumSet;

import atomicstryker.dynamiclights.client.DynamicLights;
import atomicstryker.dynamiclights.client.IDynamicLightSource;

import net.hendyzone.lightstick.common.Stick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod(modid = "LightsTouch", name = "LightsTouch", version = "1.0.0", dependencies = "required-after:DynamicLights")
public class LightStickSource implements IDynamicLightSource {
	private EntityPlayer thePlayer;
	private World lastWorld;
	private int lightLevel;
	private boolean enabled;


	@Mod.EventHandler
	public void load(FMLInitializationEvent evt) {

		enabled = false;
		lastWorld = null;

		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);

	}

	private class TickHandler implements ITickHandler {
		private final EnumSet<TickType> ticks;

		public TickHandler() {
			ticks = EnumSet.of(TickType.CLIENT);
		}

		@Override
		public void tickStart(EnumSet<TickType> type, Object... tickData) {
		}

		@Override
		public void tickEnd(EnumSet<TickType> type, Object... tickData) {
			if(lastWorld != FMLClientHandler.instance().getClient().theWorld
					|| thePlayer != FMLClientHandler.instance().getClient().thePlayer) {
				thePlayer = FMLClientHandler.instance().getClient().thePlayer;
				if(thePlayer != null) {
					lastWorld = thePlayer.worldObj;
				}
				else {
					lastWorld = null;
				}
			}

			if(thePlayer != null && thePlayer.isEntityAlive()
					&& !DynamicLights.globalLightsOff()) {
				int prevLight = lightLevel;
				if(thePlayer.getCurrentEquippedItem() != null&&(
						thePlayer.getCurrentEquippedItem().itemID == Stick.handheldTorch.itemID||
						thePlayer.getCurrentEquippedItem().itemID == 50||
						thePlayer.getCurrentEquippedItem().itemID == Stick.smallHandheldTorch.itemID)) {
					if(thePlayer.getCurrentEquippedItem().itemID == Stick.handheldTorch.itemID)
					lightLevel = (int) (15 - (15 *(thePlayer.getCurrentEquippedItem().getItemDamage() / 60.0)));
					if(thePlayer.getCurrentEquippedItem().itemID == 50)
						lightLevel =15;
					if(thePlayer.getCurrentEquippedItem().itemID ==  Stick.smallHandheldTorch.itemID){
						lightLevel = (int) (15 - (15 *(thePlayer.getCurrentEquippedItem().getItemDamage() / 30.0)));
					
						lightLevel=lightLevel-3;	
					}
				}
				else
					lightLevel = 0;
				
				if(prevLight != 0 && lightLevel != prevLight) {
					lightLevel = 0;
				}
				else {
					if(thePlayer.isInWater()) {
						lightLevel = 0;
					}

				}

				if(!enabled && lightLevel > 8) {
					enableLight();
				}
				else if(enabled && lightLevel < 9) {
					disableLight();
				}
			}
		}

		@Override
		public EnumSet<TickType> ticks() {
			return ticks;
		}

		@Override
		public String getLabel() {
			return "firestick";
		}
	}

	private void enableLight() {
		DynamicLights.addLightSource(this);
		enabled = true;
	}

	private void disableLight() {
		DynamicLights.removeLightSource(this);
		enabled = false;
	}

	@Override
	public Entity getAttachmentEntity() {
		return thePlayer;
	}

	@Override
	public int getLightLevel() {
		return lightLevel;
	}

}
