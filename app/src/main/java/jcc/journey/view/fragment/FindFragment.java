package jcc.journey.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jcc.journey.R;
import jcc.journey.module.Been.FindPagerBeen;
import jcc.journey.view.adapter.FindPagerAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/19.
 */

public class FindFragment extends Fragment{
    private ImageView mfind;
    private ViewPager mpager;
    private EditText met;
    private FindPagerAdapter adapter;
    private List<String> list;
    private ProgressBar mprogress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.findlayout,container,false);
        initview(view);
        indata();
        initevent();
        return view;
    }

    private void initevent() {
        mfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = met.getText().toString();
                if(name!=null&&!name.equals("")){
                    mprogress.setVisibility(View.VISIBLE);
                    String ur = "https://route.showapi.com/268-1?" +
                            "areaId=&" +
                            "cityId=" +
                            "&keyword=" + name +
                            "&page=" +
                            "&proId=" +
                            "&showapi_appid=25519" +
                            "&showapi_sign=74338dc1bfed4b1586fbc4a45d63acd0";
                    OkHttpClient client = new OkHttpClient.Builder().build();
                   Request request = new Request.Builder()
                            .url(ur)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {


                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            try {
                                Log.d("TAG", "onResponse: *******"+json);
                                JSONObject jsonObject = new JSONObject(json);
                                JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
                                JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
                                JSONArray contentlist = pagebean.getJSONArray("contentlist");
                                JSONObject jsonObject1 = contentlist.getJSONObject(0);
                                JSONArray jsonArray = jsonObject1.getJSONArray("picList");
                                if(jsonArray!=null&&jsonArray.length()>0){
                                    list.clear();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        list.add(jsonArray.getJSONObject(i).getString("picUrl"));

                                    }
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                             mprogress.setVisibility(View.GONE);

                                            adapter.notifyDataSetChanged();

                                        }
                                    });
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
    }

    private void indata() {
        list = new ArrayList<>();
        FindPagerBeen jzg = new FindPagerBeen();
        InputStream inputStream = getResources().openRawResource(R.raw.jiuzaigou);
        StringBuffer sb = new StringBuffer();
        byte[] bt = new byte[1024];
        int len ;
        try {
            while ((len=inputStream.read(bt))!=-1){
                sb.append(new String(bt,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String json = sb.toString();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean")
                    .getJSONArray("contentlist").getJSONObject(0).getJSONArray("picList");
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getJSONObject(i).getString("picUrl"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new FindPagerAdapter(list,getActivity());
        mpager.setAdapter(adapter);
    }

    private void initview(View view) {
        mfind = (ImageView)view.findViewById(R.id.find_tubiaoimg);
        mpager = (ViewPager)view.findViewById(R.id.find_pager);
        met = (EditText)view.findViewById(R.id.find_et);
        mprogress = (ProgressBar)view.findViewById(R.id.find_progressbar);
    }
}
