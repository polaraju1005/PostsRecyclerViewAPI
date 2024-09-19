package com.example.apidatarecycler.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apidatarecycler.databinding.ListViewBinding
import com.example.apidatarecycler.model.Response
import com.example.apidatarecycler.model.ResponseItem

class ResponseAdapter : RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {

    private var posts: List<ResponseItem> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ResponseViewHolder {
        val binding = ListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResponseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun submitList(postsData: Response) {
        posts = postsData
        notifyDataSetChanged()
    }

    class ResponseViewHolder(private val binding: ListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false

        @SuppressLint("SetTextI18n")
        fun bind(responseItem: ResponseItem) {
            binding.titleTextView.text = "Title:${responseItem.title}"
            binding.bodyTextView.text = responseItem.body

            binding.bodyTextView.maxLines = 2
            binding.bodyTextView.ellipsize = android.text.TextUtils.TruncateAt.END

            binding.bodyTextView.setOnClickListener {
                if (isExpanded) {
                    binding.bodyTextView.maxLines = 2
                    binding.bodyTextView.ellipsize = android.text.TextUtils.TruncateAt.END
                    isExpanded = false
                } else {
                    binding.bodyTextView.maxLines = Int.MAX_VALUE
                    binding.bodyTextView.ellipsize = null
                    isExpanded = true
                }
            }
        }
    }
}