package net.slayer.api.block;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.meta.ItemBlockVarients;
import net.journey.client.IHasModel;
import net.journey.util.EnumTypeHandler;
import net.journey.util.IMetaName;
import net.journey.util.LangRegistry;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
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

public class BlockModLog extends BlockLog implements IMetaName, IHasModel{
	
	protected boolean isOpaque = true, isNormalCube = true;
	
	public static final PropertyEnum<EnumTypeHandler.EnumType> VARIANT = PropertyEnum.<EnumTypeHandler.EnumType>create("variant", EnumTypeHandler.EnumType.class, new Predicate<EnumTypeHandler.EnumType>()
	{
		public boolean apply(@Nullable EnumTypeHandler.EnumType apply)
		{
			return apply.getMeta() < 2;
		}
	});
	
	private String name;
	
	public BlockModLog(String name, String finalname, Material m) {
		super();
		LangRegistry.addBlock(name, finalname);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		this.name = name;
		
		JourneyBlocks.blocks.add(this);
		JourneyItems.items.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

	}

	public BlockModLog(String name, Material m, MapColor c) {
		super();
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumTypeHandler.EnumType customblockplanks$enumtype : EnumTypeHandler.EnumType.values()) {
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumTypeHandler.EnumType.byMetadata((meta & 1) % 2));
		switch(meta & 6) {
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		return state;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumTypeHandler.EnumType)state.getValue(VARIANT)).getMeta();
		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS)) {
		case X:
			i |= 2;
			break;
		case Y:
			i |= 4;
			break;
		case Z:
			i |= 6;
		}
		return i;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT,LOG_AXIS});
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumTypeHandler.EnumType)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumTypeHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumTypeHandler.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels(ModelRegistryEvent e) {
		for(int i = 0; i < EnumTypeHandler.EnumType.values().length; i++) {
			JITL.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "logs_" + EnumTypeHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
