package com.example.funemojipacks.make;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funemojipacks.MakeFragment;
import com.example.funemojipacks.R;


/**
 * A simple {@link Fragment} subclass.
 */
//在make - text的页面中 编辑文字
public class make_text extends Fragment {

    private TextView textView;
    private EditText editText;
    private Button inputBtn;
    private View view;

    public make_text() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_make_text, container, false);
        initView();
        return view;
    }

    private void initView(){
        textView = (TextView) view.findViewById(R.id.make_text_text);
        editText = (EditText) view.findViewById(R.id.make_text_editText);
        inputBtn = (Button) view.findViewById(R.id.inputbtn);
        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeFragment makeFragment = (MakeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("makefragment");
                if(editText.getText().equals(""))
                    makeFragment.setMakeText("");
                else{
                    makeFragment.setMakeText(editText.getText().toString());
                }

            }
        });
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            //实时监听text变化
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                Toast.makeText(getActivity(), "默认的Toast", Toast.LENGTH_SHORT).show();
//                MakeFragment makeFragment = (MakeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("makefragment");
//                makeFragment.setMakeText(s.toString());
////                System.out.println(s.length());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

}
