package net.slayer.api.entity.tileentity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityModFurnace extends TileEntityLockable implements ITickable, ISidedInventory {
	
	protected static final int[] slotsTop = new int[] {0};
    protected static final int[] slotsBottom = new int[] {2, 1};
    protected static final int[] slotsSides = new int[] {1};
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    public int furnaceBurnTime, currentItemBurnTime, cookTime, speed;
    protected String customName;
    protected static boolean hasFuel;
    
    public TileEntityModFurnace(String name, boolean fuel, int speed) {
		setCustomName(name);
		hasFuel = fuel;
		this.speed = speed;
	}

    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.furnaceItemStacks.get(par1);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
    	return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int par1) {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, par1);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
    	 ItemStack itemstack = this.furnaceItemStacks.get(index);
         boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
         this.furnaceItemStacks.set(index, stack);

         if (stack.getCount() > this.getInventoryStackLimit()) {
             stack.setCount(this.getInventoryStackLimit());
         }

         if (index == 0 && !flag) {
             this.speed = this.getCookTime(stack);
             this.cookTime = 0;
             this.markDirty();
         }
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.furnace";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomName(String s) {
        this.customName = s;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.speed = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.speed);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        return this.cookTime * i / speed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i) {
        if(this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = speed;
        }
        return this.furnaceBurnTime * i / this.currentItemBurnTime;
    }
    
    public int getCookTime(ItemStack stack) {
        return speed;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    public abstract void addUpdate();

	@Override
	public void update() {
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.furnaceItemStacks.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.furnaceItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.speed)
                    {
                        this.cookTime = 0;
                        this.speed = this.getCookTime(this.furnaceItemStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.speed);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

	protected boolean canSmelt() {
		if (((ItemStack)this.furnaceItemStacks.get(0)).isEmpty()) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(0));

            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.furnaceItemStacks.get(2);

                if (itemstack1.isEmpty()) {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  {
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
                }
            }
        }
	}


	public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = this.furnaceItemStacks.get(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = this.furnaceItemStacks.get(2);

            if (itemstack2.isEmpty()) {
                this.furnaceItemStacks.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !((ItemStack)this.furnaceItemStacks.get(1)).isEmpty() && ((ItemStack)this.furnaceItemStacks.get(1)).getItem() == Items.BUCKET) {
                this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    public int getItemBurnTime(ItemStack stack) {
		if(hasFuel){
			if(stack == null) return 0;
			else {
				Item item = stack.getItem();
				if(item == Items.COAL) return 1600 / speed;
				return GameRegistry.getFuelValue(stack);
			}
		} else {
			if(stack == null) return 40;
			return 40;
		}
	}

    public boolean isItemFuel(ItemStack i) {
        return getItemBurnTime(i) > 0;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
    	return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack par2ItemStack) {
        return slot == 2 ? false : (slot == 1 ? isItemFuel(par2ItemStack) : true);
    }

	public void setCustomInventoryName(String displayName) {
		customName = displayName;
	}
}