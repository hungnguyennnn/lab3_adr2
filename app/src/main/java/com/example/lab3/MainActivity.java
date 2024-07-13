package com.example.lab3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn1 = findViewById(R.id.btnnut1);
        btn2 = findViewById(R.id.btnnut2);
        btn3 = findViewById(R.id.btnnut3);
        btn4 = findViewById(R.id.btnnut4);

        btn1.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Nội dung cần thông báo");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Bạn đồng ý", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Bạn Không đồng ý", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });


        btn2.setOnClickListener(v -> {
            String[] listcolor = {"Xanh","Đỏ","Tím","Vàng","Hồng"};
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setSingleChoiceItems(listcolor, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Bạn đã chọn màu: "+listcolor[which], Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        btn3.setOnClickListener(v -> {
            String[] listcolor = {"Xanh", "Đỏ", "Tím", "Vàng", "Hồng"};
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");

            ArrayList<String> selectedColors = new ArrayList<>();

            builder.setMultiChoiceItems(listcolor, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if (isChecked) {
                        selectedColors.add(listcolor[which]);
                    } else {
                        selectedColors.remove(listcolor[which]);
                    }
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context, "Những màu bạn đã chọn là: " + String.join(", ", selectedColors), Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        btn4.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.activity_dang_nhap, null);
            builder.setView(view);

            final EditText edtTk = view.findViewById(R.id.edt1); // Sử dụng 'view' để tìm EditText
            final EditText edtMk = view.findViewById(R.id.edt2); // Sử dụng 'view' để tìm EditText

            builder.setPositiveButton("Đăng Nhập", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Để trống, xử lý sau
                }
            });
            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Thoát!", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false); // Ngăn không cho người dùng thoát hộp thoại bằng cách bấm ra ngoài

            alertDialog.setOnShowListener(dialog -> {
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(v1 -> {
                    String username = edtTk.getText().toString().trim();
                    String password = edtMk.getText().toString().trim();

                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Bạn không được để trống thông tin!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            });

            alertDialog.show();
        });


    }
}