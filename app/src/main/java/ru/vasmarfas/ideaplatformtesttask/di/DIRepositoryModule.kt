package ru.vasmarfas.ideaplatformtesttask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vasmarfas.ideaplatformtesttask.data.MainRepository
import ru.vasmarfas.ideaplatformtesttask.data.MainRepositoryInterface

@Module
@InstallIn(ViewModelComponent::class)
abstract class DIRepositoryModule {
    @Binds
    abstract fun injectInterface(mainInterface: MainRepository): MainRepositoryInterface
}