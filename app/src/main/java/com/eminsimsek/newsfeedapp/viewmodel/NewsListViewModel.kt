package com.eminsimsek.newsfeedapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eminsimsek.newsfeedapp.model.News
import com.eminsimsek.newsfeedapp.service.NewsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class NewsListViewModel : ViewModel() {
    val news = MutableLiveData<News>()

    private val newsAPIService = NewsAPIService()
    private val disposable = CompositeDisposable()

    

    fun getData(searchItem : String?){


        disposable.add(
            newsAPIService.getData(searchItem,1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>(){
                    override fun onSuccess(t: News) {
                        news.value = t

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        ("hata geldi")
                    }
                })


        )

    }

    fun searchNextPage(){
        disposable.add(
            newsAPIService.getNextData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>(){
                    override fun onSuccess(t: News) {
                        news.value = t

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        ("unable to fetch data")
                    }
                })


        )
    }

}