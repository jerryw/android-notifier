DEPRECATED: We decided to use protocol buffers instead. Hold for an update to this document.

# Introduction #

This protocol allows the desktop app to send requests to device, such as making a call or sending an MMS

# Details #

All command messages follow the same format as the notifications:

```
VERSION/DEVICE_ID/REQUEST_ID/COMMAND_TYPE/DATA1/DATA2
```

Command responses use the notification type RESPONSE, with the first data field being the REQUEST\_ID it is replying to.

## IP discovery for Wifi ##

Since we don't know the IP address of a device over Wifi, we send a COMMAND message as UDP broadcast, including the paired device ID - e.g.:

```
v2/12345/987654/COMMAND//
```

The device will then reply by opening a TCP connection to the requester, and sending:

```
VERSION/DEVICE_ID/NOTIFICATION_ID/RESPONSE/REQUEST_ID/
```

where REQUEST\_ID is the NOTIFICATION\_ID of the discovery request.
This connection is persisted for as long as necessary, with multiple commands and responses being sent over it.

## Bluetooth ##

Over bluetooth, we'll simply initiate a RFCOMM connection to the device.
This connection is persisted for as long as necessary, with multiple commands and responses being sent over it.

## Commands ##

The available commands will be:

  * **CALL**: initiate a phonoe call - DATA1 is the phone number
  * **SEND\_SMS**: send an SMS text message - DATA1 is the phone number, DATA2 is the message contents
  * **QUERY**: queries data from the device (see below)

## Queries ##

To allow auto-completion and searching of contacts from the device, we use the QUERY command.
In the QUERY command, DATA1 is the query type (CONTACTS for now), and DATA2 is the text query.

For contacts queries, the response is in the format defined above, with the last field being a JSON-serialized list of contacts, like:

```
[{name: "John Doe", type: "Mobile", number: "+1-212-555-1234"}, {name: "Rodrigo", type:"Work", number: "+55-31-1234-5678"}]
```