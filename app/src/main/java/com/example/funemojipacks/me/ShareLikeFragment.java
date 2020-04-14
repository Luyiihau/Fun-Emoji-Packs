package com.example.funemojipacks.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.R;

public class ShareLikeFragment extends Fragment {

    private View view;
    private GridView gridView;

    public ShareLikeFragment()  {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.me_fragment, container, false);
        initView();
        return view;
    }

    private void initView(){
        final int[] pics = {R.drawable.test_img,R.drawable.test_img, R.drawable.test_img,
                R.drawable.test_img, R.drawable.test_img, R.drawable.test_img, R.drawable.test_img};
        gridView = (GridView) view.findViewById(R.id.me_pic_grid);
        gridView.setAdapter(new ShareLikeAdapter(getActivity(), pics));
    }
}

