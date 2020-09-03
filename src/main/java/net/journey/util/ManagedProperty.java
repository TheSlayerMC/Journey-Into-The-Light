package net.journey.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public abstract class ManagedProperty<T> {
	protected final net.minecraftforge.common.config.Property prop;
	protected final Configuration cfg;

	public ManagedProperty(Configuration cfg, net.minecraftforge.common.config.Property prop) {
		this.cfg = cfg;
		this.prop = prop;
	}

	public void set(T val) {
		setProp(val);
		cfg.save();
	}

	public abstract T get();

	protected abstract void setProp(T val);

	public net.minecraftforge.common.config.Property getProp() {
		return prop;
	}

	public static class BooleanManagedProperty extends ManagedProperty<Boolean> {
		public BooleanManagedProperty(Configuration cfg, Property prop) {
			super(cfg, prop);
		}

		@Override
		public Boolean get() {
			return getProp().getBoolean();
		}

		@Override
		protected void setProp(Boolean val) {
			getProp().set(val);
		}
	}
}