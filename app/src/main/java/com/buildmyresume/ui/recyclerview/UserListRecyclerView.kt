package com.buildmyresume.ui.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildmyresume.listener.UserListClickListener
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.ui.adapter.BaseRecyclerViewAdapter
import com.buildmyresume.ui.adapter.UserListAdapter
import com.buildmyresume.ui.viewholder.BaseViewHolder

class UserListRecyclerView(
    inflater: LayoutInflater,
    parent: ViewGroup,
    val activity: Activity
) : BaseRecyclerView<PersonalDetailsModel>(inflater, parent) {
    private val adapter = UserListAdapter(activity)

    private val layoutManager =
        LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)


    init {
        getRecyclerView().layoutManager = layoutManager
        getRecyclerView().adapter = adapter
    }

    override fun getAdapter(): BaseRecyclerViewAdapter<PersonalDetailsModel, out BaseViewHolder<PersonalDetailsModel>> {
        return adapter
    }

    override fun getDataNotFoundMessage(): String {
        return "No data found"
    }

    fun setListener(listener: UserListClickListener) {
        adapter.setListener(listener)
    }

}