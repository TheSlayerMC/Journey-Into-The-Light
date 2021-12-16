package net.jitl.common.entity.tasks;

import com.google.common.collect.ImmutableList;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.BrainUtil;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.monster.piglin.AdmireItemTask;
import net.minecraft.entity.monster.piglin.StartAdmiringItemTask;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FrozenTrollTasks {

    public static final Item BARTERING_ITEM = JItems.RIMESTONE;

    public static Brain<?> makeBrain(FrozenTrollEntity frozenTrollEntity, Brain<FrozenTrollEntity> brain) {
        initCoreActivity(brain);
        return brain;
    }

    protected static boolean isLovedItem(Item item) {
        return item == (JItems.RIMESTONE);
    }

    private static void initCoreActivity(Brain<FrozenTrollEntity> brain_) {
        brain_.addActivity(Activity.CORE, 0, ImmutableList.<Task<? super FrozenTrollEntity>>of(
                new LookTask(45, 90), new WalkToTargetTask(), new InteractWithDoorTask(),
                new StartAdmiringItemTask(),
                new AdmireItemTask(120),
                new GetAngryTask()));
    }

    protected static void stopHoldingOffHandItem(FrozenTrollEntity frozenTrollEntity, boolean boolean_) {
        ItemStack itemstack = frozenTrollEntity.getItemInHand(Hand.OFF_HAND);
        frozenTrollEntity.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
        boolean flag = itemstack.getItem() == BARTERING_ITEM;
        if (boolean_ && flag) {
            throwItems(frozenTrollEntity, getBarterResponseItems(frozenTrollEntity));
        } else if (!flag) {
            boolean flag1 = frozenTrollEntity.equipItemIfPossible(itemstack);
            if (!flag1) {
                putInInventory(frozenTrollEntity, itemstack);
            }
        }
    }

    private static void putInInventory(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        ItemStack itemstack = frozenTrollEntity.addToInventory(itemStack_);
        throwItemsTowardRandomPos(frozenTrollEntity, Collections.singletonList(itemstack));
    }

    private static void throwItems(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_) {
        Optional<PlayerEntity> optional = frozenTrollEntity.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
        if (optional.isPresent()) {
            throwItemsTowardPlayer(frozenTrollEntity, optional.get(), list_);
        } else {
            throwItemsTowardRandomPos(frozenTrollEntity, list_);
        }

    }

    private static void throwItemsTowardRandomPos(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_) {
        throwItemsTowardPos(frozenTrollEntity, list_, getRandomNearbyPos(frozenTrollEntity));
    }

    private static void throwItemsTowardPlayer(FrozenTrollEntity frozenTrollEntity, PlayerEntity playerEntity_, List<ItemStack> list_) {
        throwItemsTowardPos(frozenTrollEntity, list_, playerEntity_.position());
    }

    private static void throwItemsTowardPos(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_, Vector3d vector3d_) {
        if (!list_.isEmpty()) {
            frozenTrollEntity.swing(Hand.OFF_HAND);

            for (ItemStack itemstack : list_) {
                BrainUtil.throwItem(frozenTrollEntity, itemstack, vector3d_.add(0.0D, 1.0D, 0.0D));
            }
        }

    }

    private static List<ItemStack> getBarterResponseItems(FrozenTrollEntity frozenTrollEntity) {
        LootTable loottable = frozenTrollEntity.level.getServer().getLootTables().get(LootTables.PIGLIN_BARTERING);
        return loottable.getRandomItems((new LootContext.Builder((ServerWorld) frozenTrollEntity.level)).withParameter(LootParameters.THIS_ENTITY, frozenTrollEntity).withRandom(frozenTrollEntity.level.random).create(LootParameterSets.PIGLIN_BARTER));
    }

    protected static boolean wantsToPickup(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        Item item = itemStack_.getItem();
        if (isAdmiringDisabled(frozenTrollEntity) && frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET)) {
            return false;
        } else if (itemStack_.getItem() == BARTERING_ITEM) {
            return isNotHoldingLovedItemInOffHand(frozenTrollEntity);
        } else {
            boolean flag = frozenTrollEntity.canAddToInventory(itemStack_);
            if (item == Items.GOLD_NUGGET) {
                return flag;
            } else {
                return isNotHoldingLovedItemInOffHand(frozenTrollEntity) && flag;
            }
        }
    }

    public static ActionResultType mobInteract(FrozenTrollEntity frozenTrollEntity, PlayerEntity playerEntity_, Hand hand_) {
        ItemStack itemstack = playerEntity_.getItemInHand(hand_);
        if (canAdmire(frozenTrollEntity, itemstack)) {
            ItemStack itemstack1 = itemstack.split(1);
            holdInOffhand(frozenTrollEntity, itemstack1);
            admireGoldItem(frozenTrollEntity);
            stopWalking(frozenTrollEntity);
            return ActionResultType.CONSUME;
        } else {
            return ActionResultType.PASS;
        }
    }

    private static void stopWalking(FrozenTrollEntity frozenTrollEntity) {
        frozenTrollEntity.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        frozenTrollEntity.getNavigation().stop();
    }

    private static void admireGoldItem(LivingEntity livingEntity_) {
        livingEntity_.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, 120L);
    }

    private static void holdInOffhand(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        if (isHoldingItemInOffHand(frozenTrollEntity)) {
            frozenTrollEntity.spawnAtLocation(frozenTrollEntity.getItemInHand(Hand.OFF_HAND));
        }

        frozenTrollEntity.holdInOffHand(itemStack_);
    }

    public static boolean canAdmire(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        return !isAdmiringDisabled(frozenTrollEntity) && !isAdmiringItem(frozenTrollEntity) && itemStack_.getItem() == BARTERING_ITEM;
    }

    public static Optional<SoundEvent> getSoundForCurrentActivity(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().getActiveNonCoreActivity().map((activity1_) -> getSoundForActivity(frozenTrollEntity, activity1_));
    }

    private static SoundEvent getSoundForActivity(FrozenTrollEntity frozenTrollEntity, Activity activity_) {
        if (activity_ == Activity.ADMIRE_ITEM) {
            return SoundEvents.PIGLIN_ADMIRING_ITEM;
        } else if (seesPlayerHoldingLovedItem(frozenTrollEntity)) {
            return SoundEvents.PIGLIN_JEALOUS;
        } else {
            return JSounds.PIERCER.get();
        }
    }

    public static void updateActivity(FrozenTrollEntity frozenTrollEntity) {
        Brain<FrozenTrollEntity> brain = frozenTrollEntity.getBrain();
        Activity activity = brain.getActiveNonCoreActivity().orElse(null);
        brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.ADMIRE_ITEM, Activity.FIGHT, Activity.AVOID, Activity.CELEBRATE, Activity.RIDE, Activity.IDLE));
        Activity activity1 = brain.getActiveNonCoreActivity().orElse(null);
        if (activity != activity1) {
            getSoundForCurrentActivity(frozenTrollEntity).ifPresent(frozenTrollEntity::playSound);
        }

        frozenTrollEntity.setAggressive(brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET));
    }

    private static boolean isNotHoldingLovedItemInOffHand(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getOffhandItem().isEmpty() || !isLovedItem(frozenTrollEntity.getOffhandItem().getItem());
    }

    private static boolean isAdmiringItem(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM);
    }

    private static boolean isBarterCurrency(Item item_) {
        return item_ == BARTERING_ITEM;
    }

    private static boolean isHoldingItemInOffHand(FrozenTrollEntity frozenTrollEntity) {
        return !frozenTrollEntity.getOffhandItem().isEmpty();
    }

    private static boolean isAdmiringDisabled(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_DISABLED);
    }

    private static boolean seesPlayerHoldingLovedItem(LivingEntity livingEntity_) {
        return livingEntity_.getBrain().hasMemoryValue(MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM);
    }

    public static boolean isPlayerHoldingLovedItem(LivingEntity livingEntity_) {
        return livingEntity_.getType() == EntityType.PLAYER && livingEntity_.isHolding(FrozenTrollTasks::isLovedItem);
    }

    private static Vector3d getRandomNearbyPos(FrozenTrollEntity frozenTrollEntity) {
        Vector3d vector3d = RandomPositionGenerator.getLandPos(frozenTrollEntity, 4, 2);
        return vector3d == null ? frozenTrollEntity.position() : vector3d;
    }
}
