package net.journey.blocks.tileentity;

import net.journey.blocks.containers.JBlockBossCrystal;
import net.journey.blocks.containers.JBlockBossCrystal;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.slayer.api.JourneyDoubleChestHandler;

import javax.annotation.Nullable;

public class TileEntityBossCrystal extends TileEntityLockableLoot implements ITickable {
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
    private int ticksSinceSync;
    private JBlockBossCrystal.Type cachedChestType;

    public static void registerFixesBossCrystal(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityBossCrystal.class, "Items"));
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.chestContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.chest";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    private boolean isChestAt(BlockPos posIn) {
        if (this.world == null) {
            return false;
        } else {
            Block block = this.world.getBlockState(posIn).getBlock();
            return block instanceof JBlockBossCrystal && ((JBlockBossCrystal) block).type == this.getChestType();
        }
    }

    @Override
    public void update() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0) {
            this.numPlayersUsing = 0;
            float f = 5.0F;

            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class,
                    new AxisAlignedBB((float) i - 5.0F, (float) j - 5.0F,
                            (float) k - 5.0F, (float) (i + 1) + 5.0F,
                            (float) (j + 1) + 5.0F, (float) (k + 1) + 5.0F))) {
                if (entityplayer.openContainer instanceof ContainerChest) {
                    IInventory iinventory = ((ContainerChest) entityplayer.openContainer).getLowerChestInventory();
                    if (iinventory == this || iinventory instanceof InventoryLargeChest
                            && ((InventoryLargeChest) iinventory).isPartOfLargeChest(this)) {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            double d1 = (double) i + 0.5D;
            double d2 = (double) k + 0.5D;

            this.world.playSound(null, d1, (double) j + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN,
                    SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.735F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            float f3 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F) {
                double d3 = (double) i + 0.5D;
                double d0 = (double) k + 0.5D;

                this.world.playSound(null, d3, (double) j + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE,
                        SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.735F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        if (!player.isSpectator() && this.getBlockType() instanceof JBlockBossCrystal) {
            --this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    public net.minecraftforge.items.IItemHandler getSingleChestHandler() {
        return super.getCapability(net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    public JBlockBossCrystal.Type getChestType() {
        if (this.cachedChestType == null) {
            if (!(this.getBlockType() instanceof JBlockBossCrystal)) {
                return JBlockBossCrystal.Type.BASE;
            }

            this.cachedChestType = ((JBlockBossCrystal) this.getBlockType()).type;
        }
        return cachedChestType;
    }

    public void setChestType(JBlockBossCrystal.Type type) {
        cachedChestType = type;
    }

    @Override
    public String getGuiID() {
        return "minecraft:chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        this.fillWithLoot(playerIn);
        return new ContainerChest(playerInventory, this, playerIn);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
    }
}