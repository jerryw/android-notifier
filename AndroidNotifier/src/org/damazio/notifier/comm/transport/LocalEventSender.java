package org.damazio.notifier.comm.transport;

import java.util.EnumMap;
import java.util.EnumSet;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.EventListener;
import org.damazio.notifier.prefs.Preferences;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.util.DeviceUtils;

/**
 * Sends local events to remote devices.
 *
 * @author Rodrigo Damazio
 */
public class LocalEventSender implements EventListener {
  private final EnumMap<TransportType, BaseEventSender> senders =
      new EnumMap<TransportType, BaseEventSender>(TransportType.class);

  public LocalEventSender() {

  }

  public void onNewEvent(EventContext context, long eventId, Event event, boolean isLocal, boolean isCommand) {
    if (!isLocal) {
      return;
    }

    // Add source device ID to the event.
    event = event.toBuilder()
        .setSourceDeviceId(DeviceUtils.getDeviceId(context.getAndroidContext()))
        .build();

    Preferences preferences = context.getPreferences();
    EnumSet<TransportType> transportTypes = preferences.getEnabledTransports();
    for (TransportType transportType : transportTypes) {
      BaseEventSender sender = senders.get(transportType);
      sender.sendEvent(context, eventId, event);
    }
  }
}
