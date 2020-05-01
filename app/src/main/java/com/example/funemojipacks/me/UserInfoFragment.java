package com.example.funemojipacks.me;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {

    private Fragment userInfoFragment, loginFragment;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mLogout, mUsername, mUserID;
    private View view;

    DatabaseHelper memeDb;
    public static ArrayList<Bitmap> pics_0;
    public static ArrayList<Bitmap> pics_1;

    final

    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();

    public UserInfoFragment() {

    }

    public void savePreferences() {
        SharedPreferences pref = getActivity().getSharedPreferences("MEME", getActivity().MODE_PRIVATE);
        pref.edit().putInt("userid", MainActivity.userID).apply();
        pref.edit().putString("username", MainActivity.userName).apply();
        pref.edit().putBoolean("isLogIn", MainActivity.isLogin).apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_user_info, container, false);

        memeDb = new DatabaseHelper(getActivity());

        // initView();

        // -- get views
        // Bundle bundle = getIntent().getExtras();
        // String username = bundle.getString("username");
        // System.out.println(username);

        mTabLayout = (TabLayout) view.findViewById(R.id.meTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.meViewerPager);
        mUsername =  (TextView) view.findViewById(R.id.user_name_text);
        mUserID = (TextView) view.findViewById(R.id.user_id_text);
        stringList.add("Shared");
        stringList.add("Liked");

        mUsername.setText(String.valueOf(MainActivity.userName));
        mUserID.setText("ID: " + MainActivity.userID);

        list.add(new ShareLikeFragment(0));
        list.add(new ShareLikeFragment(1));

        for (String str : stringList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }

        getUserSharedImg();
        getUserLikedImg();

        // ViewPager加适配器Adapter
        MeAdapter adapter = new MeAdapter(getChildFragmentManager(), list, stringList);

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
                MainActivity.userName = "";
                savePreferences();
                // finish();

                FragmentManager fragmentManager = getChildFragmentManager();
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

    // 0
    public void getUserSharedImg() {

        Cursor res = memeDb.getSharedImg(String.valueOf(MainActivity.userID));

        pics_0 = new ArrayList<>();

        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "You have not shared anything! Come and Share!"
                    ,Toast.LENGTH_LONG).show();
        }
        else {
            while (res.moveToNext()) {
                byte[] in = res.getBlob(res.getColumnIndex("Pic"));
                pics_0.add(BitmapFactory.decodeByteArray(in, 0, in.length));
            }
        }
    }

    // 1
    public void getUserLikedImg() {

        Cursor res = memeDb.getLikedImg(String.valueOf(MainActivity.userID));

        pics_1 = new ArrayList<>();

        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "You have not shared anything! Come and Share!"
                    ,Toast.LENGTH_LONG).show();
        }
        else {
            while (res.moveToNext()) {
                byte[] in = res.getBlob(res.getColumnIndex("Pic"));
                pics_1.add(BitmapFactory.decodeByteArray(in, 0, in.length));
            }
        }
    }
}
