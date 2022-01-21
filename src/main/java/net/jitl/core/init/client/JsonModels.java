package net.jitl.core.init.client;

import net.jitl.core.JITL;
import ru.timeconqueror.timecore.api.registry.TimeModelRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.client.render.model.TimeModelLocation;

public class JsonModels {
    @AutoRegistrable
    private static final TimeModelRegister REGISTER = new TimeModelRegister(JITL.MODID);

    public static final TimeModelLocation FLORO = REGISTER.register("models/entity/floro.json");
    public static final TimeModelLocation WITHERSPINE = REGISTER.register("models/entity/witherspine.json");
    public static final TimeModelLocation TOWER_GUARDIAN = REGISTER.register("models/entity/tower_guardian.json");
    public static final TimeModelLocation GLUMP = REGISTER.register("models/entity/glump.json");
    public static final TimeModelLocation ILLAGER_MECH = REGISTER.register("models/entity/illager_mech.json");
    public static final TimeModelLocation SOUL_WATCHER = REGISTER.register("models/entity/soul_watcher.json");
    public static final TimeModelLocation BOSS_CRYSTAL = REGISTER.register("models/entity/boss_crystal.json");

}
