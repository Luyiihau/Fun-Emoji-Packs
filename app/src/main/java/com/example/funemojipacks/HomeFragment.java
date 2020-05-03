package com.example.funemojipacks;

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
import com.example.funemojipacks.home.home_newest;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        return inflater.inflate(R.layout.home_page, container, false);
//        initView();
//        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) Objects.requireNonNull(getView()).findViewById(R.id.homeviewPager);
        list.add(new home_newest());
        list.add(new home_hottest());

        stringList.add("NEWEST");
        stringList.add("HOTTEST");

        tabLayout = (TabLayout) getView().findViewById(R.id.home_tab_layout);
        for (String str : stringList) {
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager(), list, stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
