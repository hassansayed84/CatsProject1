package com.example.catsproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import com.android.volley.Response
import org.json.JSONArray


class CatViewModel : ViewModel() {

    // LiveData to hold the cat breeds
    private val _catBreeds = MutableLiveData<List<String>>()
    val catBreeds: LiveData<List<String>> = _catBreeds

    fun fetchCatBreeds(context: Context) {
        val url = "https://api.thecatapi.com/v1/breeds"

        val requestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener<JSONArray> { response ->
                try {
                    val breedsList = ArrayList<String>()
                    for (i in 0 until response.length()) {
                        val breedItem = response.getJSONObject(i)
                        val breedName = breedItem.getString("name")
                        breedsList.add(breedName)
                    }
                    _catBreeds.postValue(breedsList)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["x-api-key"] = "live_Bfrj0oTl9Xwi8VUrZ3Nnh9rvXswGKEqHseezzJtSfjTuPxU65CQrSr0n29rR1FEh" // Replace with your actual API key
                return headers
            }
        }



        requestQueue.add(jsonArrayRequest)
    }
}
