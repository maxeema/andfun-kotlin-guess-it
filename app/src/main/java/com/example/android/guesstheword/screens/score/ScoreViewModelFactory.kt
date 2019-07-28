package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(private val score: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        require(modelClass.isAssignableFrom(ScoreViewModel::class.java)).run {
            ScoreViewModel(score) as T
        }

}