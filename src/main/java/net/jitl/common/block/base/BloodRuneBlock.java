package net.jitl.common.block.base;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntityTypes;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BloodRuneBlock extends Block {

	public BloodRuneBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull ActionResultType use(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
		ItemStack inHandItem = player.getItemInHand(handIn);
		if (inHandItem.getItem() == JItems.POWDER_OF_ESSENCIA) {
			for (ItemEntity itementity : worldIn.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(pos.above()))) {
				Block rune = null;
				if (itementity.getItem().getItem() == Items.ROTTEN_FLESH) {
					rune = JBlocks.BLOOD_RUNE_FLESH;
				}
				if (itementity.getItem().getItem() == Items.TORCH) { //TODO: replace this with new item
					rune = JBlocks.BLOOD_RUNE_SOUL;
				}
				if (itementity.getItem().getItem() == Items.BONE) { //TODO: replace this with new item
					rune = JBlocks.BLOOD_RUNE_DEATH;
				}
				if (itementity.getItem().getItem() == Items.APPLE) { //TODO: replace this with new item
					rune = JBlocks.BLOOD_RUNE_LIFE;
				}

				if (rune != null) {
					EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntityTypes.ESSENCIA_BOLT_TYPE, worldIn);
					essenciaBoltEntity.setPos(pos.getX(), pos.above().getY(), pos.getZ());
					essenciaBoltEntity.setVisualOnly(true);

					worldIn.addFreshEntity(essenciaBoltEntity);
					worldIn.setBlock(pos, rune.defaultBlockState(), 1);
					itementity.remove();
					worldIn.playSound(null, pos, JSounds.RUNE_ACTIVATE.get(), SoundCategory.BLOCKS, 1.0F, player.getRandom().nextFloat() + 0.5F);
					if (worldIn.isClientSide) {
						for (int i = 0; i < 6; i++) {
							float posRandom0 = (float) player.getRandom().nextInt(2 + i) / 16;
							float posRandom1 = (float) player.getRandom().nextInt(2 + i) / 16;
							float posRandom2 = (float) player.getRandom().nextInt(2 + i) / 16;
							float posRandom3 = (float) player.getRandom().nextInt(2 + i) / 16;
							float speedRandom0 = (posRandom0 + 1) / 16;
							float speedRandom1 = (posRandom1 + 2) / 16;
							float speedRandom2 = (posRandom2 + 1) / 16;
							float speedRandom3 = (posRandom3 + 2) / 16;
							worldIn.addParticle(ParticleTypes.FLAME, (pos.getX() + posRandom0) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom0) + 0.5F, speedRandom0, 0.1D, -speedRandom3);
							worldIn.addParticle(ParticleTypes.FLAME, (pos.getX() - posRandom1) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom1) + 0.5F, -speedRandom1, 0.05D, speedRandom2);
							worldIn.addParticle(ParticleTypes.FLAME, (pos.getX() - posRandom2) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom2) + 0.5F, -speedRandom2, 0.1D, -speedRandom1);
							worldIn.addParticle(ParticleTypes.FLAME, (pos.getX() + posRandom3) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom3) + 0.5F, speedRandom3, 0.05D, speedRandom0);
						}
					}
					if (!player.isCreative()) {
						inHandItem.shrink(1);
					}
				} else {
					return ActionResultType.FAIL;
				}
			}
		}
		return ActionResultType.PASS;
	}
}
