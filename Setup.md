# Introduction #

These instructions tell you how to set up communication between the Android and the desktop components of the notifier. The android application can communicate with your desktop in multiple ways, and you can enable more than one at the same time for better reliability.

Below you'll find details of setting up each method - all methods assume that you have installed both the Android application and the desktop counterpart.

# Android application #

To easily find the android app in the Android Market, you can scan this QR code:

![http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=market%3A%2F%2Fsearch%3Fq%3Dpname%3Aorg.damazio.notifier&nonsense=something_that_ends_with.png](http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=market%3A%2F%2Fsearch%3Fq%3Dpname%3Aorg.damazio.notifier&nonsense=something_that_ends_with.png)

Once installed, running it will display a preferences screen:

![http://android-notifier.googlecode.com/svn/wiki/setup-screenshot.png](http://android-notifier.googlecode.com/svn/wiki/setup-screenshot.png)

In this screen you can select your desired notification methods and events.

After the application is closed, notifications are handled by a background service which displays nothing on the device. The service can be started and stopped from the preferences screen, and by default, it will be restarted every time the phone is rebooted (to prevent that, simply disable "Start service at boot" in the preferences screen).

# Mac note #

Mac OS X does not have a built-in pop-up notification mechanism, so you must have [Growl](http://growl.info/) installed. Please see their page to download and install it.

# Bluetooth method #

This method opens a serial (RFCOMM) connection between the device and the desktop, so they need to be paired. Because the device is specifically paired to your computer, you don't need any specific network connectivity, and the bluetooth connection is guaranteed to be encrypted.

**IMPORTANT**: This method is only supported in Android 2.0 or later, due to the bluetooth API not being accessible in older versions. To see what version of Android you have, go to Settings > About phone > Firmware version - if it's less than 2.0, you'll have to use the wi-fi method instead

To set it up using this method:
  1. Install and run the Android app from the Android Market
  1. Install and run the Desktop app for your OS from the Downloads section above
  1. Ensure the android device is paired with your desktop (Settings > Wifi and networks > Bluetooth settings on Android)
  1. Enable the Bluetooth notification method on the android app's settings (enabled by default)
  1. Open bluetooth options in the android app's settings (see below for a description of each option)
  1. Enable the Bluetooth notification method on the desktop app's settings (enabled by default)
  1. The desktop app will listen to and start displaying events from the android app - try making your phone ring, or sending additional test notifications.
  1. You can now exit the android app - the notifications service will be running in the background by default

The presented bluetooth options are the following:
  * **Device pairing** - select this to open Android's system bluetooth options, where you can pair your target device
  * **Target device** - once your device is paired, select this option to send notifications to a specific device. If this is set to "Any device" (the default), the notifications will be sent to the first computer device found
  * **Auto-enable bluetooth** - if enabled, notifications can be sent over bluetooth even if you keep it disabled - every time we need ot send a notification, we'll enable it, send the notification, then disable it again. The downside of this option is that it can take multiple seconds for the notification to actually be sent.

# IP method #

This method works by sending packets over the IP network - either Wifi or the 3G/HSDPA/UMTS/etc. cell phone data network. See notes below of each of these. The main downside of this method is that the communication is unencrypted (not more than your wifi's encryption, that is), meaning anyone with access to your wifi network will be able to intercept and forge notifications.

To set it up using this method:
  1. Install and run the Android app from the Android Market
  1. Install and run the Desktop app for your OS from the Downloads section above
  1. Ensure both the desktop and the device are on the same network
  1. If you have a firewall on your desktop, ensure it will allow incoming UDP packets on port 10600
  1. Enable the IP notification method on the android app's settings (enabled by default)
  1. Open the IP options in the android app's settings (see a description of the options below)
  1. Enable the IP notification method on the desktop app's settings (enabled by default)
  1. The desktop app will listen to and start displaying events from the android app - try making your phone ring, or sending additional test notifications.
  1. You can now exit the android app - the notifications service will be running in the background by default

The bluetooth options on the device are the following:
  * **Target IP address** - this is the IP address notifications will be sent to. While the default (global broadcast) is sufficient for most cases, you may want one of the following options intead:
    * **_Global broadcast_** - sends notifications to 255.255.255.255 (should work on most Wifi networks);
    * **_DHCP broadcast_** - detects the DHCP configuration of your current Wifi network and uses its broadcast address for sending notifications. If no DHCP information is set (e.g. you're using a static IP), the notifications won't be sent;
    * **_Custom address_** - allows you to type in a specific IP address or hostname to send notifications to (doesn't need to be a broadcast address). This is useful if your network doesn't allow broadcast packets, you want to prevent others in the network from receiving  your notifications, or you want to send the packets over the cell phone data network.
  * **Send with UDP** - sends the notifications over the less-reliable UDP protocol. This is the only acceptable method if using broadcast addresses.
  * **Send with TCP** - only possible when using a custom target address, this sends notifications over the reliable (but slightly slower) TCP protocol (this is recommended if you'll be sending notifications over the cell phone data network)
  * **Send over cell network** - this allows sending the notifications over the cell phone data network (3G/HSDPA/UMTS/etc) if Wifi is off. See notes below about properly setting this up.
  * **Auto-enable wifi** - if you do not wish to keep your wifi on all the time with the above option, and can tolerate notifications being delayed by a few seconds, then this option will make Wifi be turned on whenever a notification needs to be sent, and then turned off again after it's been sent. The extra delay introduced is the time it takes for your phone to join a network. Also notice this is mutually exclusive with sending over the cell network - only one of these two actions is possible when Wifi is off.
  * **Wifi sleep policy** - this is a system setting from android which controls when the Wifi will be turned off. By default, it's turned off whenever the screen is turned off - if you want to get notifications over Wifi when the screen is off, you probably want to change this default to either "never when plugged" or "never" - these options will consume a little more battery, but will ensure notifications are always delivered. Another option is to use the "Auto-enable wifi" option above.

Finally, you can choose on the desktop application to listen only to specific devices:
  1. Open the desktop app's preferences
  1. Under "Notifications can be sent by", choose "Only paired devices"
  1. Click the add button below the paired devices list to add a new device - it will show a dialog waiting for a test notification from the device
  1. Run the Android app, then choose "Sent test notification"
  1. The device is added to the paired devices list - you can edit its name for your own reference.

## Sending over Wifi ##

When sending over Wifi, ideally both the phone and the desktop should be on the same network - if they are not, it's likely that these packets won't reach the desktop. Also, if you use a custom IP, make sure that IP won't change, or make your wifi router serves DNS names for internal DHCP clients and then use the computer's hostname.

Another important thing to notice is that by default, Android will turn Wifi off when the phone is sleeping (i.e. the screen turns off). See the "Wifi sleep policy" option above for details.

## Sending over the cell phone data network ##

Setting up sending over the cell network is slightly more tricky, and in some cases may require more advanced setup on your network.
The basic idea is that a packet sent from your device must be able to reach the computer running the desktop application over port 10600 (TCP and/or UDP, depending on which you enabled).

The two most common cases are:

  1. Computer connected directly to the internet (public IP) - in this case all you have to do is ensure the computer's firewall is set up to allow incoming connections on port 10600.
  1. Computer connected to a (wifi or wired) router, which is in turn connected to the internet - in this case you'll need to set up your router's configuration to forward packets on port 10600 to the computer you wish to receive the notifications. How this is done depends on your specific router, but will usually involve either setting a static internal IP (in the DHCP settings) for the computer or having your router serves internal DNS names for DHCP clients, then setting up port forwarding to that IP/hostname.

Also, if your ISP gives you a dynamic IP (which most do), you'll also not want to use the IP address directly - instead you want to register a dynamic DNS name which always points to your current IP. There are many free services which do this for you, such as the one at [DynDNS](http://www.dyndns.org). The details of setting this up are beyond the scope of this document.


# USB method #

This method is still under development, we'll update this doc when it's ready for general use.