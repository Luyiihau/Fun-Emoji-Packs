package com.example.funemojipacks.me;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;

import java.util.ArrayList;

public class ShareLikeFragment extends Fragment {

    private View view;
    private GridView gridView;
    private int pageTabNo;
    DatabaseHelper memeDb;
    ArrayList<Bitmap> pics = new ArrayList<>();

    public ShareLikeFragment()  {

    }

    public ShareLikeFragment(int num)  {
        this.pageTabNo = num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.me_grid_item, container, false);

        memeDb = new DatabaseHelper(getActivity());

        initView();
        return view;
    }

    private void initView() {
        // getUserSharedImg();
        // final int[] pics = {R.drawable.test_img,R.drawable.test_img, R.drawable.test_img,
        //        R.drawable.test_img, R.drawable.test_img, R.drawable.test_img, R.drawable.test_img};
        gridView = (GridView) view.findViewById(R.id.me_pic_grid);
        // !!!
        // getActivity()
        gridView.setAdapter(new ShareLikeAdapter(getContext(), UserInfoFragment.pics));
        System.out.println("LOOK HERE!");
        System.out.println(pics.size());
    }

    // 0
    public void getUserSharedImg() {

        Cursor res = memeDb.getSharedImg(String.valueOf(MainActivity.userID));

        StringBuffer buffer = new StringBuffer();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "You have not shared anything! Come and Share!"
                    ,Toast.LENGTH_LONG).show();
        }
        else {
            while (res.moveToNext()) {
                byte[] in = res.getBlob(res.getColumnIndex("Pic_Pos"));
                pics.add(BitmapFactory.decodeByteArray(in, 0, in.length));
            }
        }
    }

    // 1
    public void getUserLikeddImg() {

    }

}

