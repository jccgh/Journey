package jcc.journey.module.http;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/20.
 */

public class HttpJourneyImpl implements IHttpJourney {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4000, TimeUnit.SECONDS)
            .build();
    @Override
    public void requestHttp(String proid, String cityid, int page, final IisSuccessful iisSuccessful) {
        Request request = new Request.Builder()
                .url("https://route.showapi.com/268-1?" +
                        "areaId=" +
                        "&cityId=" + cityid +
                        "&keyword=" +
                        "&page=" + page +
                        "&proId=" + proid +
                        "&showapi_appid=25519" +
                        "&showapi_sign=74338dc1bfed4b1586fbc4a45d63acd0")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iisSuccessful.onfailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("TAG", "onResponse: "+json);
                iisSuccessful.onsucceed(json);
            }
        });
    }
}
