package com.inmobly.shopping.midnightfashion.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inmobly.shopping.core.domain.util.AppConstants
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.FragmentFavouriteBinding
import com.inmobly.common_ui.model.products.Product
import com.inmobly.shopping.midnightfashion.ui.home.adapter.RegularProductsAdapter
import com.inmobly.common_ui.utils.extensions.gone
import com.inmobly.common_ui.utils.extensions.showToastMessage
import com.inmobly.common_ui.utils.extensions.visible
import com.inmobly.common_ui.utils.helper.ErrorMessageHelper
import com.inmobly.common_ui.utils.helper.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite),
    RegularProductsAdapter.ItemClickListener {

    private val binding by viewBinding(FragmentFavouriteBinding::bind)
    private val favouriteViewModel by viewModels<FavouriteViewModel>()

    private var regularProductsAdapter: RegularProductsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        favouriteViewModel.loadFavoriteProducts()
        observeLiveData()


    }

    private fun setupView() {
        regularProductsAdapter = RegularProductsAdapter(requireActivity(), this)
        binding.productsListRecyclerView.adapter = regularProductsAdapter

        binding.productsListSwipeRefreshLayout.setColorSchemeResources(
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_500,
            R.color.purple_200
        )
        binding.productsListSwipeRefreshLayout.setOnRefreshListener {
            binding.productsListSwipeRefreshLayout.isRefreshing = true
            favouriteViewModel.loadFavoriteProducts()
            binding.productsListSwipeRefreshLayout.isRefreshing = false
        }
    }


    private fun observeLiveData() {
        favouriteViewModel.stateListener.successResponseLiveData.observe(viewLifecycleOwner) { response: Any? ->
            val productsList = response as List<Product?>?
            if (productsList != null && productsList.isNotEmpty()) {
                binding.productsListRecyclerView.visible()
                binding.errorTextView.gone()
                regularProductsAdapter?.setData(productsList.toMutableList())
            } else {
                binding.productsListRecyclerView.gone()
                binding.errorTextView.visible()
            }

        }

        favouriteViewModel.stateListener.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage: Any? ->
            when (errorMessage) {
                is Int -> activity?.showToastMessage(
                    ErrorMessageHelper.showGeneralErrorMessage(
                        errorMessage
                    )
                )
                is String -> activity?.showToastMessage(errorMessage)
            }
            binding.errorTextView.visible()
            binding.productsListRecyclerView.gone()
        }

        favouriteViewModel.stateListener.loadingProgressLiveData.observe(viewLifecycleOwner) { status ->
            if (status == true) {
                binding.productsListRecyclerView.gone()
                binding.errorTextView.gone()
                binding.loadingProgressBar.visible()
            } else {
                binding.loadingProgressBar.gone()
            }

        }


        favouriteViewModel.productIsFavoriteLiveData().observe(viewLifecycleOwner) {

            it?.first?.let { pos ->
                val result = regularProductsAdapter?.getData()
                result?.removeAt(pos)
                regularProductsAdapter?.setDataWithout(result)
                regularProductsAdapter?.notifyItemRemoved(pos)
                if (result?.isEmpty() == true) {
                    binding.productsListRecyclerView.gone()
                    binding.errorTextView.visible()
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        regularProductsAdapter = null
    }


    override fun onItemClick(product: Product?, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.PRODUCT_ITEM_KEY, product)
        findNavController().navigate(R.id.productDetailsFragment, bundle)
    }

    override fun onFavButtonClick(
        product: Product?,
        position: Int,
        regularProductsAdapter: RegularProductsAdapter?,
    ) {

        favouriteViewModel.removeFromFavorite(product, position)

    }

    override fun onAddToCartClick(product: Product?, position: Int) {
        activity?.showToastMessage(com.inmobly.common_ui.R.string.add_to_cart)
    }
}