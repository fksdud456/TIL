package com.example.student.p447;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemAdapter itemAdapter;
    ArrayList<Item> list;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add(new Item("아이스크림", "010-2222-3333", 30, R.drawable.icecream));
        list.add(new Item("아이스크림2", "010-0700-3333", 30, R.drawable.icecream2));
        list.add(new Item("아보카도", "010-2222-3333", 30, R.drawable.avocado));
        list.add(new Item("손하트", "010-2222-3333", 30, R.drawable.icon1));
        list.add(new Item("하트", "010-2222-4567", 30, R.drawable.icon2));
        list.add(new Item("마카롱", "010-2250-4507", 30, R.drawable.macaron2));
        list.add(new Item("마카롱롱", "010-0407-5107", 30, R.drawable.macarons));

        container = findViewById(R.id.container);
        itemAdapter = new ItemAdapter(this, container, list);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
    }

    public void clickBt(View v) {
        itemAdapter.addItem(new Item("하드", "010-0407-5107", 30, R.drawable.icon));
        itemAdapter.notifyDataSetChanged();
    }
}
