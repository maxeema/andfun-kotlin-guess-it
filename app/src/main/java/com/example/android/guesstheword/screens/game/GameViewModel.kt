package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.BuildConfig
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

fun <T> MutableLiveData<T>.toImmutable() = this as LiveData<T>

class GameViewModel(state: SavedStateHandle) : ViewModel(), AnkoLogger {

    enum class Status { ACTIVE, OVER }

    private companion object {
        private const val KEY_LIST   = "list"
        private const val KEY_WORD   = "word"
        private const val KEY_SCORE  = "score"
        private const val KEY_STATUS = "status"
    }

    init { if (BuildConfig.DEBUG) info("init state: $state") }

    private val status = state.getLiveData(KEY_STATUS, Status.ACTIVE)
    private val score = state.getLiveData(KEY_SCORE, 0)
    private val list = state.getLiveData<MutableList<String>>(KEY_LIST).apply {
        if (value == null) value = shuffleList()
    }
    private val word = state.getLiveData<String>(KEY_WORD).apply {
        if (value == null) value = list.value!!.removeAt(0)
    }

    fun getStatus() = status.toImmutable()
    fun getScore()     = score.toImmutable()
    fun getWord()    = word.toImmutable()

    override fun onCleared() {
        super.onCleared()
        if (BuildConfig.DEBUG)
            info("${hashCode()} onCleared")
    }

    private fun nextWord() {
        if (list.value!!.isEmpty()) {
            status.value = Status.OVER
        } else {
            word.value = list.value!!.removeAt(0)
        }
    }

    fun onSkip() = score.takeIf { status.value == Status.ACTIVE }?.apply {
        value = value!!.dec()
        nextWord()
    }

    fun onCorrect() = score.takeIf { status.value == Status.ACTIVE }?.apply {
        value = value!!.inc()
        nextWord()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun shuffleList()= mutableListOf(
        "queen",
        "basketball",
//        "hospital",
//        "cat",
//        "change",
//        "snail",
//        "soup",
//        "calendar",
//        "sad",
//        "desk",
//        "guitar",
//        "home",
//        "railway",
//        "zebra",
//        "jelly",
//        "crow",
//        "trade",
//        "roll",
//        "bag",
//        "bubble",
        "car"
    ).apply {
        this.shuffle()
    }

}