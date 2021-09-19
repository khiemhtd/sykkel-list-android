package com.khiem.oslosykkel

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.util.concurrent.TimeUnit

private const val API_ID = "placeholder-id"
private const val FIELD_HEADER_CLIENT_IDENTIFIER = "Client-Identifier: $API_ID"
private const val URL_STATION_INFORMATION = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json"
private const val URL_STATION_STATUS = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json"

data class StationInfo( val station_id: String,
                        val name: String,
                        val address: String,
                        val lat: Double,
                        val lon: Double,
                        val capacity: Int )

class ApiManagerOsloSykkel() {
    fun getStations(context: Context): JSONObject? {
        val queue = Volley.newRequestQueue(context)
        var result: JSONObject? = null

        val jsonObjectRequest : JsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            URL_STATION_INFORMATION,
            null,
            Response.Listener { response ->
                Log.i(this::class.simpleName,"Retrieved: $response")
                result = response

                val stations: JSONArray = result?.optJSONObject("data")?.getJSONArray("stations")?

            },
            Response.ErrorListener { error ->
                Log.e(this::class.simpleName, "Error: $error")
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers[FIELD_HEADER_CLIENT_IDENTIFIER] = API_ID
                return headers
            }
        }

        Log.i(this::class.simpleName, "Requesting Oslo Sykkel station information")
        queue.add(jsonObjectRequest)
        return result
    }
}