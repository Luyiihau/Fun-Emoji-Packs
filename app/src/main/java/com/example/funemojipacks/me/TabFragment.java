package com.example.funemojipacks.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    private View view;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.make_page, container, false);

        initView();

        return view;
    }


    private void initView(){
        /*
        viewPager = (ViewPager) view.findViewById(R.id.meViewerPager);
        list.add(new showPicFrags());
        list.add(new showPicFrags());


        stringList.add("Shared");
        stringList.add("Liked");

        tabLayout = (TabLayout) view.findViewById(R.id.meTabLayout);

        for(String str: stringList){
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        MeAdapter adapter = new MeAdapter(getChildFragmentManager(), list,stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

         */

    }

}
