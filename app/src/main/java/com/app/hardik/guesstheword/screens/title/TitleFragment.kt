package com.app.hardik.guesstheword.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.hardik.guesstheword.R
import com.app.hardik.guesstheword.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_title, container, false)
            val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false)

            binding.playGameButton.setOnClickListener {
                findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
            }
            return binding.root
    }

}