package com.example.funemojipacks.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.R;

import java.util.ArrayList;
import java.util.Objects;

public class home_hottest extends Fragment {
    private Context context;
    private GridView gridView;
    private View view;
    private DatabaseHelper memeDb;
    private ArrayList<byte[]> faces = new ArrayList<>();
    private ArrayList<Integer> pic_id = new ArrayList<>();

    public home_hottest() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_hottest, container, false);
//        initView();
//        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        //添加路径
//        view = getView();
        memeDb = new DatabaseHelper(getActivity());
        Cursor res = memeDb.home_getLikeDesc("Pic_table");
        while (res.moveToNext()) {
            byte[] in = res.getBlob(res.getColumnIndex("Pic"));
            if (!faces.contains(in))
                faces.add(in);
            if (!pic_id.contains(res.getInt(res.getColumnIndex("Pic_ID"))))
                pic_id.add(res.getInt(res.getColumnIndex("Pic_ID")));
        }
        gridView = (GridView) Objects.requireNonNull(getView()).findViewById(R.id.home_hottest_grid);
        gridView.setAdapter(new HomeImageAdapter(getActivity(), faces));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Browse.class);
                Bundle bundle = new Bundle();
                bundle.putByteArray("image", faces.get(position));
                bundle.putInt("pic_id", pic_id.get(position));
                System.out.println(pic_id.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
