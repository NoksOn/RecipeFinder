package com.example.recipefinder.data

import android.graphics.Bitmap
import org.json.JSONObject

class ApiController(serviceInjection:ServiceInterface): ServiceInterface {

    private var serviceInjection:ServiceInterface? = null

    init { this.serviceInjection = serviceInjection}

    override fun getJson(url: String, completionHandler: (response: JSONObject?) -> Unit) {
        serviceInjection!!.getJson(url,completionHandler)
    }

    override fun getImage(url: String, completionHandler: (response: Bitmap?) -> Unit) {
        serviceInjection!!.getImage(url,completionHandler)
    }
}