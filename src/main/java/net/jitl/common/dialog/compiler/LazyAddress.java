package net.jitl.common.dialog.compiler;

public class LazyAddress {
    private int address = -1;

    public void set(int address) {
        this.address = address;
    }

    public int get() {
        return address;
    }
}
