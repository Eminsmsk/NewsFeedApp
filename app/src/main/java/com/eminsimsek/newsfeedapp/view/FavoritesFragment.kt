package com.eminsimsek.newsfeedapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminsimsek.newsfeedapp.R
import com.eminsimsek.newsfeedapp.model.ArticlesItem
import com.eminsimsek.newsfeedapp.service.AppDatabase
import com.eminsimsek.newsfeedapp.service.FavoritesDAO
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment() {

    private var favoritesList: ArrayList<ArticlesItem> = ArrayList<ArticlesItem>()
    private var recyclerAdapter = RecyclerAdapter(
        activity?.applicationContext,
        favoritesList
    )
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val rvFavorites: RecyclerView = view.findViewById(R.id.rvFavorites)
        rvFavorites.layoutManager = LinearLayoutManager(context)
        //rvFavorites.adapter = recyclerAdapter

        loadArticles()







        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadArticles() {
        db = AppDatabase(activity?.applicationContext)
        FavoritesDAO().getAllFavorites(db)?.let { favoritesList.addAll(it) }

        recyclerAdapter = RecyclerAdapter(activity?.applicationContext, favoritesList)
        rvFavorites.adapter = recyclerAdapter

    }


}