package com.example.android.guesstheword.screens.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.screens.game.asMutable
import com.example.android.guesstheword.screens.game.toImmutable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ScoreViewModel(value: Int) : ViewModel(), AnkoLogger {

    init {
        info("score view model init with '$value'")
    }

    val score          = MutableLiveData(value).toImmutable()
    val playAgainEvent = MutableLiveData(false).toImmutable()

    fun playAgain() {
        playAgainEvent.asMutable().value = true
    }

    override fun onCleared() {
        super.onCleared()
        info("score view model is cleared")
    }

}