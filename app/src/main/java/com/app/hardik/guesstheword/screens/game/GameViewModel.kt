package com.app.hardik.guesstheword.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Objects
import kotlin.random.Random
import java.lang.Object as Object1

class GameViewModel : ViewModel() {
    val TAG = "GameViewModel"

    companion object{
        //this is when the game is over
        private const val DONE = 0L

        //this is number of millisecond in a second
        private const val ONE_SECOND = 1000L

        //this is the total time of the game
        private const val COUNTDOWN_TIME = 60000L
    }

    private val timer:CountDownTimer

    //The Current time
    private val _currentTime = MutableLiveData<Long>()//for internal use
    val currentTime: LiveData<Long> get() = _currentTime//for outer use

    // The current word
    // var word = ""
    private val _word = MutableLiveData<String>()//for internal use
    val word: LiveData<String> get() = _word // for outer use

    // The current score
    //var score = 0
    private val _score = MutableLiveData<Int>()//for internal use
    val score: LiveData<Int> get() = _score//for outer use

    // The current state
    private val _eventGameFinish = MutableLiveData<Boolean>()//for internal use
    val eventGameFinish: LiveData<Boolean> get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
//        _eventGameFinish.value = false
        resetList()
        nextWord()
        _score.value = 0
//        word.value = ""

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }

        }
        timer.start()

    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            _eventGameFinish.value = true
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (_score.value)?.minus(1) // -1
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1) // +1
        nextWord()
    }

    fun onGameFinishCompleted() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i(TAG, ": GameViewModel Destroyed")
    }
}