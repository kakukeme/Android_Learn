package com.game.kokia.imoocbaseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kokia on 2017/4/18.
 */

public class MyAdapter extends BaseAdapter {

    // 映射数据
    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    private long mSumTime;

    // 通过构造方法，将数据源与数据适配器进行关联；
    public MyAdapter(Context context, List<ItemBean> list) {

        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    // 返回ListView需要显示的数据数量
    @Override
    public int getCount() {
        return mList.size();
    }

    // 返回指定索引对应的数据项
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    // 对应的索引项
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 返回每一项的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 逗比式===============================
        /*View view = mInflater.inflate(R.layout.item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView content = (TextView) view.findViewById(R.id.tv_content);

        // 设置数据
        ItemBean bean = mList.get(position);
        imageView.setImageResource(bean.ItemImageResId);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);

        return view;*/
        // 逗比式===============================


        // 普通式===============================
        // 为空:View未实例化，缓存池中无缓存；需要创建；
        /*if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView content = (TextView) convertView.findViewById(R.id.tv_content);

        ItemBean bean = mList.get(position);
        imageView.setImageResource(bean.ItemImageResId);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);

        return convertView;*/
        // 普通式===============================

        // 文艺式===============================
        long start = System.nanoTime();

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemBean bean = mList.get(position);
        viewHolder.imageView.setImageResource(bean.ItemImageResId);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);

        long end = System.nanoTime();
        long dValue = end - start;
        mSumTime += dValue;

        Log.d("xys", String.valueOf(mSumTime));

        return  convertView;
        // 文艺式===============================
    }

    // ViewHolder用于缓存控件，避免重复的findViewById
    class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
