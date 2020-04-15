package net.journey.blocks.tileentity;

import net.journey.JITL;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.journey.enums.EnumParticlesClasses;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class TileEntitySummoningTable extends TileEntityLockableLoot implements ITickable {

    public static final TileEntitySummoningTable INSTANCE = new TileEntitySummoningTable();

    private NonNullList<ItemStack> inventory = NonNullList.withSize(7, ItemStack.EMPTY);
    private String customName;

    public static TileEntitySummoningTable getInstance() {
        return INSTANCE;
    }

    @Override
    public void update() {
        ItemStack slot1 = this.inventory.get(0);
        ItemStack slot2 = this.inventory.get(1);
        ItemStack slot3 = this.inventory.get(2);
        ItemStack slot4 = this.inventory.get(3);
        ItemStack slot5 = this.inventory.get(4);
        ItemStack slot6 = this.inventory.get(5);
        ItemStack slot7 = this.inventory.get(6);
        if (slot1 != null && slot2 != null && slot3 != null && slot4 != null && slot5 != null && slot6 != null && slot7 != null) {
            if (areItemsInSlots(
                    JourneyItems.boilPowder,
                    JourneyItems.boilPowder,
                    JourneyItems.boilPowder,
                    JourneyItems.blazingFireball,
                    JourneyItems.boilPowder,
                    JourneyItems.boilPowder,
                    JourneyItems.boilPowder)) {
                summonItem(new ItemStack(JourneyItems.blazierOrb));
            } else if (areItemsInSlots(
                    JourneyItems.snakeSkin,
                    JourneyItems.concentratedBlood,
                    JourneyItems.snakeSkin,
                    JourneyItems.sizzlingEye,
                    JourneyItems.snakeSkin,
                    JourneyItems.concentratedBlood,
                    JourneyItems.snakeSkin)) {
                summonItem(new ItemStack(JourneyItems.soulWatcherOrb));
            } else if (areItemsInSlots(
                    JourneyItems.natureTablet,
                    JourneyItems.enchantedLeaf,
                    JourneyItems.natureTablet,
                    JourneyItems.overgrownNatureBall,
                    JourneyItems.natureTablet,
                    JourneyItems.enchantedLeaf,
                    JourneyItems.natureTablet)) {
                summonItem(new ItemStack(JourneyItems.loggerOrb));
            } else if (areItemsInSlots(
                    JourneyItems.overseeingEye,
                    JourneyItems.collectorRock,
                    JourneyItems.overseeingEye,
                    JourneyItems.overseeingTablet,
                    JourneyItems.overseeingEye,
                    JourneyItems.collectorRock,
                    JourneyItems.overseeingEye)) {
                summonItem(new ItemStack(JourneyItems.sentryKingOrb));
            } else if (areItemsInSlots(
                    JourneyItems.scale,
                    JourneyItems.beastlyStomach,
                    JourneyItems.scale,
                    JourneyItems.darkOrb,
                    JourneyItems.scale,
                    JourneyItems.beastlyStomach,
                    JourneyItems.scale)) {
                summonItem(new ItemStack(JourneyItems.scale));
            } else if (areItemsInSlots(
                    JourneyItems.rocFeather,
                    JourneyItems.darkCrystal,
                    JourneyItems.rocFeather,
                    JourneyItems.darkOrb,
                    JourneyItems.rocFeather,
                    JourneyItems.darkCrystal,
                    JourneyItems.rocFeather)) {
                summonItem(new ItemStack(JourneyItems.thunderbirdOrb));
            } else if (areItemsInSlots(
                    JourneyItems.gateKeys,
                    JourneyItems.silverClump,
                    JourneyItems.gateKeys,
                    JourneyItems.metalDisk,
                    JourneyItems.gateKeys,
                    JourneyItems.silverClump,
                    JourneyItems.gateKeys)) {
                summonItem(new ItemStack(JourneyItems.corallatorOrb));
            } else if (areItemsInSlots(
                    JourneyItems.gateKeys,
                    JourneyItems.goldClump,
                    JourneyItems.gateKeys,
                    JourneyItems.royalDisk,
                    JourneyItems.gateKeys,
                    JourneyItems.goldClump,
                    JourneyItems.gateKeys)) {
                summonItem(new ItemStack(JourneyItems.eudorOrb));
            } else if (areItemsInSlots(
                    JourneyItems.hellstoneIngot,
                    JourneyItems.hellShards,
                    JourneyItems.hellstoneIngot,
                    JourneyItems.hellcrustIngot,
                    JourneyItems.hellstoneIngot,
                    JourneyItems.hellShards,
                    JourneyItems.hellstoneIngot)) {
                summonItem(new ItemStack(JourneyItems.netherBeastOrb));
            } else if (areItemsInSlots(
                    JourneyItems.withicSpine,
                    JourneyItems.lostSoul,
                    JourneyItems.withicSpine,
                    JourneyItems.withicSoul,
                    JourneyItems.withicSpine,
                    JourneyItems.lostSoul,
                    JourneyItems.withicSpine)) {
                summonItem(new ItemStack(JourneyItems.witheringBeastOrb));
            } else if (areItemsInSlots(
                    JourneyItems.earthenCrystal,
                    JourneyItems.purplePowder,
                    JourneyItems.earthenCrystal,
                    JourneyItems.terrastar,
                    JourneyItems.earthenCrystal,
                    JourneyItems.purplePowder,
                    JourneyItems.earthenCrystal)) {
                summonItem(new ItemStack(JourneyItems.enchantedTerrastar));
            } else if (areItemsInSlots(
                    JourneyItems.spawnerBar,
                    JourneyItems.ash,
                    JourneyItems.spawnerBar,
                    JourneyItems.hellShards,
                    JourneyItems.spawnerBar,
                    JourneyItems.ash,
                    JourneyItems.spawnerBar)) {
                summonItem(new ItemStack(JourneyItems.calciaOrb));
            }
        }
    }

    public void summonItem(ItemStack orb) {
        //Shrink all stacks
        this.inventory.get(0).shrink(1);
        this.inventory.get(1).shrink(1);
        this.inventory.get(2).shrink(1);
        this.inventory.get(3).shrink(1);
        this.inventory.get(4).shrink(1);
        this.inventory.get(5).shrink(1);
        this.inventory.get(6).shrink(1);

        //Set boss orb in middle
        this.inventory.set(3, orb);

        //Add effects
        addSound();
        addParticles();
    }

    @SideOnly(Side.CLIENT)
    public void addSound() {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        world.playSound(x, y, z, JourneySounds.SUMMON_TABLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
    }

    @SideOnly(Side.CLIENT)
    public void addParticles() {
        Random r = new Random();
        if (!world.isRemote) {
            for (int i = 0; i < 20; i++)
                JITL.proxy.spawnParticle(EnumParticlesClasses.SUMMONING, world, getPos().getX() + r.nextFloat(), getPos().getY() + 1.2D, getPos().getZ() + r.nextFloat(), 0, 1, 0);
        }
    }

    public boolean areItemStacksInSlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
        return this.inventory.get(0) == s && this.inventory.get(1) == s1 && this.inventory.get(2) == s2 && this.inventory.get(3) == s3 && this.inventory.get(4) == s4 && this.inventory.get(5) == s5 && this.inventory.get(6) == s6;
    }

    public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
        return this.inventory.get(0).getItem() == s && this.inventory.get(1).getItem() == s1 && this.inventory.get(2).getItem() == s2 && this.inventory.get(3).getItem() == s3 && this.inventory.get(4).getItem() == s4 && this.inventory.get(5).getItem() == s5 && this.inventory.get(6).getItem() == s6;
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
    public int getSizeInventory() {
        return 7;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack s : this.inventory) {
            if (!s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //Now you can only put one item in the each slot
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "Summoning Table";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, inventory);
        }
        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, inventory);
        }
        if (compound.hasKey("CustomName", 8)) {
            compound.setString("CustomName", this.customName);
        }
        return compound;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerSummoningTable(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return "journey:summoningtable";
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return inventory;
    }
}