package com.example.funemojipacks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class HomeAdapter extends FragmentStatePagerAdapter {
    List<Fragment> home_lists;
    List<String> home_titles;

    public HomeAdapter(@NonNull FragmentManager fm,List<Fragment> lists,
                       List<String> titles)
    {
        super(fm);
        this.home_lists=lists;
        this.home_titles=titles;
    }

    @Override
    public Fragment getItem(int position){
        return home_lists.get(position);
    }

    @Override
    public int getCount()
    {
        return home_lists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return home_titles.get(position);
    }
}
