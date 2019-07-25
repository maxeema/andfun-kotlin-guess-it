package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class GameViewModel : ViewModel(), AnkoLogger {

    // The current word
    var word = ""

    // The current score
    var score = 0

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        info("${hashCode()} inited")
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        info("${hashCode()} onCleared")
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
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

}