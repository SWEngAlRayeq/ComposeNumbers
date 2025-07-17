package app.numbers.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: String): String

}