package com.example.scaliointerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scaliointerview.R
import com.example.scaliointerview.model.SearchModel
import de.hdodenhof.circleimageview.CircleImageView

class PagingSearchAdapter(context: Context) :
    PagingDataAdapter<SearchModel, PagingSearchAdapter.SearchViewHolder>(ITEM_COMPARATOR)
{
    private var mContext = context

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val profileImageView = itemView.findViewById<CircleImageView>(R.id.itemProfileImage)
        val loginTv = itemView.findViewById<TextView>(R.id.itemLogin)
        val typeTv = itemView.findViewById<TextView>(R.id.itemType)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.loginTv.text = currentItem!!.login
        holder.typeTv.text = currentItem!!.type
        Glide.with(mContext)
            .load(currentItem.avatar_url)
            .placeholder(R.drawable.ic_baseline_account_circle)
            .into(holder.profileImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchModel>() {
            override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel) =
                oldItem == newItem
        }
    }
}


