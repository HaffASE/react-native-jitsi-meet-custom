package dev.haffa.reactnativejitsimeetcustom;

import java.net.URL;
import java.net.MalformedURLException;

import android.util.Log;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableMap;

import org.jitsi.meet.sdk.BroadcastIntentHelper;

import timber.log.Timber;

@ReactModule(name = RNJitsiMeetModule.MODULE_NAME)
public class RNJitsiMeetModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RNJitsiMeetModule";
    private IRNJitsiMeetViewReference mJitsiMeetViewReference;

    public RNJitsiMeetModule(ReactApplicationContext reactContext, IRNJitsiMeetViewReference jitsiMeetViewReference) {
        super(reactContext);
        mJitsiMeetViewReference = jitsiMeetViewReference;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void initialize() {
        Log.d("JitsiMeet", "Initialize is deprecated in v2");
    }

    @ReactMethod
    public void call(String url, ReadableMap userInfo) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                          }
                          if (userInfo.hasKey("email")) {
                            _userInfo.setEmail(userInfo.getString("email"));
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
                            .setAudioOnly(false)
                            .setFeatureFlag("add-people.enabled", false)
                            .setFeatureFlag("calendar.enabled", false)
                            .setFeatureFlag("call-integration.enabled", false)
                            .setFeatureFlag("close-captions.enabled", false)
                            .setFeatureFlag("chat.enabled", false)
                            .setFeatureFlag("invite.enabled", false)
                            .setFeatureFlag("kick-out.enabled", false)
                            .setFeatureFlag("live-streaming.enabled", false)
                            .setFeatureFlag("notifications.enabled", false)
                            .setFeatureFlag("pip.enabled", true)
                            .setFeatureFlag("raise-hand.enabled", false)
                            .setFeatureFlag("tile-view.enabled", false)
                            .setFeatureFlag("toolbox.enabled", false)
                            .setUserInfo(_userInfo)
                            .build();
                    mJitsiMeetViewReference.getJitsiMeetView().join(options);
                }
            }
        });
    }

    @ReactMethod
    public void endCall() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    mJitsiMeetViewReference.getJitsiMeetView().leave();
                }
            }
        });
    }

    @ReactMethod
    public void setAudioMuted(Boolean muted) {
        try {
        Intent muteBroadcastIntent = BroadcastIntentHelper.buildSetAudioMutedIntent(muted);
        LocalBroadcastManager.getInstance(getReactApplicationContext()).sendBroadcast(muteBroadcastIntent);
        }
        catch(Exception e) {
            Timber.i("error in muteAudio");
        }
  }

    @ReactMethod
    public void setVideoMuted(Boolean muted) {
        try {
        Intent muteVideoBroadcastIntent = BroadcastIntentHelper.buildSetVideoMutedIntent(muted);
        LocalBroadcastManager.getInstance(getReactApplicationContext()).sendBroadcast(muteVideoBroadcastIntent);
        }
        catch(Exception e) {
        Timber.i("error in muteVideo");
        }
    }

    @ReactMethod
    public void toggleCameraFacingMode() {
        try {
            Intent toggleCameraBroadcastIntent = BroadcastIntentHelper.buildToggleCameraFacingModeIntent();
            LocalBroadcastManager.getInstance(getReactApplicationContext()).sendBroadcast(toggleCameraBroadcastIntent);
        }
        catch(Exception e) {
            Timber.i("error in toggleCameraFacingMode");
        }
    }
}
