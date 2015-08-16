package com.hotdoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/16.
 */
public class PersonalSellItemAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private String[] mItem;

    public PersonalSellItemAdapter(Context context, String[] mItem) {
        this.mContext = context;
        this.mItem = mItem;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItem.length;
    }

    @Override
    public Object getItem(int position) {
        return mItem[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = inflater.inflate(R.layout.personal_sell_submit_history_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mText.setText(mItem[position]);

        return convertView;
    }

    private class ViewHolder {
        TextView mText;

        public ViewHolder(View view) {
            mText = (TextView) view.findViewById(R.id.tv_personal_sell_submit_history_item);
        }
    }
}
