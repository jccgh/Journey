package jcc.journey.module.parsejson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jcc.journey.module.Been.CitysBeen;

/**
 * Created by Administrator on 2016/11/20.
 */

public class ParseCitysJson {
    public List<CitysBeen> parsejson(String json){
        List<CitysBeen>listcitys = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONArray list = showapi_res_body.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject jb = list.getJSONObject(i);
                if(jb.has("cityName")){
                    continue;
                }else{
                    String cityid = jb.getString("id");
                    String cityname = jb.getString("name");
                    listcitys.add(new CitysBeen(cityid,cityname));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listcitys;
    }
}
