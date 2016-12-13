package jcc.journey.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import jcc.journey.R;
import jcc.journey.module.user.Like;
import jcc.journey.module.user.MyUser;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;

public class CollectActivity extends AppCompatActivity {

    private MyCustomTopView mtop;
    private ListView mlv;
    private List<String>listname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initview();
        initdata();
        initevent();
    }

    private void initdata() {

        listname = new ArrayList();
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if (myUser != null) {
            BmobQuery<Like> query = new BmobQuery<>();
            query.addWhereEqualTo("user", myUser);
            query.findObjects(new FindListener<Like>() {
                @Override
                public void done(List<Like> list, BmobException e) {
                    if (e == null) {
                        for (Like like : list) {
                            listname.add(like.getTitle());
                        }
                        CollectActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("TAG", "run: "+listname.size());
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectActivity.this,android.R.layout.simple_list_item_1,listname);
                                mlv.setAdapter(adapter);

                            }
                        });
                    } else {
                        Toast.makeText(CollectActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } else {
            Toast.makeText(this, "您当前未登录", Toast.LENGTH_SHORT).show();

        }


    }

    private void initevent() {
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

    private void initview() {
        mtop = (MyCustomTopView) findViewById(R.id.collect_customtoview);
        mlv = (ListView) findViewById(R.id.listview);

    }
}
