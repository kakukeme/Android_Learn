package com.game.kokia.imoocbaseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建假数据
        List<ItemBean> itemBeenList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemBeenList.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "我是标题" + i,
                    "我是内容" + i
            ));
        }

        // 设置适配器
        ListView listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new MyAdapter(this, itemBeenList));

    }
}
