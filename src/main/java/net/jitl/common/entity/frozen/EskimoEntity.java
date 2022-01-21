package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.dialogue.ClientDialogueNode;
import net.jitl.client.render.gui.dialogue.DialogueScreen;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EskimoEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE, 1, Items.COMPASS, 1, 12, 5)
    }));

    public EskimoEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    @Nullable
    @Override
    protected Screen getDialogue() {
        List<String> options = new ArrayList<>();
        options.add("farts");
        options.add("nuts");
        options.add("butts");
        return new DialogueScreen(new ClientDialogueNode(JITL.rl("eskimo"), "poop", options));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}
