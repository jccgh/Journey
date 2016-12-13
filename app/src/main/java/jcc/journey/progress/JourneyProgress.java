package jcc.journey.progress;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import jcc.journey.module.Been.CitysBeen;
import jcc.journey.module.Been.JourneyBeen;
import jcc.journey.module.Been.ProvinceBeen;
import jcc.journey.module.http.HttpCitysImpl;
import jcc.journey.module.http.HttpJourneyImpl;
import jcc.journey.module.http.HttpProvinceImpl;
import jcc.journey.module.http.IHttpCitys;
import jcc.journey.module.http.IHttpJourney;
import jcc.journey.module.http.IHttpProvince;
import jcc.journey.module.http.IisSuccessful;
import jcc.journey.module.parsejson.ParseCitysJson;
import jcc.journey.module.parsejson.ParseJourneyJson;
import jcc.journey.module.parsejson.ParseProvinceJson;
import jcc.journey.view.fragment.IJourney;

/**
 * Created by Administrator on 2016/11/20.
 */

public class JourneyProgress {
    private IJourney iJourney;
    private IHttpProvince iHttpProvince;
    private IHttpCitys iHttpCitys;
    private IHttpJourney iHttpJourney;
    private Context context;
    public JourneyProgress(){}
    public JourneyProgress(Context context,IJourney iJourney){
        this.iJourney = iJourney;
        this.context = context;
        iHttpProvince = new HttpProvinceImpl();
        iHttpCitys = new HttpCitysImpl();
        iHttpJourney = new HttpJourneyImpl();
    }
    public void start(){
        iHttpProvince.requesthttp(new IisSuccessful() {
            @Override
            public void onsucceed(String json) {
                Log.d("TAG", "onsucceed: "+json);
                List<ProvinceBeen>list = new ParseProvinceJson().parsejson(json);
                iJourney.start(list);
            }

            @Override
            public void onfailed() {
                Toast.makeText(context,"网络连接失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startcity(String proid){
        iHttpCitys.requestCityHttp(proid, new IisSuccessful() {
            @Override
            public void onsucceed(String json) {
                List<CitysBeen>list = new ParseCitysJson().parsejson(json);
                iJourney.startcitys(list);
            }

            @Override
            public void onfailed() {

                Toast.makeText(context,"网络连接失败",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void startjourney (String proid,String cityid,int page){
        iHttpJourney.requestHttp(proid, cityid, page, new IisSuccessful() {
            @Override
            public void onsucceed(String json) {
                List<JourneyBeen>list = new ParseJourneyJson().parseJson(json);
                Log.d("TAG", "onsucceed: "+list.size());
                iJourney.startjourneys(list);
            }

            @Override
            public void onfailed() {

                Toast.makeText(context,"网络连接失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
