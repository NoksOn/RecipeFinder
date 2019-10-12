package com.example.recipefinder

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.recipefinder.data.ApiController
import com.example.recipefinder.data.RecyclerViewAdapter
import com.example.recipefinder.data.ServiceVolley
import com.example.recipefinder.model.RecipeModel
import kotlinx.android.synthetic.main.activity_list.*
import org.json.JSONException
import org.json.JSONObject

class ActivityList : AppCompatActivity() {

    private var url:String? = null
    private var adapter:RecyclerViewAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterList:MutableList<RecipeModel>? = mutableListOf()
    private var apiController:ApiController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val service = ServiceVolley()
        apiController = ApiController(service)
        layoutManager = LinearLayoutManager(this)
        val ingredients = intent.extras.getString("Ingredients")
        val searchTerm = intent.extras.getString("SearchTerm")

        adapter = RecyclerViewAdapter(applicationContext,adapterList!!)
        activityRecycler.layoutManager = layoutManager
        activityRecycler.adapter = adapter

        var builder = StringBuilder()

        builder.append(LEFT_LINK)
        builder.append(ingredients)
        builder.append(QUERY)
        builder.append(searchTerm)

        url = builder.toString()
        Log.d("url",url)

        setAdapter()
    }

    fun setAdapter(){
        apiController!!.getJson(url!!) { response ->
            val resultArray = response!!.getJSONArray("results")
            for(i in 0 until resultArray.length()){
                val jsonObject = resultArray.getJSONObject(i)
                var recipeModel = RecipeModel()

                recipeModel.title = jsonObject.getString("title")
                recipeModel.link = jsonObject.getString("href")
                recipeModel.thumbnail = jsonObject.getString("thumbnail")
                recipeModel.ingredients = jsonObject.getString("ingredients")

                adapterList!!.add(recipeModel)
                adapter!!.notifyDataSetChanged()
            }
        }

    }

}
