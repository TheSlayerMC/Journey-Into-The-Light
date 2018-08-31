package net.journey.util.enums;

import net.journey.util.enums.EnumTypeHandler.EnumType;
import net.minecraft.util.IStringSerializable;

public class EnumTypeLogs {
	public static enum EnumType implements IStringSerializable {
		
		SIZZLER_LOG(4, "sizzlerlog"),
		EUCA_LOG(5, "eucalog"),
		DEPTHS_LOG(6, "depthslog"),
		FROZEN_LOG(7, "frozenlog"),
		CORBA_LOG(8, "corbalog"),
		BOILING_LOG(9, "boilinglog"),
		CLOUDIA_LOG(10, "cloudialog"),
		TERRANIAN_LOG(11, "terranianlog"),
		ICE_LOG(12, "icelog");

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