package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.buildmyresume.databinding.FragmentViewPersonalDetailsBinding
import com.buildmyresume.di.fragment.FragmentComponent

class ViewPersonalDetailsFragment : BMRBaseFragment() {
    private lateinit var binding: FragmentViewPersonalDetailsBinding
    var userId:Int? = null
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    companion object {
        const val BUNDLE_NAVIGATED_FROM = "BUNDLE_NAVIGATED_FROM"

        fun createBundleHome(id: Int): Bundle {
            val args = Bundle()
            args.putSerializable(BUNDLE_NAVIGATED_FROM, id)
            return args
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewPersonalDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && requireArguments().containsKey(BUNDLE_NAVIGATED_FROM)) {
            userId = requireArguments().getInt(BUNDLE_NAVIGATED_FROM)
        }

        Toast.makeText(mContext, userId.toString(), Toast.LENGTH_SHORT).show()

    }
}