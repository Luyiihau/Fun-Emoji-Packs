package com.example.funemojipacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.funemojipacks.me.LoginFragment;
import com.example.funemojipacks.me.UserInfoFragment;

public class MeFragment extends Fragment {

    private View view;
    private Context mContext;
    Boolean isLogin = false;

    private Fragment mUserInfoFragment, mMeFragment, mRegisterFragment;
    // private MeFragment mMeFragment;
    // private RegisterFragment mRegisterFragment;

    public MeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_page, container, false);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(isLogin){
            ft.hide(new MeFragment())
                    .show(new UserInfoFragment())
                    .commit();
        }
        else {
            System.out.println("No Login");
            ft.hide(new MeFragment())
                    .show(new LoginFragment())
                    .commit();
        }

        return view;
    }
}
