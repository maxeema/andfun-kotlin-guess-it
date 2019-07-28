package com.example.android.guesstheword.screens.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.asImmutable
import com.example.android.guesstheword.asMutable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ScoreViewModel(value: Int) : ViewModel(), AnkoLogger {

    init {
        info("score view model init with '$value'")
    }

    val score          = MutableLiveData(value).asImmutable()
    val playAgainEvent = MutableLiveData(false).asImmutable()

    fun playAgain() {
        info("play again called")
        playAgainEvent.asMutable().value = true
    }

    override fun onCleared() {
        super.onCleared()
        info("score view model is cleared")
    }

}