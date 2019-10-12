package com.example.recipefinder

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SearchButton.setOnClickListener {
            var intent = Intent(this, ActivityList::class.java)

            if (verifyAvailableNetwork(this)) {

                if (TextUtils.isEmpty(IngredientsField.text.toString()) && TextUtils.isEmpty(SearchTermField.text.toString())) {
                    Toast.makeText(this, "Enter something", Toast.LENGTH_LONG).show()
                } else {
                    var ingredients: String = IngredientsField.text.toString()
                    var searchTerm: String = SearchTermField.text.toString()
                    ingredients.trim()
                    searchTerm.trim()
                    intent.putExtra("Ingredients", ingredients)
                    intent.putExtra("SearchTerm", searchTerm)
                    startActivity(intent)
                }
            }
            else{
                Toast.makeText(this,"Network connection error",Toast.LENGTH_LONG).show()
            }
        }

    }

    fun verifyAvailableNetwork(activity:AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}
