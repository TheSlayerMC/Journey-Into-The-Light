package net.journey.entity.util;

import io.netty.buffer.ByteBuf;
import net.journey.util.ChatUtils;
import net.journey.util.LootHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EntityBossCrystal extends Entity implements IEntityAdditionalSpawnData {
    private final NonNullList<ItemStack> storedItems = NonNullList.create();
    private int color;

    public EntityBossCrystal(World worldIn) {
        super(worldIn);

        setSize(width, 3);
    }

    public static EntityBossCrystal createFilledWith(World world, Vec3d pos, int argb, List<ItemStack> items) {
        EntityBossCrystal crystal = new EntityBossCrystal(world);
        crystal.storedItems.addAll(items);
        crystal.setPosition(pos.x, pos.y, pos.z);

        crystal.color = argb;

        return crystal;
    }

    public static EntityBossCrystal createFilledWith(WorldServer world, Vec3d pos, int argb, @Nullable EntityPlayerMP player, ResourceLocation lootTable, long lootTableSeed) {
        return createFilledWith(world, pos, argb, genItemList(world, player, lootTable, lootTableSeed));
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        ItemStackHelper.loadAllItems(compound, storedItems);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        ItemStackHelper.saveAllItems(compound, storedItems);
    }

    @Override //fixes issue with other entities' rendering while this is transparent
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!this.world.isRemote) {
            for (Iterator<ItemStack> iterator = storedItems.iterator(); iterator.hasNext(); ) {
                ItemStack itemStack = iterator.next();
                if (player.addItemStackToInventory(itemStack)) {
                    iterator.remove();
                } else {
                    ChatUtils.sendColored(player, TextFormatting.RED, new TextComponentTranslation("msg.journey.boss_crystal.not_enough_space"));
                    break;
                }
            }

            if (storedItems.isEmpty()) {
                setDead();
            }
        }

        return true;
    }

    public static List<ItemStack> genItemList(WorldServer world, @Nullable EntityPlayerMP player, ResourceLocation lootTable, long lootTableSeed) {
        Random random;
        if (lootTableSeed == 0L) {
            random = new Random();
        } else {
            random = new Random(lootTableSeed);
        }

        return LootHelper.genFromLootTable(lootTable, world, random, builder -> {
            if (player != null) {
                builder.withPlayer(player).withLuck(player.getLuck());
            }
        });
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(color);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        color = buffer.readInt();
    }

    public int getColor() {
        return color;
    }
}
