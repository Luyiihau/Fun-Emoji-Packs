package com.example.funemojipacks.make;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.funemojipacks.MakeFragment;
import com.example.funemojipacks.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class make_face extends Fragment{

    private Context context;
    private GridView gridView;
    private View view;

    public make_face() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_make_appear, container, false);
        initView();
        return view;
    }

    private void initView(){
        //硬读取固定的drawable
        final int[] faces = {R.drawable.face1, R.drawable.face2, R.drawable.face3, R.drawable.face4};
        gridView = (GridView) view.findViewById(R.id.make_appear_grid);
        gridView.setAdapter(new MakeImageAdapter(getActivity(), faces));
        //设置监听事件 （点击在make页面上方的imageview上显示图片）
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("face position is" + position);
                MakeFragment makeFragment = (MakeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("makefragment");
                makeFragment.setFaceImage(position);
//                TextView textView = (TextView) getActivity().findViewById(R.id.make_appear_listen);
//                textView.setText(String.valueOf(position));
            }
        });
    }
}
