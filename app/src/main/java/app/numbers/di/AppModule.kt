package app.numbers.di

import app.numbers.data.remote.NumbersApi
import app.numbers.data.repository.NumbersRepoImpl
import app.numbers.domain.repository.NumbersRepository
import app.numbers.domain.usecase.NumberFactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://numbersapi.com/"


    @Provides
    @Singleton
    fun provideNumbersApi(): NumbersApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: NumbersApi): NumbersRepository {
        return NumbersRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideNumberFactUseCase(repository: NumbersRepository): NumberFactUseCase {
        return NumberFactUseCase(repository)
    }


}