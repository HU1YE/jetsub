package com.example.jetsub

import android.app.Application
import androidx.room.Room
import com.example.jetsub.db.Driverdb
import com.example.jetsub.db.MenuDao
import com.example.jetsub.repository.MenuRepository
import com.example.jetsub.viewmodel.CartViewModel
import com.example.jetsub.viewmodel.DetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(AndroidLogger())
            androidContext(this@App)
            modules(modules)
        }
    }
}

val modules = module {
    single<Driverdb> {
        Room.databaseBuilder(androidContext(), Driverdb::class.java, "db")
            .build()
    }

    single<MenuDao> {
        get<Driverdb>().dao()
    }

    single<MenuRepository> {
        MenuRepository(get())
    }

    viewModel {
        DetailViewModel(get())
    }

    viewModel {
        CartViewModel(get())
    }
}