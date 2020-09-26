package com.shaugh.ecomerceandsocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cart_details.*

class CartDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_details)

        val title = intent.getStringExtra("cTitle")
        val photoUrl = intent.getIntExtra("cPhoto_url", 0)
        val productQuantity = intent.getStringExtra("cproductQuantity")
        val price = intent.getDoubleExtra("cPrice", 0.0)
        val totalPrice = (productQuantity.toInt()) * price

        IVproductPhoto.setImageResource(photoUrl)
        TVproductName.text = "Product name: $title"
        TVproductPrice.text = "Product price: $price"
        TVproductTotalPrice.text = "Total price: $totalPrice"

        BtnBuyNow.setOnClickListener {
            val intent = Intent(this, DeliveryAddressActivity::class.java)
/*            intent.putExtra("cTitle",title)
            intent.putExtra("cPhoto_url", photoUrl)
            intent.putExtra("cPrice", price)*/
            this.startActivity(intent)
        }
    }
}
