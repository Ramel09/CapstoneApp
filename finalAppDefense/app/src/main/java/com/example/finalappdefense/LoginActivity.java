package com.example.finalappdefense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalappdefense.Admin.AdminLoginActivity;
import com.example.finalappdefense.Model.Users;
import com.example.finalappdefense.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InputNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink,NotAdminLink,ForgotPasswordLink;


    private String parentDbName="Users";
    private CheckBox chkBoxRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        LoginButton=findViewById(R.id.btn_adminlogin);
        InputNumber=findViewById(R.id.editTextPhone);
        InputPassword=findViewById(R.id.et_password);
        loadingBar= new ProgressDialog(this);
        AdminLink=findViewById(R.id.tx_admin_link);
        NotAdminLink=findViewById(R.id.tx_not_admin_link);
        ForgotPasswordLink = findViewById(R.id.forgot_password_link);



        chkBoxRememberMe=findViewById(R.id.checkBox);
        Paper.init(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });




        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(LoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);


            }
        });

    }

    private void LoginUser() {
        String Phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();

          if (TextUtils.isEmpty(Phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
          else
          {
              loadingBar.setTitle("Login Account");
              loadingBar.setMessage("Please wait, while we are checking the credentials...");
              loadingBar.setCanceledOnTouchOutside(false);
              loadingBar.show();

              AllowAccessToAccount(Phone,password);

          }

    }

    private void AllowAccessToAccount(String phone, String password) {

        if (chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);

        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                    if (snapshot.child(parentDbName).child(phone).exists())
                    {
                        Users userData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                        if (userData.getPhone().equals(phone))
                        {
                            if (userData.getPassword().equals(password))
                            {

                                 if (parentDbName.equals("Users"))
                                {
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();

                                    Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
                                    Prevalent.currentOnlineUser=userData;
                                    startActivity(intent);
                                }

                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "User/Password is Incorrect!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Account with this "+ phone+ " number do not exists.", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}