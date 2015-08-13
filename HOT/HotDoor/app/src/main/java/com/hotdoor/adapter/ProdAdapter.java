package com.hotdoor.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotdoor.list.ProListItem;
import com.hotdoor.products.main.R;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ProdAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ProListItem> arrayItem;
    private LayoutInflater inflater;

    public ProdAdapter(Context context, ArrayList<ProListItem> Item) {
        this.context = context;
        arrayItem = Item;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        Log.d("adapter"," "+arrayItem.size());
        return arrayItem.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null) {
            Log.d("getView","convertView is null");
            convertView = inflater.inflate(R.layout.product_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        Log.d("getView","position"+position);
        holder.mIcon.setImageResource(arrayItem.get(position).getImageResource());
        holder.textTitle.setText(arrayItem.get(position).getTitle());
        holder.textProdModel.setText(arrayItem.get(position).getProductModel());

        return convertView;
    }


    private final static class ViewHolder {
        ImageView mIcon;
        ShimmerTextView textTitle;
        ShimmerTextView textProdModel;

        public ViewHolder(View view) {
            this.mIcon = (ImageView) view.findViewById(R.id.iv_product_list_icon);
            this.textTitle = (ShimmerTextView) view.findViewById(R.id.stv_product_list_title1);
            this.textProdModel = (ShimmerTextView) view.findViewById(R.id.stv_product_list_title2);
        }
    }
}
