package com.example.aplikasipetshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipetshop.databinding.ProductItemBinding
import com.example.aplikasipetshop.ui.product.Products


class ListProductsAdapter(private val productList: ArrayList<Products>) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.apply {
            binding.apply {
                tvNameProduct.text =  currentItem.name
                tvPriceProduct.text = currentItem.price
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}