package org.damazio.notifier.command.executers;

import com.google.protobuf.ByteString;

import android.content.Context;

public interface CommandExecuter {

  void executeCommand(Context context, ByteString payload);

}
