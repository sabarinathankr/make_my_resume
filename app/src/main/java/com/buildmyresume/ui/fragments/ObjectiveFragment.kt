package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentObjectiveBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.roomdb.ObjectiveModel
import com.buildmyresume.viewmodel.ObjectiveViewModel

class ObjectiveFragment : BMRBaseFragment(), OnClickListener {
    private var userId: Int = 0
    private lateinit var objectiveViewModel: ObjectiveViewModel
    private lateinit var binding: FragmentObjectiveBinding
    private var isAlreadySaved: Boolean = false
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentObjectiveBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && requireArguments().containsKey(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)) {
            userId = requireArguments().getInt(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)
        }

        objectiveViewModel = ViewModelProvider(this)[ObjectiveViewModel::class.java]


        setClickAction()

        dataObserver()
    }

    private fun dataObserver() {
        if (0 != userId)
            objectiveViewModel.getSelectedObjective(userId).observe(viewLifecycleOwner) {
                getObserveData(it)
            }

    }


    private fun setClickAction() {
        binding.saveBtn.setOnClickListener(this)
    }

    private fun getObserveData(list: List<ObjectiveModel>?) {
            setUpDbData(list)
    }

    private fun setUpDbData( list: List<ObjectiveModel>?) {
        isAlreadySaved = if (list!!.isNotEmpty()){
            binding.objectiveEt.setText(list[0].objective)
            true
        } else{
            false
        }
    }

    override fun onClick(v: View?) {
        if (null == v!!.tag) {
            return
        }
        if ("saveBtn" == v.tag) {
            if (!isAlreadySaved) {
                objectiveViewModel.insert(
                    ObjectiveModel(
                        userId,
                        binding.objectiveEt.text.toString()
                    )
                )
            } else {
                objectiveViewModel.update(
                    binding.objectiveEt.text.toString(), userId
                )
            }
            navigator.popUp()
            navigator.popUp()
        }
    }
}