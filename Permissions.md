# Introduction #

This page summarizes why the android app asks for each permission it does.

# Permissions #

  * **Receive SMS, Receive MMS** - this is used so new SMS and MMS messages can be detected and shown on your desktop
  * **Read contact data** - when a phone ring notification is sent, we send the caller ID information along with it so you can see who's calling. To read the caller's information,we need this permission
  * **Create Bluetooth connections** - this is used to send notifications over bluetooth
  * **Full Internet access** - this is used to send notifications over Wifi (the notifications never really go to the internet, though)
  * **Read phone state** - this is used to detect when the phone is ringing
  * **Bluetooth administration** - this is used for enabling bluetooth before sending a notification, if the corresponding option is enabled in the preferences
  * **Change Wi-Fi state** - this is used for enabling wifi before sending a notification, if the corresponding option is enabled in the preferences
  * **Modify global system settings** - this is used to set the wifi sleep policy from the preferences screen
  * **Prevent phone from sleeping** - this is to prevent it from sleeping until we're done sending the notification