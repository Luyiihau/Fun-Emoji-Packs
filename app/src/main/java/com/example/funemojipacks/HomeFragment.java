package com.example.funemojipacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_page, container, false);
        initView();
        return view;
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.homeviewPager);
        list.add(new home_meme());
        list.add(new home_hottest());
        list.add(new home_newest());

        stringList.add("MEME");
        stringList.add("FACE");
        stringList.add("TEXT");

        tabLayout = (TabLayout) view.findViewById(R.id.home_tab_layout);
        for (String str : stringList) {
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager(), list, stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
