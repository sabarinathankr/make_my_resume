package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentWorkExperienceBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.roomdb.WorkExperienceModel
import com.buildmyresume.viewmodel.ObjectiveViewModel
import com.buildmyresume.viewmodel.WorkExpViewModel


class WorkExperienceFragment : BMRBaseFragment() {

    private lateinit var binding: FragmentWorkExperienceBinding
    var userId = 0
    private lateinit var workExpViewModel: WorkExpViewModel
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWorkExperienceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && requireArguments().containsKey(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)) {
            userId = requireArguments().getInt(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)
        }


        workExpViewModel = ViewModelProvider(this)[WorkExpViewModel::class.java]


        setClickAction()

        dataObserver()
    }

    private fun dataObserver() {
        workExpViewModel.getSelectedWorkExp(userId).observe(viewLifecycleOwner) {
            getObserveData(it)
        }
    }

    private fun getObserveData(it: List<WorkExperienceModel>?) {

    }

    private fun setClickAction() {

    }
}