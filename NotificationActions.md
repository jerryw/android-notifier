# Introduction #

Notification actions are actions you want AND to do when a notification arrives. You can specify different actions for each notification type.

There are two types of actions: copy to clipboard and execute commands.

## Copy to Clipboard ##

AND will copy the notification description to the clipboard when it arrives. The description is the content of the notification, examples:

  * "Mom - Home (1234567890)" for a phone call notification
  * "SMS from Girlfriend - Mobile (1234567890): Where are you?!" for an SMS message notification

## Execute Commands ##

_This is an advanced feature, you need to know how to use the command line (or terminal) to use this._

If you select "Execute Commands" checkbox, AND will run all commands entered in "Commands" text field and log their output in the android-notifier-desktop.log file. The executable files must be in your PATH environment variable or have the full path specified.

You can execute more than one command separating them by semicolon (;), examples:

  * video-player --pause; "/some/path/with spaces/program.exe" /do-something

You can pass notification attributes to commands being executed surrounding them with {}, AND will replace them with actual values before execution:

  * log\_phone\_call.sh "{description}"
  * email\_suspicious\_call\_to\_me {deviceId} "{title}" "{description}" "{data}"

Here are the notification attributes available:

|name|description|
|:---|:----------|
|deviceId|ID of the device sending the notification|
|id|Unique ID generated for the notification|
|type|One of RING, SMS, MMS, BATTERY, VOICEMAIL, PING, USER|
|data|Some structured data, for example, the battery level in range 0-100|
|description|User friendly description|
|title|Notification title|