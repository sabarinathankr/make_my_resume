package com.buildmyresume.ui.adapter

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.buildmyresume.R
import com.buildmyresume.databinding.LayoutUserListBinding
import com.buildmyresume.listener.UserListClickListener
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.ui.viewholder.BaseViewHolder
import com.buildmyresume.util.UiUtil

class UserListAdapter(val activity: Activity) :
    BaseRecyclerViewAdapter<PersonalDetailsModel, BaseViewHolder<PersonalDetailsModel>>() {
    private var listener: UserListClickListener? = null
    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<PersonalDetailsModel> {
        return UserListViewHolder(LayoutUserListBinding.inflate(inflater), activity)
    }

    fun setListener(listener: UserListClickListener) {
        this.listener = listener
    }

    inner class UserListViewHolder(
        private val binding: LayoutUserListBinding,
        private val mActivity: Activity,
    ) :
        BaseViewHolder<PersonalDetailsModel>(binding.root) {

        override fun bind(item: PersonalDetailsModel) {
            UiUtil().getNonNullLayoutParams(binding.userListMainCl).width =
                LayoutParams.MATCH_PARENT
            itemView.background = ContextCompat.getDrawable(itemView.context, R.color.skin)
            binding.userNameTv.text = item.name
            binding.designationTv.text = item.profession
            itemView.setOnClickListener { listener!!.onItemClick(item) }
            setUpUiAlignment(binding, mActivity, item.itemId.toInt(), itemCount)
        }

    }

    fun setUpUiAlignment(
        binding: LayoutUserListBinding,
        activity: Activity,
        position: Int,
        itemCount: Int,
    ) {

        //main constrain layout
        UiUtil().setUIAlignment(
            binding.userListMainCl,
            activity,
            arrayOf(20.0, 10.0, null, null, 325.0, 100.0)
        )
        if (0 == position)
            UiUtil().setUIAlignment(
                binding.userListMainCl,
                activity,
                arrayOf(40.0, 10.0, null, null, 325.0, 100.0)
            )

       if (itemCount == position){
            UiUtil().setUIAlignment(
                binding.userListMainCl,
                activity,
                arrayOf(20.0, 100.0, null, null, 325.0, 100.0)
            )
        }

    }

}