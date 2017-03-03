package com.chowen.apackage.testkitdemo.dianshang_home_page.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;

import java.util.List;

/**
 * @author zhouwen  gzzhouwen1@corp.netease.com
 * @since 2017/2/27
 */

public class HomeListAdapter extends BaseAdapter {

    private List<String> mList;

    private Context mContext;

    public HomeListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public HomeListAdapter(Context context, List<String> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("天猫");
        return convertView;
    }

    class ViewHolder{
        private TextView textView;
        public ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.text);
        }
    }
}
