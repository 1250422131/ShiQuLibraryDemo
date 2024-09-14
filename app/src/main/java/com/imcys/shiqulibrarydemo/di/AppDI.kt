package com.imcys.shiqulibrarydemo.di

import com.imcys.shiqulibrarydemo.http.retrofit.api.ShiQuLibraryService
import com.imcys.shiqulibrarydemo.http.retrofit.retrofit
import com.imcys.shiqulibrarydemo.ui.library.LibraryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { retrofit.create(ShiQuLibraryService::class.java) }
    viewModel { LibraryViewModel(get()) }
}