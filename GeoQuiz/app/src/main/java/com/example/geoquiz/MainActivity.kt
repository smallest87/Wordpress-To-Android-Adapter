package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val KEY_NUMBER_ANSWER = "answer"
private const val REQUEST_CODE_CHEAT = 0

@Suppress("DEPRECATION")
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
        binding.TextViewCheatingCountNumber.text = quizViewModel.numberOfCheating.toString()
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
        binding.cheatButton.setOnClickListener {
            val answerIsTrue =quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivityForResult(
                intent,
                REQUEST_CODE_CHEAT
            )
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater =
                data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)?: false

        }
    }


    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.textViewQuestion.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId: Int
        when (userAnswer) {
            correctAnswer -> {
                messageResId = R.string.correct_toast
                quizViewModel.increaseNumberOfCorrectAnswers()
            }
            else -> {
                messageResId = R.string.incorrect_toast
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        if(quizViewModel.isCheater) {
            Toast.makeText(this, R.string.judgement_toast, Toast.LENGTH_SHORT).show()
            if(quizViewModel.numberOfCheating == 0){
                binding.cheatButton.visibility = View.INVISIBLE
            }

                quizViewModel.decreaseNumberOfCheating()
                binding.TextViewCheatingCountNumber.text =
                    quizViewModel.numberOfCheating.toString()
            
        }
        binding.TextViewCorrectAnswersNumber.text = quizViewModel.numberOfCorrectAnswers.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_NUMBER_ANSWER, quizViewModel.numberOfCorrectAnswers)
    }

}