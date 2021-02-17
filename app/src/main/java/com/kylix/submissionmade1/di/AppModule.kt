package com.kylix.submissionmade1.di

import com.kylix.core.domain.usecase.UserInteractor
import com.kylix.core.domain.usecase.UserUseCase
import com.kylix.submissionmade1.detail.DetailViewModel
import com.kylix.submissionmade1.follow.FollowViewModel
import com.kylix.submissionmade1.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FollowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}