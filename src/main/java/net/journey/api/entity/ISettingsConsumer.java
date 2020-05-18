package net.journey.api.entity;

import net.journey.entity.MobStats;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public interface ISettingsConsumer {
	/**
	 * Should return entity settings for provided entity type.
	 * EntitySettings is an entity builder, where you can specify its attributes.
	 * <p>
	 * Entity settings should be a single instance per entity type!
	 * So you should store it, for example, as a static field in Entity class.
	 */
	@NotNull
	EntitySettings getEntitySettings();

	/**
	 * Entity settings should be a single instance per entity type!
	 * So you should store it, for example, as a static field in Entity class.
	 */
	class EntitySettings {
		private HashMap<IAttribute, Double> attributes;

		public EntitySettings(double maxHealth, double attackDamage) {
			this.attributes = new HashMap<>();

			setAttribute(SharedMonsterAttributes.FOLLOW_RANGE, MobStats.standardFollowRange);
			setAttribute(SharedMonsterAttributes.MOVEMENT_SPEED, MobStats.standardMovementSpeed);
			setAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, MobStats.standardKnockBackResistance);
			setAttribute(SharedMonsterAttributes.MAX_HEALTH, maxHealth);
			setAttribute(SharedMonsterAttributes.ATTACK_DAMAGE, attackDamage);
		}

		public EntitySettings setFollowRange(double followRange) {
			return setAttribute(SharedMonsterAttributes.FOLLOW_RANGE, followRange);
		}

		public EntitySettings setMovementSpeed(double movementSpeed) {
			return setAttribute(SharedMonsterAttributes.MOVEMENT_SPEED, movementSpeed);
		}

		public EntitySettings setKnockbackResistance(double knockbackResistance) {
			return setAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, knockbackResistance);
		}

		/**
		 * Sets the value in provided entity attribute.
		 * If attribute doesn't exist in entity, then it will be created.
		 */
		public EntitySettings setAttribute(IAttribute attribute, double value) {
			attributes.put(attribute, value);
			return this;
		}

		public HashMap<IAttribute, Double> getAttributes() {
			return attributes;
		}
	}
}
