package com.example.funemojipacks.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.R;

import java.util.ArrayList;

public class home_newest extends Fragment {

    private Context context;
    private GridView gridView;
    private View view;
    private ArrayList<Integer> faces=new ArrayList<>();

    public home_newest() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_newest, container, false);
        initView();
        return view;
    }

    private void initView() {
//        final int[] faces = {R.drawable.make1, R.drawable.make2, R.drawable.make3,
//                R.drawable.make4, R.drawable.make5};
        if(!faces.contains(R.drawable.make1))
            faces.add(R.drawable.make1);
        if(!faces.contains(R.drawable.make2))
            faces.add(R.drawable.make2);
        if(!faces.contains(R.drawable.make3))
            faces.add(R.drawable.make3);
        if(!faces.contains(R.drawable.make4))
            faces.add(R.drawable.make4);
        if(!faces.contains(R.drawable.make5))
            faces.add(R.drawable.make5);
//        faces.add(R.drawable.make1);
//        faces.add(R.drawable.make2);
//        faces.add(R.drawable.make3);
//        faces.add(R.drawable.make4);
//        faces.add(R.drawable.make5);
        gridView = (GridView) view.findViewById(R.id.home_newest_grid);
        gridView.setAdapter(new HomeImageAdapter(getActivity(), faces));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Browse.class);
                Bundle bundle = new Bundle();
                bundle.putInt("image", faces.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
