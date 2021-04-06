package com.myubuntu.istestproject.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.model.Product

class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var list = ArrayList<Product>()

    fun setData(list: ArrayList<Product>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.tvProductName.text = product.name
        holder.tvProductDesc.text = product.description
        holder.tvProductPrice.text = String.format("Price: %s", product.price)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvProductDesc: TextView = itemView.findViewById(R.id.tvProductDesc)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)

    }
}