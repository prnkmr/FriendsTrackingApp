package in.littleo.praveenkumar.test3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends ActionBarActivity {

    public GoogleApiClient mGoogleApiClient;
    public String userid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

              /*  .addConnectionCallbacks(MyService1.sender)
                .addOnConnectionFailedListener(MyService1.sender)
                .addApi(LocationServices.API)
                .build();*/
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startService(View view) {
       // Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
        Intent serv=new Intent(getBaseContext(), MyService1.class);
        serv.putExtra("userid",userid);


        startService(serv);
    }

    // Method to stop the service
    public void stopService(View view) {
      //  Toast.makeText(this,"Stop",Toast.LENGTH_SHORT).show();
        stopService(new Intent(getBaseContext(), MyService1.class));
    }
}
