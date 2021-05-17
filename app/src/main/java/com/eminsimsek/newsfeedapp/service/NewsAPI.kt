package com.eminsimsek.newsfeedapp.service

import com.eminsimsek.newsfeedapp.model.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
     fun searchForNews(
        @Query("q")
        searchQuery: String?,
        @Query("page")
        pageNumber: Int?,
        @Query("apiKey")
        apiKey: String = "d8ad8bfbde464d54b25c3dd1acd5c2e1"
    ): Single<News>

}