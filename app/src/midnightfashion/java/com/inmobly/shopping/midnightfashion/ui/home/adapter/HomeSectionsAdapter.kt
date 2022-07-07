package com.inmobly.shopping.midnightfashion.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.inmobly.common_ui.databinding.ViewHolderEmptyLayoutBinding
import com.inmobly.common_ui.databinding.ViewHolderSectionFullWidthBinding
import com.inmobly.common_ui.databinding.ViewHolderSectionRegularBinding
import com.inmobly.common_ui.ui.view_holder.EmptyViewHolder
import com.inmobly.shopping.core.domain.util.AppConstants.FULL_WIDTH_CARD_STYLE
import com.inmobly.shopping.core.domain.util.AppConstants.HOME_SECTION_FULL_WIDTH
import com.inmobly.shopping.core.domain.util.AppConstants.HOME_SECTION_REGULAR
import com.inmobly.shopping.core.domain.util.AppConstants.REGULAR_CARD_STYLE
import com.inmobly.shopping.midnightfashion.ui.home.view_holders.FullWidthSectionViewHolder
import com.inmobly.shopping.midnightfashion.ui.home.view_holders.RegularSectionViewHolder

class HomeSectionsAdapter internal constructor(
    context: Context,
    private val homeSectionCallBack: HomeSectionCallBack,
    private val lifecycleOwner: LifecycleOwner,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mData: MutableList<com.inmobly.common_ui.model.products.HomeSection?>? = null
    private val context: Context
    private val mInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME_SECTION_FULL_WIDTH -> {
                val binding = ViewHolderSectionFullWidthBinding.inflate(mInflater, parent, false)
                FullWidthSectionViewHolder(
                    context,
                    binding,
                    homeSectionCallBack,
                    lifecycleOwner,
                    com.inmobly.common_ui.utils.StateListener()
                )
            }
            HOME_SECTION_REGULAR -> {
                val binding = ViewHolderSectionRegularBinding.inflate(mInflater, parent, false)
                RegularSectionViewHolder(
                    context,
                    binding,
                    homeSectionCallBack,
                    lifecycleOwner,
                    com.inmobly.common_ui.utils.StateListener()
                )
            }
            else -> {
                val binding = ViewHolderEmptyLayoutBinding.inflate(mInflater, parent, false)
                EmptyViewHolder(binding)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FullWidthSectionViewHolder -> {
                holder.onBind(mData?.get(position))
                /*val vm = homeViewModel.getBannerViewModelForIndex(position)
                vm.sectionId = getItem(position)?.id
                vm.icon = getItem(position)?.icon
                holder.onBind(
                    vm, homeViewModel
                )*/
            }
            is RegularSectionViewHolder -> {
                holder.onBind(mData?.get(position))
                /*val vm = homeViewModel.getCategoriesViewModelForIndex(position)
                vm.sectionId = getItem(position)?.id
                vm.sectionTitle = getItem(position)?.title
                vm.icon = getItem(position)?.icon
                holder.onBind(
                    vm, homeViewModel
                )*/
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return when (mData?.get(position)?.style) {
            FULL_WIDTH_CARD_STYLE -> {
                HOME_SECTION_FULL_WIDTH
            }
            REGULAR_CARD_STYLE -> {
                HOME_SECTION_REGULAR
            }
            else -> {
                -1
            }
        }
    }


    override fun getItemCount(): Int {
        return mData.orEmpty().size
    }

    fun setData(data: MutableList<com.inmobly.common_ui.model.products.HomeSection?>) {
        mData = data
        notifyDataSetChanged()
    }

    fun clear() {
        mData?.clear()
        notifyDataSetChanged()
    }

    interface HomeSectionCallBack {
        fun getSectionProductsCallBack(
            section: com.inmobly.common_ui.model.products.HomeSection?,
            position: Int,
            stateListener: com.inmobly.common_ui.utils.StateListener?
        )

        fun onItemClick(product: com.inmobly.common_ui.model.products.Product?, position: Int, sectionPosition: Int)
        fun onFavButtonClick(
            product: com.inmobly.common_ui.model.products.Product?,
            position: Int,
            sectionPosition: Int,
            regularProductsAdapter: RegularProductsAdapter?,
            fullWidthProductsAdapter: FullWidthProductsAdapter?
        )
        fun onAddToCartClick(product: com.inmobly.common_ui.model.products.Product?, position: Int, sectionPosition: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        this.context = context
    }
}