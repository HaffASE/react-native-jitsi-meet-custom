/**
 * @providesModule JitsiMeet
 */

import { NativeModules, requireNativeComponent } from 'react-native';

export const JitsiMeetView = requireNativeComponent('RNJitsiMeetView');
export const JitsiMeetModule = NativeModules.RNJitsiMeetModule;

const call = JitsiMeetModule.call;
JitsiMeetModule.call = (url, userInfo, meetOptions, meetFeatureFlags) => {
  userInfo = userInfo || {};
  call(url, userInfo, meetOptions, meetFeatureFlags);
};

export default JitsiMeetModule;
