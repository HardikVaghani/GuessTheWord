package com.app.hardik.guesstheword.screens.score

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int) : ViewModel(){
    private val TAG:String = "ScoreViewModel"
   private val _eventPlayAgain = MutableLiveData<Boolean>()//for internal use
    val eventPlayAgain : LiveData<Boolean>get() = _eventPlayAgain//for external use

    private val _score = MutableLiveData<Int>()//for internal use
    val score : LiveData<Int>get() = _score//for external use

    init {
        _score.value = finalScore

    }

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }
    fun onPlayAgainOnComplete(){
        _eventPlayAgain.value = false
    }
}