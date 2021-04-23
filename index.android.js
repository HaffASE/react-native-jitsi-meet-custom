import React from 'react-native';

const ReactNativeJitsiMeetCustom = React.NativeModules.ReactNativeJitsiMeetCustom;

export default {
  reactNativeJitsiMeetCustom: (onSuccess, onFailure) => {
    return ReactNativeJitsiMeetCustom.reactNativeJitsiMeetCustom(onSuccess, onFailure);
  },
};
