package com.example.android.guesstheword.screens.game

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.android.guesstheword.asImmutable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class GameViewModel(app: Application, state: SavedStateHandle)
    : AndroidViewModel(app), AnkoLogger, LifecycleObserver {

    private companion object {
        // Storage keys
        private const val KEY_LIST   = "list"
        private const val KEY_WORD   = "word"
        private const val KEY_SCORE  = "score"
        private const val KEY_STATUS = "status"
        private const val KEY_ELAPSED = "elapsed"
        // Timer constants
private const val TIMER_DURATION     = 6*1000L + 999/*let user see 01:00, not 00:59 at the beginning of the game*/
        private const val TIMER_TICK_TIMEOUT = 1000L
    }

    init {
        info("${hashCode()} init app: $app, state: $state")
    }

    enum class Status { CREATED, PAUSED, ACTIVE, OVER }

    fun getStatus() = status.asImmutable()
    fun getScore()     = score.asImmutable()
    fun getWord()    = word.asImmutable()
    fun getElapsed()  = elapsed.asImmutable()

    @OnLifecycleEvent(value=Lifecycle.Event.ON_RESUME)
    private fun start() = status.value!!.takeIf { it < Status.ACTIVE }?.apply {
        info(" model start called on status ${status.value}")
        status.value = Status.ACTIVE
        timer?.start()
    }
    @OnLifecycleEvent(value=Lifecycle.Event.ON_PAUSE)
    private fun pause() = status.value!!.takeIf { it == Status.ACTIVE }?.apply {
        info(" model pause called on status ${status.value}, timer ${timer}")
        status.value = Status.PAUSED
        timer = null
    }
    private fun finish() = status.value!!.takeIf { it < Status.OVER }?.run {
        info(" model finish called on status ${status.value}")
        status.value = Status.OVER
        timer = null
        true
    } ?: false

    fun onSkip() = status.value.takeIf { it == Status.ACTIVE }?.apply {
        score.value = score.value!!.dec()
        nextWord()
    }.run { Unit }
    fun onCorrect() = status.value!!.takeIf { it == Status.ACTIVE }?.apply {
        score.value = score.value!!.inc()
        nextWord()
    }.run { Unit }

    private val status = state.getLiveData(KEY_STATUS, Status.CREATED)
    private val score = state.getLiveData(KEY_SCORE, 0)
    private val list = state.getLiveData<MutableList<String>>(KEY_LIST).apply {
        if (value == null) value = shuffledList()
    }
    private val word = state.getLiveData<String>(KEY_WORD).apply {
        if (value == null) value = list.value!!.removeAt(0)
    }
    private val elapsed = state.getLiveData(KEY_ELAPSED, TIMER_DURATION)
    private var timer : CountDownTimer? = null
        get() = field ?: if (status.value == Status.OVER) null else object: CountDownTimer(elapsed.value!!, TIMER_TICK_TIMEOUT) {
            init {
                field = this
                info("new timer with '${elapsed.value}' duration. ${this}")
            }
            override fun onFinish() {
                finish()
            }
            override fun onTick(ela: Long) {
                info("onTick $ela")
                elapsed.value = ela
            }
        }
        set(value) {
            info (" set timer value $value, current is $field")
            if (value == null) field?.cancel()
            field = value
        }

    override fun onCleared() {
        super.onCleared()
        info("${hashCode()} onCleared")
        timer = null
    }

    private fun nextWord() {
        if (list.value!!.isEmpty())
            list.value = shuffledList()
        word.value = list.value!!.removeAt(0)
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun shuffledList()= mutableListOf(
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