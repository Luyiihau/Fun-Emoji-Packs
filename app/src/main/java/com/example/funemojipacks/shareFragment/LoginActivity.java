package com.example.funemojipacks.shareFragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.example.funemojipacks.RegisterActivity;
import com.example.funemojipacks.me.UserInfoFragment;

public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    private TextView mLogin, mRegister;
    private EditText vUsername, vPwd;
    DatabaseHelper memeDb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_login);
        Toast.makeText(getApplicationContext(), R.string.user_not_login, Toast.LENGTH_SHORT).show();

        vUsername = (EditText) findViewById(R.id.login_user_name_edit);
        vPwd = (EditText) findViewById(R.id.login_pwd_edit);
        mLogin = (TextView) findViewById(R.id.login_view);
        mRegister = (TextView) findViewById(R.id.me_register_view);

        // 有待确认
        memeDb = new DatabaseHelper(this);

        // -- Listener
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = vUsername.getText().toString();
                String pwd = vPwd.getText().toString();

                if (username.equals("") || pwd.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.log_empty_tips, Toast.LENGTH_LONG).show();
                }
                // add more if conditions here
                else{
                    /*
                    Intent intent = new Intent(getActivity(), ShowUserInfoActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    intent.putExtras(bundle);

                    // startActivity(intent);
                    // 带请求码值1到ShowUserInfoActivity
                    startActivityForResult(intent, 1);

                     */

                    // System.out.println("Button Click");
                    int isInputCorrect = checkUserRecords(username, pwd);
                    if(isInputCorrect==2){
                        Toast.makeText(getApplicationContext(), R.string.user_name_wrong_tips, Toast.LENGTH_LONG).show();
                    }
                    else if(isInputCorrect==1){
                        Toast.makeText(getApplicationContext(), R.string.pwd_wrong_tips, Toast.LENGTH_LONG).show();
                    }
                    else{
                        //设置static userID，在share 页面有用
                        Cursor res = memeDb.findUserID(username);
                        res.moveToFirst();
                        MainActivity.userID = res.getInt(0);

                        Toast.makeText(getApplicationContext(), R.string.login_succ_tips, Toast.LENGTH_LONG).show();
                        MainActivity.isLogin = true;
                        finish();
                    }
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                // Bundle bundle = new Bundle();
                // intent.putExtras(bundle); startActivity(intent);

                System.out.println("Register a new username");

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public Integer checkUserRecords(String name, String pwd) {
        Cursor res = memeDb.findUserRecord(name);
        if (res.getCount() == 0){
            // username wrong
            return 2;
        }
        else {
            // password wrong
            res.moveToFirst();
            if(!pwd.equals(res.getString(0))){
                return 1;
            }
            // correct
            else{
                return 0;
            }
        }
    }
}
