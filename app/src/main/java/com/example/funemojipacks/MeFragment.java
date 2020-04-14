package com.example.funemojipacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.me.MeAdapter;
import com.example.funemojipacks.me.ShareLikeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


// ----萌佳

public class MeFragment extends Fragment {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    private View view;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_page, container, false);

        initView();

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

}