package net.journey.blocks.tileentity;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.server.gui.PlayerListComponent;
import net.slayer.api.SlayerAPI;

public class TileEntityGrindstone extends TileEntity implements ITickable {

	public Item itemOnGrind = null;
	public int state = 0, count = 0;
	public float rotation = 0.0F;
	public boolean isActive = false;
	public TileEntityGrindstone() { }

	
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("ItemOnGrind", Item.getIdFromItem(itemOnGrind));
		nbt.setInteger("GrindItemState", state);
		nbt.setBoolean("Active", isActive);
		nbt.setFloat("Rotation", rotation);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		itemOnGrind = Item.getItemById(nbt.getInteger("ItemOnGrind"));
		state = nbt.getInteger("GrindItemState");
		isActive = nbt.getBoolean("Active");
		rotation = nbt.getFloat("Rotation");
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new SPacketUpdateTileEntity(pos, 1, var1);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	public Item getItem() {
		return itemOnGrind;
	}

	public boolean isActivated() {
		return isActive;
	}

	public float getRotaton() {
		return rotation;
	}

	public void setActivated(boolean a) {
		isActive = a;
	}

	public boolean work(int f) {
		boolean returnVal = false;
		if(isActivated()) {
			if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.celestiumOre)) setItem(JourneyItems.celestiumDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.hellstoneOre)) setItem(JourneyItems.hellstoneDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.luniumOre)) setItem(JourneyItems.luniumDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.shadiumOre)) setItem(JourneyItems.shadiumDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.flairiumOre)) setItem(JourneyItems.flairiumDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.ashualOre)) setItem(JourneyItems.ashDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.sapphireOre)) setItem(JourneyItems.sapphireDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.enderilliumOre)) setItem(JourneyItems.enderilliumDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(Blocks.GOLD_ORE)) setItem(JourneyItems.goldDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(Blocks.DIAMOND_ORE)) setItem(JourneyItems.diamondDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(Blocks.IRON_ORE)) setItem(JourneyItems.ironDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.gorbiteOre)) setItem(JourneyItems.gorbiteDust, f, returnVal);
			else if(itemOnGrind == SlayerAPI.toItem(JourneyBlocks.orbaditeOre)) setItem(JourneyItems.orbaditeDust, f, returnVal);

			else {
				state = 0;
				count = 0;
			}
			if(itemOnGrind != null) {
				Item item = itemOnGrind;
				if(item == JourneyItems.celestiumDust || item == JourneyItems.hellstoneDust || item == JourneyItems.shadiumDust || item == JourneyItems.luniumDust || item == JourneyItems.flairiumDust
						|| item == JourneyItems.ashDust || item == JourneyItems.sapphireDust || item == JourneyItems.enderilliumDust) {
					count += f;
					if(count >= 50) {
						count = 0;
						state++;
						returnVal = true;
						if(state == 3) {
							count = 0;
							state = 0;
							if(item == SlayerAPI.toItem(JourneyBlocks.celestiumOre)) itemOnGrind = JourneyItems.celestiumDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.hellstoneOre)) itemOnGrind = JourneyItems.hellstoneDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.shadiumOre)) itemOnGrind = JourneyItems.shadiumDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.luniumOre)) itemOnGrind = JourneyItems.luniumDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.flairiumOre)) itemOnGrind = JourneyItems.flairiumDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.ashualOre)) itemOnGrind = JourneyItems.ashDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.sapphireOre)) itemOnGrind = JourneyItems.sapphireDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.enderilliumOre)) itemOnGrind = JourneyItems.enderilliumDust;
							else if(item == SlayerAPI.toItem(Blocks.DIAMOND_ORE)) itemOnGrind = JourneyItems.diamondDust;
							else if(item == SlayerAPI.toItem(Blocks.GOLD_ORE)) itemOnGrind = JourneyItems.goldDust;
							else if(item == SlayerAPI.toItem(Blocks.IRON_ORE)) itemOnGrind = JourneyItems.ironDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.gorbiteOre)) itemOnGrind = JourneyItems.gorbiteDust;
							else if(item == SlayerAPI.toItem(JourneyBlocks.orbaditeOre)) itemOnGrind = JourneyItems.orbaditeDust;
							else itemOnGrind = null;
						}
					}
				}
			}
		}
		return returnVal;
	}

	public void setItem(Item d, int f, boolean returnVal) {
		count += f;
		if(count >= 400) {
			count = 0;
			state++;
			returnVal = true;
			if(state == 3) {
				state = 0;
				count = 0;
				itemOnGrind = d;
			}
		}
		if(itemOnGrind == d) count += f;
	}

	@Override
	public void update() {
		if(world.isBlockPowered(getPos())) {
			setActivated(true);
			work(2);
			rotation += 20.0F;
		} else {
			setActivated(false);
		}
		if(rotation == 360F) rotation = 0.0F;
	}
}