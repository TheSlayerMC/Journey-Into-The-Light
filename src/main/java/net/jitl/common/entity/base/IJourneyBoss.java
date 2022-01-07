package net.jitl.common.entity.base;

import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.common.helper.JMusic;

public interface IJourneyBoss {
    BossBarRenderer getBossBar();

    JMusic getBossMusic();
}
