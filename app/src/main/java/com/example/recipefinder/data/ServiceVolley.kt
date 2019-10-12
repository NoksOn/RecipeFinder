package com.example.recipefinder.data

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject

class ServiceVolley : ServiceInterface {

    override fun getJson(url: String, completionHandler: (response: JSONObject?) -> Unit) {
        val recipeRequest = JsonObjectRequest(
            Request.Method.GET,url,
            Response.Listener {
                    response: JSONObject ->
                try {
                    completionHandler(response)

                } catch (ex: JSONException){
                    ex.printStackTrace()
                }
            },
            Response.ErrorListener {
                    error: VolleyError? ->
                try{
                    completionHandler(null)
                    Log.d("Volley","Error massage")

                }catch (ex: JSONException){
                    ex.printStackTrace()
                }
            })
        NetworkService.instance?.addToRequestQueue(recipeRequest)
    }

    override fun getImage(url: String, completionHandler: (response: Bitmap?) -> Unit) {
        val imageRequest = ImageRequest(url,
            Response.Listener<Bitmap> {
            response:Bitmap ->
                try{
                    completionHandler(response)
                }catch (ex:JSONException){
                    ex.printStackTrace()
                }
        },0,0, ImageView.ScaleType.CENTER,Bitmap.Config.RGB_565,
            Response.ErrorListener {
                try{
                    completionHandler(null)
                    Log.d("Volley","Error image")
                }catch (ex:JSONException){
                    ex.printStackTrace()
                }
            })
        NetworkService.instance?.addToRequestQueue(imageRequest)
    }
}