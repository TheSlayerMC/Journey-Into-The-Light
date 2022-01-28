package net.jitl.common.item.interactive;

import net.jitl.client.render.screen.LoreScrollEntryScreen;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.common.scroll.ScrollAPI;
import net.jitl.common.scroll.ScrollCategory;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.ChatUtils;
import ru.timeconqueror.timecore.api.util.Pair;

import javax.annotation.Nullable;
import java.util.Objects;

public class LoreScrollItem extends Item {

    public LoreScrollItem(Properties properties_) {
        super(properties_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack heldItem = playerIn.getItemInHand(handIn);
        CompoundTag tag = heldItem.getTag();
        if(tag == null) tag = new CompoundTag();

        if (worldIn.isClientSide) {
            ScrollEntry entry = getScrollEntry(heldItem);

            if (entry != null) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F));
                displayScrollGui(null, entry);
                if(!tag.getBoolean("openedBefore")) {
                    if(!tag.getString("knowledge").equals("")) {
                        Objects.requireNonNull(JPlayer.from(playerIn)).knowledge
                                .addXP(EnumKnowledgeType.getKnowledgeFromName(tag.getString("knowledge")), tag.getFloat("xp"));//FIXME test
                    }
                    tag.putBoolean("openedBefore", true);
                }
            } else {
                ChatUtils.sendInformativeError(JITL.MODID, playerIn, "Can't retrieve entry from provided itemstack.", Pair.of("Itemstack", heldItem), Pair.of("Tag Compound", heldItem.getTag()));
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldItem);
    }

    @OnlyIn(Dist.CLIENT)
    private static void displayScrollGui(ScrollCategory category, ScrollEntry entry) {
        Minecraft.getInstance().setScreen(new LoreScrollEntryScreen(category, entry));
    }

    /**
     * Writes scroll entry into provided itemstack.
     * If itemstack is not an ItemLoreScroll item, it will print the error and won't write nbt tag.
     */
    public static void bindScrollEntry(ItemStack stack, ScrollEntry entry, EnumKnowledgeType knowledge, float xp) {
        if (stack.getItem() instanceof LoreScrollItem) {
            CompoundTag tagCompound = stack.getTag();

            if (tagCompound == null) tagCompound = new CompoundTag();

            tagCompound.putString("entry", entry.getId());
            tagCompound.putString("knowledge", knowledge.getName());
            tagCompound.putFloat("xp", xp);
            tagCompound.putBoolean("openedBefore", false);

            stack.setTag(tagCompound);
        } else {
            JITL.LOGGER.error("Provided stack param is not of {}", LoreScrollItem.class, new IllegalArgumentException());
        }
    }

    /**
     * Returns scroll entry of provided itemstack.
     * If provided itemstack is not an ItemLoreScroll item, it will print an error and return null.
     * If provided itemstack doesn't have tag compound, or there is no 'entry' record in it, it will return null.
     *
     * @return scroll entry of provided itemstack
     */
    @Nullable
    public static ScrollEntry getScrollEntry(ItemStack stack) {
        if (stack.getItem() instanceof LoreScrollItem) {
            CompoundTag tagCompound = stack.getTag();
            if (tagCompound != null && tagCompound.contains("entry")) {
                String id = tagCompound.getString("entry");
                return ScrollAPI.getEntry(id);
            }
        } else {
            JITL.LOGGER.error("Provided stack param is not an {}", LoreScrollItem.class, new IllegalArgumentException());
        }

        return null;
    }
}
