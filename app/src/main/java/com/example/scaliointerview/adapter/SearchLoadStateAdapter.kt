package com.example.scaliointerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scaliointerview.databinding.ItemLoadingStateBinding


class SearchLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SearchLoadStateAdapter.SearchLoadStateViewHolder>() {

    inner class SearchLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }
            if (loadState is LoadState.Loading){
                binding.progressbar.visibility = View.VISIBLE
            }else{
                binding.progressbar.visibility = View.GONE
            }

            if (loadState is LoadState.Error){
                binding.buttonRetry.visibility = View.VISIBLE
            }else{
                binding.buttonRetry.visibility = View.GONE
            }

            if (loadState is LoadState.Error){
                binding.textViewError.visibility = View.VISIBLE
            }else{
                binding.textViewError.visibility = View.GONE
            }


            binding.buttonRetry.setOnClickListener {
                retry()
            }

            binding.progressbar.visibility = View.VISIBLE
        }
    }

    override fun onBindViewHolder(holder: SearchLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = SearchLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}