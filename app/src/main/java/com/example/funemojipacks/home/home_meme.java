package com.example.funemojipacks.home;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.funemojipacks.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class home_meme extends Fragment {

    private Context context;
    private GridView gridView;
    private Button search_btn;
    private SearchView searchView;
    private ListView listView;
    private View view;
    private ArrayList<Integer> faces = new ArrayList<>();

    public home_meme() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_meme, container, false);
        initView();
        return view;
    }

    private void initView() {
//        final int[] faces = {R.drawable.make1, R.drawable.make2, R.drawable.make3,
//                R.drawable.make4, R.drawable.make5};
//        faces.add(R.drawable.make1);
//        faces.add(R.drawable.make2);
//        faces.add(R.drawable.make3);
//        faces.add(R.drawable.make4);
//        faces.add(R.drawable.make5);
        if (!faces.contains(R.drawable.make1))
            faces.add(R.drawable.make1);
        if (!faces.contains(R.drawable.make2))
            faces.add(R.drawable.make2);
        if (!faces.contains(R.drawable.make3))
            faces.add(R.drawable.make3);
        if (!faces.contains(R.drawable.make4))
            faces.add(R.drawable.make4);
        if (!faces.contains(R.drawable.make5))
            faces.add(R.drawable.make5);
//        final String[] mStrings = {"aaa", "bbb", "ccc", "ddd"};
        final String[] mStrings = {};

        gridView = (GridView) view.findViewById(R.id.home_meme_grid);
        gridView.setAdapter(new HomeImageAdapter(getActivity(), faces));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getActivity(), Browse.class);
                Bundle bundle = new Bundle();
                bundle.putInt("image", faces.get(position));
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
        search_btn = (Button) view.findViewById(R.id.search_button);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), Search.class);
                startActivity(intent2);
            }
        });
    }


}
