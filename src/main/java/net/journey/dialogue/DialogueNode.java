package net.journey.dialogue;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
//FIXME WHAT TO DO WITH EMPTY OPTIONS
public class DialogueNode {
	public static final DialogueNode END = new DialogueNode("");
	public static final Action EMPTY_ACTION = (world, player) -> {
	};

	private final String text;
	private final List<Option> options;

	protected DialogueNode(String text) {
		this.text = text;
		this.options = new ArrayList<>();
	}

	protected void addOption(Option option) {
		options.add(option);
	}

	public List<Option> getOptions() {
		return options;
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
