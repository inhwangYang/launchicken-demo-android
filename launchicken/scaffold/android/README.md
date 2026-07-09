# Launchicken Android Screenshot Scaffold

1. Move `LaunchickenScreenshotTest.kt` into your Android instrumented test package.
2. Add AndroidX test dependencies if they are missing.
3. Route the app using the `launchicken_screen` intent extra.
4. Configure your screenshot library to write images under:

```txt
app/build/outputs/connected_android_test_additional_output
```
