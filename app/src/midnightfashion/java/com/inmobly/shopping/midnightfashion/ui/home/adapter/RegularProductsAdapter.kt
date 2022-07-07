package com.inmobly.shopping.midnightfashion.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.ViewHolderProductRegularItemBinding
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.extensions.loadUrl

class RegularProductsAdapter internal constructor(
    context: Context,
    private var mClickListener: ItemClickListener?
) : RecyclerView.Adapter<RegularProductsAdapter.ProductViewHolder>() {
    private val mInflater: LayoutInflater
    private val context: Context
    private var mData: MutableList<com.inmobly.common_ui.model.products.Product?>? = null

    fun setData(data: MutableList<com.inmobly.common_ui.model.products.Product?>?) {
        mData = data
        notifyDataSetChanged()
    }
    fun setDataWithout(data: MutableList<com.inmobly.common_ui.model.products.Product?>?) {
        mData = data
       // notifyDataSetChanged()
    }

    fun getData():MutableList<com.inmobly.common_ui.model.products.Product?>?{
        return mData?.toMutableList()
    }


    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ViewHolderProductRegularItemBinding.inflate(mInflater, parent, false)
        return ProductViewHolder(binding, mClickListener, this)
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
        private val binding: ViewHolderProductRegularItemBinding,
        private val itemClickListener: ItemClickListener?,
        private val regularProductsAdapter: RegularProductsAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: com.inmobly.common_ui.model.products.Product?) {
            binding.productNameTextView.text = product?.name
            binding.productPriceTextView.text = " ${product?.price}$"
            binding.productPhotoImageView.loadUrl(product?.img)
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(product, adapterPosition)
            }
            binding.productFavouriteButton.setOnClickListener {
                itemClickListener?.onFavButtonClick(
                    product,
                    adapterPosition,
                    regularProductsAdapter
                )
            }
            binding.cartFab.setOnClickListener {
                itemClickListener?.onAddToCartClick(
                    product,
                    adapterPosition,
                )
            }
            binding.productFavouriteButton.setImageResource(if (product?.isFavorite == true) R.drawable.ic_fav_selected_small else R.drawable.ic_fav_not_selected_small)
        }

    }

    // convenience method for getting data at click position
    fun getItem(id: Int): com.inmobly.common_ui.model.products.Product? {
        return mData?.get(id)
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(product: com.inmobly.common_ui.model.products.Product?, position: Int)
        fun onFavButtonClick(
            product: com.inmobly.common_ui.model.products.Product?,
            position: Int,
            regularProductsAdapter: RegularProductsAdapter?
        )

        fun onAddToCartClick(product: com.inmobly.common_ui.model.products.Product?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        this.context = context
    }
}