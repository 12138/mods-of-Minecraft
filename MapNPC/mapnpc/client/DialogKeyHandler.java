package mapnpc.client;

import java.util.EnumSet;

import mapnpc.client.gui.GuiNPCDialog;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.TickType;

public class DialogKeyHandler extends
		cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler {
	public static KeyBinding kKey = new KeyBinding("OK", Keyboard.KEY_K);
	public static KeyBinding jKey = new KeyBinding("Pre", Keyboard.KEY_J);
	public static KeyBinding lKey = new KeyBinding("Next", Keyboard.KEY_L);

	public boolean doneStuff = false;

	public DialogKeyHandler() {
		super(new KeyBinding[] { kKey, jKey, lKey }, new boolean[] { false,false, false });
	}

	@Override
	public String getLabel() {
		return "Dialog select";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		if (FMLClientHandler.instance().getClient().currentScreen != null) {
			if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiNPCDialog) {
				GuiNPCDialog gui = (GuiNPCDialog) FMLClientHandler.instance().getClient().currentScreen;

				if (kb.keyCode == Keyboard.KEY_K) {
					if (doneStuff == false) {
						gui.proceed();
						doneStuff = true;
					}

				}
				if (kb.keyCode == Keyboard.KEY_J) {
					if (doneStuff == false) {
						gui.preOption();
						doneStuff = true;
					}

				}
				if (kb.keyCode == Keyboard.KEY_L) {
					if (doneStuff == false) {
						gui.nestOption();
						doneStuff = true;
					}

				}
			}
		}

	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		doneStuff = false;

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
