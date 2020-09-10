package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeModel extends ModelBase {
	private final String name;
	private List<TimeModelRenderer> pieces;
	private Map<String, TimeModelRenderer> pieceMap;

	private float scaleMultiplier = 1F;

	public TimeModel(String name, int textureWidth, int textureHeight) {
		this.name = name;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	public String getName() {
		return name;
	}

	/**
	 * Sets custom scale for the model.
	 * <p>
	 * Should only be called once and before first render frame,
	 * otherwise you'll see unexpected render behaviour.
	 */
	public TimeModel setScaleMultiplier(float scaleMultiplier) {
		this.scaleMultiplier = scaleMultiplier;

		return this;
	}

	/**
	 * Renders model with provided scale.
	 *
	 * @param initialScale controls initial scale settings of the model.
	 *                     Once you provided some number as initial scale,
	 *                     you should always provide this particular number,
	 *                     otherwise you'll see unexpected render behaviour.
	 */
	public void render(float initialScale) {
		if (pieces != null) {
			for (TimeModelRenderer piece : pieces) {
				piece.render(scaleMultiplier * initialScale);
			}
		}
	}

	@Nullable
	public List<TimeModelRenderer> getPieces() {
		return pieces;
	}

	public void setPieces(List<TimeModelRenderer> pieces) {
		this.pieces = pieces;
		this.pieceMap = new HashMap<>();

		for (TimeModelRenderer piece : pieces) {
			addRendererToMap(piece);
		}
	}

	private void addRendererToMap(TimeModelRenderer renderer) {
		pieceMap.put(renderer.boxName, renderer);

		List<ModelRenderer> children = renderer.childModels;
		if (children != null) {
			for (ModelRenderer child : children) {
				if (child instanceof TimeModelRenderer) {
					addRendererToMap(((TimeModelRenderer) child));
				}
			}
		}
	}

	@Nullable
	public TimeModelRenderer getPiece(String pieceName) {
		return pieceMap.get(pieceName);
	}
}
