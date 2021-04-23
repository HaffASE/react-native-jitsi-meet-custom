#import "RCTReactNativeJitsiMeetCustom.h"

@implementation ReactNativeJitsiMeetCustom

RCT_EXPORT_MODULE();

RCT_REMAP_METHOD(reactNativeJitsiMeetCustom,
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(@"Hello World!");
}

@end
