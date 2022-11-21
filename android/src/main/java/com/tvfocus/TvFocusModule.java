package com.tvfocus;

import androidx.annotation.NonNull;
import android.util.Log;
// import android.view.View;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;

@ReactModule(name = TvFocusModule.NAME)
public class TvFocusModule extends ReactContextBaseJavaModule {
  public static final String NAME = "TvFocus";

  private final ReactApplicationContext mContext;

  public TvFocusModule(ReactApplicationContext reactContext) {
    super(reactContext);
    mContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void updateView(int tag, String className, ReadableMap props) {
    try {
      mContext.getNativeModule(UIManagerModule.class).getUIImplementation().updateView(
        tag,
        className,
        props
      );
    } catch (IllegalViewOperationException ignore) {
      Log.w(this.getName(), "Failed updating view with tag: " + tag);
    }
  }

  // @ReactMethod
  // public void focus(int tag) {
  //   try {
  //     final View view = mContext.getNativeModule(UIManagerModule.class).resolveView(tag);
  //     if (view != null) {
  //       view.setFocusableInTouchMode(true);
  //       view.setFocusable(true);
  //       view.requestFocus();
  //       Log.w(this.getName(), "Requesting focus for view with tag: " + tag);
  //     } else {
  //       Log.w(this.getName(), "Could not resolve view with tag: " + tag);
  //     }
  //   } catch (IllegalViewOperationException ignore) {
  //     Log.w(this.getName(), "Failed focusing view with tag: " + tag);
  //   }
  // }
}
