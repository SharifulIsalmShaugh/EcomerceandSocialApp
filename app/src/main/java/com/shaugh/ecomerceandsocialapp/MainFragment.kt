package com.shaugh.ecomerceandsocialapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        Toast.makeText(context,"MainFragment",Toast.LENGTH_SHORT).show()
 /*       uiThread {
            //val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            val products =listOf<Product>(
                Product("Images 1", R.drawable.img1,"10"),
                Product("Images 2", R.drawable.img2,"20"),
                Product("Images 3", R.drawable.img3,"30"),
                Product("Images 4", R.drawable.img4,"40"),
                Product("Images 5", R.drawable.img5,"50")
            )

            root.recycler_view.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = ProductsAdapter(products)
                root.progressBar.visibility = View.GONE
            }
        }*/


        return root
    }


}
