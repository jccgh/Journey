package jcc.journey.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import jcc.journey.R;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;

public class SetActivity extends AppCompatActivity {

    private MyCustomTopView mtop;
    private RelativeLayout mabout;
    private RelativeLayout mversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initview();
        initevent();
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
        mversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetActivity.this,"已是最新版本",Toast.LENGTH_LONG).show();
            }
        });
        mabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this,AboutActivity.class));
            }
        });
    }

    private void initview() {
        mtop = (MyCustomTopView)findViewById(R.id.set_customtoview);
        mabout = (RelativeLayout)findViewById(R.id.set_aboutlayout);
        mversion = (RelativeLayout)findViewById(R.id.set_version);
    }
}
