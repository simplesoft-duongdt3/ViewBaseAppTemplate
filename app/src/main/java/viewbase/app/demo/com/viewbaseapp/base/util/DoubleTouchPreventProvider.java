package viewbase.app.demo.com.viewbaseapp.base.util;

import android.support.annotation.NonNull;

public class DoubleTouchPreventProvider {
    private static DoubleTouchPrevent doubleTouchPrevent = new DoubleTouchPrevent();

    @NonNull
    public static DoubleTouchPrevent createIfNullDoubleTouchPrevent(DoubleTouchPrevent doubleTouchPrevent) {
        return doubleTouchPrevent == null ? createDoubleTouchPrevent() : doubleTouchPrevent;
    }

    @NonNull
    public static DoubleTouchPrevent createDoubleTouchPrevent() {
        return doubleTouchPrevent;
    }
}
