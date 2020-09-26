package com.shaugh.ecomerceandsocialapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPostData(posts: PostEntity)

    @Query("SELECT * FROM postsData ORDER BY postId DESC")
    fun getAllPostData(): List<PostEntity>

    @Query("SELECT postLike FROM postsData WHERE postId = :postId ")
    fun getAllLikesData(postId: Int): Int

    @Query("SELECT postDislike FROM postsData WHERE postId = :postId ")
    fun getAllDisLikesData(postId: Int): Int

    @Query("UPDATE postsData SET postLike = :liked WHERE postId =:postId")
    fun updatePostLike(postId: Int, liked: Int)

    @Query("UPDATE postsData SET postDislike = :disLiked WHERE postId =:postId")
    fun updatePostDisLike(postId: Int, disLiked: Int)

    @Query("SELECT postComment FROM postsData WHERE postId = :postId ")
    fun getCommntData(postId: Int): String

    @Query("UPDATE postsData SET postComment = :comment WHERE postId =:postId")
    fun updatePostComment(postId: Int, comment: String)
}