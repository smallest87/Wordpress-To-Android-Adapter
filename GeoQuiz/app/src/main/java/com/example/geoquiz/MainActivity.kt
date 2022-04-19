package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val KEY_NUMBER_ANSWER = "answer"

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val quizViewModel: QuizViewModel by lazy{
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        quizViewModel.currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0)?: 0
        quizViewModel.numberOfCorrectAnswers = savedInstanceState?.getInt(KEY_NUMBER_ANSWER, 0)?: 0

        binding.TextViewCorrectAnswersNumber.text = quizViewModel.numberOfCorrectAnswers.toString()
        binding.buttonTrue.setOnClickListener {
            checkAnswer(true)
            binding.buttonFalse.isClickable = quizViewModel.arrayOfBool[1]
            binding.buttonTrue.isClickable = quizViewModel.arrayOfBool[1]
        }
        binding.buttonFalse.setOnClickListener {
            checkAnswer(false)
            binding.buttonFalse.isClickable = quizViewModel.arrayOfBool[1]
            binding.buttonTrue.isClickable = quizViewModel.arrayOfBool[1]
        }
        updateQuestion()

        binding.ImageViewNext.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
            binding.buttonFalse.isClickable = quizViewModel.arrayOfBool[0]
            binding.buttonTrue.isClickable = quizViewModel.arrayOfBool[0]
        }
        binding.ImageViewPrevious.setOnClickListener {
            if(quizViewModel.currentIndex == 0){
                Toast.makeText(this, R.string.first_question_warning, Toast.LENGTH_SHORT).show()
            }else{
                quizViewModel.moveToPrevious()
                updateQuestion()
            }
        }
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.textViewQuestion.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId: Int
         if(userAnswer == correctAnswer){
             messageResId = R.string.correct_toast
             quizViewModel.increaseNumberOfCorrectAnswers()
        }else{
             messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        binding.TextViewCorrectAnswersNumber.text = quizViewModel.numberOfCorrectAnswers.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_NUMBER_ANSWER, quizViewModel.numberOfCorrectAnswers)
    }
}