package net.jitl.common.entity.nether;

import net.jitl.JITL;
import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.client.render.gui.EyeBarRenderer;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.entity.overworld.FloroEntity;
import net.jitl.common.helper.JBossInfo;
import net.jitl.common.helper.JMusic;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

public class SoulWatcherEntity extends GhastEntity implements IJourneyBoss, AnimatedObject<SoulWatcherEntity> {
    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new EyeBarRenderer(this, JITL.tl("gui/bossbars/soul_watcher.png").fullLocation());
    private static final JMusic BOSS_TRACK = new JMusic(JSounds.TEMPLE_GUARDIAN_MUSIC.get(), 2, 0, 0);
    private final AnimationSystem<SoulWatcherEntity> animationSystem;

    private static final String LAYER_IDLE = "idle";
    private static final String LAYER_CLOSED = "closed";

    public SoulWatcherEntity(EntityType<? extends GhastEntity> type, World worldIn) {
        super(type, worldIn);

        animationSystem = AnimationSystemBuilder.forEntity(this, worldIn, builder -> {
            builder.addLayer(LAYER_IDLE, BlendType.OVERRIDE, 1F);
            builder.addLayer(LAYER_CLOSED, BlendType.ADDING, 1F);
        }, predefinedAnimations -> {
            predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.soulWatcherIdle).setSpeed(3F), LAYER_IDLE);
            predefinedAnimations.setIdleAnimation(new AnimationStarter(JAnimations.soulWatcherClosed), LAYER_CLOSED);
        });
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public JMusic getBossMusic() {
        return BOSS_TRACK;
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        JBossInfo.addInfo(player, bossInfo, this);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        JBossInfo.removeInfo(player, bossInfo, this);
    }

    @Override
    public @NotNull AnimationSystem<SoulWatcherEntity> getSystem() {
        return animationSystem;
    }
}
