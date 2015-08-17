package com.hotdoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotdoor.list.CmpIntroductItem;
import com.hotdoor.products.main.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/17.
 */
public class CmpOthersAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<CmpIntroductItem> mList;
    private LayoutInflater inflater;

    public CmpOthersAdapter(Context context,ArrayList<CmpIntroductItem> item) {
        this.mContext = context;
        this.mList = item;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = inflater.inflate(R.layout.company_introduction_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!mList.get(position).getHeaderTitle().equals("*")) {
            holder.headerTitle.setText(mList.get(position).getHeaderTitle());
        }

        if(!mList.get(position).getHeaderText().equals("*")){
            holder.headerText.setText(mList.get(position).getHeaderText());
        }

        if (!mList.get(position).getContentText().equals("*")) {
            holder.contentText.setText(mList.get(position).getContentText());
        }

        if (mList.get(position).getHeaderIconResource() != 0) {
            holder.headerIcon.setImageResource(mList.get(position).getHeaderIconResource());
        }

        if(mList.get(position).getContentIcon1Resource() != 0) {
            holder.contentIcon1.setImageResource(mList.get(position).getContentIcon1Resource());
        }

        if (mList.get(position).getContentIcon2Resource() != 0){
            holder.contentIcon2.setImageResource(mList.get(position).getContentIcon2Resource());
        }
        return convertView;
    }

    private final static class ViewHolder {
        TextView headerTitle;
        TextView headerText;
        TextView contentText;
        ImageView headerIcon;
        ImageView contentIcon1;
        ImageView contentIcon2;

        public ViewHolder(View view) {
            this.headerIcon = (ImageView) view.findViewById(R.id.iv_company_header_icon);
            this.contentIcon1 = (ImageView) view.findViewById(R.id.iv_company_content_imag1);
            this.contentIcon2 = (ImageView) view.findViewById(R.id.iv_company_content_imag2);

            this.headerTitle = (TextView) view.findViewById(R.id.tv_company_header_title);
            this.headerText = (TextView) view.findViewById(R.id.tv_company_header_text);
            this.contentText = (TextView) view.findViewById(R.id.tv_company_content_text);

        }
    }

}
