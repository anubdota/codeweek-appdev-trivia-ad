package com.example.triviamaniac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        val intent = getIntent()
        val score = intent.getIntExtra("Score",0)

        val textscore : TextView= findViewById(R.id.score)
        textscore.text = score.toString()

        val resetButton : Button = findViewById(R.id.home)
        resetButton.setOnClickListener{
            val intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}