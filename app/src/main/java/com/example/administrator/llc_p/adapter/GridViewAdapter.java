package com.example.administrator.llc_p.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.llc_p.R;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Map<String, Object>> dataList;

    public GridViewAdapter(Context c , List<Map<String, Object>> dataList){
        this.dataList = dataList;
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GirdHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_gridview, null);
            holder = new GirdHolder();
            holder.phone_function_pic =  convertView.findViewById(R.id.iv_item);
            holder.phone_function_name = convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        }else{
            holder = (GirdHolder) convertView.getTag();
        }
        Map<String, Object> stringObjectMap = dataList.get(position);
        Integer  img = (Integer) stringObjectMap.get("img");
        String  text  = (String) stringObjectMap.get("text");
        holder.phone_function_pic.setImageResource(img);
        holder.phone_function_name.setText(text);
        return convertView;
    }

    private class GirdHolder{
        ImageView phone_function_pic;
        TextView phone_function_name;
    }

}
