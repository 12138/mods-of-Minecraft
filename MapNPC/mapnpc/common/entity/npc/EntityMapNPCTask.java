package mapnpc.common.entity.npc;

import mapnpc.client.gui.GuiNPCDialog;
import mapnpc.common.entity.npc.ai.EntityNPCAIAttackPlayerInAngry;
import mapnpc.common.misc.DialogManager;
import mapnpc.common.misc.NPC;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityMapNPCTask extends EntityMapNPCBase {
	/**
	 * on talking player
	 * */
	protected EntityPlayer talkingPlayer;
	protected int id;
	private int tick = 0;

	public EntityMapNPCTask(World par1World) {
		super(par1World);
		this.setSize(0.6F, 1.8F);
		this.moveSpeed = 0.25F;
		this.tasks.addTask(7, new EntityAIWatchClosest(this,
				EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityNPCAIAttackPlayerInAngry(this,
				EntityPlayer.class, this.moveSpeed, false));
	}

	public void setCanTalk(int talk) {
		this.dataWatcher.updateObject(23, talk);

	}

	public int getCanTalk() {
		return this.dataWatcher.getWatchableObjectInt(23);
	}

	public void setFile(String fileName) {
		this.dataWatcher.updateObject(22, fileName);

	}

	public String getFile() {
		return this.dataWatcher.getWatchableObjectString(22);
	}

	public int getAngry() {
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public void setAngry(int angry) {
		this.dataWatcher.updateObject(21, Integer.valueOf(angry));
		if (angry == 1) {
			this.setAttackTarget(talkingPlayer);
		}
	}

	public String getCurTakGroup() {
		return this.dataWatcher.getWatchableObjectString(19);
	}

	@Override
	public void onLivingUpdate() {
		if (this.getAngry() == 1) {
			this.tick++;
			if (tick == 20 * 10) {
				this.tick = 0;
				this.setAngry(0);
			}

		}
		super.onLivingUpdate();

	}

	public float getSpeedModifier() {
		return super.getSpeedModifier() * (this.isChild() ? 1.5F : 1.0F);
	}

	public void setCurTakGroup(String taskGroup) {

		this.dataWatcher.updateObject(19, taskGroup);
	}

	public int getCurDialog() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public int getCurOption() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public int getMarkType() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public void setMarkType(int markType) {
		this.dataWatcher.updateObject(20, Integer.valueOf(markType));

	}

	public void setCurDialog(int dialog) {
		this.dataWatcher.updateObject(17, Integer.valueOf(dialog));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("NPCType", this.getType());
		par1nbtTagCompound.setString("CurGroup", this.getCurTakGroup());
		par1nbtTagCompound.setInteger("CurDialog", this.getCurDialog());
		par1nbtTagCompound.setInteger("CurOption", this.getCurOption());
		par1nbtTagCompound.setInteger("angry", this.getAngry());
		par1nbtTagCompound.setString("file", this.getFile());
		par1nbtTagCompound.setInteger("cantalk", this.getCanTalk());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {

		super.readEntityFromNBT(par1nbtTagCompound);
		this.setType(par1nbtTagCompound.getInteger("NPCType"));
		this.setCurTakGroup(par1nbtTagCompound.getString("CurGroup"));
		this.setCurDialog(par1nbtTagCompound.getInteger("CurDialog"));
		this.setCurOption(par1nbtTagCompound.getInteger("CurOption"));
		this.setAngry(par1nbtTagCompound.getInteger("angry"));
		this.setFile(par1nbtTagCompound.getString("file"));
		this.setCanTalk(par1nbtTagCompound.getInteger("cantalk"));
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (this.getCanTalk() == 0)
			return false;
		if (!isTalking() || this.talkingPlayer == par1EntityPlayer) {
			this.talkingPlayer = par1EntityPlayer;
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				NPC npc = DialogManager.instance.getCharacter(this.getEntityName());
				if (npc != null) {
					GuiScreen guiscreen = new GuiNPCDialog(par1EntityPlayer,this);
					((GuiNPCDialog) guiscreen).setNpc(npc);
					FMLClientHandler.instance().showGuiScreen(guiscreen);
				}
			}

		} else {
			return false;
		}
		return super.interact(par1EntityPlayer);
	}

	public void setCurOption(int option) {
		this.dataWatcher.updateObject(18, Integer.valueOf(option));
	}

	public int getType() {
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	public void setType(int type) {
		this.dataWatcher.updateObject(16, Integer.valueOf(type));
	}

	public boolean isTalking() {
		return talkingPlayer == null ? false : true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, 0);// type
		this.dataWatcher.addObject(17, 0);// CurDialog
		this.dataWatcher.addObject(18, 0);// CurOption
		this.dataWatcher.addObject(19, "");// TakGroup
		this.dataWatcher.addObject(20, 0);// marktype
		this.dataWatcher.addObject(21, 0);// isangry
		this.dataWatcher.addObject(22, "");// file
		this.dataWatcher.addObject(23, 1);// can talk
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

}
