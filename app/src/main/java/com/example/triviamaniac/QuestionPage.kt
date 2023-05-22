package com.example.triviamaniac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.triviamaniac.databinding.ActivityQuestionPageBinding
import kotlinx.coroutines.delay


class QuestionPage : AppCompatActivity() {
    lateinit var binding: ActivityQuestionPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // needs change
        var setq: List<Triv> = values.data
        //change number of question here
        setq = setq.shuffled()
        binding.questionTotal.text = "/"+setq.size.toString()
        var position = 0
        var score = 0

        fun createQues() {
            if (position < setq.size) {
                var curQues = setq[position]
                var qno= position+1
                binding.questionNumber.text =qno.toString()
                binding.questionTop.text = curQues.question
                var options: List<String> = listOf(
                    curQues.optionCorrect,
                    curQues.optionIn1,
                    curQues.optionIn2,
                    curQues.optionIn3
                )
                options = options.shuffled()

                binding.option1.text = options[0]
                binding.option2.text = options[1]
                binding.option3.text = options[2]
                binding.option4.text = options[3]

            } else {
                //end quiz frame here
                val intent : Intent = Intent(this,Score::class.java)
                intent.putExtra("Score",score)
                startActivity(intent)
            }
        }
        fun resetColours(){
            Log.d("QuestionPage","want to change colour")
            //create a val outside which stores the original colour
            binding.option1.setBackgroundColor(getColor(R.color.celestial_blue))
            binding.option2.setBackgroundColor(getColor(R.color.celestial_blue))
            binding.option3.setBackgroundColor(getColor(R.color.celestial_blue))
            binding.option4.setBackgroundColor(getColor(R.color.celestial_blue))
        }
        fun onChooseOption(optionBinding : Button){
            val curQues = setq[position]
            if(optionBinding.text == curQues.optionCorrect){
                optionBinding.setBackgroundColor(getColor(R.color.green_light))
                score++
                Log.d("QuestionPage","want to change colour")
            }
            else{
                optionBinding.setBackgroundColor(getColor(R.color.red_light))
                Log.d("QuestionPage","want to change colour")
            }
            binding.option1.setClickable(false)
            binding.option2.setClickable(false)
            binding.option3.setClickable(false)
            binding.option4.setClickable(false)
            position++

        }
        createQues()
        //add listeners to all
        binding.option1.setOnClickListener{
            onChooseOption(binding.option1)

        }
        binding.option2.setOnClickListener{
            onChooseOption(binding.option2)

        }
        binding.option3.setOnClickListener{
            onChooseOption(binding.option3)

        }
        binding.option4.setOnClickListener{
            onChooseOption(binding.option4)
        }

        binding.nextQuestion.setOnClickListener{
            binding.option1.setClickable(true)
            binding.option2.setClickable(true)
            binding.option3.setClickable(true)
            binding.option4.setClickable(true)
            resetColours()
            createQues()
        }


        }
    override fun onPause(){
        super.onPause()
        Log.d("QuestionPage","Why have i stopped?")
    }

}