package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentHomeBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.listener.UserListClickListener
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.ui.recyclerview.UserListRecyclerView
import com.buildmyresume.viewmodel.PersonalDetailsViewModel

class HomeFragment : BMRBaseFragment(), UserListClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var rvUserList: UserListRecyclerView

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    lateinit var list: List<PersonalDetailsModel>

    private lateinit var personalDetailsViewModel: PersonalDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalDetailsViewModel = ViewModelProvider(this)[PersonalDetailsViewModel::class.java]

        personalDetailsViewModel.getAllPersonalDetails().observe(viewLifecycleOwner) {
            getObserveData(it)
        }
        binding.getStartTv.setOnClickListener {
            navigator.load(FeatureDetailsFragment::class.java).replace(true)
        }
        rvUserList = UserListRecyclerView(
            LayoutInflater.from(requireContext()),
            binding.containerUserList,
            requireActivity()
        )
        binding.containerUserList.addView(rvUserList.getRootView())
        rvUserList.setListener(this)
    }

    private fun getObserveData(notes: List<PersonalDetailsModel>) {
        rvUserList.clear()
        rvUserList.appendItems(notes.sortedBy { data -> data.id })
    }

    override fun onItemClick(item: PersonalDetailsModel) {
        navigator.load(FeatureDetailsFragment::class.java).setBundle(ViewPersonalDetailsFragment.createBundleHome(item.id!!)).replace(true)
    }

}