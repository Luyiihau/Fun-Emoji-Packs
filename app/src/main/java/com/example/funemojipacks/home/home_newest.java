package com.example.funemojipacks.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.R;

public class home_newest extends Fragment {

    private Context context;
    private GridView gridView;
    private View view;

    public home_newest() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_newest, container, false);
        initView();
        return view;
    }

    private void initView() {
        final int[] faces = {R.drawable.make1, R.drawable.make2, R.drawable.make3,
                R.drawable.make4, R.drawable.make5};
        gridView = (GridView) view.findViewById(R.id.home_newest_grid);
        gridView.setAdapter(new HomeImageAdapter(getActivity(), faces));
    }
}
