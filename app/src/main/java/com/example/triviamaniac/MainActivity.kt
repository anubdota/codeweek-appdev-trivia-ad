package com.example.triviamaniac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start: Button = findViewById(R.id.startQuiz)
        start.setOnClickListener{
            val intent : Intent = Intent(this,QuestionPage::class.java)
            startActivity(intent)
            //add extra for topic,difficulty,etc
        }

    }
}