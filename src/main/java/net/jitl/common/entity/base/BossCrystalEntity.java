package net.jitl.common.entity.base;

import net.jitl.JITL;
import net.jitl.init.JEntities;
import net.jitl.init.JSounds;
import net.jitl.util.LootHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BossCrystalEntity extends Entity implements IEntityAdditionalSpawnData, AnimatedObject<BossCrystalEntity> {
    private final NonNullList<ItemStack> storedItems = NonNullList.create();
    private Type type;
    private final AnimationSystem<BossCrystalEntity> animationSystem;

    public BossCrystalEntity(Level worldIn) {
        this(JEntities.BOSS_CRYSTAL_TYPE, worldIn);
    }

    public BossCrystalEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        animationSystem = AnimationSystemBuilder.forEntity(this, worldIn,
                builder -> {
                }, predefinedAnimations -> {
                });
    }

    @Override
    public @NotNull AnimationSystem<BossCrystalEntity> getSystem() {
        return animationSystem;
    }

    public static BossCrystalEntity create(Level world, Vec3 pos, Type type, List<ItemStack> items) {
        BossCrystalEntity crystal = new BossCrystalEntity(world);
        crystal.storedItems.addAll(items);
        crystal.setPos(pos.x, pos.y, pos.z);

        crystal.type = type;

        return crystal;
    }

    public static BossCrystalEntity create(ServerLevel world, Vec3 pos, Type type, @Nullable ServerPlayer player, ResourceLocation lootTable, long lootTableSeed) {
        return create(world, pos, type, genItemList(world, player, lootTable, lootTableSeed));
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compound) {
        ContainerHelper.loadAllItems(compound, storedItems);
        type = getTypeFromIndex(compound.getInt("type"));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, storedItems);
        compound.putInt("type", type.ordinal());
    }

    @Override
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand handIn) {
        JITL.LOGGER.info("interact");
        if (!level.isClientSide()) {
            for (Iterator<ItemStack> iterator = storedItems.iterator(); iterator.hasNext(); ) {
                ItemStack itemStack = iterator.next();
                if (player.addItem(itemStack)) {
                    iterator.remove();
                } else {
                    playSound(JSounds.GLUMP_DEATH.get(), 1.0F, 1.0F);
                    break;
                }
            }

            if (storedItems.isEmpty()) {
                playSound(JSounds.PIERCER.get(), 1.0F, 1.0F);

                remove(RemovalReason.DISCARDED);
            }
        }
        return InteractionResult.PASS;
    }

    public static List<ItemStack> genItemList(ServerLevel world, @Nullable ServerPlayer player, ResourceLocation lootTable, long lootTableSeed) {
        Random random;
        if (lootTableSeed == 0L) {
            random = new Random();
        } else {
            random = new Random(lootTableSeed);
        }

        return LootHelper.genFromLootTable(lootTable, world, random, builder -> {
            if (player != null) {
                builder.withLuck(player.getLuck());
            }
        });
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeInt(type.ordinal());
    }

    @Override
    public void readSpawnData(FriendlyByteBuf buffer) {
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
        COMMON(JITL.rl("textures/entity/base/crystal/common.png")),
        NETHER(JITL.rl("textures/entity/base/crystal/nether.png")),
        BOIL(JITL.rl("textures/entity/base/crystal/boil.png")),
        EUCA(JITL.rl("textures/entity/base/crystal/euca.png")),
        DEPTHS(JITL.rl("textures/entity/base/crystal/depths.png")),
        CORBA(JITL.rl("textures/entity/base/crystal/corba.png")),
        TERRANIA(JITL.rl("textures/entity/base/crystal/terrania.png")),
        CLOUDIA(JITL.rl("textures/entity/base/crystal/cloudia.png")),
        SENTERIAN(JITL.rl("textures/entity/base/crystal/senterian.png")),
        FROZEN(JITL.rl("textures/entity/base/crystal/frozen.png"));

        private final ResourceLocation crystalTexture;

        Type(ResourceLocation crystalTexture) {
            this.crystalTexture = crystalTexture;
        }

        public ResourceLocation getTexture() {
            return crystalTexture;
        }
    }
}
