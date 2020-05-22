package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.client.render.TimeModel;
import ru.timeconqueror.timecore.api.util.MathUtils;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.List;

public class BoneOption {
	private String boneName;
	/**
	 * Immutable rotation keyframe list
	 */
	@Nullable
	private List<KeyFrame> rotations;
	/**
	 * Immutable position keyframe list
	 */
	@Nullable
	private List<KeyFrame> positions;
	/**
	 * Immutable scale keyframe list
	 */
	@Nullable
	private List<KeyFrame> scales;

	public BoneOption(String boneName, @Nullable List<KeyFrame> rotations, @Nullable List<KeyFrame> positions, @Nullable List<KeyFrame> scales) {
		this.boneName = boneName;
		this.rotations = rotations;
		this.positions = positions;
		this.scales = scales;
	}

	/**
	 * Finds start and end keyframes for provided animation time.
	 * <p>
	 * Returns:
	 * <table>
	 *     <tr><th>Result</th><th>Predicate</th></tr>
	 *     <tr><td>null</td><td>if frames are null</td></tr>
	 *     <tr><td>Pair.of(null, firstFrame)</td><td>f time has not yet reached the first frame's one</td></tr>
	 *     <tr><td>Pair.of(startFrame, endFrame)</td><td>if existing time is within these frames' time bounds</td></tr>
	 *     <tr><td>Pair.of(lastFrame, null)</td><td>if existing time is behind time bounds of last frame</td></tr>
	 * </table>
	 *
	 * @param frames       frame array in
	 * @param existingTime animation's existing time
	 * @return start and end keyframes for provided animation time
	 */
	@Nullable
	static Pair<KeyFrame, KeyFrame> findKeyFrames(List<KeyFrame> frames, long existingTime) {
		if (frames == null) return null;

//        if(frames.size() == 1) return new Pair<>(frames.get(0), null);

		for (int i = 0; i < frames.size(); i++) {
			KeyFrame keyFrame = frames.get(i);

			if (i == 0 && keyFrame.getStartTime() > existingTime) {
				return Pair.of(null, keyFrame);
			} else if (i == frames.size() - 1) {
				return Pair.of(keyFrame, null);
			}

			if (keyFrame.getStartTime() <= existingTime && frames.get(i + 1).getStartTime() > existingTime) {
				return Pair.of(keyFrame, frames.get(i + 1));
			}
		}

		return null;
	}

	@NotNull
	static Vector3f calcCurrentVectorFor(IAnimation animation, @NotNull Pair<KeyFrame, KeyFrame> keyPair, float startX, float startY, float startZ, int existingTime) {
		KeyFrame start = keyPair.getA();
		KeyFrame end = keyPair.getB();

		Vector3f startVec;
		Vector3f endVec;
		int startTime;
		int endTime;

		if (start == null) {
			startVec = new Vector3f(startX, startY, startZ);
			startTime = 0;
		} else {
			startVec = start.getVec();
			startTime = start.getStartTime();
		}

		if (end == null) {
			endVec = startVec;
			endTime = animation.getLength();
		} else {
			endVec = end.getVec();
			endTime = end.getStartTime();
		}

		return interpolate(startVec, endVec, startTime, endTime, existingTime);
	}

	static Vector3f interpolate(Vector3f start, Vector3f end, int startTime, int endTime, int existingTime) {
		float factor = endTime - startTime == 0 ? 1 : (existingTime - startTime) / (float) (endTime - startTime);

		float outX = MathUtils.lerp(factor, start.getX(), end.getX());
		float outY = MathUtils.lerp(factor, start.getY(), end.getY());
		float outZ = MathUtils.lerp(factor, start.getZ(), end.getZ());

		return new Vector3f(outX, outY, outZ);
	}

	public void apply(IAnimation animation, TimeModel model, int existingTime) {
		TimeModelRenderer piece = model.getPiece(boneName);

		if (piece != null) {
			Pair<KeyFrame, KeyFrame> keyPair = findKeyFrames(rotations, existingTime);
			if (keyPair != null) {
				Vector3f rotateVector = calcCurrentVectorFor(animation, keyPair, 0, 0, 0, existingTime);
				piece.rotateAngleX += rotateVector.getX();
				piece.rotateAngleY += rotateVector.getY();
				piece.rotateAngleZ += rotateVector.getZ();
			}

            keyPair = findKeyFrames(positions, existingTime);
            if (keyPair != null) {
	            Vector3f posVector = calcCurrentVectorFor(animation, keyPair, piece.offsetX, piece.offsetY, piece.offsetZ, existingTime);
                piece.offsetX = posVector.getX();
                piece.offsetY = posVector.getY();
                piece.offsetZ = posVector.getZ();
            }

			keyPair = findKeyFrames(scales, existingTime);
			if (keyPair != null) {
				Vector3f vec = calcCurrentVectorFor(animation, keyPair, piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ(), existingTime);
				piece.setScaleFactor(vec.getX(), vec.getY(), vec.getZ());
			}
		} else {
			TimeCore.logHelper.error("Can't find bone with name " + boneName + " in animation " + animation.getName() + " applied for model " + model.getName());
		}
	}

	public List<KeyFrame> getPositions() {
		return positions;
	}

	public List<KeyFrame> getRotations() {
		return rotations;
	}

	public List<KeyFrame> getScales() {
		return scales;
	}

	public String getName() {
		return boneName;
	}
}
