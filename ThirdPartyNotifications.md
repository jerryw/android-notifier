# Introduction #

Android Remote Notifier supports sending notifications from 3rd party applications to the desktop through the Android client. The application must directly support this, and this document is meant for developers of such applications.

# Usage #

To take advantage of this functionality all you have to do is broadcast an intent with UserReceiver.ACTION ("org.damazio.notifier.service.UserReceiver.USER\_MESSAGE") intent action. You should not forget to give the notification a title and/or a message (having just one is ok, but at least one must be present). This can be achieved by adding extras to the intent. The names for both extras are UserReceiver.EXTRA\_TITLE ("title") and UserReceiver.EXTRA\_DESCRIPTION ("description").

```
Intent i = new Intent(UserReceiver.ACTION);
i.putExtra(UserReceiver.EXTRA_TITLE, "Some title");
i.putExtra(UserReceiver.EXTRA_DESCRIPTION, "This is my test message.");
```

Also notice that you don't need to copy the entire UserReceiver class - just use the values from those constants.

# Example #

```
public class UserTest extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Button b = new Button(this);
    b.setText("Send message");
    b.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(UserReceiver.ACTION);
        i.putExtra(UserReceiver.EXTRA_TITLE, "Some title");
        i.putExtra(UserReceiver.EXTRA_DESCRIPTION, "This is my test message.");
        sendBroadcast(i);
      }
    });
    setContentView(b);   
  }
}
```