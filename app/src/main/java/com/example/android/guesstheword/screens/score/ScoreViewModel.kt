package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ScoreViewModel(value: Int) : ViewModel(), AnkoLogger {

    init {
        info("score view model init with '$value'")
    }

    val score : LiveData<Int> = MutableLiveData(value)

    override fun onCleared() {
        super.onCleared()
        info("score view model is cleared")
    }

}