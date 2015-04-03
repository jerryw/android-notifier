# Functionality #

The system provides the following functionalities:
  1. Detecting relevant events
  1. Relaying these events over a medium with a certain protocol
  1. Receiving the notifications sent
  1. Taking actions as a consequence of the relayed events
  1. Selecting commands to be issued
  1. Relaying these commands over a medium with a certain protocol
  1. Receiving the commands sent
  1. Taking actions as a consequence of the relayed commands

TODO: Insert diagram

# Responsibilities #

  * **Android application**: the Android application is always responsible for items 1 and 8 above - it's always the one detecting events, and it's always the one taking actions on commands
  * **Notification relayer**: the Android relayer is responsible for item 2 above - each relayer supports one or more notification methods and/or protocols. The Android application itself is a relayer for some methods and protocols, but other relayers may support additional ones.
  * **Notification receiver**: a receiver is an application, server or device which receives the notifications and takes some action on it (items 3 and 4 above) - e.g. displaying the notification. The desktop application is one such application, but others should also be supported (e.g. showing notifications on your TV, Jabber/GTalk servers, etc.)
  * **Command interface**: a command interface is one that's able to select commands to send to a device, and actually do the sending of them over some protocol (items 5 and 6 above)
  * **Command receiver**: on the Android side, this is the component that receives notifications using some medium/protocol (item 7 above). The Android application itself supports receiving commands over a few media/protocols, but third-party plugins should also be able to do the receiving and pass the commands for the application to execute.

# Notification Relayers #

TODO: Describe how third-party relayers work

# Command receivers #

TODO: Describe how third-party receivers work