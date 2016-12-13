package jcc.journey.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import jcc.journey.BaseActivity;
import jcc.journey.R;
import jcc.journey.view.adapter.FragmentAdapter;
import jcc.journey.view.fragment.FindFragment;
import jcc.journey.view.fragment.JourneyFragment;
import jcc.journey.view.fragment.MineFragment;

public class MainActivity extends BaseActivity {

    private ViewPager mpager;
    private RadioGroup mgroup;
    private RadioButton mRadiobtJourney;
    private RadioButton mRadiobtGoods;
    private RadioButton mRadiobtMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"b1145a4696d55a24326cf2e54e772ba5");
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        initview();
        initevent();
        ischecked();
    }

    private void initevent() {
        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRadiobtJourney.setChecked(true);

                        ischecked();
                        break;
                    case 1:
                        mRadiobtGoods.setChecked(true);

                        ischecked();
                        break;
                    case 2:
                        mRadiobtMine.setChecked(true);
                        ischecked();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiobutton_journey:
                        mpager.setCurrentItem(0);
                        break;
                    case R.id.radiobutton_goods:
                        mpager.setCurrentItem(1);
                        break;
                    case R.id.radiobutton_mine:
                        mpager.setCurrentItem(2);
                        break;
                }
            }
        });

    }

    private void initview() {
        mpager = (ViewPager)findViewById(R.id.mainviewpager);
        mgroup = (RadioGroup)findViewById(R.id.radiogroup);
        mRadiobtJourney = (RadioButton)findViewById(R.id.radiobutton_journey);
        mRadiobtGoods = (RadioButton)findViewById(R.id.radiobutton_goods);
        mRadiobtMine = (RadioButton)findViewById(R.id.radiobutton_mine);
        mRadiobtJourney.setChecked(true);
        ischecked();
        List<Fragment> list = new ArrayList<>();
        list.add(new JourneyFragment());
        list.add(new FindFragment());
        list.add(new MineFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),list);
        mpager.setAdapter(adapter);
    }

    public void ischecked() {
       if(mRadiobtJourney.isChecked()){
           mRadiobtJourney.setTextColor(Color.GREEN);
       }else {
           mRadiobtJourney.setTextColor(Color.GRAY);
       }
        if(mRadiobtGoods.isChecked()){
            mRadiobtGoods.setTextColor(Color.GREEN);
        }else{
            mRadiobtGoods.setTextColor(Color.GRAY);
        }
        if(mRadiobtMine.isChecked()){
            mRadiobtMine.setTextColor(Color.GREEN);
        }else{
            mRadiobtMine.setTextColor(Color.GRAY);
        }
    }
}
