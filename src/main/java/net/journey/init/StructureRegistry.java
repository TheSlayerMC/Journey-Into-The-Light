package net.journey.init;

import net.journey.JITL;
import net.journey.dimension.corba.village.MapGenCorbaVillage;
import net.journey.dimension.corba.village.StructureCorbaVillagePieces;

public class StructureRegistry {
	public static final String STRUCTURE_CORBA_VILLAGE = withModPrefix("corba_village");

	public static final String COMPONENT_CORBA_VILLAGE_START = "CV_S";
	public static final String COMPONENT_CORBA_VILLAGE_PATH = "CV_P";
	public static final String COMPONENT_CORBA_VILLAGE_WELL = "CV_W";
	public static final String COMPONENT_CORBA_VILLAGE_HOUSE1 = "CV_H1";
	public static final String COMPONENT_CORBA_VILLAGE_HOUSE2 = "CV_H2";
	public static final String COMPONENT_CORBA_VILLAGE_LAMP = "CV_L";
	public static final String COMPONENT_CORBA_VILLAGE_BLACKSMITH = "CV_BS";
	public static final String COMPONENT_CORBA_VILLAGE_GARDEN = "CV_G";
	public static final String COMPONENT_CORBA_VILLAGE_OUTHOUSE = "CV_OH";
	public static final String COMPONENT_CORBA_VILLAGE_CHURCH = "CV_C";
	public static final String COMPONENT_CORBA_VILLAGE_LIBRARY = "CV_LB";

	public static void init() {
		Registrar.regStructure(STRUCTURE_CORBA_VILLAGE, MapGenCorbaVillage.Start.class);
		StructureCorbaVillagePieces.registerPieces();
	}

	private static String withModPrefix(String name) {
		return JITL.ABBREV + "." + name;
	}
}
