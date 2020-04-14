package com.example.funemojipacks.me;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MeAdapter extends FragmentStatePagerAdapter {

    List<Fragment> lists;
    List<String> titles;


    public MeAdapter(@NonNull FragmentManager fm, List<Fragment> lists, List<String> titles) {
        super(fm);
        this.lists = lists;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}