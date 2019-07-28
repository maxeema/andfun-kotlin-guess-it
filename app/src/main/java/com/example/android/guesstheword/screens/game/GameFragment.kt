/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.screens.game.GameViewModel.Status.OVER
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment(), AnkoLogger {

    private val model by viewModels<GameViewModel>(factoryProducer = {
        SavedStateViewModelFactory(this)
    })

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        info("${hashCode()} onCreateView, savedInstanceState $savedInstanceState")

        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        binding.lifecycleOwner = this
        binding.model = model

        info("${model.hashCode()} model is $model")

        model.getStatus().observe(this, Observer { status ->
            if (status == OVER)
                findNavController().navigate(GameFragmentDirections.actionGameToScore(model.getScore().value!!))
        })
        model.getElapsed().observe(this, Observer { binding.timerText.text = DateUtils.formatElapsedTime(it.div(1000)) })

        lifecycle.addObserver(model)

        return binding.root
    }

    //
    override fun onAttach(context: Context) {
        super.onAttach(context)
        info("${hashCode()} onAttach, context: $context")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("${hashCode()} onCreate")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        info("${hashCode()} onActivityCreated")
    }
    override fun onStart() {
        super.onStart()
        info("${hashCode()} onStart")
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        info("${hashCode()} onViewStateRestored, savedInstanceState: $savedInstanceState")
    }
    override fun onResume() {
        super.onResume()
        info("${hashCode()} onResume")
    }
    override fun onDestroy() {
        super.onDestroy()
        info("${hashCode()} onDestroy")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        info("${hashCode()} onDestroyView")
    }
    override fun onDetach() {
        super.onDetach()
        info("${hashCode()} onDetach")
    }
    override fun onStop() {
        super.onStop()
        info("${hashCode()} onStop")
    }
    override fun onPause() {
        super.onPause()
        info("${hashCode()} onPause")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info("${hashCode()} onViewCreated, savedInstanceState: $savedInstanceState")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        info("${hashCode()} onSaveInstanceState, outState: $outState")
    }

}
