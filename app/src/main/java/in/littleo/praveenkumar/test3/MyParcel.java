package in.littleo.praveenkumar.test3;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

/**
 * Created by Praveen kumar on 07/06/2015.
 */
public class MyParcel implements Serializable {
    public GoogleApiClient apiClient;

    MyParcel(GoogleApiClient cli){
        apiClient=cli;
    }

}
