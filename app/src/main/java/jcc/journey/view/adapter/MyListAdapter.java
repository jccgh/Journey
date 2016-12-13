package jcc.journey.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jcc.journey.R;
import jcc.journey.module.Been.JourneyBeen;

/**
 * Created by Administrator on 2016/11/27.
 */

public class MyListAdapter extends BaseAdapter {
    private List<JourneyBeen> list;
    private Context context;
    private LayoutInflater inflater;

    public MyListAdapter(List<JourneyBeen> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            holder = new MyHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvsummary = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        if (list.get(position).getImgurls() != null&&list.get(position).getImgurls().size()>0) {

            Picasso.with(context).load(list.get(position).getImgurls().get(0)).into(holder.imageView);

        }
        holder.tvname.setText(list.get(position).getName());
        holder.tvsummary.setText(list.get(position).getSummary());
        return convertView;
    }

    class MyHolder {
        private ImageView imageView;
        private TextView tvname;
        private TextView tvsummary;
    }
}
