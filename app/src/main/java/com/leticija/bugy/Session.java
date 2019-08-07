package com.leticija.bugy;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Session {

    private static HttpURLConnection con;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String sendCredentials(final String username, final String password) throws MalformedURLException,
            ProtocolException, IOException {
        final StringBuilder content = new StringBuilder();

        //1. PRETVORITI PARAMETRE U BYTEARRAY

        //2.USPOSTAVITI KONEKCIJU
        new Thread(new Runnable() {
            @Override
            public void run() {
                //StringBuilder content = null;
                String url = "http://192.168.53.26:8000/login";
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
                    try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                        wr.write(postData);
                    }
                    //StringBuilder content;
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                        String line;
                        //content = new StringBuilder();
                        while ((line = in.readLine()) != null) {
                            content.append(line);
                            content.append(System.lineSeparator());
                        }
                    }

                    System.out.println(content.toString());
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    con.disconnect();
                }
                //return (content.toString());
            }
        }).start();
        return (content.toString());
    }
}
