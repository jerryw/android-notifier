package org.damazio.notifier.event;

import org.damazio.notifier.event.util.PhoneNumberUtils;
import org.damazio.notifier.prefs.Preferences;

import android.content.Context;

public class EventContext {
  private final Context context;
  private final EventManager eventManager;
  private final Preferences preferences;
  private PhoneNumberUtils numberUtils;

  public EventContext(Context context, EventManager eventManager, Preferences preferences) {
    this.context = context;
    this.eventManager = eventManager;
    this.preferences = preferences;
  }

  public synchronized PhoneNumberUtils getNumberUtils() {
    if (numberUtils == null) {
      numberUtils = new PhoneNumberUtils(context);
    }
    return numberUtils;
  }

  public Context getContext() {
    return context;
  }

  public Preferences getPreferences() {
    return preferences;
  }

  public EventManager getEventManager() {
    return eventManager;
  }
}
