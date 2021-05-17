package com.eminsimsek.newsfeedapp.service

import android.content.ContentValues
import android.database.Cursor
import com.eminsimsek.newsfeedapp.model.ArticlesItem
import com.eminsimsek.newsfeedapp.model.Source


class FavoritesDAO {

    fun getAllFavorites(db: AppDatabase) : ArrayList<ArticlesItem>? {
        var favorites:ArrayList<ArticlesItem> = ArrayList<ArticlesItem>()
        val tempDB = db.readableDatabase
        val cursor: Cursor = tempDB.rawQuery("SELECT * FROM Favorite", null)

        while (cursor.moveToNext()) {
            val w = ArticlesItem(
                cursor.getString(cursor.getColumnIndex("publishedAt")),
                cursor.getString(cursor.getColumnIndex("author")),
                cursor.getString(cursor.getColumnIndex("urlToImage")),
                cursor.getString(cursor.getColumnIndex("description")),
                Source(cursor.getString(cursor.getColumnIndex("source")), null),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("url")),
                cursor.getString(cursor.getColumnIndex("content"))
            )
            (" asıl size "+w.url)
            favorites.add(w)
        }
        cursor.close()

        return favorites
    }
    fun searchArticle(db : AppDatabase,  url : String) : Boolean{
        val tempDB = db.readableDatabase
        val cursor: Cursor = tempDB.rawQuery("SELECT * FROM Favorite WHERE url='$url'", null)
        return cursor.count>0
    }

    fun addArticle(db: AppDatabase, article: ArticlesItem) {
        val sqLiteDatabase = db.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("publishedAt", article.publishedAt)
        contentValues.put("author", article.author)
        contentValues.put("urlToImage",article.urlToImage.toString())
        contentValues.put("description", article.description)
        contentValues.put("source", article.source?.name)
        contentValues.put("title", article.title)
        contentValues.put("url", article.url)
        contentValues.put("content", article.content)
        (sqLiteDatabase.insertOrThrow("Favorite", null, contentValues).toString()+"row")
        sqLiteDatabase.close()
    }

     fun  deleteArticle( db : AppDatabase,  url : String) {
         val sqLiteDatabase = db.writableDatabase
        sqLiteDatabase.delete("Favorite", "url=?",  arrayOf(url));
        sqLiteDatabase.close();

    }

}