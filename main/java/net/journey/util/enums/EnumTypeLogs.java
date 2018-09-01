package net.journey.util.enums;

import net.journey.util.enums.EnumTypeHandler.EnumType;
import net.minecraft.util.IStringSerializable;

public class EnumTypeLogs {
	public static enum EnumType implements IStringSerializable {
		
		SIZZLER_LOG(0, "sizzlerlog"),
		EUCA_LOG(1, "eucalog"),
		DEPTHS_LOG(2, "depthslog"),
		FROZEN_LOG(3, "frozenlog"),
		CORBA_LOG(4, "corbalog"),
		BOILING_LOG(5, "boilinglog"),
		CLOUDIA_LOG(6, "cloudialog"),
		TERRANIAN_LOG(7, "terranianlog"),
		ICE_LOG(8, "icelog");

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name, unlocializedName;

		private EnumType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocializedName) {
			this.meta = meta;
			this.name = name;
			this.unlocializedName = unlocializedName;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getUnlocializedName() {
			return this.unlocializedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (EnumType enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}