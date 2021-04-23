/**
 * @providesModule JitsiMeet
 */

import { NativeModules, requireNativeComponent } from 'react-native';

export const JitsiMeetView = requireNativeComponent('RNJitsiMeetView');
export const JitsiMeetModule = NativeModules.RNJitsiMeetView;
const call = JitsiMeetModule.call;
JitsiMeetModule.call = (url, userInfo) => {
  userInfo = userInfo || {};
  call(url, userInfo);
};
export default JitsiMeetModule;
