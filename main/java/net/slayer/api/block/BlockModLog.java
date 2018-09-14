package net.slayer.api.block;

import net.minecraft.block.material.Material;

public class BlockModLog extends BlockMod {

	public BlockModLog(String name, String finalName, Material t) {
		super(name, finalName);
	}
	
	

/*public class BlockModLog extends BlockLog implements IMetaName, IHasModel{
	
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
	}*/
}
