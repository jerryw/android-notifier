package org.damazio.notifier.event.log;

import android.net.Uri;
import android.provider.BaseColumns;

public interface EventLogColumns extends BaseColumns {

  public static final Uri URI = Uri.parse("content://org.damazio.notifier.eventlog/events");
  public static final String URI_AUTHORITY = "org.damazio.notifier.eventlog";
  public static final String TABLE_TYPE = "vnd.android.cursor.dir/vnd.damazio.event";
  public static final String ITEM_TYPE = "vnd.android.cursor.item/vnd.damazio.event";
  public static final String TABLE_NAME = "events";

  public static final String COLUMN_TIMESTAMP = "timestamp";
  public static final String COLUMN_SOURCE_DEVICE_ID = "source_device_id";
  public static final String COLUMN_PROCESSED = "processed";
  public static final String COLUMN_EVENT_TYPE = "event_type";
  public static final String COLUMN_PAYLOAD = "payload";

}
