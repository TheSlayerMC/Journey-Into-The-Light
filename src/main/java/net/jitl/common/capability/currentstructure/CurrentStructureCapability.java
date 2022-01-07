package net.jitl.common.capability.currentstructure;

public class CurrentStructureCapability implements ICurrentStructureCapability {
    private int structureId;

    public void setStructure(int id) {
        structureId = id;
    }

    public int getStructure() {
        return structureId;
    }
}
