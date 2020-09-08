package net.journey.dimension.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public abstract class JStructureComponent extends StructureComponent {
	public JStructureComponent() {
	}

	protected JStructureComponent(int type) {
		this.componentType = type;
	}

	@Override // needed to be called from JStructureComponent
	protected void setBlockState(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn) {
		super.setBlockState(worldIn, blockstateIn, x, y, z, boundingboxIn);
	}
}
