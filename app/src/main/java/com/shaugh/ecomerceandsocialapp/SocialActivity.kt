package com.shaugh.ecomerceandsocialapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.shaugh.ecomerceandsocialapp.adapter.SocialAdapter
import com.shaugh.ecomerceandsocialapp.db.LocalDataBase
import com.shaugh.ecomerceandsocialapp.db.PostEntity
import kotlinx.android.synthetic.main.activity_social.*
import kotlinx.android.synthetic.main.post_row.view.*
import java.security.AccessController.getContext

class SocialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.post_row, null)
        val postDB: LocalDataBase =
            Room.databaseBuilder(this, LocalDataBase::class.java, "DB").allowMainThreadQueries().build()

        val postedData = postDB.dbDao().getAllPostData()
        postedData.forEach {
            Log.e("PostedData", "YourId  ${it.postId} your Like ${it.postLike} your dislike ${it.postDislike}")

            var postLike = postDB.dbDao().getAllLikesData(it.postId)
            if (postLike > 0) {
                layout.IVlike.setImageResource(R.drawable.liked)
            }

            val postDisLike = postDB.dbDao().getAllDisLikesData(it.postId)
            if (postDisLike > 0) {
                layout.IVdisLike.setImageResource(R.drawable.disliked)
            }
        }
        rvSocial.apply {
            layoutManager = LinearLayoutManager(this@SocialActivity)
            adapter = SocialAdapter(postedData)
        }

        val postText = TVpost.text.toString()

            Btnpost.setOnClickListener {
                val postData = PostEntity()
                postData.postText = TVpost.text.toString()
                postData.postLike = 0
                postData.postDislike = 0
                postData.postComment = ""
                postData.postTime = System.currentTimeMillis()
                postDB.dbDao().insertPostData(postData)
                val intent = Intent(this, SocialActivity::class.java)
                this.startActivity(intent)
               /* Log.e("PostedData", "YourData"+postedData.last().postText)
                Toast.makeText(this,"YourData"+postedData.last().postId,Toast.LENGTH_SHORT).show()*/

        }
    }
}
