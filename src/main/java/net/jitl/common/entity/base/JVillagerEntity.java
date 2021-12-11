package net.jitl.common.entity.base;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Set;

public abstract class JVillagerEntity extends CreatureEntity implements INPC, IMerchant, IMob {
    private PlayerEntity playerEntity;
    protected MerchantOffers offers;

    public JVillagerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void setTradingPlayer(@Nullable PlayerEntity player) {
        playerEntity = player;
    }

    @Nullable
    @Override
    public PlayerEntity getTradingPlayer() {
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

    protected abstract Int2ObjectMap<VillagerTrades.ITrade[]> getVillagerTrades();

    protected void provideTrades() {
        VillagerTrades.ITrade[] trades = getVillagerTrades().get(1);
        if (trades != null) {
            MerchantOffers merchantOffers = getOffers();
            addTrades(merchantOffers, trades);
        }
    }

    protected void addTrades(MerchantOffers offers, VillagerTrades.ITrade[] trades) {
        Set<Integer> set = Sets.newHashSet();
        for (int i = 0; i < trades.length; ++i) {
            set.add(i);
        }
        for (int int1 : set) {
            VillagerTrades.ITrade villagerTrades = trades[int1];
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
            level.addFreshEntity(new ExperienceOrbEntity(level, getX(), y, getZ(), i));
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity playerEntity, Hand playerHand) {
        if (isAlive() && this.playerEntity == null) {
            if (getOffers().isEmpty()) {
                return ActionResultType.sidedSuccess(level.isClientSide());
            } else {
                if (!level.isClientSide()) {
                    setTradingPlayer(playerEntity);
                    openTradingScreen(playerEntity, getDisplayName(), 1);
                }
                return ActionResultType.sidedSuccess(level.isClientSide());
            }
        } else {
            return super.mobInteract(playerEntity, playerHand);
        }
    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack stack) {
    }

    @Override
    public @NotNull World getLevel() {
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

}
