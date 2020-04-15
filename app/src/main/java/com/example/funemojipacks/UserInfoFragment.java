package com.example.funemojipacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.me.MeAdapter;
import com.example.funemojipacks.me.RegisterFragment;
import com.example.funemojipacks.me.ShareLikeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    Button mLogoutBtn;

    private UserInfoFragment mUserInfoFragment;
    private MeFragment mMeFragment;
    private RegisterFragment mRegisterFragment;

    private View view;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_detail, container, false);

        initView();

        mLogoutBtn = (Button)view.findViewById(R.id.logout_btn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 初始化三个btm的visibility
                //jump to MeFragment
                // 管理器要用getSupportFragmentManager获取
                FragmentManager fragmentManager = getFragmentManager();
                // 开启事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // 隐藏所有Fragment
                hideFragment(transaction);

                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    transaction.add(R.id.meDetailConstraintLayout, mMeFragment);
                } else {
                    transaction.show(mMeFragment);
                }
                // 提交事务
                transaction.commit();
            }
        });

        return view;
    }

    private void initView(){
        // 实例化
        // mViewPager = (ViewPager) view.findViewById(R.id.meViewerPager);
        mTabLayout = (TabLayout) view.findViewById(R.id.meTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.meViewerPager);
        // 创建Tab
        // TabLayout.Tab tab1 = mTabLayout.newTab().setText("Shared");
        // TabLayout.Tab tab2 = mTabLayout.newTab().setText("Liked");

        // 将Tab添加到TabLayout
        // mTabLayout.addTab(tab1);
        // mTabLayout.addTab(tab2);


        stringList.add("Shared");
        stringList.add("Liked");

        list.add(new ShareLikeFragment());
        list.add(new ShareLikeFragment());

        for(String str: stringList){
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }

        // ViewPager加适配器Adapter
        MeAdapter adapter = new MeAdapter(getChildFragmentManager(), list, stringList);
        mViewPager.setAdapter(adapter);


        // 关联tab和viewPage
        mTabLayout.setupWithViewPager(mViewPager);

    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        System.out.println("Condition1!");
        if (mRegisterFragment != null) {
            System.out.println("Condition excute!");
            transaction.hide(mRegisterFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
        if (mUserInfoFragment != null) {
            transaction.hide(mUserInfoFragment);
        }
        mLogoutBtn.setVisibility(View.INVISIBLE);
    }

}

