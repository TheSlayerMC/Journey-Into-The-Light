package net.journey.util.handler;

import net.minecraft.util.IStringSerializable;

public class EnumTypeHandler {
    public enum EnumType implements IStringSerializable {
        BRISON_STONE(0, "brisonstone"),
        DARK_BRISON_BRICK(1, "darkbrisonbrick"),
        RED_BRISON_BRICK(2, "redbrisonbrick"),
        SMALL_BRISON_BRICK(3, "smallbrisonbrick"),
        
        EUCA_DUNGEON_BRICK_0(4, "euca_dungeon_brick_0"),
        EUCA_DUNGEON_BRICK_1(5, "euca_dungeon_brick_1"),
        EUCA_DUNGEON_BRICK_2(6, "euca_dungeon_brick_2"),
        EUCA_DUNGEON_BRICK_3(7, "euca_dungeon_brick_3"),
        EUCA_DUNGEON_BRICK_4(8, "euca_dungeon_brick_4"),
        EUCA_DUNGEON_BRICK_5(9, "euca_dungeon_tile");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];

        static {
            for (EnumType enumtype : values()) {
                META_LOOKUP[enumtype.getMeta()] = enumtype;
            }
        }

        private final int meta;
        private final String name, unlocializedName;

        EnumType(int meta, String name) {
            this(meta, name, name);
        }

        EnumType(int meta, String name, String unlocializedName) {
            this.meta = meta;
            this.name = name;
            this.unlocializedName = unlocializedName;
        }

        public static EnumType byMetadata(int meta) {
            return META_LOOKUP[meta];
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
    }
}