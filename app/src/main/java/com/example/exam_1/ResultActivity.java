package com.example.exam_1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        double num1 = getIntent().getDoubleExtra("num1", 0.0);
        double num2 = getIntent().getDoubleExtra("num2", 0.0);
        String result = getIntent().getStringExtra("result");
        String operation = getIntent().getStringExtra("operation");

        if (result == null) result = "";
        if (operation == null) operation = "";

        TextView tvResultDetail = findViewById(R.id.tvResultDetail);
        tvResultDetail.setText("Num1: " + num1 + "\nNum2: " + num2 + "\nOperation: " + operation + "\nResult: " + result);
    }
}
