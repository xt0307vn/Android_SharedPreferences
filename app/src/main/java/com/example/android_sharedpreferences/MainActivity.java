package com.example.android_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText pass, username;
    CheckBox remember;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin = findViewById(R.id.signin);
        pass = findViewById(R.id.pass);
        username = findViewById(R.id.username);
        remember = findViewById(R.id.remember);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        username.setText(sharedPreferences.getString("taikhoan", ""));
        pass.setText(sharedPreferences.getString("matkhau", ""));
        remember.setChecked(sharedPreferences.getBoolean("checked", false));

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t_username = username.getText().toString().trim();
                String t_pass = pass.getText().toString().trim();
                if(t_username.equalsIgnoreCase("xt") && t_pass.equalsIgnoreCase("1")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if(remember.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", t_username);
                        editor.putString("matkhau", t_pass);
                        editor.putBoolean("checked", true);
                        editor.commit();

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("taikhoan");
                    editor.remove("matkhau");
                    editor.remove("checked");
                    editor.commit();
                }
             }
        });
    }
}