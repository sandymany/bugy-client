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

    static String username;
    static String password;
    String sessionCookie;
    private static HttpURLConnection con;
    Context context;

    UserProperties(String sessionCookie,Context context) {
        this.sessionCookie = sessionCookie;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getProperties () {

        String url;
        url = context.getString(R.string.base_ip)+"/home/getProperties";

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
                return(in.next());
            }
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
