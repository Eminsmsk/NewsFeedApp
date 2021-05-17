package com.eminsimsek.newsfeedapp.view

import android.content.Context
import android.content.Intent


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminsimsek.newsfeedapp.R
import com.eminsimsek.newsfeedapp.model.ArticlesItem


class RecyclerAdapter(val myContext: Context?, val newsList: ArrayList<ArticlesItem>) : RecyclerView.Adapter<RecyclerAdapter.CardViewHolder>() {

    class CardViewHolder : RecyclerView.ViewHolder {
         var cardViewNews: CardView? = null
         var textViewTitleCard: TextView? = null
         var textViewDescription: TextView? = null
         var imageViewNewsCard: ImageView? = null

        constructor(itemView: View) : super(itemView) {
            cardViewNews = itemView.findViewById(R.id.cardViewNews)
            textViewTitleCard = itemView.findViewById(R.id.textViewTitleCard)
            textViewDescription = itemView.findViewById(R.id.textViewDescription)
            imageViewNewsCard = itemView.findViewById(R.id.imageViewNewsCard)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(myContext).inflate(R.layout.news_card_design, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.textViewTitleCard?.text = newsList.get(position).title
        holder.textViewDescription?.text = newsList.get(position).description
       Glide.with(myContext!!).load(newsList.get(position).urlToImage)
            .into(holder.imageViewNewsCard!!);
        holder.cardViewNews!!.setOnClickListener {
            val intent : Intent = Intent(myContext, NewsDetail::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           intent.putExtra("chosenNews", newsList.get(position))

            myContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return newsList.size
    }

 /*  fun updateNewsList(list: List<ArticlesItem>){
       for (i in list){
            (i.url)
        }

            newsList.clear()

            newsList.addAll(list)

       notifyDataSetChanged()
    }
*/

}