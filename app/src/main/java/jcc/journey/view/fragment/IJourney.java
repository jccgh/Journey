package jcc.journey.view.fragment;

import java.util.List;

import jcc.journey.module.Been.CitysBeen;
import jcc.journey.module.Been.JourneyBeen;
import jcc.journey.module.Been.ProvinceBeen;

/**
 * Created by Administrator on 2016/11/20.
 */

public interface IJourney {
    void start(List<ProvinceBeen>list);
    void startcitys(List<CitysBeen>list);
    void startjourneys(List<JourneyBeen>list);
}
