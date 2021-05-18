import { NativeModules, requireNativeComponent } from 'react-native';

const { JitsiMeetExtended } = NativeModules;
const JitsiMeetViewAndroid = requireNativeComponent('JitsiView');

export { JitsiMeetExtended, JitsiMeetViewAndroid };
