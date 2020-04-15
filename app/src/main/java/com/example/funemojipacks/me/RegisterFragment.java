package com.example.funemojipacks.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.funemojipacks.MeFragment;
import com.example.funemojipacks.R;
import com.example.funemojipacks.UserInfoFragment;

import java.util.List;

public class RegisterFragment extends Fragment {

    View view;
    Button registButtom;
    EditText vNewUsername, vNewPwd, vConfirmPwd;

    private UserInfoFragment mUserInfoFragment;
    private Fragment mMeFragment;
    private RegisterFragment mRegisterFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_register, container, false);

        registButtom = (Button)view.findViewById(R.id.signup_btn);

        vNewUsername = (EditText) view.findViewById(R.id.register_user_name_edit);
        vNewPwd = (EditText) view.findViewById(R.id.register_pwd_edit);
        vConfirmPwd = (EditText) view.findViewById(R.id.register_pwd_confirm_edit);

        registButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = vNewUsername.getText().toString();
                String pwd = vNewPwd.getText().toString();
                String pwdConfirm  = vConfirmPwd.getText().toString();
                if(username.equals("") || pwd.equals("") || pwdConfirm.equals("")){
                    Toast.makeText(getContext(), R.string.log_empty_tips, Toast.LENGTH_LONG).show();
                }
                else if(!pwd.equals(pwdConfirm)){
                    Toast.makeText(getContext(), R.string.pwd_diff_tips, Toast.LENGTH_LONG).show();
                }
                // compare with Database, usename has been occupied, try another one
                // Success
                else{
                    Toast.makeText(getContext(), R.string.regist_succ_tips, Toast.LENGTH_LONG).show();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    if (mMeFragment == null) {
                        mMeFragment = new MeFragment();
                        transaction.add(R.id.meRegisterConstraint, mMeFragment);
                    }

                    // 隐藏所有Fragment
                    hideFragment(transaction);

                    transaction.show(mMeFragment);

                    // 提交事务
                    transaction.commit();
                }
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
        // return view;
    }

    // @Override
    // public void onActivityCreated(Bundle savedInstanceState){


    // }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        System.out.println("Condition1!");
        if (mRegisterFragment != null) {
            System.out.println("Condition excute!");
            transaction.hide(mRegisterFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
        if (mUserInfoFragment != null) {
            transaction.hide(mUserInfoFragment);
        }
        registButtom.setVisibility(View.INVISIBLE);
    }

}
