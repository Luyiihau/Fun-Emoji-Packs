package com.example.funemojipacks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funemojipacks.me.MeAdapter;
import com.example.funemojipacks.me.ShareLikeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShowUserInfoActivity extends AppCompatActivity {

    private Button mLogoutBtn;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();

    // 底部边框处理
    private LinearLayout ll_home;
    private LinearLayout ll_make;
    private LinearLayout ll_share;
    private LinearLayout ll_me;

    private ImageView iv_home;
    private ImageView iv_make;
    private ImageView iv_share;
    private ImageView iv_me;

    private TextView tv_home;
    private TextView tv_make;
    private TextView tv_share;
    private TextView tv_me;

    private Fragment homeFragment;
    private Fragment makeFragment;
    private Fragment shareFragment;
    private Fragment meFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        System.out.println(username);

        mTabLayout = (TabLayout) findViewById(R.id.meTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.meViewerPager);
        stringList.add("Shared");
        stringList.add("Liked");

        list.add(new ShareLikeFragment());
        list.add(new ShareLikeFragment());

        for(String str: stringList){
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }

        // ViewPager加适配器Adapter
        MeAdapter adapter = new MeAdapter(getSupportFragmentManager(), list, stringList);

        mViewPager.setAdapter(adapter);


        // 关联tab和viewPage
        mTabLayout.setupWithViewPager(mViewPager);

        // ImageView image = (ImageView) findViewById(R.id.resultImage);
        // Use list to get images


        mLogoutBtn = (Button)findViewById(R.id.logout_btn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), R.string.logout_succ_tips, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }



}
