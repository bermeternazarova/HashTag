package com.example.hashtag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtag.databinding.ItemHashTagBinding

class AdapterHashTag(private val click:(String)->Unit):RecyclerView.Adapter<AdapterHashTag.ViewHolderHashTag> (){
    private val hashtag = ArrayList<String>()

    fun addData(newHashtag: ArrayList<String>) {
        hashtag.clear()
        hashtag.addAll(newHashtag)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHashTag {
        return ViewHolderHashTag(ItemHashTagBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderHashTag, position: Int) {
        holder.bind(hashtag[position])
    }

    override fun getItemCount(): Int {
         return hashtag.size
    }

    inner class ViewHolderHashTag(private val binding: ItemHashTagBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String) {
            binding.tvHashTag.text=s
            itemView.setOnClickListener {
                click(s)
            }
        }

    }


}