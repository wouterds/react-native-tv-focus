package com.tvfocus;

import androidx.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;

@ReactModule(name = TvFocusModule.NAME)
public class TvFocusModule extends ReactContextBaseJavaModule {
  public static final String NAME = "TvFocus";

  private UIManagerModule uiManager = null;
  private UIImplementation uiImplementation = null;
  private final ReactApplicationContext context;

  private UIManagerModule getUIManager() {
    if (uiManager == null) {
      uiManager = context.getNativeModule(UIManagerModule.class);
    }

    return uiManager;
  }

  private UIImplementation getUIImplementation() {
    if (uiImplementation == null) {
      uiImplementation = this.getUIManager().getUIImplementation();
    }

    return uiImplementation;
  }

  public TvFocusModule(ReactApplicationContext reactContext) {
    super(reactContext);
    context = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void updateView(int tag, String className, ReadableMap props) {
    try {
      this.getUIImplementation().updateView(
        tag,
        className,
        props
      );
    } catch (IllegalViewOperationException ignore) {
      Log.w(this.getName(), "Failed updating view for tag: " + tag);
    }
  }
}
