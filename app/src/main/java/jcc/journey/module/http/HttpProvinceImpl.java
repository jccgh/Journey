package jcc.journey.module.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/20.
 */

public class HttpProvinceImpl implements IHttpProvince {
    @Override
    public void requesthttp(final IisSuccessful iisSuccessful) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               HttpURLConnection huc = null;
               InputStream input = null;
               StringBuffer sb = new StringBuffer();
               try {
                   URL url = new URL("https://route.showapi.com/268-2?" +
                           "showapi_appid=25519" +
                           "&showapi_sign=74338dc1bfed4b1586fbc4a45d63acd0");
                   huc = (HttpURLConnection) url.openConnection();
                   huc.setConnectTimeout(5000);
                   huc.connect();
                   if(huc.getResponseCode()==200){
                       input = huc.getInputStream();
                       int len;
                       byte[] bt = new byte[1024];
                       while((len = input.read(bt))!=-1){
                           sb.append(new String(bt,0,len));
                       }
                       iisSuccessful.onsucceed(sb.toString());
                   }else{
                       iisSuccessful.onfailed();
                   }

               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }finally {
                   if(input!=null){
                       try {
                           input.close();
                           huc.disconnect();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
       }).start();
    }
}
