package jcc.journey.module.parsejson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jcc.journey.module.Been.ProvinceBeen;

/**
 * Created by Administrator on 2016/11/20.
 */

public class ParseProvinceJson {
    public List<ProvinceBeen> parsejson(String json){
        List<ProvinceBeen>listbeen = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONObject list = showapi_res_body.getJSONObject("list");
            JSONArray listarray = list.getJSONArray("list");
            for (int i = 0; i < listarray.length(); i++) {
                JSONObject jb = listarray.getJSONObject(i);
                String id = jb.getString("id");
                String name = jb.getString("name");
                listbeen.add(new ProvinceBeen(id,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listbeen;
    }
}
