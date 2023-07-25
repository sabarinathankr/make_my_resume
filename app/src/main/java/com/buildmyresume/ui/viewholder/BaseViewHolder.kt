package com.buildmyresume.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buildmyresume.listener.RecyclerViewItem

abstract class BaseViewHolder<ItemType : RecyclerViewItem>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ItemType)
}