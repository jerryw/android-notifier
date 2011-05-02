package org.damazio.notifier.event.receivers.phone;

import org.damazio.notifier.event.EventManager;
import org.damazio.notifier.prefs.Preferences;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.protocol.Notifications.VoicemailNotification;

import android.content.Context;
import android.telephony.PhoneStateListener;

public class VoicemailListener extends PhoneStateListener {
  private final Context context;

  public VoicemailListener(Context context) {
    this.context = context;
  }
  
  @Override
  public void onMessageWaitingIndicatorChanged(boolean mwi) {
    // TODO: Start service if not live

    Preferences preferences = new Preferences(context);
    if (!preferences.isEventTypeEnabled(Event.Type.NOTIFICATION_VOICEMAIL)) {
      return;
    }

    VoicemailNotification notification = VoicemailNotification.newBuilder()
        .setHasVoicemail(mwi)
        .build();

    EventManager eventManager = new EventManager(context, preferences);
    eventManager.handleLocalEvent(Event.Type.NOTIFICATION_VOICEMAIL, notification);
  }
}
