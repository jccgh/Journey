package jcc.journey.module.parsejson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jcc.journey.module.Been.JourneyBeen;

/**
 * Created by Administrator on 2016/11/27.
 */

public class ParseJourneyJson {
    public List<JourneyBeen> parseJson(String json) {
        List<JourneyBeen> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
            JSONArray contentlist = pagebean.getJSONArray("contentlist");
            for (int i = 0; i < contentlist.length(); i++) {
                JSONObject jb = contentlist.getJSONObject(i);
                String summary = jb.getString("summary");
                String name = jb.getString("name");
                String content = null;
                if (jb.has("content")) {
                    content = jb.getString("content");
                }
                String address = null;
                if (jb.has("address")) {
                    address = jb.getString("address");
                }
                String attention = null;
                if (jb.has("attention")) {
                    attention = jb.getString("attention");
                }
                String coupon = null;
                if (jb.has("coupon")) {
                    coupon = jb.getString("coupon");

                }
                List<String> listimg = new ArrayList<>();
                JSONArray img = null;
                if (jb.has("picList")) {
                    img = jb.getJSONArray("picList");

                }
                if (img != null) {
                    for (int j = 0; j < img.length(); j++) {
                        JSONObject objectimg = img.getJSONObject(j);
                        String picUrlSmall = objectimg.getString("picUrlSmall");
                        listimg.add(picUrlSmall);
                    }
                }
                list.add(new JourneyBeen(name, summary, listimg, content, address, attention, coupon));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
