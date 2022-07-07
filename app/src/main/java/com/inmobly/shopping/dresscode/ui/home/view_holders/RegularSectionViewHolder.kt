package com.inmobly.shopping.dresscode.ui.home.view_holders

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.inmobly.common_ui.databinding.ViewHolderSectionRegularBinding
import com.inmobly.common_ui.model.products.HomeSection
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.StateListener
import com.inmobly.shopping.dresscode.ui.home.adapter.HomeSectionsAdapter
import com.inmobly.shopping.dresscode.ui.home.adapter.RegularProductsAdapter
import com.inmobly.common_ui.utils.extensions.gone
import com.inmobly.common_ui.utils.extensions.showToastMessage
import com.inmobly.common_ui.utils.extensions.visible

class RegularSectionViewHolder internal constructor(
    private val context: Context,
    private val binding: ViewHolderSectionRegularBinding,
    private val homeSectionCallBack: HomeSectionsAdapter.HomeSectionCallBack,
    private val lifecycleOwner: LifecycleOwner,
    private val stateListener: StateListener
) : RecyclerView.ViewHolder(binding.root), RegularProductsAdapter.ItemClickListener {
    private var regularProductsAdapter: RegularProductsAdapter? = null
    private var homeSection: HomeSection? = null
    private var isLoaded = false
    fun onBind(homeSection: HomeSection?) {
        this.homeSection = homeSection
        binding.sectionTitle.text = homeSection?.name
        binding.sectionTitle.setTextColor(Color.BLACK)
        initProductsRecyclerView()
     //   if (!isLoaded) {
            homeSectionCallBack.getSectionProductsCallBack(
                homeSection,
                adapterPosition,
                stateListener
            )
       // }
        observeLiveData()
    }

    private fun initProductsRecyclerView() {
        binding.sectionRegularRecyclerView.setHasFixedSize(true)
        binding.sectionRegularRecyclerView.isNestedScrollingEnabled = false
        regularProductsAdapter = RegularProductsAdapter(context, this)
        binding.sectionRegularRecyclerView.adapter = regularProductsAdapter

    }

    private fun observeLiveData() {
        stateListener.successResponseLiveData.observe(lifecycleOwner) { response: Any? ->
            val productsList = response as List<Product?>?
            productsList?.let {
               if(productsList.isNotEmpty()) binding.productContainer.visible() else binding.productContainer.gone()
                regularProductsAdapter?.setData(it.toMutableList())
            }
            isLoaded = true
        }

        stateListener.errorMessageLiveData.observe(lifecycleOwner) { errorMessage: Any? ->
            when (errorMessage) {
                is Int -> context.showToastMessage(
                    com.inmobly.common_ui.utils.helper.ErrorMessageHelper.showGeneralErrorMessage(
                        errorMessage
                    )
                )
                is String -> context.showToastMessage(errorMessage)
            }
            binding.productContainer.gone()
            isLoaded = true
        }

        stateListener.loadingProgressLiveData.observe(lifecycleOwner) { status ->
            if (status == true) {
                binding.shimmerViewContainer.showShimmer(true)
                binding.shimmerViewContainer.visible()
                binding.productContainer.gone()
            } else {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.gone()
            }

        }
    }

    override fun onItemClick(product: Product?, position: Int) {
        homeSectionCallBack.onItemClick(product,position,adapterPosition)
    }

    override fun onFavButtonClick(product: Product?, position: Int, regularProductsAdapter: RegularProductsAdapter?) {
        homeSectionCallBack.onFavButtonClick(
            product,
            position,
            adapterPosition,
            regularProductsAdapter,
            null
        )
    }

    override fun onAddToCartClick(product: Product?, position: Int) {
        homeSectionCallBack.onAddToCartClick(product,position,adapterPosition)
    }




}