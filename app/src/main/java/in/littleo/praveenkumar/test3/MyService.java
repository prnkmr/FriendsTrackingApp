package in.littleo.praveenkumar.test3;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MyService extends Service {
    public  static int count=0;
    public  static boolean cond=true;
    public static Sender sender;
    public GoogleApiClient mGoogleApiClient;
    public Object obj[]=new Object[1];
    public MyService() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(sender)
                .addOnConnectionFailedListener(sender)
                .addApi(LocationServices.API)
                .build();
        obj[0]=mGoogleApiClient;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        System.out.println("Service started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        sender.condd=false;

        sender.execute(obj);

        return START_STICKY;
    }



    public void onDestroy() {
        sender.condd=true;
        sender.cancel(true);

        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();

    }
}
