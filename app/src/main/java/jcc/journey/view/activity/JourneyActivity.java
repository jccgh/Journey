package jcc.journey.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import jcc.journey.R;
import jcc.journey.module.user.Like;
import jcc.journey.module.user.MyUser;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;

public class JourneyActivity extends AppCompatActivity {

    private TextView mcontent;
    private TextView mattention;
    private TextView mcoupun;
    private TextView maddress;
    private String content;
    private String attention;
    private String coupun;
    private String address;
    private MyCustomTopView mtop;
    private String title;
    private ImageView mCollectImg;
    private String title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        attention = getIntent().getStringExtra("attention");
        coupun = getIntent().getStringExtra("coupun");
        address = getIntent().getStringExtra("address");

        intview();
        initevent();
    }

    private void initevent() {
        mCollectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if(bmobUser!=null){
                    like();
                }else{
                   Toast.makeText(JourneyActivity.this,"请登录后再执行此操作",Toast.LENGTH_SHORT).show();

                }
            }
        });
        mtop.setOnClickListener(new OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OncenterClick() {

            }

            @Override
            public void OnRightClick() {

            }
        });
    }

    private void like() {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        Like like = new Like();
        like.setTitle(title);
        like.setUser(myUser);
        like.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    mCollectImg.setImageResource(R.drawable.w_weifeng);
                    Toast.makeText(JourneyActivity.this,"已保存至账户",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(JourneyActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void intview() {
        mtop = (MyCustomTopView)findViewById(R.id.journey_customtoview);
        mcontent = (TextView) findViewById(R.id.journey_conent);
        mattention = (TextView)findViewById(R.id.journey_attention);
        mcoupun = (TextView)findViewById(R.id.journey_coupon);
        maddress = (TextView)findViewById(R.id.journey_address);
        mCollectImg = (ImageView)findViewById(R.id.journey_collect_img);
        mcontent.setText(content);
        mattention.setText(attention);
        mcoupun.setText(coupun);
        maddress.setText(address);
    }
}
