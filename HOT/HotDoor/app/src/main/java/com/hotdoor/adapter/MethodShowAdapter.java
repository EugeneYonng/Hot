package com.hotdoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hotdoor.list.MtdListItem;
import com.hotdoor.products.main.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/15.
 */
public class MethodShowAdapter extends BaseAdapter{

    private ArrayList<MtdListItem> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public MethodShowAdapter(Context context, ArrayList<MtdListItem> list) {
        this.mContext = context;
        this.mList = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = inflater.inflate(R.layout.method_show_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mIcon.setImageResource(mList.get(position).getImagResource());
        holder.title.setText(mList.get(position).getTitle());
        holder.introduct.setText(mList.get(position).getIntroduce());
        return convertView;
    }

    private final static class ViewHolder {
        ImageView mIcon;
        TextView title;
        TextView introduct;

        public ViewHolder(View view) {
            this.mIcon = (ImageView) view.findViewById(R.id.iv_method_show_list_icon);
            this.title = (TextView) view.findViewById(R.id.tv_method_list_header);
            this.introduct = (TextView)view.findViewById(R.id.tv_method_show_list_text);
        }
    }
}
