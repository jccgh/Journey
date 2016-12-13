package jcc.journey.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import jcc.journey.R;
import jcc.journey.view.LoginActivity;
import jcc.journey.view.activity.CollectActivity;
import jcc.journey.view.activity.SetActivity;

/**
 * Created by Administrator on 2016/11/19.
 */

public class MineFragment extends Fragment {
    private TextView mtvlogin;
    private RelativeLayout mcollect;
    private RelativeLayout mset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.minelayout,container,false);

        initview(view);
        initevent();
        return view;
    }

    private void initevent() {
        mcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CollectActivity.class));
            }
        });
        mset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
            }
        });
        mtvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                String text = tv.getText().toString();
                if(text.equals("请登录")){

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("是否退出登录");
                    dialog.setNeutralButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BmobUser.logOut();
                            inituser();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private void inituser() {
        if(BmobUser.getCurrentUser()!=null){
            String user = (String) BmobUser.getObjectByKey("username");
            if (user != null) {
                mtvlogin.setText(user);
            }

        }else{
            mtvlogin.setText("请登录");

        }
    }
    private void initview(View view) {
        mtvlogin = (TextView)view.findViewById(R.id.tv_login);
        mcollect = (RelativeLayout)view.findViewById(R.id.mine_souchang);
        mset = (RelativeLayout)view.findViewById(R.id.mine_shezhi);
    }

    @Override
    public void onResume() {
        super.onResume();
        inituser();
    }
}
