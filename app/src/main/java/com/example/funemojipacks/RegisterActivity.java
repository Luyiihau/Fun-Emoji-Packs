package com.example.funemojipacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button registButtom;
    EditText vNewUsername, vNewPwd, vConfirmPwd;
    DatabaseHelper memeDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registButtom = (Button)findViewById(R.id.signup_btn);
        vNewUsername = (EditText) findViewById(R.id.register_user_name_edit);
        vNewPwd = (EditText) findViewById(R.id.register_pwd_edit);
        vConfirmPwd = (EditText) findViewById(R.id.register_pwd_confirm_edit);

        memeDb = new DatabaseHelper(this);

        registButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = vNewUsername.getText().toString();
                String pwd = vNewPwd.getText().toString();
                String pwdConfirm  = vConfirmPwd.getText().toString();

                System.out.println("Registering a new username");

                if(username.equals("") || pwd.equals("") || pwdConfirm.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.log_empty_tips, Toast.LENGTH_LONG).show();
                }
                else if(!pwd.equals(pwdConfirm)){
                    Toast.makeText(getApplicationContext(), R.string.pwd_diff_tips, Toast.LENGTH_LONG).show();
                }
                else {
                    //finish();
                    boolean isAddSucc = addUserRecord();
                    if(isAddSucc) {
                        Toast.makeText(getApplicationContext(), R.string.regist_succ_tips,
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("id",3);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.user_name_exist_tips,
                                Toast.LENGTH_LONG).show();
                    }
                    // deleteUserRecord();
                    // showUserRecords();
                }
            }
        });
    }

    public boolean addUserRecord() {
        boolean isInserted = memeDb.insertUser(vNewUsername.getText().toString(),
                vNewPwd.getText().toString());
        return isInserted;
    }

    public void showUserRecords() {
        Cursor  res = memeDb.getAllData("User_table");
        if (res.getCount() == 0){
            System.out.println("No record in the user table");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID：" + res.getString(0)+"\n");
            buffer.append("Username：" + res.getString(1)+"\n");
            buffer.append("Pwd：" + res.getString(2)+"\n");
        }
        System.out.println(buffer);
        // Toast.makeText(getApplicationContext(), buffer, Toast.LENGTH_LONG).show();
    }

    public void deleteUserRecord() {
        int id = memeDb.deleteData("User_table", "5");
        id = memeDb.deleteData("User_table", "6");
        // id = memeDb.deleteData("User_table", "3");
        // id = memeDb.deleteData("User_table", "4");
        System.out.println(id);
    }
}
