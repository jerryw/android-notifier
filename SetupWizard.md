# General preferences #

  * Select how you plan to use this app (select all that apply):
    * Send notifications from this device to a desktop (1)
    * Send notifications from another phone/tablet to this device (2)
    * Send commands from a desktop to this device (3)
    * Send commands from another phone/tablet to this device (4)

Mapping:
(1) => Send notifications
(2) => Receive notifications
(3) or (4) => Receive commands

# Communication #

  * About the desktop(s) or other device(s), are they (select all that apply):
    * On the same wifi/wired network
    * Paired for bluetooth communication (button to go to bluetooth pairing prefs)
    * Physically connected to this device by a USB cable
  * If none of the options apply, communication will happen over the internet

Mapping:
(5) => Enable wifi
(6) => Enable bluetooth
(7) => Enable USB
not ((5) or (6) or (7)) => Enable cloud

# Name #

  * Please choose a name for this device

# Security #

  * Please enter a password to encrypt all communication
  * Accept (if box non-empty) / Skip

# Pairing #

  * (If pairing fails, offer to go back to communication)
  * (warn if one of the picked methods above is not enabled)
  * Big "pair" button (must be pressed within 10 seconds on both devices)
  * Progress bar to count time
  * Confirm if detected device is the right one
  * Offer to send ping for testing
  * Offer to add more devices

## Cloud pairing note ##

  * Send a multi-hashed signature of the password to the server, if server matches two signatures in short time frame it sends the encrypted payloads to each other
  * All payloads are encrypted and server doesn't have encryption key, so it is a dumb hub (thus no privacy issues)