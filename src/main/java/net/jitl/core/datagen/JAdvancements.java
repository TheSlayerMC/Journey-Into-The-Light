package net.jitl.core.datagen;

import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JItems;
import net.jitl.core.init.world.Dimensions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.function.Consumer;

public class JAdvancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        overworld(advancementConsumer);
        frozen(advancementConsumer);
    }

    private void overworld(Consumer<Advancement> advancementConsumer) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        JBlocks.EUCA_GOLD_GRASS_BLOCK,
                        new TranslatableComponent("advancement.jitl.overworld.root.title"),
                        new TranslatableComponent("advancement.jitl.overworld.root.desc"),
                        JITL.rl("textures/block/dungeon_bricks.png"),
                        FrameType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("tick", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY))
                .save(advancementConsumer, overworldID("root"));

        Advancement mage = Advancement.Builder.advancement()
                .display(
                        JItems.POWDER_OF_ESSENCIA,
                        new TranslatableComponent("advancement.jitl.overworld.mage.title"),
                        new TranslatableComponent("advancement.jitl.overworld.mage.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("mage_chat", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                        EntityPredicate.Composite.ANY,
                        ItemPredicate.Builder.item(),
                        EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity().of(JEntities.MAGE_TYPE).build())))
                .save(advancementConsumer, overworldID("mage_chat"));

    }

    private void frozen(Consumer<Advancement> advancementConsumer) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        JBlocks.FROZEN_PORTAL_FRAME,
                        new TranslatableComponent("advancement.jitl.frozen.root.title"),
                        new TranslatableComponent("advancement.jitl.frozen.root.desc"),
                        JITL.rl("textures/block/permafrost.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("teleport_frozen", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Dimensions.FROZEN_LANDS))
                .save(advancementConsumer, frozenID("root"));
        Advancement ride_capybara = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        JItems.REDCURRANT_BERRY,
                        new TranslatableComponent("advancement.jitl.frozen.ride_capybara.title"),
                        new TranslatableComponent("advancement.jitl.frozen.ride_capybara.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("ride_capybara", StartRidingTrigger.TriggerInstance.playerStartsRiding(EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity().of(JEntities.CAPYBARA_TYPE).build())))
                .save(advancementConsumer, frozenID("ride_capybara"));
        Advancement barter_troll = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        JItems.RIMESTONE,
                        new TranslatableComponent("advancement.jitl.frozen.barter_troll.title"),
                        new TranslatableComponent("advancement.jitl.frozen.barter_troll.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("barter_troll", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                        EntityPredicate.Composite.ANY,
                        ItemPredicate.Builder.item().of(JItems.RIMESTONE),
                        EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity().of(JEntities.FROZEN_TROLL_TYPE).build())))
                .save(advancementConsumer, frozenID("barter_troll"));
    }

    private String overworldID(String name) {
        return JITL.MODID + (":jitl/overworld/" + name);
    }

    private String frozenID(String name) {
        return JITL.MODID + (":jitl/frozen/" + name);
    }
}
