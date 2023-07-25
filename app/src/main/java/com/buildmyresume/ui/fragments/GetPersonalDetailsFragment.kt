package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentGetPersonalDetailsBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.viewmodel.PersonalDetailsViewModel

class GetPersonalDetailsFragment : BMRBaseFragment() {
    lateinit var binding: FragmentGetPersonalDetailsBinding
    lateinit var personalDetailsViewModel: PersonalDetailsViewModel
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGetPersonalDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalDetailsViewModel = ViewModelProvider(this)[PersonalDetailsViewModel::class.java]
        setUpUi()
    }

    private fun setUpUi() {
        binding.saveBtn.setOnClickListener {
            if (binding.nameEt.text!!.isNotEmpty()) {
                saveInfo()
                navigator.popUp()
                return@setOnClickListener
            }
            Toast.makeText(mContext, "Enter the Your Name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveInfo() {
        personalDetailsViewModel.insert(
            PersonalDetailsModel(
                binding.nameEt.text.toString(),
                binding.professionEt.text.toString(),
                "",
                "",
                "",
                "",
                ""
            )
        )
    }
}