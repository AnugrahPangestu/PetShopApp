package com.example.aplikasipetshop.faqntips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipetshop.R

class FaqnTipsAdapter (private val listTips: ArrayList<FaqAndTips>) : RecyclerView.Adapter<FaqnTipsAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_name_faq)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_text_faq)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.faqntips_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description) = listTips[position]
        holder.tvTitle.text = name
        holder.tvDescription.text = description
    }

    override fun getItemCount(): Int = listTips.size
}
