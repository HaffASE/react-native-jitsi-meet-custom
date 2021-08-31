# React-native wrapper for Jitsi-Meet

## Installation

```sh
npm install react-native-jitsi-meet-custom

or

yarn add react-native-jitsi-meet-custom
```

## Android setup

1. In `android/app/src/main/java/com/ProjectName/MainApplication.java` add the following methods:

```
import androidx.annotation.Nullable;
```

2. In `android/build.gradle`, add the following code

```
maven { // <---- Add this block
    url "https://github.com/HaffASE/jitsi-maven-repository/raw/master/releases"
}
```

If you want to use original SDK change this line to

```
maven {
    url "https://github.com/jitsi/jitsi-maven-repository/raw/master/releases"
}
```

3. In `android/app/build.grandle` add the following code

```
implementation ('org.jitsi.react:jitsi-meet-sdk:0.7.2') { transitive = true }
```

## iOS setup

SDK https://github.com/HaffASE/jitsi-meet-ios-sdk-releases

1. Modify your `Podfile` and edit platform to

```
platform :ios, '10.0'
```

2. In Xcode, under Build setting set Enable Bitcode to 'No'

1. Edit `Info.plist` file and add the following lines

```sh
<key>NSCameraUsageDescription</key>
<string>Camera Permission Description</string>
<key>NSMicrophoneUsageDescription</key>
<string>Microphone Permission Description</string>
```
