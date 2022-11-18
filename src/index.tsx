import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-tv-focus' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const TvFocus = NativeModules.TvFocus
  ? NativeModules.TvFocus
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const updateView = (
  viewTag: number,
  className: string,
  props: Record<string, any>
) => {
  TvFocus.updateView(viewTag, className, props);
};

export const focus = (tag: number | null | undefined) => {
  if (!tag) {
    return;
  }

  if (Platform.OS === 'android') {
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
