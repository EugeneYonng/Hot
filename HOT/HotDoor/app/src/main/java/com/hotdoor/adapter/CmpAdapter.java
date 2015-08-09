package com.hotdoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotdoor.list.CmpListItem;
import com.hotdoor.products.main.R;

import java.util.ArrayList;

/**
 * Created by Yip on 2015/8/5.
 */
public class CmpAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<CmpListItem> mList = new ArrayList<>();
    private int separator = 0;

    public CmpAdapter(Context context, ArrayList<CmpListItem> list) {
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
            convertView = inflater.inflate(R.layout.company_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mIcon.setImageResource(mList.get(position).getImageId());
        holder.mText.setText(mList.get(position).getText());

        return convertView;
    }

    private final static class ViewHolder {
        ImageView mIcon;
        TextView mText;

        public ViewHolder(View view) {
            this.mIcon = (ImageView) view.findViewById(R.id.iv_company_list_item);
            this.mText = (TextView) view.findViewById(R.id.text_company_list_item);
        }
    }
}
