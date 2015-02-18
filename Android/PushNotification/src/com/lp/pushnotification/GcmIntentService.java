package com.lp.pushnotification;
/**
 * A Service to which the WakefulBroadcastReceiver passes off the work of handling the GCM message, 
 * while ensuring that the device does not go back to sleep in the process 
 **/
 

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.lp.pushnotification.R;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
   
	public class GcmIntentService extends IntentService{
		Context context;
	    public static final int NOTIFICATION_ID = 1;
	    private NotificationManager mNotificationManager;
	    NotificationCompat.Builder builder;
	    public static final String TAG = "GCM Demo";
	 
	public GcmIntentService() {
	super("GcmIntentService");
	}
	 
	
	protected void onHandleIntent(Intent intent) {
	
	Bundle extras = intent.getExtras();
	String msg = intent.getStringExtra("message");
	GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
	// The getMessageType() intent parameter must be the intent you received
    // in your BroadcastReceiver.

	String messageType = gcm.getMessageType(intent);
	 
	if (!extras.isEmpty()) {
	 
	if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
	                sendNotification("Send error: " + extras.toString());
	} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
	                sendNotification("Deleted messages on server: " + extras.toString());

	}else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
	                
	               for (int i=0; i<5; i++) {
	                    Log.i(TAG, "Working... " + (i+1)
	                            + "/5 @ " + SystemClock.elapsedRealtime());
	                    try {
	                        Thread.sleep(500);
	                    } catch (InterruptedException e) {
	                    }
	                }
	                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
	                sendNotification(msg);
	                Log.i(TAG, "Received: " + extras.toString());
	}
	}
	// Release the wake lock provided by the WakefulBroadcastReceiver.
	GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	// Put the message into a notification and post it.
    private void sendNotification(String msg) {
	        mNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
	        Intent myintent = new Intent(this, MainActivity.class);
	        myintent.putExtra("message", msg);
	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	        myintent, PendingIntent.FLAG_UPDATE_CURRENT);
	        NotificationCompat.Builder mBuilder =
	                new NotificationCompat.Builder(this)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentTitle("GCM Notification")
	        .setStyle(new NotificationCompat.BigTextStyle()
	        .bigText(msg))
	        .setAutoCancel(true)
	        .setContentText(msg);
	 
	        mBuilder.setContentIntent(contentIntent);
	        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	    }
	
	}
