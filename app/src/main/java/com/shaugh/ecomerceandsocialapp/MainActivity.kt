package com.shaugh.ecomerceandsocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.shaugh.ecomerceandsocialapp.adapter.ProductsAdapter
import com.shaugh.ecomerceandsocialapp.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val products = Gson().fromJson(json, Array<Product>::class.java).toList()
        val products =listOf<Product>(
            Product("Animal Jacket", R.drawable.animaljacket,5005.60),
            Product("Hodey", R.drawable.hodey,1000.70),
            Product("Kids Jacket", R.drawable.kidsjacket,4000.00),
            Product("Long Jackets", R.drawable.longjakate,9999.99),
            Product("Kids Complite", R.drawable.kidscomplite,4099.99),
            Product("Kids Sets", R.drawable.kidssets,409.99),
            Product("Kids Long T-shirt", R.drawable.longtshirt,499.99),
            Product("Kids Cort", R.drawable.cort,4009.99)
        )

        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ProductsAdapter(products)
            progressBar.visibility = View.GONE
        }


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

       //supportFragmentManager.beginTransaction().replace(R.id.frameLayout,MainFragment())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.actionHome->{
                    val intent = Intent(this, MainActivity::class.java)
                    this.startActivity(intent)
                }
                R.id.actionSocialGroup->{
                    val intent = Intent(this, SocialActivity::class.java)
                    this.startActivity(intent)
                }
                R.id.actionContactUs->{
                    val intent = Intent(this, ContactUsActivity::class.java)
                    this.startActivity(intent)
                }
                R.id.actionAboutUs->{
                    val intent = Intent(this, AboutUsActivity::class.java)
                    this.startActivity(intent)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
