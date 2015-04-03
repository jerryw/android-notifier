TODO: Automate these

# Bluetooth method #

  * Disable wifi method
  * Enable bluetooth method
  * Target: any
  * Auto-enable: false
  * **send test, verify arrives**
  * Target: computer name
  * **send test, verify arrives**
  * Auto-enable: true
  * **send test, verify arrives**
  * **verify bluetooth turned stayed on**
  * Disable device bluetooth
  * **send test, verify arrives** (delayed)
  * **verify bluetooth turned off again**

= Wifi method

  * Disable bluetooth method
  * Enable wifi method
  * Target IP: global
  * Auto-enable: false
  * **send test, verify arrives**
  * Target IP: custom
  * **send test, verify arrives**
  * Target IP: dhcp
  * **send test, verify arrives**
  * Auto-enable: true
  * **send test, verify arrives**
  * **verify wifi stays on**
  * Disable device's wifi
  * **send test, verify arrives** (delayed, may hiccup on android's wifi scan)
  * **verify wifi turned off again**

# Multiple methods #

  * Enable bluetooth method
  * Enable wifi method
  * Enable device's wifi
  * Enable device's bluetooth
<<<<<<< local
  * **send test, verify arrives**=======
  * 