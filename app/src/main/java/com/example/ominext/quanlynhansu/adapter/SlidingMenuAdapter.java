package com.example.ominext.quanlynhansu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ominext.quanlynhansu.R;
import com.example.ominext.quanlynhansu.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ominext on 8/2/2017.
 */

public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> menuList = new ArrayList<>();

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context, R.layout.item_sliding_menu,null);
        ImageView img=(ImageView)v.findViewById(R.id.item_image);
        TextView textView=(TextView) v.findViewById(R.id.item_title);
        //lấy ra data kiểu ItemSlideMenu từ trong menulist
        ItemSlideMenu itemSlideMenu=menuList.get(i);
        //lấy nguồn ảnh từ trong itemSlideMenu
        img.setImageResource(itemSlideMenu.getImgId());
        textView.setText(itemSlideMenu.getTitle());
        return v;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
