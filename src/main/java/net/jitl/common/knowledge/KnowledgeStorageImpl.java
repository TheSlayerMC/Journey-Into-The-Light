package net.jitl.common.knowledge;

import net.jitl.client.render.overlay.internal.KnowledgeToast;
import net.jitl.common.helper.EnumKnowledgeType;
import net.minecraft.client.Minecraft;
import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class KnowledgeStorageImpl extends PropertyContainer implements PlayerStats.KnowledgeStorage {

	private final CoffeeProperty<Float> amountOnLevel = prop("amount", 0F).synced();
	private final CoffeeProperty<Integer> levels = prop("levels", 0).synced();

	@Override
	public void add(float amount, EnumKnowledgeType type) {
		if (amountOnLevel.get() + amount >= getLevelCapacity(levels.get())) {
			amountOnLevel.set(amountOnLevel.get() + amount - getLevelCapacity(levels.get()));
			addLevel(1, type);
		} else {
			amountOnLevel.set(amountOnLevel.get() + amount);
		}
	}

	@Override
	public void addLevel(int amount, EnumKnowledgeType type) {
		levels.set(levels.get() + amount);
		Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(type, true));
	}

	@Override
	public float remove(float amount) {
		float total = getTotal();

		//nullifies stored knowledge if amount to remove is bigger than stored one
		if (amount > total) {
			float left = amount - total;

			levels.set(0);
			amountOnLevel.set(0F);

			return left;
		}

		if (amountOnLevel.get() - amount < 0) {
			amount -= amountOnLevel.get();

			while (amount > 0) {
				float levelCapacity = getLevelCapacity(levels.get());
				if (levelCapacity > amount) {
					levels.set(levels.get() - 1);
					amountOnLevel.set(levelCapacity - amount);
					return 0;
				} else {
					amount -= levelCapacity;
					levels.set(levels.get() - 1);
				}
			}

			throw new IllegalStateException("This shouldn't be achieved, because if all levels' at capacity is smaller than removed amount, it should be cut in the start of the method");
		} else {
			amountOnLevel.set(amountOnLevel.get() - amount);
			return 0;
		}
	}

	@Override
	public float getLevelCapacity(int level) {
		return level >= 5 ? 50 : level >= 10 ? 70 : level >= 15 ? 90 : level >= 20 ? 110 : level >= 30 ? 130 : level >= 40 ? 150 : 30; //May need balancing
	}

	@Override
	public float getAmountOnCurrentLevel() {
		return amountOnLevel.get();
	}

	@Override
	public int getLevelCount() {
		return levels.get();
	}

	@Override
	public float getTotal() {
		float amount = 0;

		for (int i = 0; i < getLevelCount(); i++) {
			amount += getLevelCapacity(getLevelCount());
		}

		return amount + getAmountOnCurrentLevel();
	}
}