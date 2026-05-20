package com.example.exam_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val num1 = intent.getDoubleExtra("num1", 0.0)
        val num2 = intent.getDoubleExtra("num2", 0.0)
        val result = intent.getStringExtra("result") ?: ""
        val operation = intent.getStringExtra("operation") ?: ""

        val tvResultDetail = findViewById<TextView>(R.id.tvResultDetail)
        tvResultDetail.text = "Num1: $num1\nNum2: $num2\nOperation: $operation\nResult: $result"
    }
}
