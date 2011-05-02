package org.damazio.notifier.event.receivers.phone;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.receivers.EventBroadcastReceiver;
import org.damazio.notifier.event.util.PhoneNumberUtils;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.protocol.Common.Event.Type;
import org.damazio.notifier.protocol.Common.PhoneNumber;
import org.damazio.notifier.protocol.Notifications.RingNotification;

import android.content.Intent;
import android.telephony.TelephonyManager;

public class RingReceiver extends EventBroadcastReceiver {
  @Override
  protected void onReceiveEvent(EventContext context, Intent intent) {
    String stateStr = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    if (TelephonyManager.EXTRA_STATE_RINGING.equals(stateStr)) {
      PhoneNumberUtils numberUtils = context.getNumberUtils();

      String incomingNumberStr = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
      PhoneNumber incomingNumber = numberUtils.resolvePhoneNumber(incomingNumberStr);

      RingNotification ring = RingNotification.newBuilder()
          .setNumber(incomingNumber)
          .build();
      handleEvent(ring);
    }    
  }

  @Override
  protected Type getEventType() {
    return Event.Type.NOTIFICATION_RING;
  }

  @Override
  protected String getExpectedAction() {
    return TelephonyManager.ACTION_PHONE_STATE_CHANGED;
  }
}
