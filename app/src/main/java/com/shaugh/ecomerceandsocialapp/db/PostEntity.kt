package com.shaugh.ecomerceandsocialapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postsData")
class PostEntity {
    @PrimaryKey(autoGenerate = true)
    var postId:Int = 0

    @ColumnInfo(name = "postText")
    var postText : String = ""

    @ColumnInfo(name = "postComment")
    var postComment:String = ""

    @ColumnInfo(name = "postLike")
    var postLike:Int = 0

    @ColumnInfo(name = "postDislike")
    var postDislike:Int = 0

    @ColumnInfo(name = "postTime")
    var postTime : Long  = 0
}