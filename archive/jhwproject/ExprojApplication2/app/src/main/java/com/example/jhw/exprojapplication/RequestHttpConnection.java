package com.example.jhw.exprojapplication;

import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RequestHttpConnection {

    public String request(String _url, ContentValues _params) {

        HttpURLConnection urlConn = null;
        StringBuffer sbParams = new StringBuffer();

        if(_params == null)
            sbParams.append("");
        else {
            boolean isAnd = false;
            String key;
            String value;
            for(Map.Entry<String,Object> parameter : _params.valueSet()) {
                key = parameter.getKey();
                value = parameter.getValue().toString();
                if(isAnd)
                    sbParams.append("&");
                sbParams.append(key).append("=").append(value);
                if(!isAnd)
                    if(_params.size() >= 2)
                        isAnd = true;
            }
        }
        try {

            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");

            String strParams = sbParams.toString(); //sbParams에 정리한 파라미터들을 스트링으로 저장. 예)id=id1&pw=123;
            Log.d("loooog","@@@"+urlConn);
            OutputStream os = urlConn.getOutputStream();

            os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력.

            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.

            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d("loooog",urlConn.getResponseCode()+"에러");
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            String line;
            String page = "";

            while ((line = reader.readLine()) != null){
                page += line;
            }

            return page;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConn != null)
                urlConn.disconnect();
        }
        return null;

    }


}
