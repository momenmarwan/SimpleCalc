package com.example.exam_1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnDiv: Button
    private lateinit var btnMul: Button
    private lateinit var tvResult: TextView

    private var resultText: String = "Result"
    private var resultColor: Int = Color.BLACK
    private var lastNum1: Double = 0.0
    private var lastNum2: Double = 0.0
    private var lastOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnDiv = findViewById(R.id.btnDiv)
        btnMul = findViewById(R.id.btnMul)
        tvResult = findViewById(R.id.tvResult)

        if (savedInstanceState != null) {
            resultText = savedInstanceState.getString("resultText", "Result")
            resultColor = savedInstanceState.getInt("resultColor", Color.BLACK)
            lastNum1 = savedInstanceState.getDouble("lastNum1", 0.0)
            lastNum2 = savedInstanceState.getDouble("lastNum2", 0.0)
            lastOperation = savedInstanceState.getString("lastOperation", "")
            tvResult.text = resultText
            tvResult.setTextColor(resultColor)
        }

        btnAdd.setOnClickListener { calculate("ADD", Color.parseColor("#FF5733")) }
        btnSub.setOnClickListener { calculate("SUB", Color.parseColor("#33A1FF")) }
        btnDiv.setOnClickListener { calculate("DIV", Color.parseColor("#28B463")) }
        btnMul.setOnClickListener { calculate("MUL", Color.parseColor("#8E44AD")) }

        tvResult.setOnClickListener {
            if (lastOperation.isEmpty()) return@setOnClickListener
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("num1", lastNum1)
            intent.putExtra("num2", lastNum2)
            intent.putExtra("result", resultText)
            intent.putExtra("operation", lastOperation)
            startActivity(intent)
        }
    }

    private fun calculate(operation: String, color: Int) {
        val num1Str = etNum1.text.toString()
        val num2Str = etNum2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = num1Str.toDoubleOrNull()
        val num2 = num2Str.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "ADD" -> num1 + num2
            "SUB" -> num1 - num2
            "DIV" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            "MUL" -> num1 * num2
            else -> 0.0
        }

        lastNum1 = num1
        lastNum2 = num2
        lastOperation = operation
        resultText = result.toString()
        resultColor = color

        tvResult.text = resultText
        tvResult.setTextColor(color)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("resultText", resultText)
        outState.putInt("resultColor", resultColor)
        outState.putDouble("lastNum1", lastNum1)
        outState.putDouble("lastNum2", lastNum2)
        outState.putString("lastOperation", lastOperation)
    }
}
