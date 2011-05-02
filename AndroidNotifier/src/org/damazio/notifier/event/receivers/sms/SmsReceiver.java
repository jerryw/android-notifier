package org.damazio.notifier.event.receivers.sms;

import static org.damazio.notifier.Constants.TAG;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.receivers.EventBroadcastReceiver;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.protocol.Common.Event.Type;
import org.damazio.notifier.protocol.Notifications.SmsNotification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SmsReceiver extends EventBroadcastReceiver {
  private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

  @Override
  protected void onReceiveEvent(EventContext context, Intent intent) {
    // Create the notification contents using the SMS contents
    boolean notificationSent = false;
    Bundle bundle = intent.getExtras();
    if (bundle != null) {
      Object[] pdus = (Object[]) bundle.get("pdus");
      SmsDecoder decoder = new SmsDecoder(context.getContext(), context.getNumberUtils());

      for (int i = 0; i < pdus.length; i++) {
        SmsNotification sms = decoder.decodeSms(pdus[i]);
        if (sms == null) {
          continue;
        }

        handleEvent(sms);
        notificationSent = true;
      }
    }

    if (!notificationSent) {
      // If no notification sent (extra info was not there), send one without info
      Log.w(TAG, "Got SMS but failed to extract details.");
      handleEvent(null);
    }
  }

  @Override
  protected Type getEventType() {
    return Event.Type.NOTIFICATION_SMS;
  }

  @Override
  protected String getExpectedAction() {
    return ACTION;
  }
}
