package org.damazio.notifier.event.receivers;

import static org.damazio.notifier.Constants.TAG;

import org.damazio.notifier.NotifierService;
import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.EventManager;
import org.damazio.notifier.prefs.Preferences;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.protocol.Common.Event.Type;

import com.google.protobuf.MessageLite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Base class for all broadcast receivers which receive events.
 *
 * @author Rodrigo Damazio
 */
public abstract class EventBroadcastReceiver extends BroadcastReceiver {
  private EventManager eventManager;

  @Override
  public final void onReceive(Context context, Intent intent) {
    Type eventType = getEventType();
    if (!intent.getAction().equals(getExpectedAction())) {
      Log.e(TAG, "Wrong intent received by receiver for " + eventType.name() + ": " + intent.getAction());
      return;
    }

    NotifierService.startIfNotRunning(context);

    Preferences preferences = new Preferences(context);
    if (!preferences.isEventTypeEnabled(eventType)) {
      return;
    }

    synchronized (this) {
      eventManager = new EventManager(context, preferences);

      onReceiveEvent(eventManager.getEventContext(), intent);
  
      eventManager = null;
    }
  }

  protected void handleEvent(MessageLite notification) {
    eventManager.handleLocalEvent(getEventType(), notification);
  }

  /**
   * Returns the event type being generated.
   */
  protected abstract Event.Type getEventType();

  /**
   * Returns the broadcast action this receiver handles.
   */
  protected abstract String getExpectedAction();

  /**
   * Processes the broadcast intent and calls {@link #handleEvent} with the results.
   */
  protected abstract void onReceiveEvent(EventContext context, Intent intent);
}
