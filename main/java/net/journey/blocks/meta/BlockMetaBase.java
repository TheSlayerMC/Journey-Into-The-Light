package net.journey.blocks.meta;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.IHasModel;
import net.journey.util.EnumTypeHandler;
import net.journey.util.IMetaName;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockMetaBase extends Block implements IMetaName, IHasModel {

	public static final PropertyEnum<EnumTypeHandler.EnumType> VARIANT = PropertyEnum.<EnumTypeHandler.EnumType>create(
			"variant", EnumTypeHandler.EnumType.class, new Predicate<EnumTypeHandler.EnumType>() {
				public boolean apply(@Nullable EnumTypeHandler.EnumType apply) {
					return apply.getMeta() < 2;
				}
			});

	private String name;
	
	public BlockMetaBase(String name, Material m) {
		super(m);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		this.name = name;
		
		JourneyBlocks.blocks.add(this);
		JourneyItems.items.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumTypeHandler.EnumType customblocksub$enumtype : EnumTypeHandler.EnumType.values()) {
			items.add(new ItemStack(this, 1, customblocksub$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumTypeHandler.EnumType.byMetadata((meta & 1) % 2));
		return state;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		((EnumTypeHandler.EnumType)state.getValue(VARIANT)).getMeta();
		return i;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				((EnumTypeHandler.EnumType) state.getValue(VARIANT)).getMeta());
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumTypeHandler.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumTypeHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels(ModelRegistryEvent e) {
		for (int i = 0; i < EnumTypeHandler.EnumType.values().length; i++) {
			JITL.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i,
					"block_" + EnumTypeHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
