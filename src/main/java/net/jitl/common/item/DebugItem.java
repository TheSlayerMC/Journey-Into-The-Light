package net.jitl.common.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.overlay.internal.KnowledgeToast;
import net.jitl.common.capability.dialog.DialogManager;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.core.init.JDialogs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class
DebugItem extends Item {
    public DebugItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        //ConfiguredFeature<BlockStateFeatureConfig, ?> configuredfeature = JFeatures.TORRID_CRYSTAL.get().configured(new BlockStateFeatureConfig(JBlocks.TORRID_CRYSTAL.defaultBlockState()));

        Minecraft minecraft = Minecraft.getInstance();

        Font fontRenderer = minecraft.font;

        PoseStack poseStack = new PoseStack();

        if (!worldIn.isClientSide()) {
            DialogManager.of(((ServerPlayer) playerIn)).startDialog(JDialogs.test());
//            ItemStack scrollStack = new ItemStack(JItems.LORE_SCROLL);
//            LoreScrollItem.bindScrollEntry(scrollStack, ScrollEntries.TEST, EnumKnowledgeType.SENTERIAN, 50F);
//            playerIn.addItem(scrollStack);
//
//            if (worldIn instanceof ServerLevel serverLevel) {
//                var advancement = serverLevel.getServer().getServerResources().getAdvancements().getAdvancement(new ResourceLocation("minecraft:advancements/story/root"));
//                if (playerIn instanceof ServerPlayer serverPlayer) {
//                    if (advancement != null) {
//                        boolean isComplete = serverPlayer.getAdvancements().getOrStartProgress(advancement).isDone();
//                        if (isComplete) {
//                            JITL.LOGGER.info("Player has advancement");
//                        }
//                    }
//                }
//            }

           /* List<ItemStack> loot = new ArrayList<>();
            loot.add(new ItemStack(JItems.LUNIUM_POWDER, 5));
            loot.add(new ItemStack(Items.DIAMOND, 5));
            loot.add(new ItemStack(JItems.BLOOD, 5));
            BossCrystalEntity.create(worldIn, playerIn.position(), BossCrystalEntity.Type.CORBA, loot);
            return InteractionResultHolder.success(playerIn.getItemInHand(handIn));*/
        } else {
            if (playerIn.isCrouching()) {
                Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(EnumKnowledgeType.CORBA, true));
            } else {
                Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(EnumKnowledgeType.DEPTHS, false));
            }

        }
        return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
    }


    public boolean isInMainHand(Player player) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this;
    }
}
