package com.example.funemojipacks.make;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funemojipacks.MakeFragment;
import com.example.funemojipacks.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class make_appear extends Fragment {

    private Context context;
    private GridView gridView;
    private View view;
    public make_appear() {
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
        final int[] faces = {R.drawable.make1,R.drawable.make2, R.drawable.make3, R.drawable.make4, R.drawable.make5, R.drawable.makejj
        ,R.drawable.make6};
        gridView = (GridView) view.findViewById(R.id.make_appear_grid);
        gridView.setAdapter(new MakeImageAdapter(getActivity(), faces));
        //设置监听事件 （点击在make页面上方的imageview上显示图片）
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
//                String number =
//                MakeFragment parent = (MakeFragment) getActivity().getSupportFragmentManager().findFragmentByTag()
//                Bitmap bitmap = (Bitmap) getActivity().findViewById(R.id.)
//                RelativeLayout relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.lll);
//                relativeLayout.addView();
                MakeFragment makeFragment = (MakeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("makefragment");
                makeFragment.setImage(position);
//                TextView textView = (TextView) getActivity().findViewById(R.id.make_appear_listen);
//                textView.setText(String.valueOf(position));
                //获取make页面的imageView 改变图片
//                ImageView imageView = (ImageView) getActivity().findViewById(R.id.emojiImage);
//                imageView.setBackgroundResource(faces[position]);
//                Toast.makeText(getActivity(), "fuck you!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
