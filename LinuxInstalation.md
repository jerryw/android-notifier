# Introduction #

Here are instructions for Linux instalation.

# DEB #

You can simply download one of the deb files available and let your package manager open it or use the Debian Repository set up here on googlecode. If you prefer the second option, continue reading.

The _Software Sources_ line is the following (usually to be added in System -> Administration -> Software Sources, Third-Party Software tab):

```
deb http://android-notifier.googlecode.com/svn/repos/deb-repo stable main
```

After that, you can run:

```
sudo apt-get update
sudo apt-get install android-notifier-desktop
```

Or search for _android-notifier-desktop_ in your software manager.

Authentication keys are not provided, your linux distribution may complain about packages not authenticated. You can safely ignore this warning.

After instalation, "Android Notifier Desktop" will be available in your _Accessories_ menu.

# RPM #

There are RPM packages built for OpenSUSE and Fedora (they differ only on naming standards of architecture and dependencies), you can download and install them directly.

### My Linux Distro does not support deb or rpm packages, you insensitive clod ###

Well, you can get a zip file in the Downloads tab for Linux, but you will have to install bluetooth libraries and libnotify manually to be able to use these features and you will not have a nice menu item created automatically for you.

To be able to use bluetooth communication, you need to install bluez libs for your distro and create a symlink with the name libbluetooth.so to the latest version of libbluetooth.so.X.X.X installed on your system (on ubuntu it'd be /usr/lib/libbluetooth.so -> /usr/lib/libbluetooth.so.3.5.0). The packages that do this for some distros are:

  * libbluetooth-dev on Ubuntu
  * bluez-libs-devel on Fedora
  * bluez-devel on openSUSE

To use libnotify, look for it using your system package manager.

Don't forget to install Java 6! Both Sun Java and OpenJDK work fine, GCJ does not.

Maybe future releases will have packages for your distro...