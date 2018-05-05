package net.journey.items;

import net.journey.entity.projectile.EntityBasicProjectile;

public class ItemWand extends ItemStaff {

	public ItemWand(String name, String f, boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f, essence, magic, uses, dam, unbreakable, projectile);
	}
}