package jcc.journey.module.Been;

/**
 * Created by Administrator on 2016/11/20.
 */

public class CitysBeen {
    private String cittyid;
    private String cityname;

    public CitysBeen(){}
    public CitysBeen(String cittyid, String cityname) {
        this.cittyid = cittyid;
        this.cityname = cityname;
    }

    public String getCittyid() {
        return cittyid;
    }

    public void setCittyid(String cittyid) {
        this.cittyid = cittyid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
