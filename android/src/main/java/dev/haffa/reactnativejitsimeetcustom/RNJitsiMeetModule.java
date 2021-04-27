package dev.haffa.reactnativejitsimeetcustom;

import android.util.Log;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.net.URL;
import java.net.MalformedURLException;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableMap;

import org.jitsi.meet.sdk.BroadcastIntentHelper;

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
                    jitsiMeetViewInterface.getJitsiMeetView().leave();
                    jitsiMeetViewInterface.getJitsiMeetView().dispose();
                }

                RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                String room = "test";
                String subject = "room";
                Boolean audioOnly = false;
                Boolean audioMuted = false;
                Boolean videoMuted = false;
                Boolean addPeopleEnabled = false;
                Boolean chatEnabled = false;
                Boolean inviteEnabled = false;
                Boolean meetingNameEnabled = false;
                Boolean conferenceTimerEnabled = true;
                Boolean raiseHandEnabled = false;
                Boolean recordingEnabled = false;
                Boolean liveStreamEnabled = false;
                Boolean toolBoxEnabled = true;
                Boolean toolBoxAlwaysVisible = true;
                Boolean meetingPasswordEnabled = false;
                Boolean pipModeEnabled = false;
                URL serverUrl = null;

                if (url != null) {
                    try {
                        serverUrl = new URL(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        serverUrl = new URL("https://meet.jit.si");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

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

                if (meetOptions != null) {
                    if (meetOptions.hasKey("room")) {
                        room = meetOptions.getString("room");
                    }
                    if (meetOptions.hasKey("subject")) {
                        subject = meetOptions.getString("subject");
                    }
                    if (meetOptions.hasKey("audioOnly")) {
                        audioOnly = meetOptions.getBoolean("audioOnly");
                    }
                    if (meetOptions.hasKey("audioMuted")) {
                        audioMuted = meetOptions.getBoolean("audioMuted");
                    }
                    if (meetOptions.hasKey("videoMuted")) {
                        videoMuted = meetOptions.getBoolean("videoMuted");
                    }
                }

                if (meetFeatureFlags != null) {
                    if (meetFeatureFlags.hasKey("chatEnabled")) {
                        chatEnabled = meetFeatureFlags.getBoolean("chatEnabled");
                    }
                    if (meetFeatureFlags.hasKey("addPeopleEnabled")) {
                        addPeopleEnabled = meetFeatureFlags.getBoolean("addPeopleEnabled");
                    }
                    if (meetFeatureFlags.hasKey("inviteEnabled")) {
                        inviteEnabled = meetFeatureFlags.getBoolean("inviteEnabled");
                    }
                    if (meetFeatureFlags.hasKey("meetingNameEnabled")) {
                        meetingNameEnabled = meetFeatureFlags.getBoolean("meetingNameEnabled");
                    }
                    if (meetFeatureFlags.hasKey("conferenceTimerEnabled")) {
                        conferenceTimerEnabled = meetFeatureFlags.getBoolean("conferenceTimerEnabled");
                    }
                    if (meetFeatureFlags.hasKey("raiseHandEnabled")) {
                        raiseHandEnabled = meetFeatureFlags.getBoolean("raiseHandEnabled");
                    }
                    if (meetFeatureFlags.hasKey("recordingEnabled")) {
                        recordingEnabled = meetFeatureFlags.getBoolean("recordingEnabled");
                    }
                    if (meetFeatureFlags.hasKey("liveStreamEnabled")) {
                        liveStreamEnabled = meetFeatureFlags.getBoolean("liveStreamEnabled");
                    }
                    if (meetFeatureFlags.hasKey("toolBoxEnabled")) {
                        toolBoxEnabled = meetFeatureFlags.getBoolean("toolBoxEnabled");
                    }
                    if (meetFeatureFlags.hasKey("toolBoxAlwaysVisible")) {
                        toolBoxAlwaysVisible = meetFeatureFlags.getBoolean("toolBoxAlwaysVisible");
                    }
                    if (meetFeatureFlags.hasKey("meetingPasswordEnabled")) {
                        meetingPasswordEnabled = meetFeatureFlags.getBoolean("meetingPasswordEnabled");
                    }
                    if (meetFeatureFlags.hasKey("pipModeEnabled")) {
                        pipModeEnabled = meetFeatureFlags.getBoolean("pipModeEnabled");
                    }
                }

                RNJitsiMeetConferenceOptions options = new RNJitsiMeetConferenceOptions.Builder()
                        .setServerURL(serverUrl)
                        .setRoom(room)
                        .setUserInfo(_userInfo)
                        .setSubject(subject)
                        .setAudioMuted(audioMuted)
                        .setAudioOnly(audioOnly)
                        .setVideoMuted(videoMuted)
                        .setFeatureFlag("add-people.enabled", addPeopleEnabled)
                        .setFeatureFlag("chat.enabled", chatEnabled)
                        .setFeatureFlag("invite.enabled", inviteEnabled)
                        .setFeatureFlag("meeting-name.enabled", meetingNameEnabled)
                        .setFeatureFlag("conference-timer.enabled", conferenceTimerEnabled)
                        .setFeatureFlag("raise-hand.enabled", raiseHandEnabled)
                        .setFeatureFlag("recording.enabled", recordingEnabled)
                        .setFeatureFlag("live-streaming.enabled", liveStreamEnabled)
                        .setFeatureFlag("toolbox.enabled", toolBoxEnabled)
                        .setFeatureFlag("toolbox.alwaysVisible", toolBoxAlwaysVisible)
                        .setFeatureFlag("meeting-password.enabled", meetingPasswordEnabled)
                        .setFeatureFlag("pip.enabled", pipModeEnabled)
                        .build();
                jitsiMeetViewInterface.getJitsiMeetView().join(options);
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

    @ReactMethod
    public void muteAudio(Boolean muted) {
        try {
            Intent muteBroadcastIntent = BroadcastIntentHelper.buildSetAudioMutedIntent(muted);
            LocalBroadcastManager.getInstance(getReactApplicationContext()).sendBroadcast(muteBroadcastIntent);
        }
        catch(Exception e) {
        }
    }

    @ReactMethod
    public void muteVideo(Boolean muted) {
        try {
            Intent muteVideoBroadcastIntent = BroadcastIntentHelper.buildSetVideoMutedIntent(muted);
            LocalBroadcastManager.getInstance(getReactApplicationContext()).sendBroadcast(muteVideoBroadcastIntent);
        }
        catch(Exception e) {
        }
    }
}
