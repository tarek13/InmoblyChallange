package com.inmobly.shopping.dresscode.ui.home

import androidx.lifecycle.ViewModel
import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.usecase.base.SingleUseCaseCallback
import com.inmobly.shopping.core.domain.usecase.products.*
import com.inmobly.shopping.core.domain.util.AppConstants
import com.inmobly.common_ui.utils.StateListener
import com.inmobly.common_ui.utils.helper.ErrorHandlingUtils
import com.inmobly.common_ui.utils.helper.IProductsFavouriteHelper
import com.inmobly.common_ui.utils.helper.ProductFavouriteHelper
import com.inmobly.common_ui.utils.mapper.mapToViewList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    val stateListener: StateListener,
    private val errorHandlingUtils: ErrorHandlingUtils,
    private val productFavouriteHelper: ProductFavouriteHelper,
    private val getRecentlyViewedProductsUseCase: GetRecentlyViewedProductsUseCase
) : ViewModel(), IProductsFavouriteHelper by productFavouriteHelper {


    fun loadHomeSections() {
        stateListener.setLoadingProgressLiveData(true)
        getHomeSectionsUseCase.execute(
            params = GetHomeSectionsUseCase.Params(AppConstants.APP_NAME_DRESS_CODE),
            singleUseCaseCallback = object : SingleUseCaseCallback<List<HomeSectionEntity?>?> {
                override fun onSuccess(response: List<HomeSectionEntity?>?) {
                    stateListener.setLoadingProgressLiveData(false)
                    stateListener.setSuccessResponse(response.mapToViewList())
                }

                override fun onError(throwable: Throwable) {
                    stateListener.setLoadingProgressLiveData(false)
                    errorHandlingUtils.getErrorMessage(throwable, stateListener)
                }

            }
        )
    }

    fun loadProductsForSection(stateListener: StateListener?, sectionId: Int?) {
        stateListener?.setLoadingProgressLiveData(true)
        getProductsUseCase.execute(
            params = GetProductsUseCase.Params(AppConstants.APP_NAME_DRESS_CODE, sectionId),
            singleUseCaseCallback = object : SingleUseCaseCallback<List<ProductEntity?>?> {
                override fun onSuccess(response: List<ProductEntity?>?) {
                    stateListener?.setLoadingProgressLiveData(false)
                    stateListener?.setSuccessResponse(response.mapToViewList())
                }

                override fun onError(throwable: Throwable) {
                    stateListener?.setLoadingProgressLiveData(false)
                    stateListener?.let { errorHandlingUtils.getErrorMessage(throwable, it) }
                }

            }, disposeLast = false
        )
    }


    override fun onCleared() {
        super.onCleared()
        getHomeSectionsUseCase.dispose()
        getProductsUseCase.dispose()


    }

    fun loadRecentlyViewedSection(stateListener: StateListener?) {
        stateListener?.setLoadingProgressLiveData(true)
        getRecentlyViewedProductsUseCase.execute(
            singleUseCaseCallback = object : SingleUseCaseCallback<List<ProductEntity?>> {
                override fun onSuccess(response: List<ProductEntity?>) {
                    stateListener?.setLoadingProgressLiveData(false)
                    stateListener?.setSuccessResponse(response.mapToViewList())
                }

                override fun onError(throwable: Throwable) {
                    stateListener?.setLoadingProgressLiveData(false)
                    stateListener?.let { errorHandlingUtils.getErrorMessage(throwable, it) }
                }

            }
        )
    }


}