package br.com.wcorrea.transport.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    private Integer TIMEOUT_VALUE = 5000;

    public static String StrZeroEsquerda(String value, int n) {
        String s = value.trim();
        StringBuffer resp = new StringBuffer();
        int fim = n - s.length();
        for (int x = 0; x < fim; x++)
            resp.append('0');
        return resp + s;
    }

    public String getURL(String urlParam) throws IOException {
        try {
            URL url = new URL(urlParam);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(TIMEOUT_VALUE);
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
            br.lines().forEach(l -> jsonSb.append(l.trim()));

            is.close();
            br.close();
            return jsonSb.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
