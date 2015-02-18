Push Notification uses GCM and Python for server side 

Google Cloud Messaging (GCM) is a free service that helps developers send data from servers to their Android applications.
The service can send a message of up to 4 kb to an application on an Android device.
Google claims the new service has no quotas and increases battery efficiency.
GCM also supports multicast messages, multiple senders, and time-to-live messages.
GCM replaces beta version of C2DM (Android Cloud to Device Messaging).


First you have to create a Google API project.Follow this link http://developer.android.com/google/gcm/gs.html#next 
 
Second set up android application as in Android folder. Add Google Play Services to Your Project follow this link http://developer.android.com/google/play-services/setup.html#Setup.
Android device must have Google Play Service downloaded. Minimum SdkVersion=8  

Third set up python code as in Python folder.    
 
 