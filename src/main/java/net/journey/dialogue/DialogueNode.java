package net.journey.dialogue;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogueNode {
	public static final DialogueNode END = new DialogueNode("");
	public static final Action EMPTY_ACTION = (world, player) -> {
	};

	/**
	 * Will be shown when there is no option added to the node.
	 */
	private static final List<Option> STANDBY_END_OPTION_LIST = Collections.singletonList(new Option("dialogue.jitl.standby_end_option"));

	private final String text;
	private List<Option> options;

	protected DialogueNode(String text) {
		this.text = text;
	}

	protected void addOption(Option option) {
		if (options == null) options = new ArrayList<>();
		options.add(option);
	}

	public List<Option> getOptions() {
		return options != null ? options : STANDBY_END_OPTION_LIST;
	}

	public String getTextKey() {
		return text;
	}

	@Override
	public String toString() {
		return "DialogueNode{" +
				"text='" + text + '\'' +
				", options=" + options +
				'}';
	}

	public static class Option {
		private final String text;
		private DialogueNode nextNode = END;
		private Action onClickAction = EMPTY_ACTION;

		protected Option(String text) {
			this.text = text;
		}

		protected void setOnClickAction(Action onClickAction) {
			this.onClickAction = onClickAction;
		}

		protected void setNextNode(DialogueNode nextNode) {
			this.nextNode = nextNode;
		}

		public String getText() {
			return text;
		}

		public DialogueNode getNextNode() {
			return nextNode;
		}

		public void onClickAction(World world, EntityPlayerMP player) {
			onClickAction.onClick(world, player);
		}

		@Override
		public String toString() {
			return "Option{" +
					"text='" + text + '\'' +
					", hasNextNode=" + (nextNode != END) +
					'}';
		}
	}

	public interface Action {
		void onClick(World world, EntityPlayerMP player);
	}
}
