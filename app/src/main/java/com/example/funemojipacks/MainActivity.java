package com.example.funemojipacks;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.funemojipacks.me.LoginFragment;
import com.example.funemojipacks.me.UserInfoFragment;


public class MainActivity extends FragmentActivity implements OnClickListener {
    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_make;
    private LinearLayout ll_share;
    private LinearLayout ll_me;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_make;
    private ImageView iv_share;
    private ImageView iv_me;

    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_make;
    private TextView tv_share;
    private TextView tv_me;

    // 4个Fragment
    private Fragment homeFragment;
    private Fragment makeFragment;
    private Fragment shareFragment;
    private Fragment meFragment;
    private Fragment userInfoFragment;
    private Fragment loginFragment;

    private String[] mStrs = {"kk", "kk", "wskx", "wksx"};
    private SearchView mSearchView;
    private ListView lListView;

    public static Boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSearchView = (SearchView) findViewById(R.id.searchView);
//        lListView = (ListView) findViewById(R.id.listView);
//        lListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
//        lListView.setTextFilterEnabled(false);


//        // 设置搜索文本监听
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // 当点击搜索按钮时触发该方法
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            // 当搜索内容改变时触发该方法
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText)){
//                    lListView.setFilterText(newText);
//                }else{
//                    lListView.clearTextFilter();
//                }
//                return false;
//            }
//        });

        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0);

    }

    @Override
    protected void onResume(){
        int id = getIntent().getIntExtra("id", 0);
        if(id==3){
            initFragment(3);
        }
        super.onResume();
    }

    public void initFragment(int index) {
        // 管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (makeFragment == null) {
                    makeFragment = new MakeFragment();
                    transaction.add(R.id.fl_content, makeFragment);
                } else {
                    transaction.show(makeFragment);
                }
                break;
            case 2:
                if (shareFragment == null) {
                    shareFragment = new ShareFragment();
                    transaction.add(R.id.fl_content, shareFragment);
                } else {
                    transaction.show(shareFragment);
                }
                break;
            case 3:
                if (isLogin){
                    if (userInfoFragment == null) {
                        userInfoFragment = new UserInfoFragment();
                        transaction.add(R.id.fl_content, userInfoFragment);
                    } else {
                        transaction.show(userInfoFragment);
                    }
                }
                else{
                    if (loginFragment == null) {
                        loginFragment = new LoginFragment();
                        transaction.add(R.id.fl_content, loginFragment);
                    } else {
                        transaction.show(loginFragment);
                    }
                }
                break;
            default:
                break;
        }

        // 提交事务
        transaction.commit();

    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (makeFragment != null) {
            transaction.hide(makeFragment);
        }
        if (shareFragment != null) {
            transaction.hide(shareFragment);
        }
        if (userInfoFragment != null) {
            transaction.hide(userInfoFragment);
        }
        if (loginFragment != null) {
            transaction.hide(loginFragment);
        }

    }

    public void initEvent() {
        // 设置按钮监听
        ll_home.setOnClickListener(this);
        ll_make.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_me.setOnClickListener(this);

    }

    public void initView() {

        // 底部菜单4个Linearlayout
        this.ll_home = (LinearLayout) findViewById(R.id.home);
        this.ll_make = (LinearLayout) findViewById(R.id.make);
        this.ll_share = (LinearLayout) findViewById(R.id.share);
        this.ll_me = (LinearLayout) findViewById(R.id.me);

        // 底部菜单4个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_make = (ImageView) findViewById(R.id.iv_make);
        this.iv_share = (ImageView) findViewById(R.id.iv_share);
        this.iv_me = (ImageView) findViewById(R.id.iv_me);

        // 底部菜单4个菜单标题
        this.tv_home = (TextView) findViewById(R.id.tv_home);
        this.tv_make = (TextView) findViewById(R.id.tv_make);
        this.tv_share = (TextView) findViewById(R.id.tv_share);
        this.tv_me = (TextView) findViewById(R.id.tv_me);

    }

    @Override
    public void onClick(View v) {

        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.home:
                iv_home.setImageResource(R.drawable.home_press);
                tv_home.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.make:
                iv_make.setImageResource(R.drawable.make_press);
                tv_make.setTextColor(0xff1B940A);
                initFragment(1);
                break;
            case R.id.share:
                iv_share.setImageResource(R.drawable.share_press);
                tv_share.setTextColor(0xff1B940A);
                initFragment(2);
                break;
            case R.id.me:
                iv_me.setImageResource(R.drawable.me_press);
                tv_me.setTextColor(0xff1B940A);
                initFragment(3);
                break;

            default:
                break;
        }

    }

    private void restartBotton() {
        // ImageView置为灰色
        iv_home.setImageResource(R.drawable.home);
        iv_make.setImageResource(R.drawable.make);
        iv_share.setImageResource(R.drawable.share);
        iv_me.setImageResource(R.drawable.me);
        // TextView置为灰色
        tv_home.setTextColor(Color.GRAY);
        tv_make.setTextColor(Color.GRAY);
        tv_share.setTextColor(Color.GRAY);
        tv_me.setTextColor(Color.GRAY);
    }

    // Me页面的底部边框跳转
    public void getDifferentFragment(int tabNum){
        if(tabNum == 0){
            Toast.makeText(getApplicationContext(), "Tab0", Toast.LENGTH_LONG).show();
        }
        else if(tabNum == 1){
            Toast.makeText(getApplicationContext(), "Tab1", Toast.LENGTH_LONG).show();
        }
        else if(tabNum == 2){
            Toast.makeText(getApplicationContext(), "Tab2", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Tab3", Toast.LENGTH_LONG).show();
        }
    }
}