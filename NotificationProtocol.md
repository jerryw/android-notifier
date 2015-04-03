# Protocol specification #

We use the same protocol over all notification methods, with the only change being the transmission medium.

## Version 2 ##

The packet format is the following string:

VERSION/DEVICE\_ID/NOTIFICATION\_ID/EVENT\_TYPE/DATA/EVENT\_CONTENTS

Where the fields are:
  * VERSION is "v2" for this protocol version
  * NOTIFICATION\_ID - a unique ID for the notification, used to eliminate duplicate notifications
  * EVENT\_TYPE - one of {RING,SMS,MMS,BATTERY,PING}
  * DATA - type-specific, non-human-readable data about the notification (e.g. percentage of battery left, SMS sender number, etc.) - never contains slashes
  * EVENT\_CONTENTS - human-readable details about the notification - may contain slashes (so all packet splitting ambiguity is solved for this field alone)

For backward compatibility, the desktop client will accept both this new format and the old one.

## Version 1 ##

It uses an extremely complex packet format:

DEVICE\_ID/NOTIFICATION\_ID/EVENT\_TYPE/EVENT\_CONTENTS

Where the fields are:
  * DEVICE\_ID - string that uniquely identifies the device sending the event
  * NOTIFICATION\_ID - a unique ID for the notification, used to eliminate duplicate notifications
  * EVENT\_TYPE - one of {RING,SMS,MMS,BATTERY,PING}
  * EVENT\_CONTENTS - any text that carries additional information to be displayed with the notification

EVENT\_CONTENTS can also contain embedded slashes without any need for escaping - everything after the second slash is considered part of it.

# Wifi (UDP) #

We send a broadcast packet on port 10600 to 255.255.255.255 (configurable - may also use the DHCP address or a custom IP) with the above contents from the device. The desktop app listens on anything that arrives in that port.

# Wifi (TCP) #

Similar to the UDP version, but instead we open a TCP socket on porto 10600 to the custom IP entered by the user.

# Bluetooth #

We open a RFCOMM connection from the device using UUID 7674047E-6E47-4BF0-831F-209E3F9DD23F and then send a packet over it with the above contents. We attempt to open it to every paired device until we find one that accepts it (allow the user to select the device in the future?). The desktop app publishes the service with that UUID and listens on connections to it.

# USB #

USB mode uses the debug bridge (ADB) to forward TCP connections between the device and the host.