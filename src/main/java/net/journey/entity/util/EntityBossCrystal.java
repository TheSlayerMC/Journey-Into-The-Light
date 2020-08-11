package net.journey.entity.util;

import io.netty.buffer.ByteBuf;
import net.journey.JITL;
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
    private Type type;

    public EntityBossCrystal(World worldIn) {
        super(worldIn);

        setSize(width, 3);
    }

    public static EntityBossCrystal create(World world, Vec3d pos, Type type, List<ItemStack> items) {
        EntityBossCrystal crystal = new EntityBossCrystal(world);
        crystal.storedItems.addAll(items);
        crystal.setPosition(pos.x, pos.y, pos.z);

        crystal.type = type;

        return crystal;
    }

    public static EntityBossCrystal create(WorldServer world, Vec3d pos, Type type, @Nullable EntityPlayerMP player, ResourceLocation lootTable, long lootTableSeed) {
        return create(world, pos, type, genItemList(world, player, lootTable, lootTableSeed));
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
        type = getTypeFromIndex(compound.getInteger("type"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        ItemStackHelper.saveAllItems(compound, storedItems);
        compound.setInteger("type", type.ordinal());
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
        buffer.writeInt(type.ordinal());
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        type = getTypeFromIndex(buffer.readInt());
    }

    public ResourceLocation getTexture() {
        return type.getTexture();
    }

    private Type getTypeFromIndex(int index) {
        Type[] types = Type.values();
        return types[index % types.length];
    }

    public enum Type {
        COMMON(JITL.rl("textures/entity/util/crystal/common.png")),
        NETHER(JITL.rl("textures/entity/util/crystal/nether.png")),
        BOIL(JITL.rl("textures/entity/util/crystal/boil.png")),
        EUCA(JITL.rl("textures/entity/util/crystal/euca.png")),
        DEPTHS(JITL.rl("textures/entity/util/crystal/depths.png")),
        CORBA(JITL.rl("textures/entity/util/crystal/corba.png")),
        TERRANIA(JITL.rl("textures/entity/util/crystal/terrania.png")),
        CLOUDIA(JITL.rl("textures/entity/util/crystal/cloudia.png")),
        SENTERIAN(JITL.rl("textures/entity/util/crystal/senterian.png")),
        FROZEN(JITL.rl("textures/entity/util/crystal/frozen.png"));

        private final ResourceLocation crystalTexture;

        Type(ResourceLocation crystalTexture) {
            this.crystalTexture = crystalTexture;
        }

        public ResourceLocation getTexture() {
            return crystalTexture;
        }
    }
}
