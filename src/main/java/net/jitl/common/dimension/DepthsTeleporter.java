package net.jitl.common.dimension;

import net.jitl.common.block.portal.DepthsPortalBlock;
import net.jitl.common.block.portal.DepthsPortalFrameBlock;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import java.util.Random;
import java.util.function.Function;

public class DepthsTeleporter implements ITeleporter {

    protected final ServerWorld level;
    private final DepthsPortalBlock portal_block;
    private final Block portal_frame;
    private static final int HARDCODED_DEPTHS_HEIGHT_VALUE = 30;
    private static final int HARDCODED_WORLD_HEIGHT_LIMIT = 256;
    public static final int PORTAL_SEARCH_CHUNK_RADIUS = 4;

    public DepthsTeleporter(ServerWorld worldIn, DepthsPortalBlock portal, Block frame) {
        this.level = worldIn;
        this.portal_block = portal;
        this.portal_frame = frame;
    }

    public int getTopBlockOverworld(int x, int z) {
        for (int i = HARDCODED_WORLD_HEIGHT_LIMIT; i > 0; i--) {
            if(this.level.getBlockState(new BlockPos(x, i, z)) != Blocks.AIR.defaultBlockState()) {
                System.out.println("SET HEIGHT:" + i);
                return i + 1;
            }
        }
        return 0;
    }

    public int getTopBlock(int x, int z) {
        for(int i = HARDCODED_DEPTHS_HEIGHT_VALUE; i > 0; i--) {
            if(this.level.getBlockState(new BlockPos(x, i, z)) != Blocks.AIR.defaultBlockState()) {
                return i + 1;
            }
        }
        return 0;
    }

    public void placeInPortal(Entity entity, float rotationYaw) {
        Random random = new Random();
        int chunkX;
        int chunkZ;
        if(this.level.dimension().equals(Dimensions.DEPTHS)) {
            chunkX = (MathHelper.floor(entity.getX()) & ~0xf) / 16;
            chunkZ = (MathHelper.floor(entity.getZ()) & ~0xf) / 16;
            if (!(this.placeInExistingPortal(entity, rotationYaw))) {
                this.makePortal(new BlockPos(chunkX, getTopBlock(chunkX, chunkZ), chunkZ));
                this.placeInExistingPortal(entity, rotationYaw);
            }
        }
    }

    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        if (this.level.dimension().equals(Dimensions.DEPTHS)) {

            int chunkX = entity.level.getChunk(entity.blockPosition()).getPos().x;
            int chunkZ = entity.level.getChunk(entity.blockPosition()).getPos().z;

            int portalLocationX = (chunkX * 16) + 6 + 8;
            int portalLocationZ = (chunkZ * 16) + 5 + 8;
            int portalLocationY = getTopBlockOverworld(portalLocationX, portalLocationZ);

            if (this.level.getBlockState(new BlockPos(portalLocationX, portalLocationY, portalLocationZ)).getBlock() == portal_frame) {
                entity.moveTo(portalLocationX + 1.5D, portalLocationY, portalLocationZ + 2.5D, rotationYaw, 0.0F);
                entity.xo = entity.yo = entity.zo = 0.0D;
                return true;
            } else {
                BlockPos.Mutable searchPos = new BlockPos.Mutable();
                for(int searchX = portalLocationX - (PORTAL_SEARCH_CHUNK_RADIUS * 16); searchX < portalLocationX + (PORTAL_SEARCH_CHUNK_RADIUS * 16); searchX += 16) {
                    for (int searchZ = portalLocationZ - (PORTAL_SEARCH_CHUNK_RADIUS * 16); searchZ < portalLocationZ + (PORTAL_SEARCH_CHUNK_RADIUS * 16); searchZ += 16) {
                        searchPos.set(searchX, portalLocationY, searchZ);
                        if (this.level.getBlockState(searchPos).getBlock() == portal_frame) {
                            entity.moveTo(searchX + 1.5D, portalLocationY, searchZ + 2.5D, rotationYaw, 0.0F);
                            entity.xo = entity.yo = entity.zo = 0.0D;
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        return false;
    }

    public void makePortal(BlockPos pos) {
        int x = pos.getX() * 16;
        int y = pos.getY();
        int z = pos.getZ() * 16;
        this.level.setBlock(new BlockPos(x, y, z), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x, y, z + 1), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x, y, z + 2), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 3), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 3), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 3), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z + 1), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z + 2), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z - 1), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z - 1), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z - 1), portal_frame.defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true), 0);

        this.level.setBlock(new BlockPos(x + 1, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 2), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 2), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 2), portal_block.defaultBlockState(), 0);
    }

    @Override
    public boolean playTeleportSound(ServerPlayerEntity player, ServerWorld sourceWorld, ServerWorld destWorld) {
        return true;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        if(entity instanceof ServerPlayerEntity) {
            this.placeInPortal(entity, yaw);
        } else {
            this.placeInExistingPortal(entity, yaw);
        }
        entity.setPortalCooldown();
        entity = repositionEntity.apply(false);
        if(destWorld != entity.level.getServer().getLevel(World.OVERWORLD)) {
            entity.teleportTo(entity.getX(), getTopBlock((int) entity.getX(), (int)entity.getZ()), entity.getZ());
            System.out.println("Placed OVERWORLD Player TO DEPTHS Y:" + entity.getY());
        } else {
            entity.teleportTo(entity.getX(), level.getHeight(Heightmap.Type.WORLD_SURFACE, (int)entity.getX(), (int)entity.getZ()), entity.getZ());
            System.out.println("Placed DEPTHS Player TO OVERWORLD Y:" + entity.getY());
        }
        return entity;
    }
}
