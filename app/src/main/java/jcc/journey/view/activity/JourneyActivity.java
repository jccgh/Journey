package jcc.journey.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import jcc.journey.R;
import jcc.journey.module.user.Comment;
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
    private EditText mcommentEt;
    private Button mcommentBt;
    private ListView mcommentLv;
    private List<String> mListcommet;

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
        initdata();
        initevent();
    }

    private void initdata() {
        mListcommet = new ArrayList<>();
//        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("user",null);
        query.order("-createdAt");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e==null) {
                    for (Comment comment : list) {
                        mListcommet.add(comment.getComment());
                    }
                    if (mListcommet.size() > 0) {

                        JourneyActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter adapter = new ArrayAdapter(JourneyActivity.this, android.R.layout.simple_list_item_1, mListcommet);
                                mcommentLv.setAdapter(adapter);
                                //下面是重绘listview的高度
                                ListAdapter listAdapter = mcommentLv.getAdapter();
                                if(listAdapter.getCount()>1){
                                    int toatalHeight = 0;
                                    for(int i=0;i<listAdapter.getCount();i++){
                                        View view = listAdapter.getView(i,null,mcommentLv);
                                        view.measure(0,0);
                                        toatalHeight += view.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = mcommentLv.getLayoutParams();
                                    params.height = toatalHeight+(mcommentLv.getDividerHeight()*(listAdapter.getCount()-1));
                                    mcommentLv.setLayoutParams(params);
                                }

                            }
                        });
                    }
                }
            }
        });

    }

    private void initevent() {
        mCollectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null) {
                    like();
                } else {
                    Toast.makeText(JourneyActivity.this, "请登录后再执行此操作", Toast.LENGTH_SHORT).show();

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
        mcommentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser myuser = BmobUser.getCurrentUser(MyUser.class);
                if (myuser == null) {
                    Toast.makeText(JourneyActivity.this, "请登录后再评论", Toast.LENGTH_SHORT).show();
                } else {
                    String username = (String) BmobUser.getObjectByKey("username") + ":";
                    if (!mcommentEt.getText().toString().equals("") && mcommentEt != null) {

                        String comment = username + mcommentEt.getText().toString();
                        commentFf(comment);
                    } else {
                        Toast.makeText(JourneyActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    private void commentFf(String commentcontent) {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        Comment comment = new Comment();
        comment.setComment(commentcontent);
        comment.setUser(myUser);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null) {
                    Toast.makeText(JourneyActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    initdata();

                } else {
                    Toast.makeText(JourneyActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                }
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
                if (e == null) {
                    mCollectImg.setImageResource(R.drawable.w_weifeng);
                    Toast.makeText(JourneyActivity.this, "已保存至账户", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(JourneyActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void intview() {
        mtop = (MyCustomTopView) findViewById(R.id.journey_customtoview);
        mcontent = (TextView) findViewById(R.id.journey_conent);
        mattention = (TextView) findViewById(R.id.journey_attention);
        mcoupun = (TextView) findViewById(R.id.journey_coupon);
        maddress = (TextView) findViewById(R.id.journey_address);
        mCollectImg = (ImageView) findViewById(R.id.journey_collect_img);
        mcommentEt = (EditText) findViewById(R.id.journey_commentEt);
        mcommentBt = (Button) findViewById(R.id.journey_commentBt);
        mcommentLv = (ListView) findViewById(R.id.journey_listview);
        mcontent.setText(content);
        mattention.setText(attention);
        mcoupun.setText(coupun);
        maddress.setText(address);

    }
}
