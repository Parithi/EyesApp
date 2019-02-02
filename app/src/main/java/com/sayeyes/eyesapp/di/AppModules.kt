package com.parithi.paintora.di

import com.sayeyes.eyesapp.HomeContract
import com.sayeyes.eyesapp.data.UserDataProvider
import com.sayeyes.eyesapp.data.UserDataProviderImpl
import com.sayeyes.eyesapp.model.Repository
import com.sayeyes.eyesapp.presenters.HomePresenter
import org.koin.dsl.module

val appModule = module {

    single<UserDataProvider> { UserDataProviderImpl() }
    single { Repository(get()) }

    factory { (view: HomeContract.View) -> HomePresenter(view,get()) }

}