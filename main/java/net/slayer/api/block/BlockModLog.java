package net.slayer.api.block;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.meta.ItemBlockVarients;
import net.journey.client.IHasModel;
import net.journey.util.IMetaName;
import net.journey.util.LangRegistry;
import net.journey.util.enums.EnumTypeLogs;
import net.journey.util.enums.EnumTypeLogs.EnumType;
import net.minecraft.block.Block;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

//public class BlockModLog extends BlockMod {

	//public BlockModLog(String name, String finalName, Material t) {
		//super(name, finalName);
	//}
	
	

public class BlockModLog extends BlockLog implements IMetaName, IHasModel{
	
	protected boolean isOpaque = true, isNormalCube = true;
	
	public static final PropertyEnum<EnumTypeLogs.EnumType> VARIANT = PropertyEnum.<EnumTypeLogs.EnumType>create("variant", EnumTypeLogs.EnumType.class, new Predicate<EnumTypeLogs.EnumType>()
	{
		public boolean apply(@Nullable EnumTypeLogs.EnumType apply)
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
		if(VARIANT.equals(EnumTypeLogs.EnumType.ICE_LOG)) {
			isOpaque = false;
			isNormalCube = false;
			setLightOpacity(3);
			this.setSoundType(SoundType.GLASS);
		}
		
		JourneyBlocks.blocks.add(this);
		JourneyItems.items.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

	}

	public BlockModLog(String name, Material m, MapColor c) {
		super();
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumTypeLogs.EnumType customblockplanks$enumtype : EnumTypeLogs.EnumType.values()) {
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumTypeLogs.EnumType.byMetadata((meta & 1) % 2));
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
		i = i | ((EnumTypeLogs.EnumType)state.getValue(VARIANT)).getMeta();
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
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumTypeLogs.EnumType)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumTypeLogs.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumTypeLogs.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels(ModelRegistryEvent e) {
		for(int i = 0; i < EnumTypeLogs.EnumType.values().length; i++) {
			JITL.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "logs_" + EnumTypeLogs.EnumType.values()[i].getName(), "inventory");
		}
	}
}
