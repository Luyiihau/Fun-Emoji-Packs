package com.example.funemojipacks.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funemojipacks.R;

import java.util.Objects;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ListView listView;
    private SearchView searchView;
    private String[] mStrings = {"aaa", "bbb", "ccc", "ddd"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        listView = (ListView) findViewById(R.id.auto_lv);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item, mStrings));
        listView.setTextFilterEnabled(true);
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Please Enter Keyword to Search");
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(this, "textChange--->" + newText, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(newText)) {
            // 清除ListView的过滤
            listView.clearTextFilter();
        } else {
            // 使用用户输入的内容对ListView的列表项进行过滤
            listView.setFilterText(newText);
        }
        return true;
    }

    // 单击搜索按钮时激发该方法
    @Override
    public boolean onQueryTextSubmit(String query) {
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户输入的查询内容
        Toast.makeText(this, "您的选择是:" + query, Toast.LENGTH_SHORT).show();
        return false;
    }
}
