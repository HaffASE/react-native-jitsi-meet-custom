package dev.haffa.reactnativejitsimeetcustom;

import android.util.Log;
import java.net.URL;
import java.net.MalformedURLException;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableMap;

@ReactModule(name = RNJitsiMeetModule.MODULE_NAME)
public class RNJitsiMeetModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RNJitsiMeetModule";
    private RNJitsiMeetViewInterface jitsiMeetViewInterface;

    public RNJitsiMeetModule(ReactApplicationContext reactContext, RNJitsiMeetViewInterface jitsiMeetViewReference) {
        super(reactContext);
        jitsiMeetViewInterface = jitsiMeetViewReference;
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void initialize() {
        Log.d("JitsiMeet", "Initialize is deprecated in v2");
    }

    @ReactMethod
    public void call(String url, ReadableMap userInfo, ReadableMap meetOptions, ReadableMap meetFeatureFlags) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (jitsiMeetViewInterface.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                          }
                          if (userInfo.hasKey("avatar")) {
                            String avatarURL = userInfo.getString("avatar");
                            try {
                                _userInfo.setAvatar(new URL(avatarURL));
                            } catch (MalformedURLException e) {
                            }
                          }
                    }
                    RNJitsiMeetConferenceOptions options = new RNJitsiMeetConferenceOptions.Builder()
                            .setRoom(url)
                            .setToken(meetOptions.getString("token"))
                            .setSubject(meetOptions.getString("subject"))
                            .setAudioMuted(meetOptions.getBoolean("audioMuted"))
                            .setAudioOnly(meetOptions.getBoolean("audioOnly"))
                            .setVideoMuted(meetOptions.getBoolean("videoMuted"))
                            .setUserInfo(_userInfo)
                            .setFeatureFlag("add-people.enabled", meetFeatureFlags.getBoolean("add-people.enabled"))
                            .setFeatureFlag("calendar.enabled", meetFeatureFlags.getBoolean("calendar.enabled"))
                            .setFeatureFlag("call-integration.enabled", meetFeatureFlags.getBoolean("call-integration.enabled"))
                            .setFeatureFlag("chat.enabled", meetFeatureFlags.getBoolean("chat.enabled"))
                            .setFeatureFlag("close-captions.enabled", meetFeatureFlags.getBoolean("close-captions.enabled"))
                            .setFeatureFlag("invite.enabled", meetFeatureFlags.getBoolean("invite.enabled"))
                            .setFeatureFlag("ios.recording.enabled", meetFeatureFlags.getBoolean("ios.recording.enabled"))
                            .setFeatureFlag("live-streaming.enabled", meetFeatureFlags.getBoolean("live-streaming.enabled"))
                            .setFeatureFlag("meeting-name.enabled", meetFeatureFlags.getBoolean("meeting-name.enabled"))
                            .setFeatureFlag("meeting-password.enabled", meetFeatureFlags.getBoolean("meeting-password.enabled"))
                            .setFeatureFlag("pip.enabled", meetFeatureFlags.getBoolean("pip.enabled"))
                            .setFeatureFlag("raise-hand.enabled", meetFeatureFlags.getBoolean("raise-hand.enabled"))
                            .setFeatureFlag("recording.enabled", meetFeatureFlags.getBoolean("recording.enabled"))
                            .setFeatureFlag("tile-view.enabled", meetFeatureFlags.getBoolean("tile-view.enabled"))
                            .setFeatureFlag("toolbox.alwaysVisible", meetFeatureFlags.getBoolean("toolbox.alwaysVisible"))
                            .setFeatureFlag("welcomepage.enabled", meetFeatureFlags.getBoolean("welcomepage.enabled"))
                            .build();
                    jitsiMeetViewInterface.getJitsiMeetView().join(options);
                }
            }
        });
    }

    @ReactMethod
    public void endCall() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (jitsiMeetViewInterface.getJitsiMeetView() != null) {
                    jitsiMeetViewInterface.getJitsiMeetView().leave();
                }
            }
        });
    }
}
