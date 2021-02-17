package com.kylix.core.di

import androidx.room.Room
import com.kylix.core.BuildConfig
import com.kylix.core.data.UserRepository
import com.kylix.core.data.source.local.LocalDataSource
import com.kylix.core.data.source.local.room.UserDatabase
import com.kylix.core.data.source.remote.RemoteDataSource
import com.kylix.core.data.source.remote.network.ApiService
import com.kylix.core.domain.repository.IUserRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_KEY = BuildConfig.API_KEY
private const val BASE_URL = BuildConfig.BASE_URL

val databaseModule = module {
    factory {
        get<UserDatabase>().userDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java, "User.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module { 
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IUserRepository> {
        UserRepository(
            get(),
            get()
        )
    }
}