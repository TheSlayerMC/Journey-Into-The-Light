package net.journey.items.ranged;

import net.journey.entity.projectile.EntityDamagingProjectile;

public class ItemWand extends ItemStaff {

	public ItemWand(boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityDamagingProjectile> projectile) {
		super(magic, uses, dam, unbreakable, projectile);
	}
}