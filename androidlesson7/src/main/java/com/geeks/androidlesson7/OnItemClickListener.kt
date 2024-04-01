package com.geeks.androidlesson7

interface OnItemClickListener {
    fun onItemClick(product: Product)
    fun onLongClick(position: Int)
}