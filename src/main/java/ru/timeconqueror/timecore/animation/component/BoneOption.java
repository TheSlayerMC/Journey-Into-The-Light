package ru.timeconqueror.timecore.animation.component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.animation.util.AnimationUtils;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationLayer;
import ru.timeconqueror.timecore.api.util.MathUtils;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.List;

public class BoneOption {
    private final String boneName;
    /**
     * Immutable rotation keyframe list
     */
    @Nullable
    private final List<KeyFrame> rotations;
    /**
     * Immutable position keyframe list
     */
    @Nullable
    private final List<KeyFrame> positions;
    /**
     * Immutable scale keyframe list
     */
    @Nullable
    private final List<KeyFrame> scales;

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
    static Vector3f calcCurrentVectorFor(Animation animation, @NotNull Pair<KeyFrame, KeyFrame> keyPair, float startX, float startY, float startZ, int existingTime) {
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

        float outX = MathUtils.interpolate(factor, start.getX(), end.getX());
        float outY = MathUtils.interpolate(factor, start.getY(), end.getY());
        float outZ = MathUtils.interpolate(factor, start.getZ(), end.getZ());

        return new Vector3f(outX, outY, outZ);
    }

    public void apply(Animation animation, AnimationLayer layer, TimeModelRenderer piece, int existingTime) {
        Pair<KeyFrame, KeyFrame> keyPair = findKeyFrames(rotations, existingTime);
        if (keyPair != null) {
            Vector3f rotateVec = calcCurrentVectorFor(animation, keyPair, 0, 0, 0, existingTime);
            AnimationUtils.applyRotation(piece, layer, rotateVec);
        }

        keyPair = findKeyFrames(positions, existingTime);
        if (keyPair != null) {
            Vector3f posVec = calcCurrentVectorFor(animation, keyPair, piece.offsetX, piece.offsetY, piece.offsetZ, existingTime);
            AnimationUtils.applyOffset(piece, layer, posVec);
        }

        keyPair = findKeyFrames(scales, existingTime);
        if (keyPair != null) {
            Vector3f currentScale = piece.getScaleFactor();
            Vector3f scaleVec = calcCurrentVectorFor(animation, keyPair, currentScale.getX(), currentScale.getY(), currentScale.getZ(), existingTime);
            AnimationUtils.applyScale(piece, layer, scaleVec);
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
