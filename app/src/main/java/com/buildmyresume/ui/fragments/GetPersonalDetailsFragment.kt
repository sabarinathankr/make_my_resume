package com.buildmyresume.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.buildmyresume.databinding.FragmentGetPersonalDetailsBinding
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.viewmodel.PersonalDetailsViewModel

class GetPersonalDetailsFragment : BMRBaseFragment(), OnClickListener {
    private lateinit var binding: FragmentGetPersonalDetailsBinding
    private lateinit var personalDetailsViewModel: PersonalDetailsViewModel
    private var userId: Int = 0
    private var userListSize = 0
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
        binding = FragmentGetPersonalDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && requireArguments().containsKey(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)) {
            userId = requireArguments().getInt(ViewPersonalDetailsFragment.BUNDLE_NAVIGATED_FROM)
        }

        personalDetailsViewModel = ViewModelProvider(this)[PersonalDetailsViewModel::class.java]
        personalDetailsViewModel.getAllPersonalDetails().observe(viewLifecycleOwner) {
            getObserveData(it)
        }
        setUpUi()

    }

    private fun setUpDbData(id: Int, list: List<PersonalDetailsModel>?) {
        val index = id-1
        binding.nameEt.setText(list?.get(index)!!.name)
        binding.professionEt.setText(list[index].profession)
    }

    private fun getObserveData(list: List<PersonalDetailsModel>?) {
        userListSize = list!!.size
        if (0 != userId)
            setUpDbData(userId, list)
    }

    private fun setUpUi() {
        binding.saveBtn.setOnClickListener(this)
    }

    private fun saveInfo(userId: Int) {
        if (0 == userId) {
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
            navigator.load(ObjectiveFragment::class.java)
                .setBundle(createBundleHome(userListSize+1)).replace(true)
        } else {
            personalDetailsViewModel.update(
                PersonalDetailsModel(
                    binding.nameEt.text.toString(),
                    binding.professionEt.text.toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    userId
                )
            )
            navigator.load(ObjectiveFragment::class.java)
                .setBundle(createBundleHome(userId)).replace(true)
        }
    }

    override fun onClick(v: View?) {
        if (null == v!!.tag) {
            return
        }
        if ("saveBtn" == v.tag) {
            if (binding.nameEt.text!!.isNotEmpty()) {
                saveInfo(userId)
                return
            }
            Toast.makeText(mContext, "Enter the Your Name", Toast.LENGTH_SHORT).show()
        }

    }
}