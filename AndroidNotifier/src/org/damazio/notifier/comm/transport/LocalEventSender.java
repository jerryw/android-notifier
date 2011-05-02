package org.damazio.notifier.comm.transport;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.EventListener;
import org.damazio.notifier.protocol.Common.Event;

/**
 * Sends local events to remote devices.
 *
 * @author Rodrigo Damazio
 */
public class LocalEventSender implements EventListener {

  public void onNewEvent(EventContext context, long eventId, Event event, boolean isLocal, boolean isCommand) {
    if (!isLocal) {
      return;
    }

  }
}
