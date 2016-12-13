package jcc.journey.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import jcc.journey.BaseActivity;
import jcc.journey.R;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;


public class LoginActivity extends BaseActivity {

    private EditText usernameEt;
    private EditText userpasswordEt;
    private Button mbt;
    private TextView mnologinTv;
    private TextView mregisterTv;
    private MyCustomTopView mtopview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        initevent();
    }

    private void initevent() {
        mtopview.setOnClickListener(new OnClickListener() {
            @Override
            public void OnLeftClick() {
//                Toast.makeText(LoginActivity.this,"left",Toast.LENGTH_SHORT).show();
                LoginActivity.this.finish();
            }

            @Override
            public void OncenterClick() {
//                Toast.makeText(LoginActivity.this,"center",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void OnRightClick() {
//                Toast.makeText(LoginActivity.this,"right",Toast.LENGTH_SHORT).show();

            }
        });
        mregisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//                LoginActivity.this.finish();
            }
        });
        mnologinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "联系我们", Toast.LENGTH_SHORT).show();
            }
        });
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                final String userpass = userpasswordEt.getText().toString();
                if (!username.equals("") && username != null && !userpass.equals("") && userpass != null) {
                    execute(username, userpass);
                } else {
                    Toast.makeText(LoginActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void execute(final String username, final String userpass) {

        BmobUser bu2 = new BmobUser();
        bu2.setUsername(username);
        bu2.setPassword(userpass);
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    Intent intent = new Intent();
                    intent.putExtra("user", username);
                    LoginActivity.this.setResult(2, intent);
                    LoginActivity.this.finish();
                } else {
//                    loge(e);
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initview() {
        usernameEt = (EditText) findViewById(R.id.username);
        userpasswordEt = (EditText) findViewById(R.id.userpassword);
        mbt = (Button) findViewById(R.id.login_bt);
        mnologinTv = (TextView) findViewById(R.id.notlogintv);
        mregisterTv = (TextView) findViewById(R.id.registertv);
        mtopview = (MyCustomTopView) findViewById(R.id.mycustomtoview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
