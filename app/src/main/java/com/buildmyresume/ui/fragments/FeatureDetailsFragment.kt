package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.buildmyresume.databinding.FragmentFeatureDetailsBinding
import com.buildmyresume.di.fragment.FragmentComponent

class FeatureDetailsFragment : BMRBaseFragment(), OnClickListener {
    private lateinit var binding: FragmentFeatureDetailsBinding
    var userId: Int = 0
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFeatureDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && requireArguments().containsKey(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)) {
            userId = requireArguments().getInt(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)
        }
        setClickAction()
    }

    private fun setClickAction() {
        binding.personalDetailsTv.setOnClickListener(this)
        binding.objectiveTv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (null == v!!.tag) {
            return
        }
        if ("personalDetailsTv" == v.tag || 0 == userId) {
            if (0 == userId){
                navigator.popUp()
            }
            navigator.load(GetPersonalDetailsFragment::class.java)
                .setBundle(GetPersonalDetailsFragment.createBundleHome(userId)).replace(true)
        } else   if ("objectiveTv" == v.tag) {
            navigator.load(ObjectiveFragment::class.java)
                .setBundle(GetPersonalDetailsFragment.createBundleHome(userId)).replace(true)
        }

    }
}