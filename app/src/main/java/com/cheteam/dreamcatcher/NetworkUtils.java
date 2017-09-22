package com.cheteam.dreamcatcher;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nicolas Juniar on 20/09/2017.
 */

public class NetworkUtils {
    public static final int TYPE_WIFI=1;
    public static final int TYPE_MOBILE=2;
    public static final int TYPE_NOT_CONNECTED=3;
    private Context context;
    public NetworkUtils(Context context)
    {
        this.context=context;
    }
    private int getConnectivityStatus()
    {
        ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork= cm.getActiveNetworkInfo();
        if(null !=activeNetwork)
        {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return  TYPE_NOT_CONNECTED;
    }

    public boolean isConnected()
    {
        boolean isConnected=false;
        isConnected=getConnectivityStatus()!=TYPE_NOT_CONNECTED;
        return  isConnected;
    }
}
