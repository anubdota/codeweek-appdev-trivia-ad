package com.example.triviamaniac

import android.content.Context
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

        val sharedPref = getSharedPreferences("lastScore", Context.MODE_PRIVATE)

        val lastScore = sharedPref.getInt("Score",-1)
        val tvLastScore : TextView = findViewById(R.id.last_score)
        if(lastScore !=-1) {
            tvLastScore.setText("Last Score was: $lastScore")
        }

        //save to pref

        val editor = sharedPref.edit()
        editor.apply {
            putInt("Score",score)
            apply()
        }

        val textscore : TextView= findViewById(R.id.score)
        textscore.text = score.toString()



        val resetButton : Button = findViewById(R.id.home)
        resetButton.setOnClickListener{
            val intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}