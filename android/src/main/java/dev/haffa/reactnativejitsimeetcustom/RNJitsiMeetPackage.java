package dev.haffa.reactnativejitsimeetcustom;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNJitsiMeetPackage implements ReactPackage, RNJitsiMeetViewInterface {

    private RNJitsiMeetView mJitsiMeetView = null;


    public void setJitsiMeetView(RNJitsiMeetView jitsiMeetView) {
        mJitsiMeetView = jitsiMeetView;
    }

    public RNJitsiMeetView getJitsiMeetView() {
        return mJitsiMeetView;
    }

    /**
     * @param reactContext react application context that can be used to create modules
     * @return list of native modules to register with the newly created catalyst instance
     */
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new RNJitsiMeetModule(reactContext, this));

        return modules;
    }

    /**
     * @param reactContext
     * @return a list of view managers that should be registered with {@link UIManagerModule}
     */
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
            new RNJitsiMeetViewManager(reactContext, this)
        );
    }
}