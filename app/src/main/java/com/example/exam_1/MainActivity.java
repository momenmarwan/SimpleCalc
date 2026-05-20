package com.example.exam_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etNum2;
    private Button btnAdd;
    private Button btnSub;
    private Button btnDiv;
    private Button btnMul;
    private TextView tvResult;

    private String resultText = "Result";
    private int resultColor = Color.BLACK;
    private double lastNum1 = 0.0;
    private double lastNum2 = 0.0;
    private String lastOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        tvResult = findViewById(R.id.tvResult);

        if (savedInstanceState != null) {
            resultText = savedInstanceState.getString("resultText", "Result");
            resultColor = savedInstanceState.getInt("resultColor", Color.BLACK);
            lastNum1 = savedInstanceState.getDouble("lastNum1", 0.0);
            lastNum2 = savedInstanceState.getDouble("lastNum2", 0.0);
            lastOperation = savedInstanceState.getString("lastOperation", "");
            tvResult.setText(resultText);
            tvResult.setTextColor(resultColor);
        }

        btnAdd.setOnClickListener(v -> calculate("ADD", Color.parseColor("#FF5733")));
        btnSub.setOnClickListener(v -> calculate("SUB", Color.parseColor("#33A1FF")));
        btnDiv.setOnClickListener(v -> calculate("DIV", Color.parseColor("#28B463")));
        btnMul.setOnClickListener(v -> calculate("MUL", Color.parseColor("#8E44AD")));

        tvResult.setOnClickListener(v -> {
            if (lastOperation.isEmpty()) return;
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("num1", lastNum1);
            intent.putExtra("num2", lastNum2);
            intent.putExtra("result", resultText);
            intent.putExtra("operation", lastOperation);
            startActivity(intent);
        });
    }

    private void calculate(String operation, int color) {
        String num1Str = etNum1.getText().toString();
        String num2Str = etNum2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1;
        double num2;
        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }

        double result;
        switch (operation) {
            case "ADD":
                result = num1 + num2;
                break;
            case "SUB":
                result = num1 - num2;
                break;
            case "DIV":
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                break;
            case "MUL":
                result = num1 * num2;
                break;
            default:
                result = 0.0;
                break;
        }

        lastNum1 = num1;
        lastNum2 = num2;
        lastOperation = operation;
        resultText = String.valueOf(result);
        resultColor = color;

        tvResult.setText(resultText);
        tvResult.setTextColor(color);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("resultText", resultText);
        outState.putInt("resultColor", resultColor);
        outState.putDouble("lastNum1", lastNum1);
        outState.putDouble("lastNum2", lastNum2);
        outState.putString("lastOperation", lastOperation);
    }
}
