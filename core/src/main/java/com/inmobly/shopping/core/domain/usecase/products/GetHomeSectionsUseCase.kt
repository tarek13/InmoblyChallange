package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.domain.repository.IProductsRepository
import com.inmobly.shopping.core.domain.usecase.base.SingleUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val productsRepository: IProductsRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCaseWithParams<List<HomeSectionEntity?>?, GetHomeSectionsUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params?): Single<List<HomeSectionEntity?>?> {
        return productsRepository.getHomeSections(params?.appName)
    }
    data class Params(var appName: String?)
}