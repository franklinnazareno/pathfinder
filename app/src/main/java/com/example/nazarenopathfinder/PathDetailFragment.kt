package com.example.nazarenopathfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nazarenopathfinder.databinding.FragmentPathDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PathDetailFragment : Fragment() {

    private lateinit var binding: FragmentPathDetailBinding

    private var sourceLongitude: Double? = null
    private var sourceLatitude: Double? = null

    private var destinationLongitude: Double? = null
    private var destinationLatitude: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPathDetailBinding.inflate(inflater, container, false)

        val source = arguments?.getString("source")
        val destination = arguments?.getString("destination")


        var BaseUrl = "https://api.mapbox.com/"
        var access_token = resources.getString(R.string.mapbox_access_token)

        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(GeocodingService::class.java)
        val callSource = service.getGeocodingResponse(source!!, access_token)
        callSource.enqueue(object: Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                if (response.code() == 200) {
                    val geoCodingResponse = response.body()!!

                    sourceLongitude = geoCodingResponse.features?.get(0)?.center?.get(0)
                    sourceLatitude = geoCodingResponse.features?.get(0)?.center?.get(1)

                    val callDestination = service.getGeocodingResponse(destination!!, access_token)
                    callDestination.enqueue(object: Callback<GeocodingResponse> {
                        override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                            if (response.code() == 200) {
                                val geoCodingResponse = response.body()!!

                                destinationLongitude = geoCodingResponse.features?.get(0)?.center?.get(0)
                                destinationLatitude = geoCodingResponse.features?.get(0)?.center?.get(1)


                                if (sourceLongitude == null || sourceLatitude == null || destinationLongitude == null || destinationLatitude == null) {
                                    val toast = Toast.makeText(requireActivity(),"Error occurred. Cannot retrieve path from source to destination", Toast.LENGTH_SHORT)
                                    toast.show()
                                } else {
                                    val url = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + destinationLatitude + "," + destinationLongitude + "&dirflg=w"
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(url)
                                    )
                                    startActivity(intent)
                                }

                            }
                        }
                        override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                            val toast = Toast.makeText(requireActivity(),t.message, Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    })

                }
            }
            override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                val toast = Toast.makeText(requireActivity(),t.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        })

        return binding.root
    }

}