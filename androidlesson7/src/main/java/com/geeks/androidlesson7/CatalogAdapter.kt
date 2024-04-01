package com.geeks.androidlesson7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeks.androidlesson7.databinding.ItemProductBinding

class CatalogAdapter(
    private val catalogList: MutableList<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CatalogAdapter.ProductViewHolder>() {

    fun addProduct(product: Product) {
        catalogList.add(product)
        notifyDataSetChanged()
    }

    fun removeProduct(position: Int) {
        catalogList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(catalogList[adapterPosition])
            }
            binding.root.setOnLongClickListener {
                onItemClickListener.onLongClick(adapterPosition)
                false
            }
        }

        fun bind(product: Product) = with(binding) {
            ivProductCover.setImageResource(product.coverImage)
            tvProductPrice.text = product.price.toString()
            tvProductFirm.text = product.firm
            tvProductBody.text = product.body
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(catalogList[position])
    }

    override fun getItemCount() = catalogList.size
}