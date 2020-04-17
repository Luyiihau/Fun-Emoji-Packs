package com.example.funemojipacks.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {

    private Fragment userInfoFragment, loginFragment;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mLogout;
    private View view;

    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();

    public UserInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_user_info, container, false);

        // initView();

        // -- get views
        // Bundle bundle = getIntent().getExtras();
        // String username = bundle.getString("username");
        // System.out.println(username);

        mTabLayout = (TabLayout) view.findViewById(R.id.meTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.meViewerPager);
        stringList.add("Shared");
        stringList.add("Liked");

        list.add(new ShareLikeFragment());
        list.add(new ShareLikeFragment());

        for (String str : stringList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }

        // ViewPager加适配器Adapter
        MeAdapter adapter = new MeAdapter(getFragmentManager(), list, stringList);

        mViewPager.setAdapter(adapter);


        // 关联tab和viewPage
        mTabLayout.setupWithViewPager(mViewPager);

        // ImageView image = (ImageView) findViewById(R.id.resultImage);
        // Use list to get images


        mLogout = (TextView) view.findViewById(R.id.logout_view);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), R.string.logout_succ_tips, Toast.LENGTH_LONG).show();
                MainActivity.isLogin = false;
                // finish();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (userInfoFragment != null) {
                    transaction.hide(userInfoFragment);
                }

                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                    transaction.add(R.id.user_info_layout, loginFragment);
                } else {
                    transaction.show(loginFragment);
                }
                transaction.commit();

            }
        });

        return view;
    }
}
