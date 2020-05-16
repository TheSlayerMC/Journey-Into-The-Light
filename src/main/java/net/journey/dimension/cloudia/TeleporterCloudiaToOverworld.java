package net.journey.dimension.cloudia;

import net.journey.blocks.portal.BlockSenterianPortalFrame;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.NbtUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterCloudiaToOverworld extends Teleporter {

    private final Block portal = JourneyBlocks.cloudiaPortal;
    private final IBlockState frame = JourneyBlocks.cloudiaPortalFrame.getDefaultState();
    protected WorldServer myWorld;

    public TeleporterCloudiaToOverworld(WorldServer var1) {
        super(var1);
        this.myWorld = var1;
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float f) {
        double posX, posZ, posY;

        BlockPos correctPosition = readOrDefault(entity);
        posX = correctPosition.getX();
        posY = correctPosition.getY();
        posZ = correctPosition.getZ();


        short searchRange = 200;
        double var10 = -1.0D;
        int var12 = 0;
        int var13 = 0;
        int var14 = 0;
        int entityPosX_floored = MathHelper.floor(posX);
        int entityPosY = MathHelper.floor(posZ);
        double var24;

        for (int searchX = entityPosX_floored - searchRange; searchX <= entityPosX_floored + searchRange; ++searchX) {
            double var18 = searchX + 0.5D - posX;

            for (int searchZ = entityPosY - searchRange; searchZ <= entityPosY + searchRange; ++searchZ) {
                double var21 = searchZ + 0.5D - posZ;

                for (int searchY = 128 - 1; searchY >= 0; --searchY) {
                    if (this.isBlockPortal(this.myWorld, searchX, searchY, searchZ)) {
                        while (this.isBlockPortal(this.myWorld, searchX, searchY - 1, searchZ)) {
                            --searchY;
                        }

                        var24 = searchY + 0.5D - posY;
                        double var26 = var18 * var18 + var24 * var24 + var21 * var21;

                        if (var10 < 0.0D || var26 < var10) {
                            var10 = var26;
                            var12 = searchX;
                            var13 = searchY;
                            var14 = searchZ;
                        }
                    }
                }
            }
        }

        if (var10 >= 0.0D) {
            double var28 = var12 + 0.5D;
            double var22 = var13 + 0.5D;
            var24 = var14 + 0.5D;
            if (this.isBlockPortal(this.myWorld, var12 - 1, var13, var14)) var28 -= 0.5D;
            if (this.isBlockPortal(this.myWorld, var12 + 1, var13, var14)) var28 += 0.5D;
            if (this.isBlockPortal(this.myWorld, var12, var13, var14 - 1)) var24 -= 0.5D;
            if (this.isBlockPortal(this.myWorld, var12, var13, var14 + 1)) var24 += 0.5D;
            entity.setLocationAndAngles(var28, var22 + 1.0D, var24 + 1.0D, 180F, 0.0F);
            entity.motionX = entity.motionY = entity.motionZ = 0.0D;
            return true;
        } else
            return false;
    }


    public boolean isBlockPortal(World var1, int var2, int var3, int var4) {
        return var1.getBlockState(new BlockPos(var2, var3, var4)) == JourneyBlocks.cloudiaPortal.getDefaultState();
    }

    @Override
    public boolean makePortal(Entity p_85188_1_) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(p_85188_1_.posX);
        int k = MathHelper.floor(p_85188_1_.posY);
        int l = MathHelper.floor(p_85188_1_.posZ);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = random.nextInt(4);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j2 = j - i; j2 <= j + i; ++j2) {
            double d1 = j2 + 0.5D - p_85188_1_.posX;

            for (int l2 = l - i; l2 <= l + i; ++l2) {
                double d2 = l2 + 0.5D - p_85188_1_.posZ;
                label142:

                for (int j3 = myWorld.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (myWorld.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2))) {
                        while (j3 > 0
                                && myWorld.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2))) {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;

                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int l4 = -1; l4 < 4; ++l4) {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutableblockpos.setPos(i5, j5, k5);

                                        if (l4 < 0
                                                && !myWorld.getBlockState(blockpos$mutableblockpos)
                                                .getMaterial().isSolid()
                                                || l4 >= 0
                                                && !myWorld.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label142;
                                        }
                                    }
                                }
                            }

                            double d5 = j3 + 0.5D - p_85188_1_.posY;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;

                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int l5 = j - i; l5 <= j + i; ++l5) {
                double d3 = l5 + 0.5D - p_85188_1_.posX;

                for (int j6 = l - i; j6 <= l + i; ++j6) {
                    double d4 = j6 + 0.5D - p_85188_1_.posZ;
                    label562:

                    for (int i7 = myWorld.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (myWorld.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6))) {
                            while (i7 > 0 && myWorld
                                    .isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for (int k7 = i2; k7 < i2 + 2; ++k7) {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10) {
                                    for (int j11 = -1; j11 < 4; ++j11) {
                                        int j12 = l5 + (j10 - 1) * j8;
                                        int i13 = i7 + j11;
                                        int j13 = j6 + (j10 - 1) * j9;
                                        blockpos$mutableblockpos.setPos(j12, i13, j13);

                                        if (j11 < 0
                                                && !myWorld.getBlockState(blockpos$mutableblockpos)
                                                .getMaterial().isSolid()
                                                || j11 >= 0
                                                && !myWorld.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label562;
                                        }
                                    }
                                }

                                double d6 = i7 + 0.5D - p_85188_1_.posY;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;

                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = k7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;

        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            j1 = MathHelper.clamp(j1, 70, myWorld.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int l7 = 1; l7 < 3; ++l7) {
                    for (int k8 = -1; k8 < 3; ++k8) {
                        int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
                        int k10 = k2 + k8;
                        int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        myWorld.setBlockState(new BlockPos(k9, k10, k11),
                                flag ? frame : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        IBlockState iblockstate = portal.getDefaultState().withProperty(BlockPortal.AXIS,
                l6 != 0 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);

        for (int i8 = 0; i8 < 4; ++i8) {
            for (int l8 = 0; l8 < 4; ++l8) {
                for (int l9 = -1; l9 < 4; ++l9) {
                    int l10 = i6 + (l8 - 1) * l6;
                    int l11 = k2 + l9;
                    int k12 = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
                    myWorld.setBlockState(new BlockPos(l10, l11, k12), flag1 ? frame : iblockstate, 2);
                }
            }

            for (int i9 = 0; i9 < 4; ++i9) {
                for (int i10 = -1; i10 < 4; ++i10) {
                    int i11 = i6 + (i9 - 1) * l6;
                    int i12 = k2 + i10;
                    int l12 = k6 + (i9 - 1) * i3;
                    BlockPos blockpos = new BlockPos(i11, i12, l12);
                    myWorld.notifyNeighborsOfStateChange(blockpos,
                            myWorld.getBlockState(blockpos).getBlock(), false);

                }
            }
        }

        return true;
    }
    /**
     * Reading the tag position or current entity position
     *
     * @param e
     * @return
     */
    private BlockPos readOrDefault(Entity e) {
        // reading prev position
        NBTTagLong tag = NbtUtil.readTagFromEntity(e, "cloudia_position", NBTTagLong.class);
        if (tag != null) {
            return BlockPos.fromLong(tag.getLong());
        }

        return e.getPosition();
    }
}