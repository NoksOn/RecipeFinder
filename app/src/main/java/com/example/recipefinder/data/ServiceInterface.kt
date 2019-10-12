package com.example.recipefinder.data

import android.graphics.Bitmap
import org.json.JSONObject

interface ServiceInterface {

    fun getJson(url:String,completionHandler:(response: JSONObject?) -> Unit)

    fun getImage(url:String,completionHandler:(response: Bitmap?) -> Unit)

}