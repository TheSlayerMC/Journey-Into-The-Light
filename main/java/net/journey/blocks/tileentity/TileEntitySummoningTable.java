package net.journey.blocks.tileentity;

import java.util.Random;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.journey.client.render.particles.EntityModFireFX;
import net.journey.client.render.particles.EntityModSnowFX;
import net.journey.client.render.particles.EntitySummoningFX;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class TileEntitySummoningTable extends TileEntity implements ITickable, IInventory {

    private NonNullList<ItemStack> summoningItemStacks = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
	//private ItemStack[] inventory;

	public TileEntitySummoningTable() {
		//this.inventory = new ItemStack[7];
		EntityPlayer EntityPlayer;
	}

	@Override
	public int getSizeInventory() {
		return summoningItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.summoningItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return summoningItemStacks.get(i);
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j) {
		return ItemStackHelper.getAndSplit(this.summoningItemStacks, i, j);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.summoningItemStacks, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.summoningItemStacks.get(index);
		
		boolean flag = stack != null && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);

		if(stack != null && stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit()); 
		if(index == 0 && !flag) this.markDirty();
	}
	
	@Override
	public String getName() {
		return "Summoning Table";
	}
	
	@Override
	public boolean hasCustomName() {
		return true;
	}
	
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerSummoningTable(playerInventory, this, world);
    }
    
	public static void registerFixesFurnace(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntitySummoningTable.class, new String[] { "Items" }));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
        this.summoningItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.summoningItemStacks);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		for(int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			//if(b0 >= 0 && b0 < this.inventory.length)
			//	this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		} 
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, this.summoningItemStacks);
		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < this.summoningItemStacks.size(); i++) {
			if(this.summoningItemStacks.get(i) != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.summoningItemStacks.get(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
		return nbt;
	}



	@Override
	public void update() {
		if(this.summoningItemStacks.get(0) != null && this.summoningItemStacks.get(1) != null && this.summoningItemStacks.get(2) != null && this.summoningItemStacks.get(3) != null && this.summoningItemStacks.get(4) != null && this.summoningItemStacks.get(5) != null && this.summoningItemStacks.get(6) != null) {
			if(areItemsInSlots(
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
						JourneyItems.blazingFireball, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder)) {
				setAllSlotsToNull();
				this.summoningItemStacks.set(3, new ItemStack(JourneyItems.blazierOrb));
				addSound();
				addParticles();
			} /*
			else if(areItemsInSlots(
					JourneyItems.snakeSkin, 
					JourneyItems.concentratedBlood, 
					JourneyItems.snakeSkin, 
						JourneyItems.sizzlingEye, 
					JourneyItems.snakeSkin, 
					JourneyItems.concentratedBlood, 
					JourneyItems.snakeSkin)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.soulWatcherOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.natureTablet, 
					JourneyItems.enchantedLeaf, 
					JourneyItems.natureTablet, 
						JourneyItems.overgrownNatureBall, 
					JourneyItems.natureTablet, 
					JourneyItems.enchantedLeaf, 
					JourneyItems.natureTablet)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.loggerOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.overseeingEye, 
					JourneyItems.collectorRock, 
					JourneyItems.overseeingEye, 
						JourneyItems.overseeingTablet, 
					JourneyItems.overseeingEye, 
					JourneyItems.collectorRock, 
					JourneyItems.overseeingEye)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.sentryKingOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.scale, 
					JourneyItems.beastlyStomach, 
					JourneyItems.scale, 
						JourneyItems.darkOrb, 
					JourneyItems.scale, 
					JourneyItems.beastlyStomach, 
					JourneyItems.scale)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.scaleOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.rocFeather, 
					JourneyItems.darkCrystal, 
					JourneyItems.rocFeather, 
						JourneyItems.darkOrb, 
					JourneyItems.rocFeather, 
					JourneyItems.darkCrystal, 
					JourneyItems.rocFeather)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.thunderbirdOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.gateKeys, 
					JourneyItems.silverClump, 
					JourneyItems.gateKeys, 
						JourneyItems.metalDisk, 
					JourneyItems.gateKeys, 
					JourneyItems.silverClump, 
					JourneyItems.gateKeys)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.corallatorOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.gateKeys, 
					JourneyItems.goldClump, 
					JourneyItems.gateKeys, 
						JourneyItems.royalDisk, 
					JourneyItems.gateKeys, 
					JourneyItems.goldClump, 
					JourneyItems.gateKeys)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.eudorOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.hellstoneIngot, 
					JourneyItems.hellShards, 
					JourneyItems.hellstoneIngot, 
						JourneyItems.hellcrustIngot, 
					JourneyItems.hellstoneIngot, 
					JourneyItems.hellShards, 
					JourneyItems.hellstoneIngot)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.netherBeastOrb);
				addSound();
				addParticles();
			}
			else if(areItemsInSlots(
					JourneyItems.withicSpine, 
					JourneyItems.lostSoul, 
					JourneyItems.withicSpine, 
						JourneyItems.withicSoul, 
					JourneyItems.withicSpine, 
					JourneyItems.lostSoul, 
					JourneyItems.withicSpine)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.witheringBeastOrb);
				addSound();
				addParticles();
			}		
			else if(areItemsInSlots(
					JourneyItems.earthenCrystal, 
					JourneyItems.purplePowder, 
					JourneyItems.earthenCrystal, 
						JourneyItems.terrastar, 
					JourneyItems.earthenCrystal, 
					JourneyItems.purplePowder, 
					JourneyItems.earthenCrystal)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.enchantedTerrastar);
				addSound();
				addParticles();
			}
			/**else if(areItemsInSlots(
					JourneyItems.spawnerBar, 
					JourneyItems.ash, 
					JourneyItems.spawnerBar, 
						JourneyItems.hellShards, 
					JourneyItems.spawnerBar, 
					JourneyItems.ash, 
					JourneyItems.spawnerBar)) {
				setAllSlotsToNull();
				inventory[3] = new ItemStack(JourneyItems.calciaOrb);
				addParticles();
				
			}**/
		}
	}

	@SideOnly(Side.CLIENT)
	public void addSound() {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		//world.playSoundEffect(x, y, z, "essence:summon", 1.0F, 1.0F);
	}
	@SideOnly(Side.CLIENT)
	public void addParticles() {
		Random r = new Random();
		if(!world.isRemote) {
			for(int i = 0; i < 20; i++)
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(new EntitySummoningFX(world, getPos().getX() + r.nextFloat(), getPos().getY() + 1.2D, getPos().getZ() + r.nextFloat(), 0, 1, 0));
		}
	}

	public boolean areItemStacksInSlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
		return this.summoningItemStacks.get(0) == s && this.summoningItemStacks.get(1) == s1 && this.summoningItemStacks.get(2) == s2 && this.summoningItemStacks.get(3) == s3 && this.summoningItemStacks.get(4) == s4 && this.summoningItemStacks.get(5) == s5 && this.summoningItemStacks.get(6) == s6;
	}

	public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
		return this.summoningItemStacks.get(0).getItem() == s && this.summoningItemStacks.get(1).getItem() == s1 && this.summoningItemStacks.get(2).getItem() == s2 && this.summoningItemStacks.get(3).getItem() == s3 && this.summoningItemStacks.get(4).getItem() == s4 && this.summoningItemStacks.get(5).getItem() == s5 && this.summoningItemStacks.get(6).getItem() == s6;
	}

	public void setAllSlotsToNull() {
		clear();
	}

	public void setInventorySlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
		s = this.summoningItemStacks.get(0);
		s1 = this.summoningItemStacks.get(1);
		s2 = this.summoningItemStacks.get(2);
		s3 = this.summoningItemStacks.get(3);
		s4 = this.summoningItemStacks.get(4);
		s5 = this.summoningItemStacks.get(5);
		s6 = this.summoningItemStacks.get(6);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() != null;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) { }

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.summoningItemStacks.clear();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}
}