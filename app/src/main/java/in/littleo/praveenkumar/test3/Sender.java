package in.littleo.praveenkumar.test3;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen kumar on 06/06/2015.
 */
public class Sender extends AsyncTask implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation,mCurrentLocation;
    private double lat,lon;
    private LocationRequest mLocationRequest;
    private String baseURL="http://www.prn.t15.org/";
    private Updater updater=null;

    public static boolean condd=false;
    public static int count=0;
    public String userid;
    Sender(String userid){
        this.userid=userid;
    }
    protected Object doInBackground(Object[] params) {
        boolean cond=true;
        mGoogleApiClient = (GoogleApiClient)params[0];

        mGoogleApiClient.connect();
        while(!(isCancelled()||condd)) {

            System.out.println("Sending Location");


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    public void onConnected(Bundle bundle) {
        System.out.println("Play service Connected");
        createLocationRequest();

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();
        System.out.println(lat+" "+lon);

        String lats,lons;
        lats=String.valueOf(lat);
        lons=String.valueOf(lon);



        if(updater!=null) System.out.println(updater.getStatus());
        if((updater == null) || (updater.getStatus() == Status.FINISHED)) {
            System.out.println("create updater");
            updater = new Updater(location,userid);
            updater.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }



    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void onCancelled(){
        try {
            mGoogleApiClient.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onCancelled();

    }
}
