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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLink {

    String response;
    String sessionCookie;
    String endpoint;
    Map<String,String> dataToSend;

    ServerLink (String sessionCookie,String endpoint) {
        this.sessionCookie = sessionCookie;
        this.endpoint=endpoint;
    }

    public String getServerResponse (final String dataToSend,final Context context) {

        Thread t = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void run() {
                String url;
                url = context.getString(R.string.base_ip) + endpoint;

                //byte[] postData = dataToSend.getBytes(StandardCharsets.UTF_8);
                try {
                    URL myurl = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
                    //POTVRDITI DA BUDE ZAPISIVAL
                    con.setDoOutput(true);

                    // POSLATI REQUEST HEADER
                    con.setRequestMethod("POST"); //tip requesta
                    con.setRequestProperty("User-Agent", "Java client");
                    con.setRequestProperty("Sessioncookie",sessionCookie);
                    con.setRequestProperty("Tosearch",dataToSend);
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    //3.POSLATI REQUEST BODY
                    //DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    //wr.write(postData);

                    Scanner in = new Scanner(con.getInputStream());
                    in.useDelimiter("\\A");
                    if (in.hasNext()) {
                        response = in.next();
                        System.out.println("I GOT RESPONSE: "+response);
                        //System.out.println("RESPONSE IS "+isResponseValid(properties,context));
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
        };
        t.start();
        try {
            t.join(); //JOIN CEKA THREAD DA ZAVRSI
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String mapToString(HashMap<String,String> mapa) {
        String string = "";
        int i = 0;
        for (String key : mapa.keySet()) {
            if(i != mapa.size()-1) {
                string += key + "=" + mapa.get(key)+"&";
            }
            else {
                string += key+"="+mapa.get(key);
            }
            i+=1;
        }
        return string;
    }
}
