package com.example.triviamaniac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.triviamaniac.network.TriviaApi
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start: Button = findViewById(R.id.startQuiz)
        val eButton : Button = findViewById(R.id.easy_mode)
        val mButton : Button  = findViewById(R.id.medium_mode)
        val hButton : Button = findViewById(R.id.hard_mode)

        var diff : String? = null

        eButton.setOnClickListener{
            if(diff!="easy"){
                diff = "easy"
                eButton.setBackgroundColor(getColor(R.color.celestial_blue_dark))
                mButton.setBackgroundColor(getColor(R.color.celestial_blue))
                hButton.setBackgroundColor(getColor(R.color.celestial_blue))
            }
            else{
                diff = null
                eButton.setBackgroundColor(getColor(R.color.celestial_blue))
            }
        }
        mButton.setOnClickListener{
            if(diff!="medium"){
                diff = "medium"
                eButton.setBackgroundColor(getColor(R.color.celestial_blue))
                mButton.setBackgroundColor(getColor(R.color.celestial_blue_dark))
                hButton.setBackgroundColor(getColor(R.color.celestial_blue))
            }
            else{
                diff = null
                mButton.setBackgroundColor(getColor(R.color.celestial_blue))
            }
        }
        hButton.setOnClickListener{
            if(diff!="hard"){
                diff = "hard"
                eButton.setBackgroundColor(getColor(R.color.celestial_blue))
                mButton.setBackgroundColor(getColor(R.color.celestial_blue))
                hButton.setBackgroundColor(getColor(R.color.celestial_blue_dark))
            }
            else{
                diff = null
                hButton.setBackgroundColor(getColor(R.color.celestial_blue))
            }
        }



        start.setOnClickListener{
                    val intent : Intent = Intent(this@MainActivity,QuestionPage::class.java)
                    intent.putExtra("Difficulty",diff)
                    startActivity(intent)
            }

        }
    }
