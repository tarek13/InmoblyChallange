package com.inmobly.shopping.midnightfashion.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.ViewHolderProductFullItemBinding
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.extensions.loadUrl

class FullWidthProductsAdapter internal constructor(
    context: Context,
    private var mClickListener: ItemClickListener?
) : RecyclerView.Adapter<FullWidthProductsAdapter.ProductViewHolder>() {
    private val mInflater: LayoutInflater
    private val context: Context
    private var mData: List<Product?>? = null

    fun setData(data: List<Product?>?) {
        mData = data
        notifyDataSetChanged()
    }


    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ViewHolderProductFullItemBinding.inflate(mInflater, parent, false)
        return ProductViewHolder(binding, mClickListener,this)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = mData?.get(position)
        holder.onBind(item)

    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    // stores and recycles views as they are scrolled off screen
    inner class ProductViewHolder internal constructor(
        private val binding: ViewHolderProductFullItemBinding,
        private val itemClickListener: ItemClickListener?,
        private  val  fullWidthProductsAdapter: FullWidthProductsAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: Product?) {
            binding.productNameTextView.text = product?.name
            binding.productPriceTextView.text = " ${product?.price}$"
            binding.productPhotoImageView.loadUrl(product?.img)
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(product, adapterPosition)
            }
            binding.productFavoriteButton.setOnClickListener {
                itemClickListener?.onFavButtonClick(product,adapterPosition,fullWidthProductsAdapter)
            }
            binding.cartFab.setOnClickListener {
                itemClickListener?.onAddToCartClick(
                    product,
                    adapterPosition
                )
            }
            binding.productFavoriteButton.setImageResource(if (product?.isFavorite == true) R.drawable.ic_fav_selected_small else R.drawable.ic_fav_not_selected_small)

        }

    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(product: Product?, position: Int)
        fun onFavButtonClick(
            product: Product?,
            position: Int,
            fullWidthProductsAdapter: FullWidthProductsAdapter
        )
        fun onAddToCartClick(product: Product?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        this.context = context
    }
}