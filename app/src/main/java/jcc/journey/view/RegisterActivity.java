package jcc.journey.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import jcc.journey.R;
import jcc.journey.module.user.MyUser;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText mpassEt;
    private EditText repeadPassEt;
    private EditText mcallEt;
    private Button mbt;
    private MyCustomTopView mtopView;
    private EditText memailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();
        initevent();
    }

    private void initevent() {
        mtopView.setOnClickListener(new OnClickListener() {
            @Override
            public void OnLeftClick() {
                RegisterActivity.this.finish();
            }

            @Override
            public void OncenterClick() {

            }

            @Override
            public void OnRightClick() {

            }
        });
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = nameEt.getText().toString();
                final String userpassword = mpassEt.getText().toString();
                final String repeadPass = repeadPassEt.getText().toString();
                final String call = mcallEt.getText().toString();

                if (username != null && repeadPass != null && call != null && !username.equals("") && !userpassword.equals("") && !call.equals("")) {

                    if (!userpassword.equals(repeadPass)) {
                        repeadPassEt.setError("两次密码不一样");

                    } else {

                        execute(username, userpassword, call);
                        Toast.makeText(RegisterActivity.this, "正在注册，请稍后", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void execute(final String username, final String userpassword, final String call) {
        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<BmobUser>() {
            @Override
            public void done(List<BmobUser> object, BmobException e) {
                if (e == null) {
                    Log.d("TAG", "done: " + "查询成功");
                    if (object.size() > 0) {
                        Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    } else {
                        register(username, userpassword, call);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "网络开小差了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void register(String username, String userpassword, String call) {
//
        BmobUser bu = new BmobUser();
        bu.setUsername(username);
        bu.setPassword(userpassword);
//注意：不能用save方法进行注册
        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
//                if(e==null) {

                RegisterActivity.this.finish();
//
//                }else{
//                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void initview() {
        mtopView = (MyCustomTopView) findViewById(R.id.register_TopView);
        nameEt = (EditText) findViewById(R.id.registername);
        mpassEt = (EditText) findViewById(R.id.registerpassword);
        repeadPassEt = (EditText) findViewById(R.id.repeadpassword);
        mcallEt = (EditText) findViewById(R.id.callet);
        mbt = (Button) findViewById(R.id.register_bt);

    }
}
