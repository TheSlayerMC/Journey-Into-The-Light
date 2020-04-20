package net.journey.blocks.meta;

import net.journey.JITL;
import net.journey.client.api.IHasModel;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.api.IMetaName;
import net.journey.util.handler.EnumTypeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;

public class BlockMetaBase extends Block implements IMetaName, IHasModel {

    public static final PropertyEnum<EnumTypeHandler.EnumType> VARIANT = PropertyEnum.create("variant", EnumTypeHandler.EnumType.class);

    private String name;
    private String metaName;

    public BlockMetaBase(String name, Material m, String metaName) {
        super(m);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        this.name = name;
        this.metaName = metaName;

        JourneyBlocks.blocks.add(this);
        JourneyItems.items.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumTypeHandler.EnumType customblockplanks$enumtype : EnumTypeHandler.EnumType.values()) {
            items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumTypeHandler.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumTypeHandler.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void registerModels(ModelRegistryEvent e) {
        for (int i = 0; i < EnumTypeHandler.EnumType.values().length; i++) {
            JITL.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i,
                    metaName + "_" + EnumTypeHandler.EnumType.values()[i].getName(), "inventory");
        }
    }
}
