package org.damazio.notifier.event.receivers.thirdparty;

import org.damazio.notifier.event.EventContext;
import org.damazio.notifier.event.receivers.EventBroadcastReceiver;
import org.damazio.notifier.protocol.Common.Event;
import org.damazio.notifier.protocol.Common.Event.Type;
import org.damazio.notifier.protocol.Notifications.ThirdPartyNotification;

import com.google.protobuf.ByteString;

import android.content.Intent;
import android.os.Bundle;

public class ThirdPartyReceiver extends EventBroadcastReceiver {
  /**
   * The intent extra to set for the notification's title.
   * Either this or {@link #EXTRA_DESCRIPTION} (or both) must be set.
   */
  private static final String EXTRA_TITLE = "title";

  /**
   * The intent extra to set for the notification's description.
   * Either this or {@link #EXTRA_TITLE} (or both) must be set.
   */
  private static final String EXTRA_DESCRIPTION = "description";

  /**
   * The intent extra to set for the notification's image.
   * The image must be a (small) array of bytes.
   */
  private static final String EXTRA_IMAGE = "image";

  @Override
  protected void onReceiveEvent(EventContext context, Intent intent) {
    String title = null;
    String description = null;
    byte[] image = null;

    // Try to read extras from intent
    Bundle extras = intent.getExtras();
    if (extras != null) {
      title = extras.getString(EXTRA_TITLE);
      description = extras.getString(EXTRA_DESCRIPTION);
      image = extras.getByteArray(EXTRA_IMAGE);
    }

    ThirdPartyNotification.Builder builder = ThirdPartyNotification.newBuilder();
    if (title != null) {
      builder.setTitle(title);
    }
    if (description != null) {
      builder.setDescription(description);
    }
    if (image != null) {
      builder.setImage(ByteString.copyFrom(image));
    }

    handleEvent(builder.build());
  }

  @Override
  protected Type getEventType() {
    return Event.Type.NOTIFICATION_THIRD_PARTY;
  }

  @Override
  protected String getExpectedAction() {
    // We use this action for backwards compatibility
    return "org.damazio.notifier.service.UserReceiver.USER_MESSAGE";
  }
}
