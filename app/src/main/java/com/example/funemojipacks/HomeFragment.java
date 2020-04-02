package com.example.funemojipacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


// ----酷杨

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 使用LayoutInflater.inflate()来载入一个没有被载入或者想要动态载入的界面
        return inflater.inflate(R.layout.home_page, container, false);
    }

}
