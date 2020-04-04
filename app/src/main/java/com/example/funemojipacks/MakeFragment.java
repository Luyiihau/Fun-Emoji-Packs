package com.example.funemojipacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MakeFragment extends Fragment {
    Button likeButton;//点赞按钮
    Button downButton;//下载按钮
    Button shareButton;//分享按钮
    ImageView emojiImage;//表情图片（按钮左边的ImageView）
    TabLayout tabLayout;//滑动子页面
    ViewPager viewPager;//
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
        likeButton = (Button) view.findViewById(R.id.likeBtn);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        downButton = (Button) view.findViewById(R.id.downBtn);
        shareButton = (Button) view.findViewById(R.id.shareBtn);
        emojiImage = (ImageView) view.findViewById(R.id.emojiImage);

        viewPager = (ViewPager) view.findViewById(R.id.makeviewPager);
        list.add(new make_appear());
        list.add(new make_face());
        list.add(new make_text());

        stringList.add("Appearance");
        stringList.add("Face");
        stringList.add("Text");

        tabLayout = (TabLayout) view.findViewById(R.id.make_tab_layout);

        for(String str: stringList){
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        MakeAdapter adapter = new MakeAdapter(getChildFragmentManager(), list,stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.make_page, null);
        super.onActivityCreated(savedInstanceState);


    }
}
