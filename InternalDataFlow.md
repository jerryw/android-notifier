# Overview #

The basic flow is always the following:
  1. An event is generated somewhere
  1. The event goes to the EventManager
  1. EventManager saves the event to the event log and returns
  1. NotifierService listens to changes in the event log
  1. Upon detecting a change, it loads the new events and calls all its listeners
  1. The listeners act on the notification if it's meant for them
  1. If the user selected the non-default option to not keep the event log, event is deleted after it was processed, otherwise it's just marked as processed

The advantages of this design are the following:
  * Added reliability - events are kept in persistent storage
  * Retries - we can keep retrying for all unprocessed events whenever some phone state changes (e.g. wifi state changed) without needing a complicated sequence of methods to transmit state
  * We can easily keep an event log which the user can see later

# Locally-generated events #

# Locally-generated commands #

# Remotely-generated events #

# Remotely-generated commands #