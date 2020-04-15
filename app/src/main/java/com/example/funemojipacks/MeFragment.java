package com.example.funemojipacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.funemojipacks.me.RegisterFragment;

public class MeFragment  extends Fragment {

    private View view;
    private Context mContext;
    EditText vUsername, vPwd;
    Button loginButtom, registButtom;

    private Fragment mUserInfoFragment, mMeFragment, mRegisterFragment;
    // private MeFragment mMeFragment;
    // private RegisterFragment mRegisterFragment;

    public MeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_page, container, false);

        // initView();

        // -- get views
        vUsername = (EditText) view.findViewById(R.id.login_user_name_edit);
        vPwd = (EditText) view.findViewById(R.id.login_pwd_edit);

        loginButtom = (Button)view.findViewById(R.id.login_btn);
        registButtom = (Button)view.findViewById(R.id.regist_btn);

        // -- Listener
        loginButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = vUsername.getText().toString();
                String pwd = vPwd.getText().toString();

                if (username.equals("") || pwd.equals("")){
                    Toast.makeText(getContext(), R.string.log_empty_tips, Toast.LENGTH_LONG).show();
                }
                // add more if conditions here
                else{
                    Intent intent = new Intent(getActivity(), ShowUserInfoActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });


        registButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                // Bundle bundle = new Bundle();
                // intent.putExtras(bundle); startActivity(intent);

                System.out.println("Register a new username");

                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
