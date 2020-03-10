package net.journey.blocks.portal;

import java.util.Random;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.render.particles.EntityBoilPotalFX;
import net.journey.dimension.ModTeleporter;
import net.journey.util.Config;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class BlockBoilPortal extends BlockModPortal {
	
	public BlockBoilPortal(String name) {
		super(name, "Boiling Portal");
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
			WorldServer worldserver = thePlayer.mcServer.getWorld(thePlayer.dimension);
			//thePlayer.triggerAchievement(JourneyAchievements.achievementBoil);
			int dimensionID = Config.boil;
			Block blockFrame = JourneyBlocks.boilPortalFrame;
			if(thePlayer.timeUntilPortal > 0) 
				thePlayer.timeUntilPortal = 10;
			else if(thePlayer.dimension != dimensionID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, dimensionID, new ModTeleporter(thePlayer.mcServer.getWorld(dimensionID), dimensionID, this, blockFrame, AXIS));
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0, new ModTeleporter(thePlayer.mcServer.getWorld(0), 0, this, blockFrame, AXIS));
			}
		}
	}
	
	public static int getMetaForAxis(EnumFacing.Axis axis) {
        if(axis == EnumFacing.Axis.X) {
            return 1;
        } else {
            return axis == EnumFacing.Axis.Z ? 2 : 0;
        }
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if(rand.nextInt(100) == 0)
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);

		for(int i = 0; i < 4; ++i) {
			double d0 = (float)pos.getX() + rand.nextFloat();
			double d1 = (float)pos.getY() + rand.nextFloat();
			double d2 = (float)pos.getZ() + rand.nextFloat();
			double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if(worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * j;
				d3 = rand.nextFloat() * 2.0F * j;
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * j;
				d5 = rand.nextFloat() * 2.0F * j;
			}
			EntityBoilPotalFX var20 = new EntityBoilPotalFX(worldIn, d0, d1, d2, d3, d4, d5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	@Override
	public boolean makePortal(World worldIn, BlockPos p) {
		EntityLightningBolt bolt = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
		BlockBoilPortal.Size size = new BlockBoilPortal.Size(worldIn, p, EnumFacing.Axis.X);
		if(size.isValid() && size.portalBlockCount == 0) {
			size.placePortalBlocks();
			worldIn.addWeatherEffect(bolt);
			worldIn.createExplosion(bolt, p.getX(), p.getY(), p.getZ(), 0.0F, true);
			return true;
		} else {
			EntityLightningBolt bolt1 = new EntityLightningBolt(worldIn, p.getX(), p.getY(), p.getZ(), false);
			BlockBoilPortal.Size size1 = new BlockBoilPortal.Size(worldIn, p, EnumFacing.Axis.Z);
			if(size1.isValid() && size1.portalBlockCount == 0) {
				size1.placePortalBlocks();
				worldIn.addWeatherEffect(bolt1);
				worldIn.createExplosion(bolt1, p.getX(), p.getY(), p.getZ(), 0.0F, true);
				return true;
			} else {
				return false;
			}
		}
	}

	public static class Size {
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos pos, EnumFacing.Axis axis) {
			this.world = worldIn;
			this.axis = axis;

			if(axis == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for(BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(pos.down()).getBlock()); pos = pos.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;

			if(i >= 0) {
				this.bottomLeft = pos.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

				if(this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if(this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos pos, EnumFacing facing) {
			int i;

			for(i = 0; i < 22; ++i) {
				BlockPos blockpos = pos.offset(facing, i);

				if(!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()).getBlock() != JourneyBlocks.boilPortalFrame) {
					break;
				}
			}

			Block block = this.world.getBlockState(pos.offset(facing, i)).getBlock();
			return block == JourneyBlocks.boilPortalFrame ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56:

				for(this.height = 0; this.height < 21; ++this.height) {
					for(int i = 0; i < this.width; ++i) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						Block block = this.world.getBlockState(blockpos).getBlock();

						if(!this.isEmptyBlock(block)) {
							break label56;
						}

						if(block == JourneyBlocks.boilPortal) {
							++this.portalBlockCount;
						}

						if(i == 0) {
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();

							if(block != JourneyBlocks.boilPortalFrame) {
								break label56;
							}
						}
						else if(i == this.width - 1) {
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();

							if(block != JourneyBlocks.boilPortalFrame) {
								break label56;
							}
						}
					}
				}

		for(int j = 0; j < this.width; ++j) {
			if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != JourneyBlocks.boilPortalFrame) {
				this.height = 0;
				break;
			}
		}

		if (this.height <= 21 && this.height >= 3) {
			return this.height;
		} else {
			this.bottomLeft = null;
			this.width = 0;
			this.height = 0;
			return 0;
		}
		}

		protected boolean isEmptyBlock(Block blockIn) {
			return blockIn.getMaterial(blockIn.getDefaultState()) == Material.AIR || blockIn == JourneyBlocks.fire || blockIn == JourneyBlocks.boilPortal;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks() {
			for(int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for(int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), JourneyBlocks.boilPortal.getDefaultState().withProperty(BlockBoilPortal.AXIS, this.axis), 2);
				}
			}
		}
	}
}