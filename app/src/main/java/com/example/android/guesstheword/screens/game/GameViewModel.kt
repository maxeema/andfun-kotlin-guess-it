package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class GameViewModel : ViewModel(), AnkoLogger {

    init {
        info("${hashCode()} inited")
    }

    override fun onCleared() {
        super.onCleared()
        info("${hashCode()} onCleared")
    }

}