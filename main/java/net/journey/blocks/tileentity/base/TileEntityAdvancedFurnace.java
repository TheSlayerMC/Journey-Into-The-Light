package net.journey.blocks.tileentity.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockNetherFurnace;

public abstract class TileEntityAdvancedFurnace extends TileEntity implements ISidedInventory, ITickable {
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    protected ItemStack[] furnaceItemStacks = new ItemStack[3];
    public int 
    furnaceBurnTime, 
    furnaceCookTime, 
    speed, 
    flameTime;
    private String customName;

    public TileEntityAdvancedFurnace(String name, int speed) {
		this.setCustomName(name);
		this.speed = speed;
	}
    
    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
    	if(this.furnaceItemStacks[index] != null) {
    		ItemStack itemstack;
    		if(this.furnaceItemStacks[index].stackSize <= count) {
    			itemstack = this.furnaceItemStacks[index];
    			this.furnaceItemStacks[index] = null;
    			return itemstack;
    		} else {
    			itemstack = this.furnaceItemStacks[index].splitStack(count);
    			if (this.furnaceItemStacks[index].stackSize == 0) this.furnaceItemStacks[index] = null;
    			return itemstack;
    		}
    	} else {
    		return null;
    	}
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (this.furnaceItemStacks[index] != null) {
            ItemStack itemstack = this.furnaceItemStacks[index];
            this.furnaceItemStacks[index] = null;
            return itemstack;
        }
        else {
            return null;
        }
    }
    
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.furnaceItemStacks[index] != null)
        {
            ItemStack itemstack = this.furnaceItemStacks[index];
            this.furnaceItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.furnaceItemStacks[index] = stack;
        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) 
        	stack.stackSize = this.getInventoryStackLimit();
    }
    
    @Override
    public void openInventory(EntityPlayer player)
    {}

    @Override
    public void closeInventory(EntityPlayer player)
    {}
    
    @Override
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.flameTime;
            case 2:
                return this.speed;
            case 3:
                return this.furnaceCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.flameTime = value;
                break;
            case 2:
                this.speed = value;
                break;
            case 3:
                this.furnaceCookTime = value;
        }
    }
    
    @Override
    public int getFieldCount()
    {
        return 4;
    }
    
    @Override
    public void clear()
    {
        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            this.furnaceItemStacks[i] = null;
        }
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "Nether Furnace";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomName(String name) {
        this.customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b = nbttagcompound1.getByte("Slot");

            if(b >= 0 && b < this.furnaceItemStacks.length) this.furnaceItemStacks[b] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        this.furnaceBurnTime = nbt.getShort("BurnTime");
        this.furnaceCookTime = nbt.getShort("CookTime");

        if(nbt.hasKey("CustomName", 8)) this.customName = nbt.getString("CustomName");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short)this.furnaceBurnTime);
        nbt.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if(this.furnaceItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
        if (this.hasCustomName()) nbt.setString("CustomName", this.customName);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        return this.furnaceCookTime * i / speed;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i) {
        return this.furnaceBurnTime * i / 40;
    }
    
    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }
    
    public abstract void addUpdate();
    
    @Override
    public void update() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if(this.furnaceBurnTime > 0) --this.furnaceBurnTime;

        if(!this.worldObj.isRemote) {
            if(this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.furnaceBurnTime = 40;
            }

            if(this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if(this.flameTime > 0) this.flameTime--;
                else this.flameTime = 40;

                if(this.furnaceCookTime == speed) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }

            if(flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                addUpdate();
            }
        }

        if(flag1) this.markDirty();
    }
    
	private boolean canSmelt() {
		if (this.furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemstack == null) return false;
			if (this.furnaceItemStacks[1] == null) return true;
			if (!this.furnaceItemStacks[1].isItemEqual(itemstack)) return false;
			int result = furnaceItemStacks[1].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[1].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);

			if(this.furnaceItemStacks[1] == null) 
				this.furnaceItemStacks[1] = itemstack.copy();

			else if(this.furnaceItemStacks[1].getItem() == itemstack.getItem()) 
				this.furnaceItemStacks[1].stackSize += itemstack.stackSize;


			--this.furnaceItemStacks[0].stackSize;

			if(this.furnaceItemStacks[0].stackSize <= 0) this.furnaceItemStacks[0] = null;
		}
	}
	
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
     }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack par2ItemStack) {
        return slot != 1;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket) {
                return false;
            }
        }
        return true;
    }
}