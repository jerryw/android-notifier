# Requirements #

First, make sure your system is supported.

### For Windows you need ###

  * Microsoft Bluetooth stack (this means Windows XP SP2 or newer)
  * Properly recognized and configured bluetooth hardware using Windows tools

`*` **HTC phones and some Samsung (Galaxy) ones have known issues with bluetooth communication, please star [issue 3](https://code.google.com/p/android-notifier/issues/detail?id=3) to get updates about it**

`*` Toshiba bluetooth stack is not supported

`*` Widcomm 64-bit stack is not supported

### For Linux you need ###

  * Your bluetooth hardware must be properly recognized by the operating system

### For Mac, you need ###

  * PowerPC- or Intel-based Mac OS X 10.4 (Bluetooth v1.2) or later
  * Your bluetooth hardware must be properly recognized by the operating system

`*` Mac OS 10.5 is not supported with 64bit Java, try running the 32bit version or the native Mac client

# Setup #

Please, follow these steps carefully to get bluetooth working.

  * Close Android Notifier Desktop if it is running (right-click tray icon -> Quit)
  * Pair your device with your computer using the Bluetooth Manager of your Operating System
  * Run Android Notifier Desktop again
  * Enable bluetooth in the preferences window if it's not enabled already (right-click tray icon -> Preferences...)
  * Send test notification from your device
  * Wait a couple seconds
  * If the desktop app doesn't show a test notification, send test notification again

Bluetooth takes some time to send the first notifications but it'll be pretty quick after the first ones.

If you still don't get bluetooth notifications, you probably have an HTC or Samsung phone and should star [issue 3](https://code.google.com/p/android-notifier/issues/detail?id=3), otherwise feel free to [create an issue](http://code.google.com/p/android-notifier/issues/entry).