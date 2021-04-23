import React from 'react-native';

const ReactNativeJitsiMeetCustom = React.NativeModules.ReactNativeJitsiMeetCustom;

export default {
  reactNativeJitsiMeetCustom: () => {
    return ReactNativeJitsiMeetCustom.reactNativeJitsiMeetCustom();
  },
};
