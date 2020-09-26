package com.shaugh.ecomerceandsocialapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getIntExtra("photo_url",0)
        val price = intent.getDoubleExtra("price",0.0)







        product_name.text = "Product Name: $title"
        product_price.text = "Price $price TK"
        //Picasso.get().load(photoUrl).into(photo)
        //photo.setImageResource(photoUrl.toInt())
        photo.setImageResource(photoUrl)

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("$title has been added to your cart!")
                .setPositiveButton("OK") {
                        p0, p1 ->
                    var productQuantity = etAmount.text.toString()
                    val intent = Intent(this, CartDetailsActivity::class.java)
                    intent.putExtra("cTitle",title)
                    intent.putExtra("cPhoto_url", photoUrl)
                    intent.putExtra("cPrice", price)
                    intent.putExtra("cproductQuantity", productQuantity)
                    this.startActivity(intent)
                }
                .setNegativeButton("Cancel"){
                        p0, p1 ->
                    p0.dismiss()
                }
                .create()
                .show()
        }
    }
}
