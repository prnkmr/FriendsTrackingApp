package in.littleo.praveenkumar.test3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MyService1 extends Service {
    public GoogleApiClient mGoogleApiClient;
    private static boolean running=false;
    public  static int count=0;
    public  static boolean cond=true;
    public Sender sender;
    public String userid;
    public Object obj[]=new Object[1];

    public MyService1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Let it continue running until it is stopped.
        if(running) {
            System.out.println("Alreading Running");
            return START_STICKY;
        }

        userid=intent.getStringExtra("userid");
        running=true;
        System.out.println("Service started");

        sender=new Sender(userid);
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(sender)
                .addOnConnectionFailedListener(sender)
                .build();
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        obj[0]=mGoogleApiClient;
        sender.condd=false;

            sender.execute(obj);

        return START_STICKY;
    }

    public void onDestroy() {
        running=false;
        sender.condd=true;
        sender.cancel(true);

        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();

    }
}
