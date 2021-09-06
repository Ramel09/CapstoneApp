package com.example.finalappdefense.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalappdefense.R;

public class AdminLoginActivity extends AppCompatActivity {

   private EditText et_username,et_password;
   private Button btn_loginadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        
        et_username=findViewById(R.id.editTextPhone);
        et_password=findViewById(R.id.et_password);
        btn_loginadmin=findViewById(R.id.btn_adminlogin);

        String username=et_username.getText().toString();
        String password=et_password.getText().toString();

        btn_loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminLoginActivity.this, AdminCategoryActivity.class);
                startActivity(intent);

            }
        });
        
    }
}