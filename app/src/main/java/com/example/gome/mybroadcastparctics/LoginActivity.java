package com.example.gome.mybroadcastparctics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mAccount;
    private EditText mPassword;
    private Button mLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccount = (EditText)findViewById(R.id.text_account);
        mPassword = (EditText)findViewById(R.id.text_password);
        mLogin = (Button)findViewById(R.id.btn_login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccount.getText().toString();
                String password = mPassword.getText().toString();

                if((account!=null && password!=null) && (account.equals("admin") && password.equals("123456"))){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "请输入正确的账号密码，不为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
