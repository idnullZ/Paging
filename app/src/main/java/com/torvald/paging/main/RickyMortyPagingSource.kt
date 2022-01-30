package com.torvald.paging.main

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.torvald.paging.model.RickMorty

class RickyMortyPagingSource(
    private val apiService: Api
) : PagingSource<Int, RickMorty>() {

    override fun getRefreshKey(state: PagingState<Int, RickMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickMorty> {

        return try {
            val currentPage = params.key ?: 1//текущая страница
            val response = apiService.getAll(currentPage)//дергаем запрос и передаем страницу
            val responseData = mutableListOf<RickMorty>()// общие данные
            val data = response.body()?.results ?: emptyList()// партия данных
            responseData.addAll(data)// добовляем партию данных в общий лист
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}