package com.example.triviamaniac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.triviamaniac.databinding.ActivityQuestionPageBinding
import com.example.triviamaniac.network.TriviaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


class QuestionPage : AppCompatActivity() {
    lateinit var binding: ActivityQuestionPageBinding
    private var timer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var setq : MutableList<Triv> = emptyList<Triv>().toMutableList()
        lifecycleScope.launchWhenCreated {
            try{
                binding.option1.isVisible = false
                binding.option2.isVisible = false
                binding.option3.isVisible = false
                binding.option4.isVisible = false
                binding.nextQuestion.isVisible = false
                val diff = intent.getStringExtra("Difficulty")
                values.apiResponseQuestionList = TriviaApi.retrofitService.getQuestions(10, difficulty = diff)
                Log.d("MainActivity","${values.apiResponseQuestionList.results.size} size of list created")
                Log.d("MainActivity","${values.apiResponseQuestionList.results}")
                setq = values.apiResponseQuestionList.results.toMutableList()
            }
            catch(e: IOException) {
                Toast.makeText(this@QuestionPage, "You might not have internet connection",Toast.LENGTH_SHORT).show()

            } catch (e: HttpException) {
                Toast.makeText(this@QuestionPage,"Unexpected response from server. Try again",Toast.LENGTH_SHORT).show()

            }

        // needs change
        binding.questionTotal.text = "/" + setq.size.toString()
        var position = 0
        var score = 0
            binding.progress.isVisible = false
            binding.option1.isVisible = true
            binding.option2.isVisible = true
            binding.option3.isVisible = true
            binding.option4.isVisible = true
            binding.nextQuestion.isVisible = true



        fun createQues() {
            if (position < setq.size) {
                var curQues = setq[position]
                var qno= position+1
                binding.questionNumber.text =qno.toString()
                curQues.question = Html.fromHtml(curQues.question).toString()
                binding.questionTop.text = curQues.question
                var options: MutableList<String> = setq[position].incorrect_answers.toMutableList()
                options.add(setq[position].correct_answer)
                options.shuffle()

                //parse through Html.fromHtml
                for(i in 0..3){
                    options[i] = Html.fromHtml(options[i]).toString()
                }
                binding.option1.text = options[0]
                binding.option2.text = options[1]
                binding.option3.text = options[2]
                binding.option4.text = options[3]

                timer?.cancel()
                timer = object : CountDownTimer(10000, 1000) {

                    // Callback function, fired on regular interval
                    override fun onTick(millisUntilFinished: Long) {
                        binding.timeLeft.setText("${millisUntilFinished / 1000}")
                    }

                    // Callback function, fired
                    // when the time is up
                    override fun onFinish() {
                        binding.timeLeft.setText("0")
                        binding.option1.setClickable(false)
                        binding.option2.setClickable(false)
                        binding.option3.setClickable(false)
                        binding.option4.setClickable(false)
                        if(binding.option1.text == curQues.correct_answer){binding.option1.setBackgroundColor(getColor(R.color.green_light))}
                        if(binding.option2.text == curQues.correct_answer){binding.option2.setBackgroundColor(getColor(R.color.green_light))}
                        if(binding.option3.text == curQues.correct_answer){binding.option3.setBackgroundColor(getColor(R.color.green_light))}
                        if(binding.option4.text == curQues.correct_answer){binding.option4.setBackgroundColor(getColor(R.color.green_light))}

                    }
                }.start()


            } else {
                //end quiz frame here
                val intent : Intent = Intent(this@QuestionPage,Score::class.java)
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
            if(optionBinding.text == curQues.correct_answer){
                optionBinding.setBackgroundColor(getColor(R.color.green_light))
                score++
            }
            else{
                optionBinding.setBackgroundColor(getColor(R.color.red_light))
                if(binding.option1.text == curQues.correct_answer){binding.option1.setBackgroundColor(getColor(R.color.green_light))}
                if(binding.option2.text == curQues.correct_answer){binding.option2.setBackgroundColor(getColor(R.color.green_light))}
                if(binding.option3.text == curQues.correct_answer){binding.option3.setBackgroundColor(getColor(R.color.green_light))}
                if(binding.option4.text == curQues.correct_answer){binding.option4.setBackgroundColor(getColor(R.color.green_light))}
            }
            binding.option1.setClickable(false)
            binding.option2.setClickable(false)
            binding.option3.setClickable(false)
            binding.option4.setClickable(false)

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
            position++
            binding.option1.setClickable(true)
            binding.option2.setClickable(true)
            binding.option3.setClickable(true)
            binding.option4.setClickable(true)
            resetColours()
            createQues()
        }


        }
}}