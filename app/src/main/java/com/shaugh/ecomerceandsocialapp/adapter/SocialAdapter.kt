package com.shaugh.ecomerceandsocialapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.shaugh.ecomerceandsocialapp.ProductDetailsActivity
import com.shaugh.ecomerceandsocialapp.R
import com.shaugh.ecomerceandsocialapp.db.LocalDataBase
import com.shaugh.ecomerceandsocialapp.db.PostEntity
import com.shaugh.ecomerceandsocialapp.model.Product
import kotlinx.android.synthetic.main.comments.view.*
import kotlinx.android.synthetic.main.post_row.view.*

class SocialAdapter(private val posts: List<PostEntity>) :
    RecyclerView.Adapter<SocialAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: SocialAdapter.ViewHolder, position: Int) {
        holder.bindView(posts[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from().inflate(R.layout.product_row, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        val holder = ViewHolder(view)
        holder.like.setOnClickListener {
            val postDB: LocalDataBase =
                Room.databaseBuilder(parent.context, LocalDataBase::class.java, "DB")
                    .allowMainThreadQueries().build()
            var postLike = postDB.dbDao().getAllLikesData(holder.id)
            postDB.dbDao().updatePostLike(holder.id, postLike + 1)
            Toast.makeText(parent.context, "Liked", Toast.LENGTH_SHORT).show()
            if (postLike > 0) {
                holder.like.setImageResource(R.drawable.liked)
            }
        }
        holder.disLike.setOnClickListener {
            val postDB: LocalDataBase =
                Room.databaseBuilder(parent.context, LocalDataBase::class.java, "DB")
                    .allowMainThreadQueries().build()
            val postDisLike = postDB.dbDao().getAllDisLikesData(holder.id)
            postDB.dbDao().updatePostDisLike(holder.id, postDisLike + 1)
            Toast.makeText(parent.context, "DisLiked", Toast.LENGTH_SHORT).show()
            if (postDisLike > 0) {
                holder.disLike.setImageResource(R.drawable.disliked)
            }
        }

        holder.comment.setOnClickListener {
            val postDB: LocalDataBase =
                Room.databaseBuilder(parent.context, LocalDataBase::class.java, "DB")
                    .allowMainThreadQueries().build()
            //Toast.makeText(parent.context, "Comment ${holder.id}", Toast.LENGTH_SHORT).show()
            val layout: View =
                LayoutInflater.from(parent.context).inflate(R.layout.comments, parent, false)
            val comment = postDB.dbDao().getCommntData(holder.id)
            Toast.makeText(parent.context, "Commented $comment", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(parent.context)
            builder.setView(layout)
            builder.show()
            val btnPostComment = layout.findViewById(R.id.btnPostComment) as Button
            val etComment = layout.findViewById(R.id.etComment) as EditText
                holder.tvComment.text = comment


            btnPostComment.setOnClickListener {
                postDB.dbDao().updatePostComment(holder.id, etComment.text.toString())
                Toast.makeText(parent.context, "Commented ${etComment.text}", Toast.LENGTH_SHORT).show()
            }
        }

        return holder
    }

    override fun getItemCount() = posts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.IVpostImage)
        val like: ImageView = itemView.findViewById(R.id.IVlike)
        val disLike: ImageView = itemView.findViewById(R.id.IVdisLike)
        val comment: ImageView = itemView.findViewById(R.id.IVcomment)
        val title: TextView = itemView.findViewById(R.id.tvPostText)
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
        var id = 0
        fun bindView(post: PostEntity) {
            title.text = " ${post.postText}"
            id = post.postId

        }
    }
}