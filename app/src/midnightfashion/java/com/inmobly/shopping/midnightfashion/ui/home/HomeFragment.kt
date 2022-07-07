package com.inmobly.shopping.midnightfashion.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inmobly.shopping.core.domain.util.AppConstants
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.FragmentHomeBinding
import com.inmobly.common_ui.model.products.HomeSection
import com.inmobly.common_ui.model.products.Product
import com.inmobly.shopping.midnightfashion.ui.home.adapter.FullWidthProductsAdapter
import com.inmobly.shopping.midnightfashion.ui.home.adapter.HomeSectionsAdapter
import com.inmobly.shopping.midnightfashion.ui.home.adapter.RegularProductsAdapter
import com.inmobly.common_ui.utils.extensions.gone
import com.inmobly.common_ui.utils.extensions.showToastMessage
import com.inmobly.common_ui.utils.extensions.visible
import com.inmobly.common_ui.utils.StateListener
import com.inmobly.common_ui.utils.helper.ErrorMessageHelper
import com.inmobly.common_ui.utils.helper.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), HomeSectionsAdapter.HomeSectionCallBack {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel by viewModels<HomeViewModel>()

    private var homeSectionsAdapter: HomeSectionsAdapter? = null
    private var regularProductsAdapter: RegularProductsAdapter?=null
    private var fullWidthProductsAdapter: FullWidthProductsAdapter?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        homeViewModel.loadHomeSections()
        observeLiveData()

    }

    private fun setupView() {

        binding.homeSwipeRefreshLayout.setColorSchemeResources(
            com.inmobly.common_ui.R.color.black,
            com.inmobly.common_ui.R.color.black,
            com.inmobly.common_ui.R.color.black,
            com.inmobly.common_ui.R.color.black
        )
        binding.homeSwipeRefreshLayout.setOnRefreshListener {
            binding.homeSwipeRefreshLayout.isRefreshing = true
            homeSectionsAdapter?.clear()
            homeSectionsAdapter=null
            homeViewModel.loadHomeSections()
            binding.homeSwipeRefreshLayout.isRefreshing = false
        }
    }


    private fun observeLiveData() {
        homeViewModel.stateListener.successResponseLiveData.observe(viewLifecycleOwner) { response: Any? ->
            homeSectionsAdapter = HomeSectionsAdapter(requireActivity(), this, viewLifecycleOwner)
            binding.homeSectionRecyclerview.adapter = homeSectionsAdapter

            val homeSectionList = response as List<HomeSection?>?
            homeSectionList?.let {
                if(homeSectionList.isNotEmpty()) {
                    binding.homeSectionRecyclerview.visible()
                    binding.errorTextView.gone()
                }else {
                    binding.homeSectionRecyclerview.gone()
                    binding.errorTextView.visible()
                }
                homeSectionsAdapter?.setData(it.toMutableList())
            }
        }

        homeViewModel.stateListener.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage: Any? ->
            when (errorMessage) {
                is Int -> activity?.showToastMessage(
                    ErrorMessageHelper.showGeneralErrorMessage(
                        errorMessage
                    )
                )
                is String -> activity?.showToastMessage(errorMessage)
            }
            binding.errorTextView.visible()
            binding.homeSectionRecyclerview.gone()
        }

        homeViewModel.stateListener.loadingProgressLiveData.observe(viewLifecycleOwner) { status ->
            if (status == true) {
                binding.homeSectionRecyclerview.gone()
                binding.errorTextView.gone()
                binding.loadingProgressBar.visible()
            } else {
                binding.loadingProgressBar.gone()
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeSectionsAdapter = null
    }

    override fun getSectionProductsCallBack(
        section: HomeSection?,
        position: Int,
        stateListener: StateListener?
    ) {
        if(section?.id==-1) {
            homeViewModel.loadRecentlyViewedSection(stateListener)
        }else {
            homeViewModel.loadProductsForSection(stateListener, section?.id)

        }
    }

    override fun onItemClick(product: Product?, position: Int, sectionPosition: Int) {
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.PRODUCT_ITEM_KEY, product)
        findNavController().navigate(R.id.productDetailsFragment, bundle)
    }

    override fun onFavButtonClick(
        product: Product?,
        position: Int,
        sectionPosition: Int,
        regularProductsAdapter: RegularProductsAdapter?,
        fullWidthProductsAdapter: FullWidthProductsAdapter?
    ) {
        this.regularProductsAdapter=regularProductsAdapter
        this.fullWidthProductsAdapter=fullWidthProductsAdapter
        if (product?.isFavorite == false) {
            homeViewModel.addProductToFavorites(product,position)
            product.isFavorite = true
        } else {
            homeViewModel.removeFromFavorite(product,position)
            product?.isFavorite = false
        }
        homeViewModel.productIsFavoriteLiveData().observe(viewLifecycleOwner) {
            it?.first?.let { it1 -> this.regularProductsAdapter?.notifyItemChanged(it1) }
            it?.first?.let { it1 -> this.fullWidthProductsAdapter?.notifyItemChanged(it1) }
        }
    }

    override fun onAddToCartClick(product: Product?, position: Int, sectionPosition: Int) {
        activity?.showToastMessage(com.inmobly.common_ui.R.string.add_to_cart)
    }

    override fun onDestroy() {
        super.onDestroy()
        homeSectionsAdapter=null
    }
}