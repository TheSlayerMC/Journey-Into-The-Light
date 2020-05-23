package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.client.render.TimeModel;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Transition implements IAnimation {
    private static final IAnimation DUMMY_ANIMATION = new IAnimation() {
        @Override
        public void apply(TimeEntityModel model, int existingTime) {

        }

        @Override
        public String getName() {
            return "dummy";
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public boolean isLooped() {
            return false;
        }

        @Override
        public @NotNull IAnimation.TransitionFactory getTransitionFactory() {
            return IDLE_END_TRANSITION_FACTORY;
        }

        @Override
        public void forEachBone(Consumer<String> action) {

        }
    };
    private static final IAnimation.TransitionFactory IDLE_END_TRANSITION_FACTORY = new IAnimation.TransitionFactory(DUMMY_ANIMATION) {
        @Override
        public @Nullable List<TransitionBoneOption> createBoneOptions(IAnimation dest, TimeModel model, int existingTime, int transitionTime) {
            throw new UnsupportedOperationException("Idle End Transition Factory shouldn't be used with source animation");
        }

        @Override
        public @NotNull KeyFrame getDestKeyFrame(TimeModelRenderer piece, String boneName, OptionType optionType, int transitionTime) {
            if (optionType == OptionType.ROTATION) {
                return KeyFrame.createIdleKeyFrame(transitionTime, 0, 0, 0);
            } else if (optionType == OptionType.POSITION) {
                return KeyFrame.createIdleKeyFrame(transitionTime, piece.offsetX, piece.offsetY, piece.offsetZ);
            } else if (optionType == OptionType.SCALE) {
                return KeyFrame.createIdleKeyFrame(transitionTime, piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ());
            }

            throw new UnsupportedOperationException("Can't handle " + optionType + " option type");
        }
    };
    private int transitionLength;
    private String name;
    private List<TransitionBoneOption> options = new ArrayList<>();
    private IAnimation destAnimation;

    private Transition(int transitionLength, String name, IAnimation destAnimation) {
        this.transitionLength = transitionLength;
        this.name = name;
        this.destAnimation = destAnimation;
    }

    private static IAnimation createFromIdleState(@NotNull IAnimation dest, TimeModel model, int transitionTime) {
        Transition transition = new Transition(transitionTime, "idle_to_" + dest.getName(), dest);

        IAnimation.TransitionFactory transitionFactory = dest.getTransitionFactory();

        dest.forEachBone(name -> {
            TimeModelRenderer piece = model.getPiece(name);
            if (piece != null) {
                // Rotations
                KeyFrame startKeyFrame = KeyFrame.createIdleKeyFrame(transitionTime, 0, 0, 0);
                KeyFrame endKeyFrame = transitionFactory.getDestKeyFrame(piece, name, OptionType.ROTATION, transitionTime);
                Pair<KeyFrame, KeyFrame> rotations = Pair.of(startKeyFrame, endKeyFrame);

                // Positions
                startKeyFrame = KeyFrame.createIdleKeyFrame(transitionTime, piece.offsetX, piece.offsetY, piece.offsetZ);
                endKeyFrame = transitionFactory.getDestKeyFrame(piece, name, OptionType.POSITION, transitionTime);
                Pair<KeyFrame, KeyFrame> positions = Pair.of(startKeyFrame, endKeyFrame);

                // Scales
                startKeyFrame = KeyFrame.createIdleKeyFrame(transitionTime, piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ());
                endKeyFrame = transitionFactory.getDestKeyFrame(piece, name, OptionType.SCALE, transitionTime);

                Pair<KeyFrame, KeyFrame> scales = Pair.of(startKeyFrame, endKeyFrame);

                transition.options.add(new TransitionBoneOption(name, rotations, positions, scales));
            }
        });

        return transition;
    }

    private static IAnimation createToIdleState(@Nullable IAnimation source, TimeModel model, int existingTime, int transitionTime) {
        Transition transition = new Transition(transitionTime, (source != null ? source.getName() : "idle") + "_to_idle", null);

        if (source != null) {
            IAnimation.TransitionFactory transitionFactory = source.getTransitionFactory();
            transition.options = transitionFactory.createBoneOptions(DUMMY_ANIMATION, model, existingTime, transitionTime);
        }

        return transition;
    }

    public static IAnimation create(@Nullable AnimationWatcher sourceWatcher, @Nullable IAnimation dest, TimeModel model, int transitionTime) {
        if (dest == null) {
            return createToIdleState(sourceWatcher != null ? sourceWatcher.getAnimation() : null, model, sourceWatcher != null ? sourceWatcher.getExistingTime() : -1, transitionTime);
        } else if (sourceWatcher == null || sourceWatcher.getAnimation() == null) {
            return createFromIdleState(dest, model, transitionTime);
        }

        IAnimation source = sourceWatcher.getAnimation();
        int existingTime = sourceWatcher.getExistingTime();

        return create(source, dest, model, existingTime, transitionTime);
    }

    private static IAnimation create(@NotNull IAnimation source, @NotNull IAnimation dest, TimeModel model, int existingTime, int transitionTime) {
        IAnimation.TransitionFactory sourceTFactory = source.getTransitionFactory();

        List<TransitionBoneOption> options = sourceTFactory.createBoneOptions(dest, model, existingTime, transitionTime);
        if (options == null) {
            return createFromIdleState(dest, model, transitionTime);
        }

        Transition transition = new Transition(transitionTime, source.getName() + "_to_" + dest.getName(), dest);
        transition.options = options;

        return transition;
    }

    @Override
    public void apply(TimeEntityModel model, int existingTime) {
        TimeModel baseModel = model.getBaseModel();
        if (options != null) {
            if (existingTime <= transitionLength) {
                options.forEach(boneOption -> boneOption.apply(this, baseModel, existingTime));
            }
        }
    }

    @Override
    public void forEachBone(Consumer<String> action) {
        options.forEach(transitionBoneOption -> action.accept(transitionBoneOption.name));
    }

    @Override
    public boolean isLooped() {
        return false;
    }

    @Override
    public @NotNull IAnimation.TransitionFactory getTransitionFactory() {
        return new TransitionFactory(this);
    }

    @Override
    public int getLength() {
        return transitionLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Nullable
    public IAnimation getDestAnimation() {
        return destAnimation;
    }

    private static class TransitionFactory extends IAnimation.TransitionFactory {

        public TransitionFactory(Transition source) {
            super(source);
        }

        private static KeyFrame calcStartKeyFrame(IAnimation sourceAnimation, @Nullable Pair<KeyFrame, KeyFrame> sourceKeyFrames, float modelIdleX, float modelIdleY, float modelIdleZ, int existingTime) {
            if (sourceKeyFrames != null) {
                Vector3f vec = BoneOption.calcCurrentVectorFor(sourceAnimation, sourceKeyFrames, modelIdleX, modelIdleY, modelIdleZ, existingTime);
                return new KeyFrame(0, vec);
            }

            return KeyFrame.createIdleKeyFrame(0, modelIdleX, modelIdleY, modelIdleZ);
        }

        @Override
        public @Nullable List<TransitionBoneOption> createBoneOptions(IAnimation dest, TimeModel model, int existingTime, int transitionTime) {
            Transition source = getSourceTyped();
            if (source.options == null || source.options.isEmpty()) {
                return null;
            }

            IAnimation.TransitionFactory destFactory = dest.getTransitionFactory();

            List<TransitionBoneOption> transitionBones = new ArrayList<>();
            source.options.forEach(sourceBone -> {
                TimeModelRenderer piece = model.getPiece(sourceBone.name);
                if (piece != null) {
                    // Rotations
                    KeyFrame startKeyFrame = calcStartKeyFrame(source, sourceBone.rotations, 0, 0, 0, existingTime);
                    KeyFrame endKeyFrame = destFactory.getDestKeyFrame(piece, sourceBone.name, OptionType.ROTATION, transitionTime);
                    Pair<KeyFrame, KeyFrame> rotations = Pair.of(startKeyFrame, endKeyFrame);

                    // Positions
                    startKeyFrame = calcStartKeyFrame(source, sourceBone.positions, piece.offsetX, piece.offsetY, piece.offsetZ, existingTime);
                    endKeyFrame = destFactory.getDestKeyFrame(piece, sourceBone.name, OptionType.POSITION, transitionTime);
                    Pair<KeyFrame, KeyFrame> positions = Pair.of(startKeyFrame, endKeyFrame);

                    // Scales
                    startKeyFrame = calcStartKeyFrame(source, sourceBone.scales, piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ(), existingTime);
                    endKeyFrame = destFactory.getDestKeyFrame(piece, sourceBone.name, OptionType.SCALE, transitionTime);
                    Pair<KeyFrame, KeyFrame> scales = Pair.of(startKeyFrame, endKeyFrame);

                    transitionBones.add(new Transition.TransitionBoneOption(sourceBone.name, rotations, positions, scales));
                }
            });

            return transitionBones;
        }

        @Override
        public @NotNull KeyFrame getDestKeyFrame(TimeModelRenderer piece, String boneName, OptionType optionType, int transitionTime) {
            throw new UnsupportedOperationException("This should never be reached. Transition shouldn't be set manually as an destination animation");
        }
    }

    public static class TransitionBoneOption {
        private String name;

        private Pair<KeyFrame, KeyFrame> rotations;
        private Pair<KeyFrame, KeyFrame> positions;
        private Pair<KeyFrame, KeyFrame> scales;

        public TransitionBoneOption(String boneName, Pair<KeyFrame, KeyFrame> rotations, Pair<KeyFrame, KeyFrame> positions, Pair<KeyFrame, KeyFrame> scales) {
            this.name = boneName;
            this.rotations = rotations;
            this.positions = positions;
            this.scales = scales;
        }

        private static Vector3f interpolate(KeyFrame start, KeyFrame end, int existingTime) {
            return BoneOption.interpolate(start.getVec(), end.getVec(), start.getStartTime(), end.getStartTime(), existingTime);
        }

        public void apply(Transition animation, TimeModel model, int existingTime) {
            TimeModelRenderer piece = model.getPiece(name);

            if (piece != null) {
                Vector3f interpolated = interpolate(rotations.getA(), rotations.getB(), existingTime);
                piece.rotateAngleX += interpolated.getX();
                piece.rotateAngleY += interpolated.getY();
                piece.rotateAngleZ += interpolated.getZ();

                interpolated = interpolate(positions.getA(), positions.getB(), existingTime);
                piece.offsetX = interpolated.getX();
                piece.offsetY = interpolated.getY();
                piece.offsetZ = interpolated.getZ();

                interpolated = interpolate(scales.getA(), scales.getB(), existingTime);
                piece.setScaleFactor(interpolated.getX(), interpolated.getY(), interpolated.getZ());
            } else {
                TimeCore.logHelper.error("Can't find bone with name " + name + " for transition " + animation.getName() + " applied for model " + model.getName());
            }
        }
    }
}
