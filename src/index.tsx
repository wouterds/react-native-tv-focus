import { NativeModules, Platform } from 'react-native';

const updateView = (
  viewTag: number,
  className: string,
  props: Record<string, any>
) => {
  if (Platform.OS === 'android') {
    NativeModules.TvFocus.updateView(viewTag, className, props);
  } else {
    NativeModules.UIManager.updateView(viewTag, className, props);
  }
};

export const focus = (tag: number | null | undefined) => {
  if (!tag) {
    return;
  }

  if (Platform.OS === 'android') {
    NativeModules.TvFocus.focus(tag);
    updateView(tag, 'RCTView', {
      hasTVPreferredFocus: true,
      tvFocusable: true,
      accessible: true,
    });
  } else {
    updateView(tag, 'RCTTVView', {
      hasTVPreferredFocus: true,
      tvFocusable: true,
      accessible: true,
    });
  }
};
