package net.journey.common.knowledge;

public enum EnumKnowledgeType {

	OVERWORLD("overworld"),
	NETHER("nether"),
	END("end"),
	EUCA("euca"),
	BOIL("boil"),
	FROZEN("frozen"),
	DEPTHS("depths"),
	CORBA("corba"),
	CLOUDIA("cloudia"),
	TERRANIA("terrania"),
	SENTERIAN("senterian");

	private final String name;

	EnumKnowledgeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}