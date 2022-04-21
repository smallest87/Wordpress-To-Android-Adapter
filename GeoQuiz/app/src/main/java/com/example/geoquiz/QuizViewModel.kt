package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question1, true),
        Question(R.string.question2,false),
        Question(R.string.question3, true),
        Question(R.string.question4,false))
    var currentIndex = 0
    var isCheater = false
    var numberOfCheating = 3
    var numberOfCorrectAnswers: Int = 0
    var arrayOfBool = arrayOf(true, false)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

    fun increaseNumberOfCorrectAnswers(){
        numberOfCorrectAnswers++
    }

    fun decreaseNumberOfCheating(){
        numberOfCheating--
    }

}