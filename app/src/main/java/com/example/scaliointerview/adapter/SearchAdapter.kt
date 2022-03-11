package com.example.scaliointerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scaliointerview.R
import com.example.scaliointerview.model.SearchModel
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapter(
    context: Context,
    itemsList : MutableList<SearchModel>
) : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    private var mContext = context
    private var mItemsList = itemsList

    class SearchAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val profileImageView = itemView.findViewById<CircleImageView>(R.id.itemProfileImage)
        val loginTv = itemView.findViewById<TextView>(R.id.itemLogin)
        val typeTv = itemView.findViewById<TextView>(R.id.itemType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
       val currentItem = mItemsList[position]
        holder.loginTv.text = currentItem.login
        holder.typeTv.text = currentItem.type
        Glide.with(mContext)
            .load(currentItem.profileUrl)
            .placeholder(R.drawable.ic_baseline_account_circle)
            .into(holder.profileImageView)
    }

    override fun getItemCount(): Int {
       return mItemsList.size
    }
}