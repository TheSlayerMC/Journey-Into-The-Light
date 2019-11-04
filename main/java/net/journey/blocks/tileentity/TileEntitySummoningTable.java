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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.slayer.api.SlayerAPI;

public class TileEntitySummoningTable extends TileEntity implements ITickable, IItemHandler {

    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
    private String customName;
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory.get(i);
	}
	
	/*@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);

        if(stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if(index == 0 && !flag) {
            this.markDirty();
        }
	}*/
	
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerSummoningTable(playerInventory, this, world);
    }
    
	public static void registerFixesFurnace(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntitySummoningTable.class, new String[] { "Items" }));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
        ItemStackHelper.loadAllItems(nbt, this.inventory);
		if(nbt.hasKey("CustomName", 8)) this.setCustomName(nbt.getString("CustomName"));
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, this.inventory);
        
        //if(this.hasCustomName()) nbt.setString("CustomName", this.customName);
		return nbt;
	}

	@Override
	public void update() {
		/*if(this.inventory.get(0) != null && this.inventory.get(1) != null && this.inventory.get(2) != null && this.inventory.get(3) != null && this.inventory.get(4) != null && this.inventory.get(5) != null && this.inventory.get(6) != null) {
			if(areItemsInSlots(
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
						JourneyItems.blazingFireball, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder, 
					JourneyItems.boilPowder)) {
				setAllSlotsToNull();
				this.inventory.set(3, new ItemStack(JourneyItems.blazierOrb));
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
		//}
	}
	
	public String getGuiID() {
		return "journey:summoningtable";
	}
	
	@SideOnly(Side.CLIENT)
	public void addSound() {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		//world.playSoundEffect(x, y, z, "journey:summon", 1.0F, 1.0F);
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
		return this.inventory.get(0) == s && this.inventory.get(1) == s1 && this.inventory.get(2) == s2 && this.inventory.get(3) == s3 && this.inventory.get(4) == s4 && this.inventory.get(5) == s5 && this.inventory.get(6) == s6;
	}

	public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
		return this.inventory.get(0).getItem() == s && this.inventory.get(1).getItem() == s1 && this.inventory.get(2).getItem() == s2 && this.inventory.get(3).getItem() == s3 && this.inventory.get(4).getItem() == s4 && this.inventory.get(5).getItem() == s5 && this.inventory.get(6).getItem() == s6;
	}

	public void setAllSlotsToNull() {
		this.inventory.clear();
	}

	public void setInventorySlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
		s = this.inventory.get(0);
		s1 = this.inventory.get(1);
		s2 = this.inventory.get(2);
		s3 = this.inventory.get(3);
		s4 = this.inventory.get(4);
		s5 = this.inventory.get(5);
		s6 = this.inventory.get(6);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) new ItemStackHandler(1);
		return super.getCapability(capability, facing);
	}
	
	@Override
	public int getSlots() {
		return inventory.size();
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return ItemStackHelper.getAndRemove(this.inventory, slot);
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}
}