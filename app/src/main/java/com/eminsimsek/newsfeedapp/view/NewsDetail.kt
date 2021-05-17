package com.eminsimsek.newsfeedapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eminsimsek.newsfeedapp.R
import com.eminsimsek.newsfeedapp.model.ArticlesItem
import com.eminsimsek.newsfeedapp.service.AppDatabase
import com.eminsimsek.newsfeedapp.service.FavoritesDAO
import kotlinx.android.synthetic.main.activity_news_detail.*
import java.util.*

class NewsDetail : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    private var link : String? = null
    private lateinit var db : AppDatabase
    private lateinit var  article : ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
     //   toolbarDetail = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbarDetail);

        article =  intent.extras?.getSerializable("chosenNews") as ArticlesItem
        Glide.with(applicationContext).load(article.urlToImage)
            .into(imageViewNews);
        textViewTitleDetail.text = article.title
        textViewAuthor.text = article.author
        textViewDate.text = article.publishedAt?.subSequence(0, 10)
        textViewNewsContent.text = article.content+"\n\nSource: "+article.source?.name
        link = article.url
        buttonNewsSource.setOnClickListener {
            val intent : Intent = Intent(applicationContext, NewsSource::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("chosenNewsLink", link)

            startActivity(intent)
        }

        imageViewBackDetail.setOnClickListener {
            onBackPressed()
        }



    }
    private fun shareIt(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    private fun addFavorite(){
        db = AppDatabase(applicationContext)
        FavoritesDAO().addArticle(db, this.article)

    }
    private fun searchFavorite(url : String):Boolean{
        db = AppDatabase(applicationContext)
       return FavoritesDAO().searchArticle(db, url)
    }

    private fun removeFavorite(url : String){
        if(searchFavorite(url)){
            db = AppDatabase(applicationContext)
            FavoritesDAO().deleteArticle(db, url)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_addfavorite, menu);
        return true
    }

    @SuppressLint("ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itemShare -> {
                shareIt()
            }
            R.id.itemAddFavorite -> {
                if(link?.let { searchFavorite(it) } == true){
                    removeFavorite(link!!)
                    Toast.makeText(applicationContext,"Removed from favorites",Toast.LENGTH_LONG).show()
                }

                else{
                    addFavorite()
                    Toast.makeText(applicationContext,"Added to favorites",Toast.LENGTH_LONG).show()
                }

            }
        }



        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}