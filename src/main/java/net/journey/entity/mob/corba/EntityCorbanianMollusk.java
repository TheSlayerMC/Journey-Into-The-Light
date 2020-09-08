package net.journey.entity.mob.corba;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.JEntityPeacefulMob;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityCorbanianMollusk extends JEntityPeacefulMob implements AnimationProvider<EntityCorbanianMollusk> {

	private final ActionManager<EntityCorbanianMollusk> actionManager;

	private static final String LAYER_WALKING = "walking";

	public EntityCorbanianMollusk(World par1World) {
		super(par1World);
		addMeleeAttackingAI();
		setSize(2.0F, 2.0F);
		actionManager = ActionManagerBuilder.<EntityCorbanianMollusk>create(
				AnimationManagerBuilder.create()
						.addLayer(LAYER_WALKING, BlendType.ADDING, 1F)
						.addWalkingAnimationHandling(new AnimationStarter(JAnimations.CORBANIAN_MOLLUSK_WALK).setSpeed(1F), LAYER_WALKING)
		).build(this, world);
	}

	@Override
	public @NotNull ActionManager<EntityCorbanianMollusk> getActionManager() {
		return actionManager;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		EntityAttributesHelper.setMovementSpeed(this, MobStats.CORBANIAN_MOLLUSK_SPEED);
		EntityAttributesHelper.setMaxHealth(this, MobStats.CORBANIAN_MOLLUSK_HEALTH);
		this.applyKnowledge(EnumKnowledgeType.CORBA, 1);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.TERRA_SLUG;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.TERRA_SLUG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.TERRA_SLUG_DEATH;
	}

	protected ResourceLocation getLootTable() {
		return JourneyLootTables.CORBANIAN_MOLLUSK;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		int i;
		int j;
		int k;

		for (int l = 0; l < 4; ++l) {
			i = MathHelper.floor(this.posX + (l % 2 * 2 - 1) * 0.25F);
			j = MathHelper.floor(this.posY);
			k = MathHelper.floor(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);
			if (this.world.isAirBlock(new BlockPos(i, j, k)) && JourneyBlocks.slime.canPlaceBlockAt(this.world, new BlockPos(i, j, k)))
				this.world.setBlockState(new BlockPos(i, j, k), JourneyBlocks.slime.getDefaultState());
		}
	}
}