package in.littleo.praveenkumar.test3;

import android.location.Location;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen kumar on 07/06/2015.
 */
public class Updater extends AsyncTask {
    private String lats,lons;
    private String userid;
    Updater(Location location,String userid){
        lats=String.valueOf(location.getLatitude());
        lons=String.valueOf(location.getLongitude());
        this.userid=userid;

    }
    @Override
    protected Object doInBackground(Object[] params) {


        System.out.println("Start url request "+lats+" "+lons);
        String baseURL="http://www.prn.t15.org/";


        HttpParams httpParameters = new BasicHttpParams();
// Set the timeout in milliseconds until a connection is established.
// The default value is zero, that means the timeout is not used.
        int timeoutConnection = 3000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
// Set the default socket timeout (SO_TIMEOUT)
// in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 5000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);


        HttpClient client=new DefaultHttpClient(httpParameters);
        HttpPost post=new HttpPost(baseURL+"tracker/updateMyLocation.php");
        List<NameValuePair> json=new ArrayList<NameValuePair>();
        json.add(new BasicNameValuePair("lat",lats));
        json.add(new BasicNameValuePair("lon",lons));
        json.add(new BasicNameValuePair("userid",userid));

        try {

            post.setEntity(new UrlEncodedFormEntity(json));
            System.out.println(lats+" "+lons);
            HttpResponse response = client.execute(post);
            System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
