package jcc.journey.module.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/20.
 */

public class HttpCitysImpl implements IHttpCitys {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4000, TimeUnit.SECONDS)
            .build();
    @Override
    public void requestCityHttp(final String proid, final IisSuccessful iisSuccessful) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL ur = new URL("https://route.showapi.com/268-3?" +
                            "proId=" + proid +
                            "&showapi_appid=25519" +
                            "&showapi_sign=74338dc1bfed4b1586fbc4a45d63acd0");
                    final Request request = new Request.Builder()
                            .url(ur)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            iisSuccessful.onfailed();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            iisSuccessful.onsucceed(json);

                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
