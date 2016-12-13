package jcc.journey.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.srain.cube.views.ptr.PtrFrameLayout;
import jcc.journey.R;
import jcc.journey.module.Been.CitysBeen;
import jcc.journey.module.Been.JourneyBeen;
import jcc.journey.module.Been.ProvinceBeen;
import jcc.journey.progress.JourneyProgress;
import jcc.journey.view.activity.JourneyActivity;
import jcc.journey.view.adapter.MyListAdapter;

/**
 * Created by Administrator on 2016/11/19.
 */

public class JourneyFragment extends Fragment implements IJourney {
    private Spinner mprovince;
    private Spinner mcitys;
    private Map<String, String> map;
    private JourneyProgress progress;
    private Map<String, String> mapcitys;
    private String proid;
    private String cityid;
    private int page = 1;
    private ListView mlistview;
    private PtrFrameLayout ptrFrameLayout;
    private List<JourneyBeen> mlist;
    private ProgressBar mprogress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.journey_fragment_layout, container, false);
        initview(view);
        progress = new JourneyProgress(getActivity(), this);
        progress.start();
        initevent();
        return view;
    }

    private void initevent() {
//        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
//        ptrFrameLayout.setHeaderView(defaultHeader);
//        ptrFrameLayout.addPtrUIHandler(defaultHeader);
//        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                ++page;
//                progress.startjourney(proid,cityid,page);
//            }
//        });

        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), JourneyActivity.class);
                if (mlist != null) {
                    intent.putExtra("title",mlist.get(position).getName());
                    intent.putExtra("content", mlist.get(position).getContent());
                    intent.putExtra("attention", mlist.get(position).getAttention());
                    intent.putExtra("coupun", mlist.get(position).getCoupon());
                    intent.putExtra("address", mlist.get(position).getAddress());
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"暂无此景点信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mprovince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String province = ((TextView) view).getText().toString();
                if (province != null) {
                    proid = map.get(province);
                    progress.startcity(proid);
                    mprogress.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mcitys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = ((TextView) view).getText().toString();
                if (city != null) {
                    cityid = mapcitys.get(city);
                    if (proid != null && cityid != null) {
                        progress.startjourney(proid, cityid, 1);
                        mprogress.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initview(View view) {
        mprovince = (Spinner) view.findViewById(R.id.spinnerprovince);
        mcitys = (Spinner) view.findViewById(R.id.spinnercity);
        mlistview = (ListView) view.findViewById(R.id.listview);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptrframelayout);
        mprogress = (ProgressBar)view.findViewById(R.id.shouye_progress);

    }

    @Override
    public void start(List<ProvinceBeen> list) {
        map = new HashMap<>();
        for (ProvinceBeen been : list) {
            map.put(been.getName(), been.getId());
        }
        Set<String> set = map.keySet();
        final List<String> listprovince = new ArrayList<>();
        for (String string : set) {
            listprovince.add(string);
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mprogress.setVisibility(View.GONE);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, listprovince);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mprovince.setAdapter(adapter);
                mprovince.setSelection(17);


            }
        });

    }

    @Override
    public void startcitys(List<CitysBeen> list) {
        mapcitys = new HashMap<>();
        for (CitysBeen been : list) {
            mapcitys.put(been.getCityname(), been.getCittyid());
        }
        Set<String> set = mapcitys.keySet();
        final List<String> listcitys = new ArrayList<>();
        for (String str : set) {
            listcitys.add(str);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mprogress.setVisibility(View.GONE);

                ArrayAdapter<String> adaptercitys = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, listcitys);
                adaptercitys.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mcitys.setAdapter(adaptercitys);
            }
        });

    }

    @Override
    public void startjourneys(final List<JourneyBeen> list) {
        mlist = null;
        mlist = list;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                if(ptrFrameLayout.isRefreshing()){
//                    ptrFrameLayout.refreshComplete();
//                }
                mprogress.setVisibility(View.GONE);
                MyListAdapter adapter = new MyListAdapter(list, getActivity());
                mlistview.setAdapter(adapter);

            }
        });

    }

}
