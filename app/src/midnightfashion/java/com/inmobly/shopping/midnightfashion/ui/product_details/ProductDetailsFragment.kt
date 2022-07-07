package com.inmobly.shopping.midnightfashion.ui.product_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.FragmentProductDetailsBinding
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.extensions.loadUrl
import com.inmobly.common_ui.utils.helper.viewBinding
import com.inmobly.shopping.core.domain.util.AppConstants

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)

    private val productDetailsViewModel by viewModels<ProductDetailsViewModel>()

    private var product: Product? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsData()
        setupView()
        productDetailsViewModel.addProductToFavorites(product)
    }

    private fun getArgumentsData() {
        product = requireArguments().getParcelable(AppConstants.PRODUCT_ITEM_KEY)
    }

    private fun setupView() {
        binding.productTitleTextView.text = product?.name
        binding.productNameTextView.text = product?.name
        binding.productDescriptionTextView.text = product?.description
        binding.productPriceTextView.text = "${product?.price}$"
        binding.productPhotoImageView.loadUrl(product?.img)
        binding.productDetailsBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
        updateFavIcon()
        binding.productFavoriteFab.setOnClickListener {
            if (product?.isFavorite == false) {
                productDetailsViewModel.addProductToFavorites(product)
                product?.isFavorite = true
            } else {
                productDetailsViewModel.removeFromFavorite(product)
                product?.isFavorite = false
            }
            productDetailsViewModel.productIsFavoriteLiveData().observe(viewLifecycleOwner) {
                updateFavIcon()
            }
        }
    }

    private fun updateFavIcon() {
        binding.productFavoriteFab.setImageResource(if (product?.isFavorite == true) R.drawable.ic_fav_selected_small else R.drawable.ic_fav_not_selected_small)

    }


}