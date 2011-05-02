package org.damazio.notifier.event.display;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.EventListener;
import org.damazio.notifier.protocol.Common.Event;

/**
 * Locally displays remote notifications.
 *
 * @author Rodrigo Damazio
 */
public class RemoteNotificationDisplayer implements EventListener {

  public void onNewEvent(EventContext context, long eventId, Event event, boolean isLocal, boolean isCommand) {
    // Only care about remote notifications.
    if (isLocal || isCommand) {
      return;
    }

    // TODO

    context.getEventManager().markEventProcessed(eventId);
  }
}
