package com.example.recipefinder.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.recipefinder.ActivityWeb
import com.example.recipefinder.R

import com.example.recipefinder.model.RecipeModel

class RecyclerViewAdapter(private val mContext:Context, private val list:MutableList<RecipeModel>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var apiController:ApiController? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item,p0,false)
        val service = ServiceVolley()
        apiController = ApiController(service)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.ingredients!!.text = list.get(p1).ingredients
        if(!list.get(p1).thumbnail.equals("")){
            apiController!!.getImage(list.get(p1).thumbnail!!){response: Bitmap? ->
                p0.progressBar!!.visibility = View.GONE
                p0.thumbnail!!.setImageBitmap(response)
            }
        }
        else{
            p0.thumbnail!!.setImageResource(R.drawable.ic_no_image)
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var ingredients:TextView? = null
        private var linkButton:Button? = null
        var thumbnail:ImageView? = null
        var progressBar:ProgressBar? = null

        init {
            ingredients = itemView.findViewById(R.id.IngredientsText)
            progressBar = itemView.findViewById(R.id.progressBar)
            linkButton = itemView.findViewById(R.id.LinkButton)
            thumbnail = itemView.findViewById(R.id.Thumbnail)
            linkButton!!.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var intent = Intent(mContext,ActivityWeb::class.java)
            intent.putExtra("url",list.get(adapterPosition).link)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        }
    }
}