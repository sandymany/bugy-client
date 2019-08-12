package com.leticija.bugy.log;

import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Session {

    private static HttpURLConnection con;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String sendCredentials(final String username, final String password,String what) {

        //1. PRETVORITI PARAMETRE U BYTEARRAY

        //2.USPOSTAVITI KONEKCIJU
        String url;

        if (what.equals("login")) {
            url = "http://192.168.53.26:8000/login";
        }
        else {
            url = "http://192.168.53.26:8000/register";
        }

        String urlParameters = "username="+username+"&password="+password;
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
                    return in.next();
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
