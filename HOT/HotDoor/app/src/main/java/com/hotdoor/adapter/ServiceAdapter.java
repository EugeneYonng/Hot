package com.hotdoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/17.
 */
public class ServiceAdapter extends BaseAdapter {
    String[] mHeader;
    String[] mContent;
    Context mContext;
    LayoutInflater inflater;

    public ServiceAdapter(Context context, String[] header, String[] content) {
        this.mContent = content;
        this.mContext = context;
        this.mHeader = header;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mHeader.length;
    }

    @Override
    public Object getItem(int position) {
        return mHeader[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = inflater.inflate(R.layout.service_show_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextHeader.setText(mHeader[position]);
        holder.mTextContent.setText(mContent[position]);

        return convertView;
    }

    private class ViewHolder {
        TextView mTextHeader;
        TextView mTextContent;

        public ViewHolder(View view) {
            mTextHeader = (TextView) view.findViewById(R.id.tv_service_show_list_header);
            mTextContent = (TextView) view.findViewById(R.id.tv_service_show_list_content);
        }
    }

}
