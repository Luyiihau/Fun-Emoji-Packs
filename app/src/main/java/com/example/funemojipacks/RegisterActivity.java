package com.example.funemojipacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button registButtom;
    EditText vNewUsername, vNewPwd, vConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registButtom = (Button)findViewById(R.id.signup_btn);
        vNewUsername = (EditText) findViewById(R.id.register_user_name_edit);
        vNewPwd = (EditText) findViewById(R.id.register_pwd_edit);
        vConfirmPwd = (EditText) findViewById(R.id.register_pwd_confirm_edit);


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
                    Toast.makeText(getApplicationContext(), R.string.regist_succ_tips, Toast.LENGTH_LONG).show();
                    // Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    // startActivity(intent);

                    //finish();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("id",3);
                    startActivity(intent);



                }
            }
        });


    }

}
