package com.example.student.p447;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// BaseAdapter : ListView에 Data를 뿌려주는 역할을 하는 Class
public class ItemAdapter extends BaseAdapter {
    ArrayList<Item> list;
    Context context;
    LinearLayout container;

    public ItemAdapter() {

    }

    public ItemAdapter(Context context, LinearLayout container, ArrayList<Item> list) {
        this.context = context;
        this.container = container;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).resId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vw = inflater.inflate(R.layout.item, container, true);

        TextView name = vw.findViewById(R.id.tx_name);
        TextView phone = vw.findViewById(R.id.tx_phone);
        TextView age = vw.findViewById(R.id.tx_age);
        ImageView img = vw.findViewById(R.id.imageView);

        name.setText(list.get(i).getName());
        phone.setText(list.get(i).getMobile());
        age.setText(list.get(i).getAge()+"");
        img.setImageResource(list.get(i).getResId());

        return vw;
    }

    public void addItem(Item item) {
        list.add(item);
    }
}
