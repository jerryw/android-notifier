package org.damazio.notifier.event.receivers;

import org.damazio.notifier.NotifierService.NotifierServiceModule;
import org.damazio.notifier.event.receivers.battery.BatteryEventReceiver;
import org.damazio.notifier.event.receivers.phone.VoicemailListener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class LocalEventReceiver implements NotifierServiceModule {

  private final Context context;
  private VoicemailListener voicemailListener;
  private BatteryEventReceiver batteryReceiver;
  private TelephonyManager telephonyManager;

  public LocalEventReceiver(Context context) {
    this.context = context;
  }
  
  public void onCreate() {
    if (voicemailListener != null) {
      throw new IllegalStateException("Already started");
    }

    voicemailListener = new VoicemailListener(context);
    telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    telephonyManager.listen(voicemailListener, PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR);

    // Register the battery receiver
    // (can't be registered in the manifest for some reason)
    batteryReceiver = new BatteryEventReceiver();
    context.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  }

  public void onDestroy() {
    if (voicemailListener == null) {
      throw new IllegalStateException("Not started");
    }

    context.unregisterReceiver(batteryReceiver);
    telephonyManager.listen(voicemailListener, PhoneStateListener.LISTEN_NONE);
  }
}
