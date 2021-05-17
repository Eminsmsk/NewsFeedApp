package com.eminsimsek.newsfeedapp.service

import com.eminsimsek.newsfeedapp.model.News
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {
private var mQuery:String? = null
    private var mPageNumber:Int? = null


private val baseURL = "https://newsapi.org/v2/"
    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getData(searchItem:String?, pageNumber:Int?) : Single<News>{
        mQuery = searchItem
        mPageNumber = pageNumber
        return api.searchForNews(searchItem,pageNumber)
    }
    fun getNextData() : Single<News> {
       return  getData(mQuery, mPageNumber?.plus(1))
    }


}