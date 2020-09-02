package net.journey.client.handler;

import net.journey.blocks.tileentity.TileEntityIncubator;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.container.ContainerIncubator;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.journey.client.render.gui.GuiIncubator;
import net.journey.client.render.gui.GuiSummoningTable;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.slayer.api.client.gui.MerchantGuis;
import net.slayer.api.entity.tileentity.container.JContainerMerchant;
import ru.timeconqueror.timecore.api.util.Wrapper;

import java.util.HashSet;
import java.util.Objects;

public class GuiHandler implements IGuiHandler {
    private static final HashSet<Identifier> IDS = new HashSet<>();

    // Merchants
    public static final Identifier MAGE;
    public static final Identifier BLACKSMITH;
    public static final Identifier FROZEN_MERCHANT;
    public static final Identifier ROCKITE;
    public static final Identifier STARING_GUARDIAN;
    public static final Identifier TORDO;
    public static final Identifier BOIL_TRADER;
    public static final Identifier ALLOY_MENDER;
    public static final Identifier STARLIGHT_VILLAGER;
    public static final Identifier STARLIGHT_BLACKSMITH;
    public static final Identifier TERRANIAN;
    public static final Identifier TERRANIAN_ENCHANTER;
    public static final Identifier OVERGROWN_MERCHANT;
    public static final Identifier ESCAPED;

    // Tile Entities
    public static final Identifier NETHER_FURNACE;
    public static final Identifier SUMMONING_TABLE;
    public static final Identifier INCUBATOR;

    static {
        Wrapper<Integer> id = new Wrapper<>(0);

        MAGE = createAndIncrease(id);
        BLACKSMITH = createAndIncrease(id);
        FROZEN_MERCHANT = createAndIncrease(id);
        ROCKITE = createAndIncrease(id);
        STARING_GUARDIAN = createAndIncrease(id);
        TORDO = createAndIncrease(id);
        BOIL_TRADER = createAndIncrease(id);
        ALLOY_MENDER = createAndIncrease(id);

        STARLIGHT_VILLAGER = createAndIncrease(id);
        STARLIGHT_BLACKSMITH = createAndIncrease(id);
        TERRANIAN = createAndIncrease(id);
        TERRANIAN_ENCHANTER = createAndIncrease(id);
        OVERGROWN_MERCHANT = createAndIncrease(id);
        ESCAPED = createAndIncrease(id);

        id.set(1000);

        NETHER_FURNACE = createAndIncrease(id);
        SUMMONING_TABLE = createAndIncrease(id);
        INCUBATOR = createAndIncrease(id);
    }

    @Override
    public Object getServerGuiElement(int numericId, EntityPlayer player, World world, int x, int y, int z) {
        Identifier id = new Identifier(numericId);

        if (!exists(id)) {
            return null;
        }

        if (isMerchant(id)) {
            return new JContainerMerchant(player.inventory, (IMerchant) world.getEntityByID(x), world);
        } else if (isTileEntity(id)) {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

            if (id.equals(SUMMONING_TABLE)) {
                return new ContainerSummoningTable(player.inventory, (TileEntitySummoningTable) te);
            } else if (id.equals(INCUBATOR)) {
                return new ContainerIncubator(player.inventory, (TileEntityIncubator) te);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int numericId, EntityPlayer player, World world, int x, int y, int z) {
        Identifier id = new Identifier(numericId);

        if (isMerchant(id)) {
            IMerchant merchant = (IMerchant) world.getEntityByID(x);
            JContainerMerchant containerMerchant = new JContainerMerchant(player.inventory, merchant, world);

            if (id.equals(MAGE))
                return MerchantGuis.MAGE.create(containerMerchant, merchant);
            else if (id.equals(BLACKSMITH))
                return MerchantGuis.BLACKSMITH.create(containerMerchant, merchant);
            else if (id.equals(FROZEN_MERCHANT))
                return MerchantGuis.FROZEN_MERCHANT.create(containerMerchant, merchant);
            else if (id.equals(STARING_GUARDIAN))
                return MerchantGuis.STARING_GUARDIAN.create(containerMerchant, merchant);
            else if (id.equals(TORDO))
                return MerchantGuis.TORDO.create(containerMerchant, merchant);
            else if (id.equals(BOIL_TRADER))
                return MerchantGuis.BOIL_TRADER.create(containerMerchant, merchant);
            else if (id.equals(ALLOY_MENDER))
                return MerchantGuis.ALLOY_MENDER.create(containerMerchant, merchant);
            else if (id.equals(STARLIGHT_VILLAGER))
                return MerchantGuis.STARLIGHT_VILLAGER.create(containerMerchant, merchant);
            else if (id.equals(STARLIGHT_BLACKSMITH))
                return MerchantGuis.STARLIGHT_BLACKSMITH.create(containerMerchant, merchant);
            else if (id.equals(TERRANIAN))
                return MerchantGuis.TERRANIAN_TRADER.create(containerMerchant, merchant);
            else if (id.equals(TERRANIAN_ENCHANTER))
                return MerchantGuis.TERRANIAN_TRADER.create(containerMerchant, merchant);
            else if (id.equals(OVERGROWN_MERCHANT))
                return MerchantGuis.OVERGROWN_MERCHANT.create(containerMerchant, merchant);
            else if (id.equals(ESCAPED))
                return MerchantGuis.ESCAPED.create(containerMerchant, merchant);
            else if (id.equals(ROCKITE))
                return MerchantGuis.ROCKITE_GOLEM.create(containerMerchant, merchant);
        } else if (isTileEntity(id)) {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

            if (id.equals(SUMMONING_TABLE)) {
                return new GuiSummoningTable(player.inventory, (TileEntitySummoningTable) te, world);
            } else if (id.equals(INCUBATOR)) {
                return new GuiIncubator(player.inventory, (TileEntityIncubator) te);
            }
        }

        return null;
    }

    private static Identifier createAndIncrease(Wrapper<Integer> counter) {
        Identifier id = new Identifier(counter.get());
        counter.set(counter.get() + 1);

        if (!IDS.add(id)) {
            throw new IllegalArgumentException("ID " + id + " already exists.");
        }

        return id;
    }

    private static boolean isMerchant(Identifier id) {
        return id.get() < 1000;
    }

    private static boolean isTileEntity(Identifier id) {
        return id.get() > 1000;
    }

    private static boolean exists(Identifier id) {
        return IDS.contains(id);
    }

    public static class Identifier {
        private final int id;

        public Identifier(int id) {
            this.id = id;
        }

        public int get() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Identifier)) return false;
            Identifier id1 = (Identifier) o;
            return id == id1.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}