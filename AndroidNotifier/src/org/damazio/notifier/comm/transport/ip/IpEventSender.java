package org.damazio.notifier.comm.transport.ip;

import org.damazio.notifier.comm.transport.BaseEventSender;
import org.damazio.notifier.comm.transport.TransportType;
import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.prefs.Preferences;
import org.damazio.notifier.protocol.Common.Event;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class IpEventSender extends BaseEventSender {
  private WifiManager wifi;
  private ConnectivityManager connectivity;

  public IpEventSender(Context context) {
    this.wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    this.connectivity =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  @Override
  protected boolean handleEvent(EventContext context, long eventId, Event event) {
    byte[] payload = event.toByteArray();
    Preferences preferences = context.getPreferences();
    if (preferences.isIpOverTcp()) {
      // Try sending over TCP
      if (trySendOverTcp(context, event)) {
        return true;
      }
    }

    return false;
  }

  private boolean trySendOverTcp(EventContext context, Event event) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  protected TransportType getTransportType() {
    return TransportType.IP;
  }

}
