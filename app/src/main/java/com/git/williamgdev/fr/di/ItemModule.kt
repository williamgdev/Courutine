package com.git.williamgdev.fr.di

import com.git.williamgdev.fr.data.remote.ItemRemoteRepository
import com.git.williamgdev.fr.data.remote.ItemRemoteRepositoryImpl
import com.git.williamgdev.fr.data.repository.ItemRepository
import com.git.williamgdev.fr.data.repository.ItemRepositoryImpl
import com.git.williamgdev.fr.usecase.LoadItemsUseCase
import com.git.williamgdev.fr.ui.item.ItemViewModel
import com.git.williamgdev.fr.usecase.LoadItemsUseCaseImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val itemModule = module {

    viewModel { ItemViewModel(get()) }

    // Use Cases
    single<LoadItemsUseCase> { LoadItemsUseCaseImpl(get(), get()) }

    // Repositories
    single<ItemRepository> { ItemRepositoryImpl() }
    single<ItemRemoteRepository> { ItemRemoteRepositoryImpl(get()) }
}