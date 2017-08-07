package com.example.ominext.quanlynhansu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.ominext.quanlynhansu.adapter.SlidingMenuAdapter;
import com.example.ominext.quanlynhansu.fragment.Fragment1;
import com.example.ominext.quanlynhansu.fragment.Fragment2;
import com.example.ominext.quanlynhansu.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity {
    @BindView(R.id.sliding_menu)
    ListView slidingMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private List<ItemSlideMenu> slideMenuList;
    private SlidingMenuAdapter adapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        slideMenuList = new ArrayList<>();
        slideMenuList.add(new ItemSlideMenu(R.drawable.ic_white_list_employee, "Danh sách nhân viên"));
        slideMenuList.add(new ItemSlideMenu(R.drawable.ic_white_total_employee, "Thống kê nhân viên"));
        adapter = new SlidingMenuAdapter(this, slideMenuList);
        slidingMenu.setAdapter(adapter);
        //Hiển thị icon để đóng hoặc mở slideMenuList
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(slideMenuList.get(0).getTitle());
        slidingMenu.setItemChecked(0, true);
        drawerLayout.closeDrawer(slidingMenu);
        //Hiển thị fragment 1 khi bắt đầu
        replaceFragment(0);
        slidingMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setTitle(slideMenuList.get(i).getTitle());
                slidingMenu.setItemChecked(i, true);
                drawerLayout.closeDrawer(slidingMenu);
                replaceFragment(i);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //khi nguoi dung cham vao menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void replaceFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0:
                fragment = Fragment1.newInstance();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            default:

                break;
        }
        if (fragment != null) {
            //v4 getSupport, thường: getFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
//            drawerLayout.closeDrawer(slidingMenu);
        }
    }
}
