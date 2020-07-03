package net.journey.blocks.tileentity;

import net.journey.blocks.containers.BlockIncubator;
import net.journey.blocks.tileentity.container.ContainerIncubator;
import net.journey.blocks.tileentity.container.slot.SlotIncubatorFuel;
import net.journey.util.IncubatorRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.slayer.api.SlayerAPI;

import javax.annotation.Nullable;

public class TileEntityIncubator extends TileEntityLockable implements ITickable, ISidedInventory {

	private static final int[] SLOTS_TOP = new int[]{0};
	private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
	private static final int[] SLOTS_SIDES = new int[]{1};
	private final IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	private final IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	private final IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	private NonNullList<ItemStack> incubatorItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);

	private int incubatorBurnTime;
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String incubatorCustomName;

	@Override
	public int getSizeInventory() {
		return this.incubatorItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.incubatorItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.incubatorItemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.incubatorItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.incubatorItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.incubatorItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.incubatorItemStacks.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.incubatorCustomName : "container.incubator";
	}

	@Override
	public boolean hasCustomName() {
		return this.incubatorCustomName != null && !this.incubatorCustomName.isEmpty();
	}

	public void setCustomInventoryName(String p_145951_1_) {
		this.incubatorCustomName = p_145951_1_;
	}

	public static void registerFixesincubator(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityIncubator.class, "Items"));
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.incubatorItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.incubatorItemStacks);
		this.incubatorBurnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime(this.incubatorItemStacks.get(1));

		if (compound.hasKey("CustomName", 8)) {
			this.incubatorCustomName = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.incubatorBurnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.incubatorItemStacks);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.incubatorCustomName);
		}

		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isBurning() {
		return this.incubatorBurnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(0) > 0;
	}

	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;

		if (this.isBurning()) {
			--this.incubatorBurnTime;
		}

		if (!this.world.isRemote) {
			ItemStack itemstack = this.incubatorItemStacks.get(1);

			if (this.isBurning() || !itemstack.isEmpty() && !this.incubatorItemStacks.get(0).isEmpty()) {
				if (!this.isBurning() && this.canSmelt()) {
					this.incubatorBurnTime = getItemBurnTime(itemstack);
					this.currentItemBurnTime = this.incubatorBurnTime;

					if (this.isBurning()) {
						flag1 = true;

						if (!itemstack.isEmpty()) {
							Item item = itemstack.getItem();
							itemstack.shrink(1);

							if (itemstack.isEmpty()) {
								ItemStack item1 = item.getContainerItem(itemstack);
								this.incubatorItemStacks.set(1, item1);
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt()) {
					++this.cookTime;

					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime(this.incubatorItemStacks.get(0));
						this.smeltItem();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			}
			else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				BlockIncubator.setState(this.isBurning(), this.world, this.pos);
			}
		}
		if (flag1) {
			this.markDirty();
		}
	}

	public int getCookTime(ItemStack stack) {
		return 500;
	}

	private boolean canSmelt() {
		if (this.incubatorItemStacks.get(0).isEmpty()) {
			return false;
		} else {
			ItemStack itemstack = IncubatorRecipes.instance().getSmeltingResult(this.incubatorItemStacks.get(0));

			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.incubatorItemStacks.get(2);

				if (itemstack1.isEmpty()) {
					return true;
				}
				else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				}
				else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
				}
			}
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = this.incubatorItemStacks.get(0);
			ItemStack itemstack1 = IncubatorRecipes.instance().getSmeltingResult(itemstack);
			ItemStack itemstack2 = this.incubatorItemStacks.get(2);

			if (itemstack2.isEmpty()) {
				this.incubatorItemStacks.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !this.incubatorItemStacks.get(1).isEmpty() && this.incubatorItemStacks.get(1).getItem() == Items.BUCKET) {
				this.incubatorItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
			}
			itemstack.shrink(1);
		}
	}

	public static int getItemBurnTime(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			int burnTime = ForgeEventFactory.getItemBurnTime(stack);
			if (burnTime >= 0) return burnTime;
			Item item = stack.getItem();


			if (item == Items.COAL) {
				return 100;
			}
			return 300;
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2) {
			return false;
		}
		else if (index != 1) {
			return true;
		} else {
			ItemStack itemstack = this.incubatorItemStacks.get(1);
			return isItemFuel(stack) || SlotIncubatorFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return SLOTS_BOTTOM;
		} else {
			return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (direction == EnumFacing.DOWN && index == 1) {
			Item item = stack.getItem();

			return item == Items.WATER_BUCKET || item == Items.BUCKET;
		}

		return true;
	}

	@Override
	public String getGuiID() {
		return SlayerAPI.PREFIX + "incubator";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerIncubator(playerInventory, this);
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.incubatorBurnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.incubatorBurnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		this.incubatorItemStacks.clear();
	}

	@Override
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}
}