package viewbase.app.demo.com.viewbaseapp.base.viewbase.screenchangehandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler;

/**
 * An {@link AnimatorChangeHandler} that will slide the views left or right, depending on if it's a push or pop.
 */
public class VerticalChangeHandler extends AnimatorChangeHandler {

    public VerticalChangeHandler() {
    }

    public VerticalChangeHandler(boolean removesFromViewOnPush) {
        super(removesFromViewOnPush);
    }

    public VerticalChangeHandler(long duration) {
        super(duration);
    }

    public VerticalChangeHandler(long duration, boolean removesFromViewOnPush) {
        super(duration, removesFromViewOnPush);
    }

    @Override
    @NonNull
    protected Animator getAnimator(@NonNull ViewGroup container, @Nullable View from, @Nullable View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animatorSet = new AnimatorSet();

        if (isPush) {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, -from.getHeight()));
            }
            if (to != null) {
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, to.getHeight(), 0));
            }
        } else {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, from.getHeight()));
            }
            if (to != null) {
                // Allow this to have a nice transition when coming off an aborted push animation
                float fromTop = from != null ? from.getTranslationY() : 0;
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, fromTop - to.getHeight(), 0));
            }
        }

        return animatorSet;
    }

    @Override
    protected void resetFromView(@NonNull View from) {
        from.setTranslationX(0);
    }

    @Override
    @NonNull
    public ControllerChangeHandler copy() {
        return new com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler(getAnimationDuration(), removesFromViewOnPush());
    }

}
