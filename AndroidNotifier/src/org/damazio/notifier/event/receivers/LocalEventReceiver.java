/*
 * Copyright 2011 Rodrigo Damazio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.damazio.notifier.event.receivers;

import org.damazio.notifier.NotifierService.NotifierServiceModule;
import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.receivers.battery.BatteryEventReceiver;
import org.damazio.notifier.event.receivers.phone.MissedCallListener;
import org.damazio.notifier.event.receivers.phone.VoicemailListener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class LocalEventReceiver implements NotifierServiceModule {

  private final EventContext eventContext;

  private VoicemailListener voicemailListener;
  private BatteryEventReceiver batteryReceiver;
  private TelephonyManager telephonyManager;
  private MissedCallListener missedCallListener;

  public LocalEventReceiver(EventContext eventContext) {
    this.eventContext = eventContext;
  }

  public void onCreate() {
    if (voicemailListener != null) {
      throw new IllegalStateException("Already started");
    }

    Context context = eventContext.getAndroidContext();

    // TODO: Start/stop each type of listener depending on preferences
    
    // Register telephony listeners
    telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    voicemailListener = new VoicemailListener(eventContext);
    missedCallListener = new MissedCallListener(eventContext);
    telephonyManager.listen(voicemailListener, PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR);
    missedCallListener.onCreate();

    // Register the battery receiver
    // (can't be registered in the manifest for some reason)
    batteryReceiver = new BatteryEventReceiver();
    context.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  }

  public void onDestroy() {
    if (voicemailListener == null) {
      throw new IllegalStateException("Not started");
    }

    eventContext.getAndroidContext().unregisterReceiver(batteryReceiver);
    telephonyManager.listen(voicemailListener, PhoneStateListener.LISTEN_NONE);
    missedCallListener.onDestroy();
  }
}
