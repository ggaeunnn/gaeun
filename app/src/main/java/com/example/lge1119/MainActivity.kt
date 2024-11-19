package com.example.lge1119

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var currentNumber = 0
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberText = findViewById<TextView>(R.id.numberText)
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val randomNumber = data?.getIntExtra("randomNumber", 0) ?: 0
                numberText.text = randomNumber.toString() // 수정: 랜덤 값으로 TextView 업데이트
            }
        }
        val  toastButton = findViewById<Button>(R.id.button_toast)
        toastButton.setOnClickListener {
            Toast.makeText(this, "toast 입니다.", Toast.LENGTH_SHORT).show()
        }
        val countButton = findViewById<Button>(R.id.button_count)
        countButton.setOnClickListener{
            currentNumber++
            numberText.text = currentNumber.toString()
        }
        val randomButton = findViewById<Button>(R.id.button_random)
        randomButton.setOnClickListener{
            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("maxNumber",currentNumber)
            activityResultLauncher.launch(intent)
        }
    }
}
