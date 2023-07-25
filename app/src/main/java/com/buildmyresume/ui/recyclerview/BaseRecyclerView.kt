package com.buildmyresume.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.buildmyresume.R
import com.buildmyresume.listener.RecyclerViewItem
import com.buildmyresume.ui.adapter.BaseRecyclerViewAdapter
import com.buildmyresume.ui.viewholder.BaseViewHolder

abstract class BaseRecyclerView<ItemType : RecyclerViewItem>(
     inflater: LayoutInflater,
    parent: ViewGroup?
) {

    // RootView
    private val rootView: View = inflater.inflate(R.layout.custom_recycler_container, parent, false)

    // Views
    private val recyclerView: RecyclerView
    private val textViewDataNotFound: AppCompatTextView


    init {
        recyclerView = getRootView().findViewById(R.id.recyclerView) as RecyclerView
        textViewDataNotFound = getRootView().findViewById(R.id.textViewDataNotFound) as AppCompatTextView

        resetInitialState()
    }

    // region UI methods

    fun getRootView(): View = rootView

    protected fun getContext(): Context = getRootView().context

    protected fun getRecyclerView(): RecyclerView = recyclerView

    fun showLoading() {
        textViewDataNotFound.visibility = View.GONE
        recyclerView.visibility = View.INVISIBLE
    }

    fun showMessage(message: String) {
        textViewDataNotFound.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE

        textViewDataNotFound.text = message
    }

    private fun resetInitialState() {
        textViewDataNotFound.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        textViewDataNotFound.visibility = View.GONE
    }
    // endregion UI methods

    // region Data methods
    fun replaceItems(items: List<RecyclerViewItem>) {
        showRecyclerView()
        getAdapter().replaceItems(items)
        getRecyclerView().scheduleLayoutAnimation()
    }

    fun appendItems(items: List<RecyclerViewItem>) {
        showRecyclerView()
        getAdapter().appendItems(items)
    }

    fun appendItems(index: Int, items: List<RecyclerViewItem>) {
        showRecyclerView()
        getAdapter().appendItems(index, items)
    }

    fun addItem(item: RecyclerViewItem) {
        showRecyclerView()
        getAdapter().addItem(item)
    }

    fun removeItem(item: RecyclerViewItem) {
        getAdapter().removeItem(item)
        if (getAdapter().itemCount == 0) {
            showMessage(getDataNotFoundMessage())
        }
    }

    fun updateItem(item: RecyclerViewItem) {
        showRecyclerView()
        getAdapter().updateItem(item)
    }

    fun getItems(): List<ItemType> {
        return getAdapter().getItems()
    }

    fun getItemById(id: String): ItemType? {
        return getAdapter().getItemById(id)
    }

    fun getItemByIndex(index: Int): ItemType? {
        return getAdapter().getItemByIndex(index)
    }

    fun clear() {
        getAdapter().clearData()
//        getScrollListener()?.resetState()
        resetInitialState()
    }

    // endregion Data methods

    abstract fun getAdapter(): BaseRecyclerViewAdapter<ItemType, out BaseViewHolder<ItemType>>

    abstract fun getDataNotFoundMessage(): String

//    abstract fun getScrollListener(): BaseRecyclerViewScrollListener?
}