package jcc.journey.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jcc.journey.R;
import jcc.mycustomtopview.MyCustomTopView;
import jcc.mycustomtopview.OnClickListener;

public class SetActivity extends AppCompatActivity {

    private MyCustomTopView mtop;

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
    }

    private void initview() {
        mtop = (MyCustomTopView)findViewById(R.id.set_customtoview);
    }
}
