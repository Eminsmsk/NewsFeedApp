package com.eminsimsek.newsfeedapp.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminsimsek.newsfeedapp.R
import com.eminsimsek.newsfeedapp.model.ArticlesItem

import com.eminsimsek.newsfeedapp.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var viewModel : NewsListViewModel
    private var newsList : ArrayList<ArticlesItem> = ArrayList<ArticlesItem>()
    private var recyclerAdapter = RecyclerAdapter(
        activity?.applicationContext,
        newsList
    )
    private var flag : Boolean = true



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_news, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView: SearchView = view.findViewById(R.id.searchNews)
        viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)

        val rvNews: RecyclerView = view.findViewById(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(context)
        rvNews.adapter = recyclerAdapter


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                newsList.clear()
                viewModel.getData(query)
                recyclerAdapter.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsList.clear()
                viewModel.getData(newText)
                recyclerAdapter.notifyDataSetChanged()
                return false
            }
        })

        rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged( recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) ) {
                    viewModel.searchNextPage()

                }
            }
        })

        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {

                news.articles?.let {articleList ->
                  if(flag) {
                      newsList.addAll(articleList)
                      recyclerAdapter = RecyclerAdapter(context, newsList)
                      rvNews.adapter = recyclerAdapter
                      flag=false
                  }
                    else{

                      newsList.addAll(articleList)
                      recyclerAdapter.notifyDataSetChanged()
                  }

                }

            }
        })
    }




}