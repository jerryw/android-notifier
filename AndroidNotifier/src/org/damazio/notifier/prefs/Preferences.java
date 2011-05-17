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
package org.damazio.notifier.prefs;

import java.util.HashSet;
import java.util.Set;

import org.damazio.notifier.Constants;
import org.damazio.notifier.R;
import org.damazio.notifier.protocol.Common.Event;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/**
 * Wrapper for preference access.
 *
 * @author Rodrigo Damazio
 */
public class Preferences {

  private final SharedPreferences prefs;
  private final Context context;

  private final Set<PreferenceListener> listeners = new HashSet<Preferences.PreferenceListener>();
  private final OnSharedPreferenceChangeListener prefsListener = new OnSharedPreferenceChangeListener() {
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
      notifyPreferenceChanged(key);
    }
  };

  /** Semantic listener for preference changes. */
  public static abstract class PreferenceListener {
    public void onStartForegroundChanged(boolean startForeground) {}
  }

  public Preferences(Context ctx) {
    this(ctx, ctx.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE));
  }

  public Preferences(Context ctx, SharedPreferences prefs) {
    this.context = ctx;
    this.prefs = prefs;
  }

  public void registerListener(PreferenceListener listener) {
    synchronized (listeners) {
      if (listeners.isEmpty()) {
        prefs.registerOnSharedPreferenceChangeListener(prefsListener);
      }
      listeners.add(listener);
    }
  }

  public void unregisterListener(PreferenceListener listener) {
    synchronized (listeners) {
      listeners.remove(listener);

      if (listeners.isEmpty()) {
        prefs.unregisterOnSharedPreferenceChangeListener(prefsListener);
      }
    }
  }

  private void notifyPreferenceChanged(String key) {
    if (context.getString(R.string.prefkey_start_foreground).equals(key)) {
      synchronized (listeners) {
        boolean startForeground = startForeground();
        for (PreferenceListener listener : listeners) {
          listener.onStartForegroundChanged(startForeground);
        }
      }
    }
  }

  public boolean isEventTypeEnabled(Event.Type type) {
    String prefName = context.getString(R.string.prefkey_event_type_format, type.name());
    return prefs.getBoolean(prefName, true);
  }

  public boolean startForeground() {
    return prefs.getBoolean(context.getString(R.string.prefkey_start_foreground), true);
  }

  public int getMaxBatteryLevel() {
    return prefs.getInt(context.getString(R.string.prefkey_max_battery_level), 100);
  }

  public int getMinBatteryLevel() {
    return prefs.getInt(context.getString(R.string.prefkey_min_battery_level), 0);
  }

  public int getMinBatteryLevelChange() {
    return prefs.getInt(context.getString(R.string.prefkey_min_battery_level_change), 5);
  }

  public boolean shouldPruneLog() {
    return prefs.getBoolean(context.getString(R.string.prefkey_prune_log), false);
  }

  public int getPruneLogDays() {
    String daysStr = prefs.getString(context.getString(R.string.prefkey_prune_log_days), "7");
    return Integer.parseInt(daysStr);
  }
}
