package com.app.hardik.guesstheword.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.app.hardik.guesstheword.R
import com.app.hardik.guesstheword.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private val TAG = "GameFragment"
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game, container, false)
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        /** Methods for updating the UI **/
        viewModel.word.observe(viewLifecycleOwner) { newWord ->
            binding.wordText.text = newWord.toString()
        }
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.scoreText.text = newScore.toString()
        }
        viewModel.currentTime.observe(viewLifecycleOwner){ newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        }
        /** Called when the game is finished **/
        viewModel.eventGameFinish.observe(viewLifecycleOwner) { isFinish ->
            if (isFinish) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
//                Toast.makeText(requireContext(), "Game is finish", Toast.LENGTH_SHORT).show()
                viewModel.onGameFinishCompleted()
            }
        }

        return binding.root
    }


}