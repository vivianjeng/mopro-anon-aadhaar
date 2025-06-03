# Anon Aadhaar Mopro Bindings

It is used to generate mobile bindings for [`anon-aadhaar-react-native`](https://github.com/anon-aadhaar/anon-aadhaar-react-native)

You can use the command to build the bindings in the local

```sh
mopro build
```

> Learn more about how to install the `mopro` command: [Getting Started](https://zkmopro.org/docs/getting-started)


Then you can update the bindings in the project:

**iOS**

```sh
cp -r MoproiOSBindings <XCODE_PATH>
```

**Android**

```sh
cp -r MoproAndroidBindings/uniffi <ANDROID_PATH>/app/src/main/java && \
cp -r MoproAndroidBindings/jniLibs <ANDROID_PATH>/app/src/main
```

**React Native**

```sh
cp -r MoproiOSBindings <REACT_NATIVE_PATH>/modules/mopro/ios && \
cp -r MoproAndroidBindings/uniffi <REACT_NATIVE_PATH>/modules/mopro/android/src/main/java && \
cp -r MoproAndroidBindings/jniLibs <REACT_NATIVE_PATH>/modules/mopro/android/src/main
```

**Flutter**

```sh
cp -r MoproiOSBindings <FLUTTER_PATH>/mopro_flutter_plugin/ios && \
cp -r MoproAndroidBindings/uniffi <FLUTTER_PATH>/mopro_flutter_plugin/android/src/main/kotlin && \
cp -r MoproAndroidBindings/jniLibs <FLUTTER_PATH>/mopro_flutter_plugin/android/src/main
```