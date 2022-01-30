package com.torvald.paging.main

import com.torvald.paging.model.ResponseApi
import com.torvald.paging.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(Constants.END_POINT)
    suspend fun getAll(
        @Query("page") page: Int
    ):Response<ResponseApi>

}