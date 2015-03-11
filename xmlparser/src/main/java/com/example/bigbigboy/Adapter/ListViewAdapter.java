package com.example.bigbigboy.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bigbigboy.bean.PersonBean;
import com.example.bigbigboy.xmlparser.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<PersonBean> list = null;
    private Context mContext;

    public ListViewAdapter(Context mContext, List<PersonBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<PersonBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        final PersonBean person = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            Log.d("LayoutInflater", "LayoutInflater");
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.listviewitem, arg2, false);
            viewHolder.idText = (TextView) view
                    .findViewById(R.id.idtext);
            viewHolder.name = (TextView) view
                    .findViewById(R.id.name);
            viewHolder.sex = (TextView) view.findViewById(R.id.sex);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Log.d("listLength", "listLength--->" + list.size() + "\nposition--->" + position);
        viewHolder.name.setText(person.getName());
        viewHolder.sex.setText(person.getSex());
        viewHolder.idText.setText(String.valueOf(person.getId()));
        return view;
    }

    final static class ViewHolder {
        TextView idText;
        TextView name;
        TextView sex;
    }


}
