# Changes in each version #

## Android app ##

### Future ###
  * Notify missed calls separately so their notifications can be sticky
  * Allow desktop to send SMS or make calls
  * Allow desktop to ping phone ("phone finder")
  * Add USB support via TCP
  * Widget to turn service on/off
  * Run service as foreground

### v0.2.7 ###
  * Added encryption support
  * Added Locale/Tasker plugin for changing settings
  * Reorganized preferences screen
  * Fixed multiple small bugs
  * Updated Dutch and Swedish translations

### v0.2.6 ###
  * Added multiple IPs support
  * Added multiple bluetooth device support
  * Added third-party app notification support
  * Fixed many bugs reported through android reporting
  * Added support for all screen sizes
  * Added battery reporting options
  * Fixed battery reporting logic
  * Fixed screen orientation bug
  * Improve wifi preferences
  * Improved Dutch translation
  * Added Swedish translation

### v0.2.5 ###
  * Fixed custom IP validation
  * (hopefully) made the app show up for 1.6 users again
  * Added battery level filtering options
  * Added an optional notification icon

### v0.2.4 ###
  * Protocol v2
  * Give option to use TCP for Wifi
  * Option to allow sending TCP/UDP over cell phone network
  * Froyo preferences backup support
  * Fixed boot startup
  * Respects background data system setting
  * Added Dutch translation

### v0.2.3 ###
  * Notify about new voicemail
  * Added faciltiies for reporting bugs from within the app
  * Added Russian localization (thakns to admin@krom.org.ru)
  * Added Chinese localization (thanks to TitanJiang)
  * Changed icons (thanks to Kahil Young)
  * Added retries to bluetooth notifications (possible Nexus One bug fix)
  * Fixed leaked listener registration
  * Fixed bug where newly-paired bluetooth devices weren't shown
  * Changed messages to battery level can be parsed easily
  * Fixed wrong battery string

### v0.2.2 ###
  * Added compatibility for Cupcake (Android 1.5/API level 3)
  * Added real MMS support (not just text)
  * Added caller ID resolution to SMS and MMS senders

### v0.2.1 ###
  * Fixed bluetooth device selection - stops discovery before enumerating
  * Fixed delayed notifications - added looper to notificaiton thread
  * Improved log messages
  * Added missing pt-br translations

### v0.2 ###
  * Rebuilt UI using an Android standard preference UI
  * Added option to enable wifi only when about to send a notification
  * Added option to enable bluetooth only when about to send a notification
  * Added selection of which broadcast/unicast IP to use for Wifi
  * Added target bluetooth device selection
  * Added shortcut to Android's wifi sleep policy option
  * Added shortcut to Android's bluetooth pairing screen
  * Notification sending is now asynchronous (much faster/doesn't block the UI)
  * Only uses computer devices for bluetooth notifications (thanks to Steve Atwell)

### v0.1.1 ###
  * UI changes
    * Added toast notifications for most events in settings
    * Added warning when trying to enable bluetooth in Android < 2.0
    * Added a welcome dialog
    * Added an about dialog
  * Added caller ID from contacts on incoming calls
  * Service starts at boot and app start by default

### v0.1 ###
  * Initial version

## Android Locale Notification Plugin ##

### v0.2 ###
  * Improved info screen (by Peter Vegh)
  * Added hint about empty fields

### v0.1 ###
  * Initial version

## Multiplatform desktop app ##

### Future ###
  * Add missed call support
  * Allow sending SMS or making calls
  * Add usb support
  * Allow bluetooth device selection/pairing
  * Allow hiding of the menu icon (use a preference pane instead, like Growl does)
  * i18n
  * Add native Growl support on Macs

### v0.5.1 ###
  * Fixes critical [issue 209](https://code.google.com/p/android-notifier/issues/detail?id=209)
  * Fixes issue with linux version not keeping preference changes

### v0.5 ###
  * Fixes issues 24, 105, 161, 181, 195
  * Using icon _/usr/share/icons/android-notifier-desktop.png_ for tray item on Linux
  * Using black and white icon on Mac
  * Using revamped icons for notifications
  * Protocol buffers support for notifications

### v0.4 ###
  * Fixes issues 6, 9, 57, 58, 82, 87, 88, 97, 107
  * Copy notification description to clipboard when it arrives
  * Execute commands when notifications arrive (for example, pause video player on incoming call)
  * Support for third-party apps notifications
  * Command-line parameters to run without tray icon and stop if running

### v0.3 ###
  * Fixes issues 23, 25, 32, 62, 77
  * No need to click OK in Preferences window to apply changes, they are applied as soon as check boxes are clicked
  * Improved bluetooth handling with some bug fixes
  * Better Growl for Windows integration. Growl for Mac doesn't support icons for network notifications. The Growl developers are planning to support icons in version 2.
  * Check for updates and show log tray menu items

### v0.2.1 ###
Includes a fix for a critical issue reported in Ubuntu.

## Mac app ##

### Future ###
  * Merge with multiplatform app

### v0.2.8 ###
  * Added encryption support

### v0.2.7 ###
  * Added third-party notification support
  * Added support for PowerPC macs
  * Updated icons (thanks to Kahil Young)

### v0.2.6 ###
  * Allow receiving notifications over TCP

### v0.2.4 / v0.2.5 ###
  * Added "copy to clipboard" as an action
  * Added nice icons, thanks to Kahil Young
  * Added Chinese translation, thanks to Michael Jiang
  * Added Russian translation thanks to admin at krom dot org dot ru
  * Support for the newer v2 protocol (ahead of the Android app using it)

### v0.2.3 ###
  * Rebuilt preferences UI
  * Allowing other actions than just Growl notifications: mute and execute
  * Added example scripts for execute action
  * Improved 64-bit compatibility
  * Update checks changed to once a week
  * Allowing multiple simultaneous bluetooth notifications
  * Fixed "pairing required" when no devices are paired
  * Added a check that Growl is running (in addition to installed)

### v0.2.2 ###
  * Fixed SMS notifications through Growl
  * Added pt-BR locale
  * Added startup check for Growl being installed

### v0.2.1 ###
  * Fix for wifi pairing

### v0.2 ###
  * Fixed enabling/disabling of bluetooth method
  * Fixed race condition in receiving multiple notifications in different threads
  * Saving settings before pairing a new device

### v0.1 ###
  * Initial version