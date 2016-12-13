package jcc.mycustomtopview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MyCustomTopView extends RelativeLayout {
    private Drawable leftImgView;
    private Drawable rightImgVisw;
    private String centerTextTitle;
    private  int centerTextSize;
    private int centerTextColor;
    private ImageView leftView;
    private ImageView rightView;
    private TextView centerView;
    private jcc.mycustomtopview.OnClickListener onClickListener;

    public MyCustomTopView(Context context) {
        this(context,null);
    }

    public MyCustomTopView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCustomTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyCustomTopView);
        leftImgView = ta.getDrawable(R.styleable.MyCustomTopView_leftImgView);
        leftView = new ImageView(context);
        leftView.setImageDrawable(leftImgView);

        rightImgVisw = ta.getDrawable(R.styleable.MyCustomTopView_rightImgVisw);
        rightView = new ImageView(context);
        rightView.setImageDrawable(rightImgVisw);

        centerTextTitle = ta.getString(R.styleable.MyCustomTopView_centerTextTitle);
        centerTextColor = ta.getColor(R.styleable.MyCustomTopView_centerTextColor, Color.BLACK);
        centerTextSize = ta.getDimensionPixelSize(R.styleable.MyCustomTopView_centerTextSize,18);
        centerView = new TextView(context);
        centerView.setText(centerTextTitle);
        centerView.setTextColor(centerTextColor);
        centerView.setTextSize(centerTextSize);

        ta.recycle();
       LayoutParams leftlp = new LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()));
        leftlp.addRule(ALIGN_PARENT_LEFT,TRUE);
        addView(leftView,leftlp);

        LayoutParams rightlp = new LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()));
        rightlp.addRule(ALIGN_PARENT_RIGHT,TRUE);
        addView(rightView,rightlp);

        LayoutParams centerlp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerlp.addRule(CENTER_IN_PARENT,TRUE);
        addView(centerView,centerlp);
        leftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null) {
                    onClickListener.OnLeftClick();
                }
            }
        });
        centerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null) {
                    onClickListener.OncenterClick();
                }
            }
        });
        rightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null) {
                    onClickListener.OnRightClick();
                }
            }
        });
    }
    public void setOnClickListener(jcc.mycustomtopview.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setcenterTextTitle(String title){
        centerView.setText(title);
    }
}


