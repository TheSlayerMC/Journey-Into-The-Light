package net.journey.common.network.dialogue;

import net.journey.common.network.BaseMsg;
import net.minecraft.network.PacketBuffer;

public class C2SChosenOptionMsg extends BaseMsg {
	private int optionIndex;

	@Deprecated // is called via reflection, not for direct use
	public C2SChosenOptionMsg() {
	}

	public C2SChosenOptionMsg(int optionIndex) {
		this.optionIndex = optionIndex;
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(optionIndex);
	}

	@Override
	protected void read(PacketBuffer buffer) {
		optionIndex = buffer.readInt();
	}

	public int getOptionIndex() {
		return optionIndex;
	}
}
