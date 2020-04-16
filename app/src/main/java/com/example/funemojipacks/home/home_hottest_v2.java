package com.example.funemojipacks.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.R;

import java.util.ArrayList;
import java.util.Objects;

public class home_hottest_v2 extends Fragment {
    private Context context;
    private GridView gridView;
    private View view;
    private DatabaseHelper memeDb;
    private ArrayList<String> faces = new ArrayList<>();

    public home_hottest_v2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_hottest, container, false);
        initView();
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (Objects.requireNonNull(getActivity()).checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }
        return view;
//        return inflater.inflate(R.layout.fragment_home_hottest,container,false);
    }

    private void initView() {
//        final int[] faces = {R.drawable.make1, R.drawable.make2, R.drawable.make3,
//                R.drawable.make4, R.drawable.make5};
        //添加路径
        faces.add(Environment.getExternalStorageDirectory().toString() + "/DCIM/make1.jpeg");
        faces.add(Environment.getExternalStorageDirectory().toString() + "/DCIM/make2.jpeg");
        faces.add(Environment.getExternalStorageDirectory().toString() + "/DCIM/make3.jpeg");
        faces.add(Environment.getExternalStorageDirectory().toString() + "/DCIM/make4.jpeg");
        faces.add(Environment.getExternalStorageDirectory().toString() + " /DCIM/make5.jpeg");
        gridView = (GridView) view.findViewById(R.id.home_hottest_grid);
        gridView.setAdapter(new HomeImageAdapter_v2(getActivity(), faces));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Browse_v2.class);
                Bundle bundle = new Bundle();
                bundle.putString("image", faces.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getLikePic() {
        memeDb = new DatabaseHelper(getActivity());
        Cursor res = memeDb.home_getLikeDesc("Pic_table");
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "No record in the Student Database.", Toast.LENGTH_SHORT).show();
        }
        ArrayList<String> path = new ArrayList<>();
        while (res.moveToNext()) {
            path.add(res.getString(1));//获取路径
        }
    }
}
