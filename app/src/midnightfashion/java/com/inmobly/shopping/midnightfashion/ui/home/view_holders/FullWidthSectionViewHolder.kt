package com.inmobly.shopping.midnightfashion.ui.home.view_holders

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.inmobly.common_ui.databinding.ViewHolderSectionFullWidthBinding
import com.inmobly.common_ui.model.products.HomeSection
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.extensions.gone
import com.inmobly.common_ui.utils.extensions.showToastMessage
import com.inmobly.common_ui.utils.extensions.visible
import com.inmobly.common_ui.utils.helper.ErrorMessageHelper
import com.inmobly.shopping.midnightfashion.ui.home.adapter.FullWidthProductsAdapter
import com.inmobly.shopping.midnightfashion.ui.home.adapter.HomeSectionsAdapter

class FullWidthSectionViewHolder internal constructor(
    private val context: Context,
    private val binding: ViewHolderSectionFullWidthBinding,
    private val homeSectionCallBack: HomeSectionsAdapter.HomeSectionCallBack,
    private val lifecycleOwner: LifecycleOwner,
    private val stateListener: com.inmobly.common_ui.utils.StateListener
) : RecyclerView.ViewHolder(binding.root), FullWidthProductsAdapter.ItemClickListener {
    private var regularProductsAdapter: FullWidthProductsAdapter? = null
    private var homeSection: HomeSection? = null
    fun onBind(homeSection: HomeSection?) {
        this.homeSection = homeSection
        initProductsRecyclerView()
            homeSectionCallBack.getSectionProductsCallBack(
                homeSection,
                adapterPosition,
                stateListener
            )
        observeLiveData()
    }

    private fun initProductsRecyclerView() {
        binding.sectionFullRecyclerView.setHasFixedSize(true)
        binding.sectionFullRecyclerView.isNestedScrollingEnabled = false
        regularProductsAdapter = FullWidthProductsAdapter(context, this)
        binding.sectionFullRecyclerView.adapter = regularProductsAdapter

    }

    private fun observeLiveData() {
        stateListener.successResponseLiveData.observe(lifecycleOwner) { response: Any? ->
            val productsList = response as List<Product?>?
            productsList?.let {
                binding.sectionFullRecyclerView.visible()
                regularProductsAdapter?.setData(it)
            }
        }

        stateListener.errorMessageLiveData.observe(lifecycleOwner) { errorMessage: Any? ->
            when (errorMessage) {
                is Int -> context.showToastMessage(
                    ErrorMessageHelper.showGeneralErrorMessage(
                        errorMessage
                    )
                )
                is String -> context.showToastMessage(errorMessage)
            }
            binding.sectionFullRecyclerView.gone()
        }

        stateListener.loadingProgressLiveData.observe(lifecycleOwner) { status ->
            if (status == true) {
                binding.shimmerViewContainer.showShimmer(true)
                binding.shimmerViewContainer.visible()
                binding.sectionFullRecyclerView.gone()
            } else {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.gone()
            }

        }
    }

    override fun onItemClick(product: Product?, position: Int) {
        homeSectionCallBack.onItemClick(product,position,adapterPosition)
    }

    override fun onFavButtonClick(
        product: Product?,
        position: Int,
        fullWidthProductsAdapter: FullWidthProductsAdapter
    ) {
        homeSectionCallBack.onFavButtonClick(
            product,
            position,
            adapterPosition,
            null,
            fullWidthProductsAdapter
        )
    }

    override fun onAddToCartClick(product: Product?, position: Int) {
        homeSectionCallBack.onAddToCartClick(product,position,adapterPosition)
    }

}