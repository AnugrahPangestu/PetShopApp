package com.example.aplikasipetshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ProductItemBinding
import com.example.aplikasipetshop.ui.product.Products
import com.squareup.picasso.Picasso


class ListProductsAdapter(private var productList: ArrayList<Products>) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    class ViewHolder(val binding: ProductItemBinding, clickListener: onItemClickListener): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        //return ViewHolder(itemView,mListener)
        return ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent, false), mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.apply {
            binding.apply {
                tvNameProduct.text =  currentItem.name
                tvPriceProduct.text = currentItem.price
                Picasso.get().load(currentItem.imgUri).into(ivProduct)

            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun onApplySearch(productList: ArrayList<Products>){
        this.productList = productList
        notifyDataSetChanged()
    }


}