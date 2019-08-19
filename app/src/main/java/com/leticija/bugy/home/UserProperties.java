package com.leticija.bugy.home;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.leticija.bugy.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserProperties {

    String sessionCookie;
    private static HttpURLConnection con;
    Context context;
    String properties;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    UserProperties(String sessionCookie, Context context) {
        this.sessionCookie = sessionCookie;
        this.context = context;
        setProperties();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setProperties () {

        new Thread() {
            public void run() {
                String url;
                url = context.getString(R.string.base_ip) + "/home/getProperties";

                String urlParameters = "sessionCookie=" + sessionCookie;
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                try {
                    URL myurl = new URL(url);
                    con = (HttpURLConnection) myurl.openConnection();
                    //POTVRDITI DA BUDE ZAPISIVAL
                    con.setDoOutput(true);
                    con.setRequestMethod("POST"); //tip requesta
                    con.setRequestProperty("User-Agent", "Java client");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    //3.ZAPISATI RESPONSE
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.write(postData);

                    Scanner in = new Scanner(con.getInputStream());
                    in.useDelimiter("\\A");
                    if (in.hasNext()) {
                        properties = in.next();
                        System.out.println("I GOT RESPONSE: "+properties);
                        //return (in.next());
                    }
                } catch (ProtocolException e1) {
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }.start();
    }

    public String getProperties () {
        return (properties);
    }
}
