package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentSplashBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.viewmodel.PersonalDetailsViewModel

class SplashFragment : BMRBaseFragment() {

    private lateinit var binding: FragmentSplashBinding

    lateinit var personalDetailsViewModel: PersonalDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalDetailsViewModel = ViewModelProvider(this)[PersonalDetailsViewModel::class.java]

        personalDetailsViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {

        })

        binding.welcomeTv.setOnClickListener {
            navigator.load(HomeFragment::class.java).replace(true)
        }
    }


    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }
}