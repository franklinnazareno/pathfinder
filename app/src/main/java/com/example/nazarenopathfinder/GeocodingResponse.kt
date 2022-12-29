package com.example.nazarenopathfinder

import com.google.gson.annotations.SerializedName

class GeocodingResponse {
    @SerializedName("features")
    var features: List<Features>? = null
}

class Features {
    @SerializedName("center")
    var center: List<Double>? = null
}