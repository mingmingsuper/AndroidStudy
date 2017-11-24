package com.study.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mAccountEdit;
    private EditText mPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    public void initUI() {
        mAccountEdit = (EditText) findViewById(R.id.account);
        mPasswordEdit = (EditText)findViewById(R.id.password);
        Button button = (Button)findViewById(R.id.login_button);
        button.setOnClickListener( view -> {
                String account = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                if (account.equals("admin") && password.equals("admin")) {
                    finish();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                }
        });
    }
}
