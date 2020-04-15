package com.example.funemojipacks;

import android.content.Context;
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


// ----萌佳

public class MeFragment  extends Fragment {

    private View view;
    private Context mContext;
    EditText vUsername, vPwd;
    Button loginButtom, registButtom;

    private UserInfoFragment mUserInfoFragment;
    private MeFragment mMeFragment;
    private RegisterFragment mRegisterFragment;

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
                    // toast message
                    // String tips = getString(R.string.log_empty_tips);
                    Toast.makeText(getContext(), R.string.log_empty_tips, Toast.LENGTH_LONG).show();
                }
                // add more conditions here
                // jump to LoginFragment
                else{
                    System.out.println("The username:" + username + ".The pwd" + pwd);

                    // 管理器要用getSupportFragmentManager获取
                    FragmentManager fragmentManager = getFragmentManager();
                    // 开启事务
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    // 隐藏所有Fragment
                    hideFragment(transaction);

                    if (mUserInfoFragment == null) {
                        mUserInfoFragment = new UserInfoFragment();
                        transaction.add(R.id.meConstraintLayout, mUserInfoFragment);
                    } else {
                        transaction.show(mUserInfoFragment);
                    }
                    // 提交事务
                    transaction.commit();
                }
            }
        });


        registButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                // Bundle bundle = new Bundle();
                // intent.putExtras(bundle); startActivity(intent);

                // RegistFragment
                // RegistXML
                // 写一个buttom注册存数据
                // 注册之后跳转到登录页面

                System.out.println("Register a new username");

                // 管理器要用getSupportFragmentManager获取
                FragmentManager fragmentManager = getFragmentManager();
                // 开启事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // 隐藏所有Fragment
                hideFragment(transaction);

                if (mRegisterFragment == null) {
                    mRegisterFragment = new RegisterFragment();
                    transaction.add(R.id.meConstraintLayout, mRegisterFragment);
                } else {
                    transaction.show(mRegisterFragment);
                }
                // 提交事务
                transaction.commit();

            }
        });

        return view;
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        System.out.println("Condition1!");
        if (mRegisterFragment != null) {
            System.out.println("Condition1 execute!");
            transaction.hide(mRegisterFragment);
        }
        if (mMeFragment != null) {
            System.out.println("Condition2 execute!");
            transaction.hide(mMeFragment);
        }
        if (mUserInfoFragment != null) {
            System.out.println("Condition3 execute!");
            transaction.hide(mUserInfoFragment);
        }
        loginButtom.setVisibility(View.INVISIBLE);
        registButtom.setVisibility(View.INVISIBLE);
    }

}
