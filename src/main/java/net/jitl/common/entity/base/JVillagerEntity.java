package net.jitl.common.entity.base;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.jitl.common.capability.dialog.DialogManager;
import net.jitl.common.dialog.Dialog;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Set;

public abstract class JVillagerEntity extends PathfinderMob implements Npc, Merchant, Enemy {
    private Player playerEntity;
    protected MerchantOffers offers;

    public JVillagerEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        playerEntity = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return playerEntity;
    }

    @Override
    public @NotNull MerchantOffers getOffers() {
        if (offers == null) {
            offers = new MerchantOffers();
            provideTrades();
        }
        return offers;
    }

    protected abstract Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades();

    @Nullable
    protected abstract Dialog getDialog(); //TODO: replace with page when dialogue is ported

    protected void provideTrades() {
        VillagerTrades.ItemListing[] trades = getVillagerTrades().get(1);
        if (trades != null) {
            MerchantOffers merchantOffers = getOffers();
            addTrades(merchantOffers, trades);
        }
    }

    protected void addTrades(MerchantOffers offers, VillagerTrades.ItemListing[] trades) {
        Set<Integer> set = Sets.newHashSet();
        for (int i = 0; i < trades.length; ++i) {
            set.add(i);
        }
        for (int int1 : set) {
            VillagerTrades.ItemListing villagerTrades = trades[int1];
            MerchantOffer merchantoffer = villagerTrades.getOffer(this, random);
            if (merchantoffer != null) {
                offers.add(merchantoffer);
            }
        }
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) {
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        if (offer.shouldRewardExp()) {
            int i = 3 + random.nextInt(4);
            double y = getY() + getBbHeight() / 2;
            level.addFreshEntity(new ExperienceOrb(level, getX(), y, getZ(), i));
        }
    }

    @Override
    public InteractionResult mobInteract(Player playerEntity, InteractionHand playerHand) {
        if (isAlive() && this.playerEntity == null) {
            if (getDialog() == null) {
                if (getOffers().isEmpty()) {
                    return InteractionResult.sidedSuccess(level.isClientSide());
                } else {
                    if (!level.isClientSide()) {
                        setTradingPlayer(playerEntity);
                        openTradingScreen(playerEntity, getDisplayName(), 1);
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            } else {
                if (!level.isClientSide()) {
                    DialogManager.of(((ServerPlayer) playerEntity)).startDialog(getDialog());
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        } else {
            return super.mobInteract(playerEntity, playerHand);
        }
    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack stack) {
    }

    @Override
    public @NotNull Level getLevel() {
        return level;
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xpIn) {
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean isClientSide() {
        return this.getLevel().isClientSide;
    }
}
