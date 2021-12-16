package net.jitl.common.entity.frozen;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.JITL;
import net.jitl.client.dialogue.ClientDialogueNode;
import net.jitl.client.render.gui.dialogue.DialogueScreen;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.trades.CurrencyForItemsTrade;
import net.jitl.init.JItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EskimoEntity extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE, 1, Items.COMPASS, 1, 12, 5)
    }));

    public EskimoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ITrade[]> getVillagerTrades() {
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

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}
