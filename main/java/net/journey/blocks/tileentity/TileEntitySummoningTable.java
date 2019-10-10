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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class TileEntitySummoningTable extends TileEntity implements ITickable, IInventory {


	/* TODO:
	 * Finish slot index in container for blood and rotten flesh
	 * Create custom burn time for rotten flesh
	 * Finish slots for 6 summoning items, 1 result item, blood slot and flesh slot
	 * Create summoning recipe class
	 */
	
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
    private String customName;

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.summoningtable";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName!= null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory.get(i);
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j) {
		return ItemStackHelper.getAndSplit(this.inventory, i, j);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);

		if(stack != null && stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit()); 
		if(index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
		}
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
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.inventory);
		
		if(nbt.hasKey("CustomName", 8)) this.setCustomName(nbt.getString("CustomName"));
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, this.inventory);
        
        if (this.hasCustomName()) nbt.setString("CustomName", this.customName);
		return nbt;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBloodActive(IInventory i) {
		return i.getField(0) > 0;
	}

	@Override
	public void update() {
		if(this.inventory.get(0) != null && this.inventory.get(1) != null && this.inventory.get(2) != null && this.inventory.get(3) != null && this.inventory.get(4) != null && this.inventory.get(5) != null && this.inventory.get(6) != null) {
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
		}
	}
	
	public int getCreationTime(ItemStack input1, ItemStack input2) {
		return 1000;
	}
	
	/*private boolean canCreate() {
		if(((ItemStack)this.inventory.get(0)).isEmpty() || ((ItemStack)this.inventory.get(1)).isEmpty()) return false;
		else {
			ItemStack result = SummoningTableRecipes.getInstance().getSummonResult((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1));
			if(result.isEmpty()) return false;
			else {
				ItemStack output = (ItemStack)this.inventory.get(3);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
	
	public void createItem() {
		if(this.canCreate()) {
			ItemStack i1 = (ItemStack)this.inventory.get(0);
			ItemStack i2 = (ItemStack)this.inventory.get(1);
			ItemStack result = SummoningTableRecipes.getInstance().getSummonResult(i1, i2);
			ItemStack output = (ItemStack)this.inventory.get(3);
			
			if(output.isEmpty()) this.inventory.set(3, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());
			
			i1.shrink(1);
			i2.shrink(1);
		}
	}*/
	
	public static int getItemCreationTime(ItemStack stack) {
		if(stack.isEmpty()) return 0;
		else {
			Item item = stack.getItem();
			
			if(item == JourneyItems.concentratedBlood) return 200;
			return GameRegistry.getFuelValue(stack);
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemCreationTime(stack) > 0;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		double x = this.pos.getX() + 0.5D;
		double y = this.pos.getY() + 0.5D;
		double z = this.pos.getZ() + 0.5D;
		
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(x, y, z) <= 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 3) return false;
		else if(index !=2) return true;
		else {
			return isItemFuel(stack);
		}
	}
	
	public String getGuiID() {
		return "journey:summoningtable";
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
		return this.inventory.get(0) == s && this.inventory.get(1) == s1 && this.inventory.get(2) == s2 && this.inventory.get(3) == s3 && this.inventory.get(4) == s4 && this.inventory.get(5) == s5 && this.inventory.get(6) == s6;
	}

	public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
		return this.inventory.get(0).getItem() == s && this.inventory.get(1).getItem() == s1 && this.inventory.get(2).getItem() == s2 && this.inventory.get(3).getItem() == s3 && this.inventory.get(4).getItem() == s4 && this.inventory.get(5).getItem() == s5 && this.inventory.get(6).getItem() == s6;
	}

	public void setAllSlotsToNull() {
		clear();
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
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public void setField(int id, int value) { }

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public int getField(int id) {
		return id;
	}
}