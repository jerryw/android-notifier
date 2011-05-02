package org.damazio.notifier.event;

import org.damazio.notifier.protocol.Common.Event;

public interface EventListener {

  void onNewEvent(EventContext context, long eventId, Event event, boolean isLocal, boolean isCommand);

}
