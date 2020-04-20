package com.example.funemojipacks;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.home.HomeAdapter;
//import com.example.funemojipacks.home.LoginActivity;
import com.example.funemojipacks.home.home_hottest;
import com.example.funemojipacks.home.home_hottest_v2;
import com.example.funemojipacks.home.home_hottest_v3;
import com.example.funemojipacks.home.home_meme;
import com.example.funemojipacks.home.home_newest;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


// ----酷杨

public class HomeFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    private View view;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_page,container,false);
        initView();
        return view;
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.homeviewPager);
        list.add(new home_meme());
//        list.add(new home_hottest());
        list.add(new home_hottest_v3());
        list.add(new home_newest());

        stringList.add("MEME");
        stringList.add("HOTTEST");
        stringList.add("NEWEST");

        tabLayout = (TabLayout) view.findViewById(R.id.home_tab_layout);
        for (String str : stringList) {
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager(), list, stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (!MainActivity.isLogin) {
//            System.out.println("Not login2");
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            startActivity(intent);
//        } else
//            initView();
//    }
}
